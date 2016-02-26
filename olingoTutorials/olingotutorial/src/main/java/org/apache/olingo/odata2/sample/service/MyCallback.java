package org.apache.olingo.odata2.sample.service;

import static org.apache.olingo.odata2.sample.service.MyEdmProvider.ENTITY_NAME_MANUFACTURER;
import static org.apache.olingo.odata2.sample.service.MyEdmProvider.ENTITY_SET_NAME_CARS;
import static org.apache.olingo.odata2.sample.service.MyEdmProvider.ENTITY_SET_NAME_MANUFACTURERS;

import java.net.URI;
import java.util.Map;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.ep.callback.OnWriteEntryContent;
import org.apache.olingo.odata2.api.ep.callback.OnWriteFeedContent;
import org.apache.olingo.odata2.api.ep.callback.WriteCallbackContext;
import org.apache.olingo.odata2.api.ep.callback.WriteEntryCallbackContext;
import org.apache.olingo.odata2.api.ep.callback.WriteEntryCallbackResult;
import org.apache.olingo.odata2.api.ep.callback.WriteFeedCallbackContext;
import org.apache.olingo.odata2.api.ep.callback.WriteFeedCallbackResult;
import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCallback implements OnWriteEntryContent, OnWriteFeedContent {

	private Logger LOG = LoggerFactory.getLogger(getClass());
	private DataStore dataStore;
	private URI serviceRoot;

	public MyCallback(DataStore dataStore, URI serviceRoot) {
		this.dataStore = dataStore;
		this.serviceRoot = serviceRoot;
	}

	@Override
	public WriteEntryCallbackResult retrieveEntryResult(WriteEntryCallbackContext context)
			throws ODataApplicationException {
		WriteEntryCallbackResult result = new WriteEntryCallbackResult();

		try {
			if (isNavigationFromTo(context, ENTITY_SET_NAME_CARS, ENTITY_NAME_MANUFACTURER)) {
				EntityProviderWriteProperties inlineProperties = EntityProviderWriteProperties.serviceRoot(serviceRoot)
						.expandSelectTree(context.getCurrentExpandSelectTreeNode()).build();

				Map<String, Object> keys = context.extractKeyFromEntryData();
				Integer carId = (Integer) keys.get("Id");
				result.setEntryData(dataStore.getManufacturerFor(carId));
				result.setInlineProperties(inlineProperties);
			}
		} catch (EdmException e) {
			// TODO: should be handled and not only logged
			LOG.error("Error in $expand handling.", e);
		} catch (EntityProviderException e) {
			// TODO: should be handled and not only logged
			LOG.error("Error in $expand handling.", e);
		}

		return result;
	}

	@Override
	public WriteFeedCallbackResult retrieveFeedResult(WriteFeedCallbackContext context)
			throws ODataApplicationException {
		WriteFeedCallbackResult result = new WriteFeedCallbackResult();
		try {
			if (isNavigationFromTo(context, ENTITY_SET_NAME_MANUFACTURERS, ENTITY_SET_NAME_CARS)) {
				EntityProviderWriteProperties inlineProperties = EntityProviderWriteProperties.serviceRoot(serviceRoot)
						.expandSelectTree(context.getCurrentExpandSelectTreeNode()).selfLink(context.getSelfLink())
						.build();

				Map<String, Object> keys = context.extractKeyFromEntryData();
				Integer manufacturerId = (Integer) keys.get("Id");
				result.setFeedData(dataStore.getCarsFor(manufacturerId));
				result.setInlineProperties(inlineProperties);
			}
		} catch (EdmException e) {
			// TODO: should be handled and not only logged
			LOG.error("Error in $expand handling.", e);
		} catch (EntityProviderException e) {
			// TODO: should be handled and not only logged
			LOG.error("Error in $expand handling.", e);
		}
		return result;
	}

	private boolean isNavigationFromTo(WriteCallbackContext context, String entitySetName,
			String navigationPropertyName) throws EdmException {
		if (entitySetName == null || navigationPropertyName == null) {
			return false;
		}
		EdmEntitySet sourceEntitySet = context.getSourceEntitySet();
		EdmNavigationProperty navigationProperty = context.getNavigationProperty();
		return entitySetName.equals(sourceEntitySet.getName())
				&& navigationPropertyName.equals(navigationProperty.getName());
	}
}
