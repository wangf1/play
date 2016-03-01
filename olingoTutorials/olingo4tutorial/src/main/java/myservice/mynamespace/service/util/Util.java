/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package myservice.mynamespace.service.util;

import java.util.List;
import java.util.Locale;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.edm.EdmProperty;
import org.apache.olingo.commons.api.edm.EdmType;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;

public class Util {

	public static Entity findEntity(EdmEntityType edmEntityType, EntityCollection entitySet,
			List<UriParameter> keyParams) throws ODataApplicationException {

		List<Entity> entityList = entitySet.getEntities();

		// loop over all entities in order to find that one that matches all
		// keys in request
		// e.g. contacts(ContactID=1, CompanyID=1)
		for (Entity entity : entityList) {
			boolean foundEntity = entityMatchesAllKeys(edmEntityType, entity, keyParams);
			if (foundEntity) {
				return entity;
			}
		}

		return null;
	}

	private static boolean entityMatchesAllKeys(EdmEntityType edmEntityType, Entity entity,
			List<UriParameter> keyParams) throws ODataApplicationException {

		// loop over all keys
		for (final UriParameter key : keyParams) {
			// key
			String keyName = key.getName();
			String keyText = key.getText();

			// Edm: we need this info for the comparison below
			EdmProperty edmKeyProperty = (EdmProperty) edmEntityType.getProperty(keyName);
			Boolean isNullable = edmKeyProperty.isNullable();
			Integer maxLength = edmKeyProperty.getMaxLength();
			Integer precision = edmKeyProperty.getPrecision();
			Boolean isUnicode = edmKeyProperty.isUnicode();
			Integer scale = edmKeyProperty.getScale();
			// get the EdmType in order to compare
			EdmType edmType = edmKeyProperty.getType();
			EdmPrimitiveType edmPrimitiveType = (EdmPrimitiveType) edmType;

			// Runtime data: the value of the current entity
			// don't need to check for null, this is done in olingo library
			Object valueObject = entity.getProperty(keyName).getValue();

			// now need to compare the valueObject with the keyText String
			// this is done using the type.valueToString
			String valueAsString;
			try {
				valueAsString = edmPrimitiveType.valueToString(valueObject, isNullable, maxLength, precision, scale,
						isUnicode);
			} catch (EdmPrimitiveTypeException e) {
				throw new ODataApplicationException("Failed to retrieve String value",
						HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode(), Locale.ENGLISH, e);
			}

			if (valueAsString == null) {
				return false;
			}

			boolean matches = valueAsString.equals(keyText);
			if (!matches) {
				// if any of the key properties is not found in the entity, we
				// don't need to search further
				return false;
			}
		}

		return true;
	}
}
