package com.training.hibernate.dto;

import java.util.Set;

public class RoleDto extends BaseDto {

	private String role;

	private Set<PersonDto> personDtos;

	public RoleDto() {
	}

	public RoleDto(String role) {
		this.role = role;
	}

	public RoleDto(String role, Set<PersonDto> personDtos) {
		this.role = role;
		this.personDtos = personDtos;
	}

	public String getRole(){
		return this.role;
	}

	public void setRole(String role){
		this.role = role;
	}

	public Set<PersonDto> getPersonDtos(){
		return this.personDtos;
	}

	public void setPersonDtos(Set<PersonDto> personDtos){
		this.personDtos = personDtos;
	}
}
