package myservice.mynamespace.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessorAndEdmProviderRegister implements IOdataProcessorAndProviderRegister {

	public static ProcessorAndEdmProviderRegister instance = new ProcessorAndEdmProviderRegister();

	private Logger LOG = LoggerFactory.getLogger(getClass());
	private List<EntityCollectionProcessor> processors = new LinkedList<>();

	private ProcessorAndEdmProviderRegister() {
	}

	@Override
	public void registerEntityCollectionProcessor(EntityCollectionProcessor processor) {
		if (processor == null) {
			LOG.warn("Should not register null Olingo Processor");
			return;
		}
		getProcessors().add(processor);
	}

	List<EntityCollectionProcessor> getProcessors() {
		return processors;
	}

}
