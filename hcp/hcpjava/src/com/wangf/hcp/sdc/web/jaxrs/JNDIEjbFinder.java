package com.wangf.hcp.sdc.web.jaxrs;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.wangf.hcp.sdc.persistence.dao.PersonDaoEjb;

class JNDIEjbFinder {

	private static final String JNDI_PREFIX = "java:app/hcpjava/";
	static JNDIEjbFinder instance = new JNDIEjbFinder();

	private JNDIEjbFinder() {
	}

	PersonDaoEjb getPersonDaoEjb() throws NamingException {
		String lookupName = JNDI_PREFIX + "PersonDaoEjb";
		PersonDaoEjb personDao = (PersonDaoEjb) InitialContext.doLookup(lookupName);
		return personDao;
	}
}
