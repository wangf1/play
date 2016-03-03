package myservice.mynamespace.service.api;

import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;

/**
 * Suppose I will expose this interface as a OSGi service.
 * 
 * @author wangf
 *
 */
public interface IOdataProcessorAndMetadataRegister {
	// Service Namespace
	public static final String NAMESPACE = "OData.Demo";

	void registerEntityCollectionProcessor(IEntityCollectionProcessor processor);

	void registerEntityProcessor(IEntityProcessor processor);

	void registerPrimitiveProcessor(IPrimitiveProcessor processor);

	void registerEntityType(CsdlEntityType entityType);

	void registerEntitySet(CsdlEntitySet entitySet);

}