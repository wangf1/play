package com.wangf.hcp.sdc.persistence.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wangf.hcp.sdc.persistence.entity.Person;

@Stateless
@LocalBean
public class PersonDaoEjb {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Get all persons from the table.
	 */
	public List<Person> getAllPersons() {
		return em.createNamedQuery("AllPersons", Person.class).getResultList();
	}

	/**
	 * Add a person to the table.
	 */
	public void addPerson(Person person) {
		em.persist(person);
		em.flush();
	}
}
