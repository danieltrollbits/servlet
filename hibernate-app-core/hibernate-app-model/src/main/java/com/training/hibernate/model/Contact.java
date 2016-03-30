package com.training.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
public class Contact extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;

	@Column(name = "value")
	private String value;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	public Contact(){};

	public Contact(Type type, String value){
		this.type = type;
		this.value = value;
	}
	
	public Type getType(){
		return this.type;
	}

	public void setType(Type type){
		this.type = type;
	}

	public String getValue(){
		return this.value;
	}

	public void setValue(String value){
		this.value = value;
	}

	public Person getPerson(){
		return this.person;
	}

	public void setPerson(Person person){
		this.person = person;
	}

	public String toString(){
		return this.type + ": " + this.value;
	}
}