package myservice.mynamespace.service.api;

import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.server.api.processor.EntityProcessor;
import org.apache.olingo.server.api.processor.PrimitiveProcessor;

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

	void registerEntityProcessor(EntityProcessor processor);

	void registerPrimitiveProcessor(PrimitiveProcessor processor);

	void registerEntityType(CsdlEntityType entityType);

	void registerEntitySet(CsdlEntitySet entitySet);

}