package myservice.mynamespace.service.thirdparty.productcategory.common;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import myservice.mynamespace.service.api.IOdataProcessorAndMetadataRegister;

public class MetadataConstants {
	// Entity Types Names
	public static final String ET_PRODUCT_NAME = "Product";
	public static final FullQualifiedName ET_PRODUCT_FQN = new FullQualifiedName(
			IOdataProcessorAndMetadataRegister.NAMESPACE, ET_PRODUCT_NAME);

	public static final String ET_CATEGORY_NAME = "Category";
	public static final FullQualifiedName ET_CATEGORY_FQN = new FullQualifiedName(
			IOdataProcessorAndMetadataRegister.NAMESPACE, ET_CATEGORY_NAME);

	// Entity Set Names
	public static final String ES_PRODUCTS_NAME = "Products";
	public static final String ES_CATEGORIES_NAME = "Categories";
}
