package myservice.mynamespace.service;

import java.util.List;

import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataLibraryException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.PrimitiveProcessor;
import org.apache.olingo.server.api.uri.UriInfo;

public class PrimitiveProcessorChain implements PrimitiveProcessor {

	@Override
	public void init(OData odata, ServiceMetadata serviceMetadata) {
		List<PrimitiveProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getPrimitiveProcessors();
		for (PrimitiveProcessor processor : processors) {
			processor.init(odata, serviceMetadata);
		}
	}

	@Override
	public void readPrimitive(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType responseFormat)
			throws ODataApplicationException, ODataLibraryException {
		List<PrimitiveProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getPrimitiveProcessors();
		for (PrimitiveProcessor processor : processors) {
			processor.readPrimitive(request, response, uriInfo, responseFormat);
		}
	}

	@Override
	public void updatePrimitive(ODataRequest request, ODataResponse response, UriInfo uriInfo,
			ContentType requestFormat, ContentType responseFormat)
					throws ODataApplicationException, ODataLibraryException {
		List<PrimitiveProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getPrimitiveProcessors();
		for (PrimitiveProcessor processor : processors) {
			processor.updatePrimitive(request, response, uriInfo, requestFormat, responseFormat);
		}
	}

	@Override
	public void deletePrimitive(ODataRequest request, ODataResponse response, UriInfo uriInfo)
			throws ODataApplicationException, ODataLibraryException {
		List<PrimitiveProcessor> processors = OlingoProcessorAndMetadataRegister.instance.getPrimitiveProcessors();
		for (PrimitiveProcessor processor : processors) {
			processor.deletePrimitive(request, response, uriInfo);
		}
	}

}
