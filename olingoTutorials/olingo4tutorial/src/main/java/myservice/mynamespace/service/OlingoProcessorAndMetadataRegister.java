package myservice.mynamespace.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myservice.mynamespace.service.api.IEntityCollectionProcessor;
import myservice.mynamespace.service.api.IEntityProcessor;
import myservice.mynamespace.service.api.IOdataProcessorAndMetadataRegister;
import myservice.mynamespace.service.api.IPrimitiveProcessor;

public class OlingoProcessorAndMetadataRegister implements IOdataProcessorAndMetadataRegister {

	public static OlingoProcessorAndMetadataRegister instance = new OlingoProcessorAndMetadataRegister();

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private List<IEntityCollectionProcessor> entityCollectionProcessors = new LinkedList<>();
	private List<IEntityProcessor> entityProcessors = new LinkedList<>();
	private List<IPrimitiveProcessor> primitiveProcessors = new LinkedList<>();
	private Map<FullQualifiedName, CsdlEntityType> entityTypes = new HashMap<>();
	private Map<String, CsdlEntitySet> entitySets = new HashMap<>();

	private OlingoProcessorAndMetadataRegister() {
	}

	@Override
	public void registerEntityCollectionProcessor(IEntityCollectionProcessor processor) {
		if (processor == null) {
			LOG.warn("Should not register null Olingo Processor");
			return;
		}
		entityCollectionProcessors.add(processor);
	}

	@Override
	public void registerEntityProcessor(IEntityProcessor processor) {
		entityProcessors.add(processor);
	}

	@Override
	public void registerPrimitiveProcessor(IPrimitiveProcessor processor) {
		primitiveProcessors.add(processor);
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

	List<IEntityCollectionProcessor> getEntityCollectionProcessors() {
		return entityCollectionProcessors;
	}

	Map<FullQualifiedName, CsdlEntityType> getEntityTypes() {
		return entityTypes;
	}

	Map<String, CsdlEntitySet> getEntitySets() {
		return entitySets;
	}

	List<IEntityProcessor> getEntityProcessors() {
		return entityProcessors;
	}

	List<IPrimitiveProcessor> getPrimitiveProcessors() {
		return primitiveProcessors;
	}

}
