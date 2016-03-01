package myservice.mynamespace.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessorAndEdmProviderRegister implements IOdataProcessorAndProviderRegister {

	public static ProcessorAndEdmProviderRegister instance = new ProcessorAndEdmProviderRegister();

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private List<EntityCollectionProcessor> processors = new LinkedList<>();
	private Map<FullQualifiedName, CsdlEntityType> entityTypes = new HashMap<>();
	private Map<String, CsdlEntitySet> entitySets = new HashMap<>();

	private ProcessorAndEdmProviderRegister() {
	}

	@Override
	public void registerEntityCollectionProcessor(EntityCollectionProcessor processor) {
		if (processor == null) {
			LOG.warn("Should not register null Olingo Processor");
			return;
		}
		processors.add(processor);
	}

	@Override
	public void registerEntityType(CsdlEntityType entityType) {
		String entityName = entityType.getName();
		FullQualifiedName fullQualifiedName = new FullQualifiedName(NAMESPACE, entityName);
		entityTypes.put(fullQualifiedName, entityType);
	}

	@Override
	public void registerEntitySet(CsdlEntitySet entitySet) {
		entitySets.put(entitySet.getName(), entitySet);
	}

	List<EntityCollectionProcessor> getProcessors() {
		return processors;
	}

	Map<FullQualifiedName, CsdlEntityType> getEntityTypes() {
		return entityTypes;
	}

	Map<String, CsdlEntitySet> getEntitySets() {
		return entitySets;
	}

}
