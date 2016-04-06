package com.training.hibernate.model;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {

	@Column(name="role")
	private String role;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	private Set<Person> persons;

	public Role() {
	}

	public Role(String role) {
		this.role = role;
	}

	public Role(String role, Set<Person> persons) {
		this.role = role;
		this.persons = persons;
	}

	public String getRole(){
		return this.role;
	}

	public void setRole(String role){
		this.role = role;
	}

	public Set<Person> getPersons(){
		return this.persons;
	}

	public void setPersons(Set<Person> persons){
		this.persons = persons;
	}
}