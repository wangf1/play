package myservice.mynamespace.service;

import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.processor.EntityProcessor;

/**
 * Suppose I will expose this interface as a OSGi service.
 * 
 * @author wangf
 *
 */
public interface IOdataProcessorAndMetadataRegister {
	// Service Namespace
	public static final String NAMESPACE = "OData.Demo";

	void registerEntityCollectionProcessor(EntityCollectionProcessor processor);

	void registerEntityProcessor(EntityProcessor processor);

	void registerEntityType(CsdlEntityType entityType);

	void registerEntitySet(CsdlEntitySet entitySet);

}