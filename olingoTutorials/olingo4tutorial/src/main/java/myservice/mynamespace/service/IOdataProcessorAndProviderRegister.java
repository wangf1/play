package myservice.mynamespace.service;

import org.apache.olingo.server.api.processor.EntityCollectionProcessor;

/**
 * Suppose I will expose this interface as a OSGi service.
 * 
 * @author wangf
 *
 */
public interface IOdataProcessorAndProviderRegister {
	void registerEntityCollectionProcessor(EntityCollectionProcessor processor);
}