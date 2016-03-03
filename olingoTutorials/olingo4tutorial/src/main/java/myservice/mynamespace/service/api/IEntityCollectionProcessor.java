package myservice.mynamespace.service.api;

import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.uri.UriInfo;

/**
 * Force 3rd party processor implement {@link #canHandle(UriInfo)} method, in
 * order to avoid multiple processor handle same request.
 * 
 * @author wangf
 *
 */
public interface IEntityCollectionProcessor extends EntityCollectionProcessor, ICanHandleODataUri {
}
