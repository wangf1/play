package com.wangf.hcp.sdc.web.jaxrs;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.wangf.hcp.sdc.persistence.dao.PersonDaoEjb;
import com.wangf.hcp.sdc.persistence.entity.Person;

@Path("/Persons")
public class PersonServcie {
	private PersonDaoEjb personDao;

	public PersonServcie() throws NamingException {
		personDao = JNDIEjbFinder.instance.getPersonDaoEjb();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPersons() {
		List<Person> persons = personDao.getAllPersons();
		Response response = Response.status(Status.OK).entity(persons).build();
		return response;
	}
}
