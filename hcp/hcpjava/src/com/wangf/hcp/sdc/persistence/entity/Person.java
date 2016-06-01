package com.wangf.hcp.sdc.persistence.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Class holding information on a person.
 */
@Entity
@Table(name = "PERSONS")
@NamedQuery(name = "AllPersons", query = "select p from Person p")
public class Person {
	@Id
	@GeneratedValue
	private long id;
	@Basic
	@Column(length = 255)
	private String firstName;
	@Basic
	@Column(length = 255)
	private String lastName;

	/**
	 * Create a Person instance with unique id.
	 */
	public Person() {
	}

	public long getId() {
		return id;
	}

	public void setId(long newId) {
		this.id = newId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
