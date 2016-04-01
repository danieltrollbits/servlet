package com.training.hibernate.dto;

import java.util.*;
import com.training.hibernate.model.Gender;

public class PersonDto extends BaseDto {

	private String lastName;

	private String firstName;

	private String middleName;

	private Gender gender;
	
	private Date birthdate;
	
	private AddressDto addressDto;

	private boolean employed;
	
	private float gwa;
	
	private Set<ContactDto> contactDtos;
	
	private Set<RoleDto> roleDtos = new HashSet<RoleDto>(0);

	public PersonDto(){};

	public PersonDto(String lastName, String firstName, String middleName, Gender gender, Date birthdate, boolean employed, float gwa){
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

	public AddressDto getAddressDto(){
		return this.addressDto;
	}

	public void setAddressDto(AddressDto addressDto){
		this.addressDto = addressDto;
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

	public Set<ContactDto> getContactDtos(){
		return this.contactDtos;
	}

	public void setContactDtos(Set<ContactDto> contactDtos){
		this.contactDtos = contactDtos;
	}

	public Set<RoleDto> getRoleDtos(){
		return this.roleDtos;
	}

	public void setRoleDtos(Set<RoleDto> roleDtos){
		this.roleDtos = roleDtos;
	}

	public String toString(){
		return "name: " + getFullName() + "gender: " + this.gender + "birthdate: " + this.birthdate;
	}

	public String rolesToString(){
		String roles = "";
		int loop = 1;
		int size = this.roleDtos.size();
		for (RoleDto roleDto : this.roleDtos){
			roles += roleDto.getRole();
			if (loop >= 0 && loop <size)
				roles += ", ";
			loop++;
		}
		return roles;
	}
}
