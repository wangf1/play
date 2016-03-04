package myservice.mynamespace.service.thridparty.products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

public class ProductsServiceRegister {

	public static ProductsServiceRegister instance = new ProductsServiceRegister();

	private static final String NAV_TO_CATEGORY = "Category";

	private boolean registered = false;

	private ProductsServiceRegister() {
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
					.registerEntityCollectionProcessor(new ProductsEntityCollectionProcessor());
			OlingoProcessorAndMetadataRegister.instance.registerEntityProcessor(new ProductEntityProcessor());
			OlingoProcessorAndMetadataRegister.instance.registerPrimitiveProcessor(new ProductPrimitiveProcessor());
			OlingoProcessorAndMetadataRegister.instance.registerEntityType(createProductEntityType());
			OlingoProcessorAndMetadataRegister.instance.registerEntitySet(createProductsEntitySet());
			registered = true;
		}
	}

	private CsdlEntitySet createProductsEntitySet() {
		CsdlEntitySet entitySet = new CsdlEntitySet();
		entitySet.setName(MetadataConstants.ES_PRODUCTS_NAME);
		entitySet.setType(MetadataConstants.ET_PRODUCT_FQN);
		// navigation
		CsdlNavigationPropertyBinding navPropBinding = new CsdlNavigationPropertyBinding();
		navPropBinding.setTarget(MetadataConstants.ES_CATEGORIES_NAME);
		navPropBinding.setPath(MetadataConstants.ET_CATEGORY_NAME);
		List<CsdlNavigationPropertyBinding> navPropBindingList = new ArrayList<CsdlNavigationPropertyBinding>();
		navPropBindingList.add(navPropBinding);
		entitySet.setNavigationPropertyBindings(navPropBindingList);
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

		// navigation property: many-to-one, null not allowed (product must have
		// a category)
		CsdlNavigationProperty navProp = new CsdlNavigationProperty().setName(NAV_TO_CATEGORY)
				.setType(MetadataConstants.ET_CATEGORY_FQN).setNullable(false)
				.setPartner(MetadataConstants.ES_PRODUCTS_NAME);
		List<CsdlNavigationProperty> navPropList = new ArrayList<CsdlNavigationProperty>();
		navPropList.add(navProp);

		// configure EntityType
		CsdlEntityType entityType = new CsdlEntityType();
		entityType.setName(MetadataConstants.ET_PRODUCT_NAME);
		entityType.setProperties(Arrays.asList(id, name, description));
		entityType.setKey(Collections.singletonList(propertyRef));
		entityType.setNavigationProperties(navPropList);
		return entityType;
	}
}
