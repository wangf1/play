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
package myservice.mynamespace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

/**
 * this class is supposed to declare the metadata of the OData service it is
 * invoked by the Olingo framework e.g. when the metadata document of the
 * service is invoked e.g.
 * http://localhost:8080/ExampleService1/ExampleService1.svc/$metadata
 */
public class DemoEdmProvider extends CsdlAbstractEdmProvider {

	// Service Namespace
	private static final String NAMESPACE = IOdataProcessorAndProviderRegister.NAMESPACE;

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	@Override
	public List<CsdlSchema> getSchemas() {

		// create Schema
		CsdlSchema schema = new CsdlSchema();
		schema.setNamespace(NAMESPACE);

		// add EntityTypes
		List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
		Map<FullQualifiedName, CsdlEntityType> entityMap = ProcessorAndEdmProviderRegister.instance.getEntityTypes();
		entityTypes.addAll(entityMap.values());
		schema.setEntityTypes(entityTypes);

		// add EntityContainer
		schema.setEntityContainer(getEntityContainer());

		// finally
		List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
		schemas.add(schema);

		return schemas;
	}

	@Override
	public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {
		Map<FullQualifiedName, CsdlEntityType> entityMap = ProcessorAndEdmProviderRegister.instance.getEntityTypes();
		CsdlEntityType type = entityMap.get(entityTypeName);
		return type;
	}

	@Override
	public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {
		if (entityContainer.equals(CONTAINER)) {
			Map<String, CsdlEntitySet> entitySets = ProcessorAndEdmProviderRegister.instance.getEntitySets();
			CsdlEntitySet entitySet = entitySets.get(entitySetName);
			return entitySet;
		}
		return null;
	}

	@Override
	public CsdlEntityContainer getEntityContainer() {

		// create EntitySets
		List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
		Map<String, CsdlEntitySet> entitySetMap = ProcessorAndEdmProviderRegister.instance.getEntitySets();
		entitySets.addAll(entitySetMap.values());
		// create EntityContainer
		CsdlEntityContainer entityContainer = new CsdlEntityContainer();
		entityContainer.setName(CONTAINER_NAME);
		entityContainer.setEntitySets(entitySets);

		return entityContainer;
	}

	@Override
	public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {

		// This method is invoked when displaying the service document at e.g.
		// http://localhost:8080/DemoService/DemoService.svc
		if (entityContainerName == null || entityContainerName.equals(CONTAINER)) {
			CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
			entityContainerInfo.setContainerName(CONTAINER);
			return entityContainerInfo;
		}

		return null;
	}
}
