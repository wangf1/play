package myservice.mynamespace.service.api;

import org.apache.olingo.server.api.uri.UriInfo;

public interface ICanHandleODataUri {
	boolean canHandle(UriInfo uriInfo);
}
