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
package myservice.mynamespace.service.thirdparty.categories;

import java.util.List;
import java.util.Locale;

import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmNavigationProperty;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.serializer.EntityCollectionSerializerOptions;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriParameter;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.UriResourceNavigation;

import myservice.mynamespace.service.api.IEntityCollectionProcessor;
import myservice.mynamespace.service.thirdparty.productcategory.common.MetadataConstants;
import myservice.mynamespace.service.thirdparty.productcategory.common.Storage;
import myservice.mynamespace.service.util.Util;

/**
 * This class is invoked by the Olingo framework when the the OData service is
 * invoked order to display a list/collection of data (entities). This is the
 * case if an EntitySet is requested by the user. Such an example URL would be:
 * http://localhost:8080/ExampleService1/ExampleService1.svc/Products
 */
public class CategoriesEntityCollectionProcessor implements IEntityCollectionProcessor {

	private OData odata;
	private ServiceMetadata serviceMetadata;

	// our processor is initialized with the OData context object
	public void init(OData odata, ServiceMetadata serviceMetadata) {
		this.odata = odata;
		this.serviceMetadata = serviceMetadata;
	}

	// the only method that is declared in the EntityCollectionProcessor
	// interface
	// this method is called, when the user fires a request to an EntitySet
	// in our example, the URL would be:
	// http://localhost:8080/ExampleService1/ExampleServlet1.svc/Products
	public void readEntityCollection(ODataRequest request, ODataResponse response, UriInfo uriInfo,
			ContentType responseFormat) throws ODataApplicationException, SerializerException {

		// 1st retrieve the requested EntitySet from the uriInfo (representation
		// of the parsed URI)
		List<UriResource> resourceParts = uriInfo.getUriResourceParts();
		// in our example, the first segment is the EntitySet
		UriResource uriResource = resourceParts.get(0);
		if (!(uriResource instanceof UriResourceEntitySet)) {
			throw new ODataApplicationException("Only EntitySet is supported",
					HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ROOT);
		}

		UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) uriResource;
		EdmEntitySet startEdmEntitySet = uriResourceEntitySet.getEntitySet();

		int segmentCount = resourceParts.size();
		EdmEntitySet responseEdmEntitySet = null;
		EntityCollection responseEntityCollection = null;
		if (segmentCount == 1) {
			// this is the case for: DemoService/DemoService.svc/Categories
			// the response body is built from the first (and only) entitySet
			responseEdmEntitySet = startEdmEntitySet;
			// 2nd: fetch the data from backend for this requested EntitySetName
			// and deliver as EntitySet
			responseEntityCollection = Storage.instance.getCategories();
		} else if (segmentCount == 2) {
			// in case of navigation:
			// Example URL: GET
			// http://localhost:8080/olingo4tutorial/DemoService.svc/Categories(1)/Products
			// in our example we don't support more complex URIs
			UriResource lastSegment = resourceParts.get(1);
			if (lastSegment instanceof UriResourceNavigation) {
				UriResourceNavigation uriResourceNavigation = (UriResourceNavigation) lastSegment;
				EdmNavigationProperty edmNavigationProperty = uriResourceNavigation.getProperty();
				EdmEntityType targetEntityType = edmNavigationProperty.getType();
				// from Categories(1) to Products
				responseEdmEntitySet = Util.getNavigationTargetEntitySet(startEdmEntitySet, edmNavigationProperty);

				// 2nd: fetch the data from backend
				// first fetch the entity where the first segment of the URI
				// points to
				List<UriParameter> keyPredicates = uriResourceEntitySet.getKeyPredicates();
				// e.g. for Categories(3)/Products we have to find the single
				// entity: Category with ID 3
				Entity sourceEntity = Storage.instance.getCategory(startEdmEntitySet.getEntityType(), keyPredicates);
				// error handling for e.g.
				// DemoService.svc/Categories(99)/Products
				if (sourceEntity == null) {
					throw new ODataApplicationException("Entity not found.", HttpStatusCode.NOT_FOUND.getStatusCode(),
							Locale.ROOT);
				}
				// then fetch the entity collection where the entity navigates
				// to
				// note: we don't need to check
				// uriResourceNavigation.isCollection(),
				// because we are the EntityCollectionProcessor
				responseEntityCollection = Storage.instance.getRelatedEntityCollection(sourceEntity, targetEntityType);
			}
		} else { // this would be the case for e.g.
					// Products(1)/Category/Products
			throw new ODataApplicationException("Not supported", HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(),
					Locale.ROOT);
		}

		// 3rd: create and configure a serializer
		ContextURL contextUrl = ContextURL.with().entitySet(responseEdmEntitySet).build();
		final String id = request.getRawBaseUri() + "/" + responseEdmEntitySet.getName();
		EntityCollectionSerializerOptions opts = EntityCollectionSerializerOptions.with().contextURL(contextUrl).id(id)
				.build();
		EdmEntityType edmEntityType = responseEdmEntitySet.getEntityType();

		ODataSerializer serializer = odata.createSerializer(responseFormat);
		SerializerResult serializerResult = serializer.entityCollection(this.serviceMetadata, edmEntityType,
				responseEntityCollection, opts);

		// 4th: configure the response object: set the body, headers and status
		// code
		response.setContent(serializerResult.getContent());
		response.setStatusCode(HttpStatusCode.OK.getStatusCode());
		response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
	}

	@Override
	public boolean canHandle(UriInfo uriInfo) {
		// 1st we have retrieve the requested EntitySet from the uriInfo object
		// (representation of the parsed service URI)
		List<UriResource> resourcePaths = uriInfo.getUriResourceParts();
		// in our example, the first segment is the EntitySet
		UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0);
		EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();
		if (MetadataConstants.ES_CATEGORIES_NAME.equals(edmEntitySet.getName())) {
			// if resource name is not Products, this processor will not process
			// the request
			return true;
		} else {
			return false;
		}
	}
}
