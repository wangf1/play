package myservice.mynamespace.service;

import java.util.List;

import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataLibraryException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityProcessor;
import org.apache.olingo.server.api.uri.UriInfo;

/**
 * Use same pattern as {@link EntityCollectionProcessorChain}.
 * 
 * @author wangf
 *
 */
public class EntityProcessorChain implements EntityProcessor {

	@Override
	public void init(OData odata, ServiceMetadata serviceMetadata) {
		List<EntityProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getEntityProcessors();
		for (EntityProcessor processor : processors) {
			processor.init(odata, serviceMetadata);
		}
	}

	@Override
	public void readEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType responseFormat)
			throws ODataApplicationException, ODataLibraryException {
		List<EntityProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getEntityProcessors();
		for (EntityProcessor processor : processors) {
			processor.readEntity(request, response, uriInfo, responseFormat);
		}
	}

	@Override
	public void createEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType requestFormat,
			ContentType responseFormat) throws ODataApplicationException, ODataLibraryException {
		List<EntityProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getEntityProcessors();
		for (EntityProcessor processor : processors) {
			processor.createEntity(request, response, uriInfo, requestFormat, responseFormat);
		}
	}

	@Override
	public void updateEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType requestFormat,
			ContentType responseFormat) throws ODataApplicationException, ODataLibraryException {
		List<EntityProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getEntityProcessors();
		for (EntityProcessor processor : processors) {
			processor.updateEntity(request, response, uriInfo, requestFormat, responseFormat);
		}
	}

	@Override
	public void deleteEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo)
			throws ODataApplicationException, ODataLibraryException {
		List<EntityProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getEntityProcessors();
		for (EntityProcessor processor : processors) {
			processor.deleteEntity(request, response, uriInfo);
		}
	}

}
