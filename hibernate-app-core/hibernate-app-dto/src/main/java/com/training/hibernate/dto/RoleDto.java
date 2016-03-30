package com.training.hibernate.dto;

import java.util.Set;

public class RoleDto extends BaseDto {

	private String role;

	private Set<PersonDto> persons;

	public RoleDto() {
	}

	public RoleDto(String role) {
		this.role = role;
	}

	public RoleDto(String role, Set<PersonDto> persons) {
		this.role = role;
		this.persons = persons;
	}

	public String getRole(){
		return this.role;
	}

	public void setRole(String role){
		this.role = role;
	}

	public Set<PersonDto> getPersons(){
		return this.persons;
	}

	public void setPersons(Set<PersonDto> persons){
		this.persons = persons;
	}
}
