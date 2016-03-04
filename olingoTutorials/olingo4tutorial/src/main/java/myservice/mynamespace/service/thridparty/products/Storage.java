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
package myservice.mynamespace.service.thridparty.products;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.http.HttpMethod;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;

import myservice.mynamespace.service.util.Util;

public class Storage {

	public static Storage instance = new Storage();

	private List<Entity> productList;

	private Storage() {
		productList = new ArrayList<Entity>();
		initSampleData();
	}

	public EntityCollection getProducts() {
		EntityCollection retEntitySet = new EntityCollection();

		for (Entity productEntity : this.productList) {
			retEntitySet.getEntities().add(productEntity);
		}

		return retEntitySet;
	}

	public Entity getProduct(EdmEntityType edmEntityType, List<UriParameter> keyParams)
			throws ODataApplicationException {

		// the list of entities at runtime
		EntityCollection entitySet = getProducts();

		/* generic approach to find the requested entity */
		Entity requestedEntity = Util.findEntity(edmEntityType, entitySet, keyParams);

		if (requestedEntity == null) {
			// this variable is null if our data doesn't contain an entity for
			// the requested key
			// Throw suitable exception
			throw new ODataApplicationException("Entity for requested key doesn't exist",
					HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
		}

		return requestedEntity;
	}

	/* HELPER */

	private void initSampleData() {

		// add some sample product entities
		final Entity e1 = new Entity().addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 1))
				.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Notebook Basic 15"))
				.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
						"Notebook Basic, 1.7GHz - 15 XGA - 1024MB DDR2 SDRAM - 40GB"));
		e1.setId(createId("Products", 1));
		productList.add(e1);

		final Entity e2 = new Entity().addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 2))
				.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "1UMTS PDA"))
				.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
						"Ultrafast 3G UMTS/HSDPA Pocket PC, supports GSM network"));
		e2.setId(createId("Products", 2));
		productList.add(e2);

		final Entity e3 = new Entity().addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 3))
				.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Ergo Screen"))
				.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
						"19 Optimum Resolution 1024 x 768 @ 85Hz, resolution 1280 x 960"));
		e3.setId(createId("Products", 3));
		productList.add(e3);
	}

	private URI createId(String entitySetName, Object id) {
		try {
			return new URI(entitySetName + "(" + String.valueOf(id) + ")");
		} catch (URISyntaxException e) {
			throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
		}
	}

	public Entity createProduct(Entity entity) {
		// the ID of the newly created product entity is generated automatically
		int newId = 1;
		while (productIdExists(newId)) {
			newId++;
		}

		Property idProperty = entity.getProperty("ID");
		if (idProperty != null) {
			idProperty.setValue(ValueType.PRIMITIVE, Integer.valueOf(newId));
		} else {
			// as of OData v4 spec, the key property can be omitted from the
			// POST request body
			entity.getProperties().add(new Property(null, "ID", ValueType.PRIMITIVE, newId));
		}
		entity.setId(createId("Products", newId));
		this.productList.add(entity);

		return entity;
	}

	private boolean productIdExists(int id) {

		for (Entity entity : this.productList) {
			Integer existingID = (Integer) entity.getProperty("ID").getValue();
			if (existingID.intValue() == id) {
				return true;
			}
		}
		return false;
	}

	public void updateProduct(EdmEntityType entityType, List<UriParameter> keyPredicates, Entity entity,
			HttpMethod httpMethod) throws ODataApplicationException {
		Entity productEntity = getProduct(entityType, keyPredicates);
		if (productEntity == null) {
			throw new ODataApplicationException("Entity not found", HttpStatusCode.NOT_FOUND.getStatusCode(),
					Locale.ENGLISH);
		}

		// loop over all properties and replace the values with the values of
		// the given payload
		// Note: ignoring ComplexType, as we don't have it in our odata model
		List<Property> existingProperties = productEntity.getProperties();
		for (Property existingProp : existingProperties) {
			String propName = existingProp.getName();

			// ignore the key properties, they aren't updateable
			if (isKey(entityType, propName)) {
				continue;
			}

			Property updateProperty = entity.getProperty(propName);
			// the request payload might not consider ALL properties, so it can
			// be null
			if (updateProperty == null) {
				// if a property has NOT been added to the request payload
				// depending on the HttpMethod, our behavior is different
				if (httpMethod.equals(HttpMethod.PATCH)) {
					// as of the OData spec, in case of PATCH, the existing
					// property is not touched
					continue; // do nothing
				} else if (httpMethod.equals(HttpMethod.PUT)) {
					// as of the OData spec, in case of PUT, the existing
					// property is set to null (or to default value)
					existingProp.setValue(existingProp.getValueType(), null);
					continue;
				}
			}
			// change the value of the properties
			existingProp.setValue(existingProp.getValueType(), updateProperty.getValue());
		}
	}

	private boolean isKey(EdmEntityType edmEntityType, String propertyName) {
		List<EdmKeyPropertyRef> keyPropertyRefs = edmEntityType.getKeyPropertyRefs();
		for (EdmKeyPropertyRef propRef : keyPropertyRefs) {
			String keyPropertyName = propRef.getName();
			if (keyPropertyName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}

	public void deleteProduct(EdmEntityType entityType, List<UriParameter> keyPredicates)
			throws ODataApplicationException {
		Entity productEntity = getProduct(entityType, keyPredicates);
		if (productEntity == null) {
			throw new ODataApplicationException("Entity not found", HttpStatusCode.NOT_FOUND.getStatusCode(),
					Locale.ENGLISH);
		}
		this.productList.remove(productEntity);
	}
}
