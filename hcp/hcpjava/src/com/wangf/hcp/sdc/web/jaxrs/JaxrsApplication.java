package com.wangf.hcp.sdc.web.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class JaxrsApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> servcies = new HashSet<Class<?>>();
		servcies.add(PersonServcie.class);
		return servcies;
	}
}
