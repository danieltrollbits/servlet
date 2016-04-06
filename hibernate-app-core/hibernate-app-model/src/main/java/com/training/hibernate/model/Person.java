package com.training.hibernate.model;

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "PERSON")
public class Person extends BaseEntity {

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	
	@Column(name = "birthdate")
	private Date birthdate;
	
	@Embedded
	private Address address;

	@Column(name="employed")
	@Type(type="yes_no")
	private boolean employed;
	
	@Column(name = "gwa")
	private float gwa;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
	private Set<Contact> contacts;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PERSON_ROLE", joinColumns = {
			@JoinColumn(name = "person_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id", 
					nullable = false, updatable = false) })
	private Set<Role> roles = new HashSet<Role>(0);

	public Person(){};

	public Person(String lastName, String firstName, String middleName, Gender gender, Date birthdate, boolean employed, float gwa){
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.employed = employed;
		this.gwa = gwa;
	}

	public String getLastName(){
		return this.lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getMiddleName(){
		return this.middleName;
	}

	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}

	public String getFullName(){
		return this.firstName + " " + this.middleName + " " + this.lastName;
	}

	public Gender getGender(){
		return this.gender;
	}

	public void setGender(Gender gender){
		this.gender = gender;
	}

	public Date getBirthdate(){
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate){
		this.birthdate = birthdate;
	}

	public Address getAddress(){
		return this.address;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public boolean isEmployed(){
		return this.employed;
	}

	public void setEmployed(boolean employed){
		this.employed = employed;
	}

	public float getGwa(){
		return this.gwa;
	}

	public void setGwa(float gwa){
		this.gwa = gwa;
	}

	public Set<Contact> getContacts(){
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts){
		this.contacts = contacts;
	}

	public Set<Role> getRoles(){
		return this.roles;
	}

	public void setRoles(Set<Role> roles){
		this.roles = roles;
	}

	public String toString(){
		return "name: " + getFullName() + "gender: " + this.gender + "birthdate: " + this.birthdate;
	}
}