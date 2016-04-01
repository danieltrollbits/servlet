package com.training.hibernate.dto;

import com.training.hibernate.model.Type;

public class ContactDto extends BaseDto {

	private Type type;

	private String value;

	private PersonDto personDto;

	public ContactDto(){};

	public ContactDto(Type type, String value){
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

	public PersonDto getPersonDto(){
		return this.personDto;
	}

	public void setPersonDto(PersonDto personDto){
		this.personDto = personDto;
	}

	public String toString(){
		return this.type + ": " + this.value;
	}
}
