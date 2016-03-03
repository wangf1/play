package myservice.mynamespace.service;

import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataLibraryException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.processor.Processor;
import org.apache.olingo.server.api.uri.UriInfo;

import myservice.mynamespace.service.util.Util;

/**
 * This class use a simplified responsibility chain pattern, in order to make
 * 3rd party {@link Processor} can be registered and handle specific request.
 * <br>
 * The purpose of doing this is, in a OSGi environment, we want put Processor
 * into separate bundles, Processor provider bundle should depends on this
 * "root" bundle, but this "root" bundle should not depends on Processor
 * provider bundle.<br>
 * Note: Olingo V4 cannot register multiple Processor of same type, so our
 * responsibility chain is necessary.
 * 
 * @author wangf
 *
 */
public class EntityCollectionProcessorChain implements EntityCollectionProcessor {

	@Override
	public void init(OData odata, ServiceMetadata serviceMetadata) {
		for (EntityCollectionProcessor processor : OlingoProcessorAndMetadataRegister.instance
				.getEntityCollectionProcessors()) {
			processor.init(odata, serviceMetadata);
		}
	}

	@Override
	public void readEntityCollection(ODataRequest request, ODataResponse response, UriInfo uriInfo,
			ContentType responseFormat) throws ODataApplicationException, ODataLibraryException {
		EntityCollectionProcessor processor = (EntityCollectionProcessor) Util
				.selectProcessor(OlingoProcessorAndMetadataRegister.instance.getEntityCollectionProcessors(), uriInfo);
		processor.readEntityCollection(request, response, uriInfo, responseFormat);
	}

}
