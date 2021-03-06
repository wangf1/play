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
	public List<Person> getAll() {
		return em.createNamedQuery("AllPersons", Person.class).getResultList();
	}

	/**
	 * Add a person to the table.
	 */
	public Person add(Person person) {
		em.persist(person);
		em.flush();
		return person;
	}

	public Person remove(long id) {
		Person person = em.find(Person.class, id);
		em.remove(person);
		em.flush();
		return person;
	}
}
