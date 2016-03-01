package myservice.mynamespace.service.thridparty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationPropertyBinding;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import myservice.mynamespace.service.IOdataProcessorAndProviderRegister;
import myservice.mynamespace.service.ProcessorAndEdmProviderRegister;

public class Register3rdPartyThings {
	// Entity Types Names
	private static final String ET_PRODUCT_NAME = "Product";
	private static final FullQualifiedName ET_PRODUCT_FQN = new FullQualifiedName(
			IOdataProcessorAndProviderRegister.NAMESPACE, ET_PRODUCT_NAME);
	private static final String ET_CATEGORY_NAME = "Category";
	private static final FullQualifiedName ET_CATEGORY_FQN = new FullQualifiedName(
			IOdataProcessorAndProviderRegister.NAMESPACE, ET_CATEGORY_NAME);

	// Entity Set Names
	static final String ES_PRODUCTS_NAME = "Products";
	static final String ES_CATEGORIES_NAME = "Categories";
	static final String NAV_TO_PRODUCTS = "Products";

	public void registerAll3rdPartyThings() {
		// Mimic 3rd party code register Processors
		ProcessorAndEdmProviderRegister.instance
				.registerEntityCollectionProcessor(new EntityCollectionProcessor3rdParty());
		ProcessorAndEdmProviderRegister.instance.registerEntityCollectionProcessor(new DemoEntityCollectionProcessor());
		ProcessorAndEdmProviderRegister.instance.registerEntityType(createProductEntityType());
		ProcessorAndEdmProviderRegister.instance.registerEntityType(createCategoryEntityType());
		ProcessorAndEdmProviderRegister.instance.registerEntitySet(createProductsEntitySet());
		ProcessorAndEdmProviderRegister.instance.registerEntitySet(createCategoriesEntitySet());

	}

	private CsdlEntitySet createCategoriesEntitySet() {
		CsdlEntitySet entitySet = new CsdlEntitySet();
		entitySet = new CsdlEntitySet();
		entitySet.setName(ES_CATEGORIES_NAME);
		entitySet.setType(ET_CATEGORY_FQN);
		// navigation
		CsdlNavigationPropertyBinding navPropBinding = new CsdlNavigationPropertyBinding();
		// the target entity set, where the navigation property points
		// to
		navPropBinding.setTarget("Products");
		// the path from entity type to navigation property
		navPropBinding.setPath("Products");
		List<CsdlNavigationPropertyBinding> navPropBindingList = new ArrayList<CsdlNavigationPropertyBinding>();
		navPropBindingList.add(navPropBinding);
		entitySet.setNavigationPropertyBindings(navPropBindingList);
		return entitySet;
	}

	private CsdlEntitySet createProductsEntitySet() {
		CsdlEntitySet entitySet = new CsdlEntitySet();
		entitySet.setName(ES_PRODUCTS_NAME);
		entitySet.setType(ET_PRODUCT_FQN);
		return entitySet;
	}

	private CsdlEntityType createProductEntityType() {
		// create EntityType properties
		CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
		CsdlProperty name = new CsdlProperty().setName("Name")
				.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
		CsdlProperty description = new CsdlProperty().setName("Description")
				.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

		// create CsdlPropertyRef for Key element
		CsdlPropertyRef propertyRef = new CsdlPropertyRef();
		propertyRef.setName("ID");

		// configure EntityType
		CsdlEntityType entityType = new CsdlEntityType();
		entityType.setName(ET_PRODUCT_NAME);
		entityType.setProperties(Arrays.asList(id, name, description));
		entityType.setKey(Collections.singletonList(propertyRef));

		return entityType;
	}

	private CsdlEntityType createCategoryEntityType() {
		// create EntityType properties
		CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
		CsdlProperty name = new CsdlProperty().setName("Name")
				.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

		// create PropertyRef for Key element
		CsdlPropertyRef propertyRef = new CsdlPropertyRef();
		propertyRef.setName("ID");

		// navigation property: one-to-many
		CsdlNavigationProperty navProp = new CsdlNavigationProperty().setName(NAV_TO_PRODUCTS).setType(ET_PRODUCT_FQN)
				.setCollection(true).setPartner("Category");
		List<CsdlNavigationProperty> navPropList = new ArrayList<CsdlNavigationProperty>();
		navPropList.add(navProp);

		// configure EntityType
		CsdlEntityType entityType = new CsdlEntityType();
		entityType.setName(ET_CATEGORY_NAME);
		entityType.setProperties(Arrays.asList(id, name));
		entityType.setKey(Arrays.asList(propertyRef));
		entityType.setNavigationProperties(navPropList);

		return entityType;
	}
}
