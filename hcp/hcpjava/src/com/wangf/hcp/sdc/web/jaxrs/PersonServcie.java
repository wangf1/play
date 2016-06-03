package com.wangf.hcp.sdc.web.jaxrs;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
		List<Person> persons = personDao.getAll();
		Response response = Response.status(Status.OK).entity(persons).build();
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPerson(Person person) {
		personDao.add(person);
		Response response = Response.status(Status.OK).entity(person).build();
		return response;
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id") long id) {
		Person person = personDao.remove(id);
		Response response = Response.status(Status.OK).entity(person).build();
		return response;
	}
}
