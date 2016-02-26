package org.apache.olingo.odata2.sample.service;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;

public class MyServiceFactory extends ODataServiceFactory {

	@Override
	public ODataService createService(ODataContext arg0) throws ODataException {
		EdmProvider edmProvider = new MyEdmProvider();
		ODataSingleProcessor singleProcessor = new MyODataSingleProcessor();
		ODataService service = createODataSingleProcessorService(edmProvider, singleProcessor);
		return service;
	}

}
