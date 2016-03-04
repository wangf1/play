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
package myservice.mynamespace.service.thirdparty.productcategory.common;

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
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.http.HttpMethod;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;

import myservice.mynamespace.service.util.Util;

public class Storage {

	public static Storage instance = new Storage();

	private List<Entity> productList;
	private List<Entity> categoryList;

	private Storage() {
		productList = new ArrayList<Entity>();
		categoryList = new ArrayList<Entity>();
		initProductSampleData();
		initCategorySampleData();
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

	private void initProductSampleData() {

		Entity entity = new Entity();

		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 1));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Notebook Basic 15"));
		entity.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
				"Notebook Basic, 1.7GHz - 15 XGA - 1024MB DDR2 SDRAM - 40GB"));
		entity.setType(MetadataConstants.ET_PRODUCT_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ET_PRODUCT_NAME, entity.getProperty("ID").getValue()));
		productList.add(entity);

		entity = new Entity();
		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 2));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Notebook Professional 17"));
		entity.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
				"Notebook Professional, 2.8GHz - 15 XGA - 8GB DDR3 RAM - 500GB"));
		entity.setType(MetadataConstants.ET_PRODUCT_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ET_PRODUCT_NAME, entity.getProperty("ID").getValue()));
		productList.add(entity);

		entity = new Entity();
		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 3));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "1UMTS PDA"));
		entity.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
				"Ultrafast 3G UMTS/HSDPA Pocket PC, supports GSM network"));
		entity.setType(MetadataConstants.ET_PRODUCT_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ET_PRODUCT_NAME, entity.getProperty("ID").getValue()));
		productList.add(entity);

		entity = new Entity();
		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 4));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Comfort Easy"));
		entity.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
				"32 GB Digital Assitant with high-resolution color screen"));
		entity.setType(MetadataConstants.ET_PRODUCT_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ET_PRODUCT_NAME, entity.getProperty("ID").getValue()));
		productList.add(entity);

		entity = new Entity();
		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 5));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Ergo Screen"));
		entity.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
				"19 Optimum Resolution 1024 x 768 @ 85Hz, resolution 1280 x 960"));
		entity.setType(MetadataConstants.ET_PRODUCT_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ET_PRODUCT_NAME, entity.getProperty("ID").getValue()));
		productList.add(entity);

		entity = new Entity();
		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, 6));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Flat Basic"));
		entity.addProperty(new Property(null, "Description", ValueType.PRIMITIVE,
				"Optimum Hi-Resolution max. 1600 x 1200 @ 85Hz, Dot Pitch: 0.24mm"));
		entity.setType(MetadataConstants.ET_PRODUCT_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ET_PRODUCT_NAME, entity.getProperty("ID").getValue()));
		productList.add(entity);
	}

	private void initCategorySampleData() {

		Entity entity = new Entity();

		int id1 = 1;
		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, id1));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Notebooks"));
		entity.setType(MetadataConstants.ET_CATEGORY_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ES_CATEGORIES_NAME, id1));
		categoryList.add(entity);

		entity = new Entity();
		int id2 = 2;
		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, id2));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Organizers"));
		entity.setType(MetadataConstants.ET_CATEGORY_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ES_CATEGORIES_NAME, id2));
		categoryList.add(entity);

		entity = new Entity();
		int id3 = 3;
		entity.addProperty(new Property(null, "ID", ValueType.PRIMITIVE, id3));
		entity.addProperty(new Property(null, "Name", ValueType.PRIMITIVE, "Monitors"));
		entity.setType(MetadataConstants.ET_CATEGORY_FQN.getFullQualifiedNameAsString());
		entity.setId(createId(MetadataConstants.ES_CATEGORIES_NAME, id3));
		categoryList.add(entity);
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
		entity.setId(createId(MetadataConstants.ES_PRODUCTS_NAME, newId));
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

	public EntityCollection getCategories() {
		EntityCollection entitySet = new EntityCollection();

		for (Entity categoryEntity : this.categoryList) {
			entitySet.getEntities().add(categoryEntity);
		}
		return entitySet;
	}

	public EntityCollection getRelatedEntityCollection(Entity sourceEntity, EdmEntityType targetEntityType) {
		EntityCollection navigationTargetEntityCollection = new EntityCollection();

		FullQualifiedName relatedEntityFqn = targetEntityType.getFullQualifiedName();
		String sourceEntityFqn = sourceEntity.getType();

		if (sourceEntityFqn.equals(MetadataConstants.ET_PRODUCT_FQN.getFullQualifiedNameAsString())
				&& relatedEntityFqn.equals(MetadataConstants.ET_CATEGORY_FQN)) {
			// relation Products->Category (result all categories)
			int productID = (Integer) sourceEntity.getProperty("ID").getValue();
			if (productID == 1 || productID == 2) {
				navigationTargetEntityCollection.getEntities().add(categoryList.get(0));
			} else if (productID == 3 || productID == 4) {
				navigationTargetEntityCollection.getEntities().add(categoryList.get(1));
			} else if (productID == 5 || productID == 6) {
				navigationTargetEntityCollection.getEntities().add(categoryList.get(2));
			}
		} else if (sourceEntityFqn.equals(MetadataConstants.ET_CATEGORY_FQN.getFullQualifiedNameAsString())
				&& relatedEntityFqn.equals(MetadataConstants.ET_PRODUCT_FQN)) {
			// relation Category->Products (result all products)
			int categoryID = (Integer) sourceEntity.getProperty("ID").getValue();
			if (categoryID == 1) {
				// the first 2 products are notebooks
				navigationTargetEntityCollection.getEntities().addAll(productList.subList(0, 2));
			} else if (categoryID == 2) {
				// the next 2 products are organizers
				navigationTargetEntityCollection.getEntities().addAll(productList.subList(2, 4));
			} else if (categoryID == 3) {
				// the first 2 products are monitors
				navigationTargetEntityCollection.getEntities().addAll(productList.subList(4, 6));
			}
		}
		if (navigationTargetEntityCollection.getEntities().isEmpty()) {
			return null;
		}
		return navigationTargetEntityCollection;
	}

	public Entity getCategory(EdmEntityType entityType, List<UriParameter> keyPredicates)
			throws ODataApplicationException {
		// the list of entities at runtime
		EntityCollection entitySet = getCategories();
		/* generic approach to find the requested entity */
		return Util.findEntity(entityType, entitySet, keyPredicates);
	}
}
