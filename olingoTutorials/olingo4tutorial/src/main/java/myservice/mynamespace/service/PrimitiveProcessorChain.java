package myservice.mynamespace.service;

import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataLibraryException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.PrimitiveProcessor;
import org.apache.olingo.server.api.uri.UriInfo;

import myservice.mynamespace.service.util.Util;

public class PrimitiveProcessorChain implements PrimitiveProcessor {

	@Override
	public void init(OData odata, ServiceMetadata serviceMetadata) {
		for (PrimitiveProcessor processor : OlingoProcessorAndMetadataRegister.instance.getPrimitiveProcessors()) {
			processor.init(odata, serviceMetadata);
		}
	}

	@Override
	public void readPrimitive(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType responseFormat)
			throws ODataApplicationException, ODataLibraryException {
		PrimitiveProcessor processor = (PrimitiveProcessor) Util
				.selectProcessor(OlingoProcessorAndMetadataRegister.instance.getPrimitiveProcessors(), uriInfo);
		processor.readPrimitive(request, response, uriInfo, responseFormat);
	}

	@Override
	public void updatePrimitive(ODataRequest request, ODataResponse response, UriInfo uriInfo,
			ContentType requestFormat, ContentType responseFormat)
					throws ODataApplicationException, ODataLibraryException {
		PrimitiveProcessor processor = (PrimitiveProcessor) Util
				.selectProcessor(OlingoProcessorAndMetadataRegister.instance.getPrimitiveProcessors(), uriInfo);
		processor.updatePrimitive(request, response, uriInfo, requestFormat, responseFormat);
	}

	@Override
	public void deletePrimitive(ODataRequest request, ODataResponse response, UriInfo uriInfo)
			throws ODataApplicationException, ODataLibraryException {
		PrimitiveProcessor processor = (PrimitiveProcessor) Util
				.selectProcessor(OlingoProcessorAndMetadataRegister.instance.getPrimitiveProcessors(), uriInfo);
		processor.deletePrimitive(request, response, uriInfo);
	}

}
