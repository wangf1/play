package myservice.mynamespace.service.thirdparty.categories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationPropertyBinding;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import myservice.mynamespace.service.OlingoProcessorAndMetadataRegister;
import myservice.mynamespace.service.thirdparty.productcategory.common.MetadataConstants;

public class CategoriesServiceRegister {

	public static CategoriesServiceRegister instance = new CategoriesServiceRegister();
	private static final String NAV_TO_PRODUCTS = "Products";

	private boolean registered = false;

	private CategoriesServiceRegister() {
	}

	public void register() {
		if (registered) {
			// only register once!
			return;
		}
		synchronized (this) {
			if (registered) {
				return;
			}
			// Mimic 3rd party code register Processors
			OlingoProcessorAndMetadataRegister.instance
					.registerEntityCollectionProcessor(new CategoriesEntityCollectionProcessor());
			OlingoProcessorAndMetadataRegister.instance.registerEntityType(createCategoryEntityType());
			OlingoProcessorAndMetadataRegister.instance.registerEntitySet(createCategoriesEntitySet());
			registered = true;
		}
	}

	private CsdlEntitySet createCategoriesEntitySet() {
		CsdlEntitySet entitySet = new CsdlEntitySet();
		entitySet = new CsdlEntitySet();
		entitySet.setName(MetadataConstants.ES_CATEGORIES_NAME);
		entitySet.setType(MetadataConstants.ET_CATEGORY_FQN);
		// navigation
		CsdlNavigationPropertyBinding navPropBinding = new CsdlNavigationPropertyBinding();
		// the target entity set, where the navigation property points
		// to
		navPropBinding.setTarget(MetadataConstants.ES_PRODUCTS_NAME);
		// the path from entity type to navigation property
		navPropBinding.setPath(MetadataConstants.ES_PRODUCTS_NAME);
		List<CsdlNavigationPropertyBinding> navPropBindingList = new ArrayList<CsdlNavigationPropertyBinding>();
		navPropBindingList.add(navPropBinding);
		entitySet.setNavigationPropertyBindings(navPropBindingList);
		return entitySet;
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
		CsdlNavigationProperty navProp = new CsdlNavigationProperty().setName(NAV_TO_PRODUCTS)
				.setType(MetadataConstants.ET_PRODUCT_FQN).setCollection(true)
				.setPartner(MetadataConstants.ET_CATEGORY_NAME);
		List<CsdlNavigationProperty> navPropList = new ArrayList<CsdlNavigationProperty>();
		navPropList.add(navProp);

		// configure EntityType
		CsdlEntityType entityType = new CsdlEntityType();
		entityType.setName(MetadataConstants.ET_CATEGORY_NAME);
		entityType.setProperties(Arrays.asList(id, name));
		entityType.setKey(Arrays.asList(propertyRef));
		entityType.setNavigationProperties(navPropList);

		return entityType;
	}

}
