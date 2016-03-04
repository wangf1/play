package myservice.mynamespace.service.thridparty.products;

import java.util.List;

import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;

import myservice.mynamespace.service.thirdparty.productcategory.common.MetadataConstants;

public class ProductEntityUtil {
	static boolean isProductsUri(UriInfo uriInfo) {
		// 1st we have retrieve the requested EntitySet from the uriInfo object
		// (representation of the parsed service URI)
		List<UriResource> resourcePaths = uriInfo.getUriResourceParts();
		// in our example, the first segment is the EntitySet
		UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0);
		EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();
		if (MetadataConstants.ES_PRODUCTS_NAME.equals(edmEntitySet.getName())) {
			// if resource name is not Products, this processor will not process
			// the request
			return true;
		} else {
			return false;
		}
	}
}
