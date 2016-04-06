package com.training.hibernate.services;

import com.training.hibernate.model.*;
import com.training.hibernate.dto.*;
import com.training.hibernate.dao.PersonDao;
import java.util.*;
import org.hibernate.HibernateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.*;
import java.lang.NumberFormatException;

public class PersonService {
	private PersonDao personDao = new PersonDao();

	public List<PersonDto> getAllPersons() throws HibernateException {
		return personDao.getAllPersons();
	}

	public PersonDto getPersonById(int id) throws HibernateException {
		return personDao.getPersonById(id);
	}

	public String saveOrUpdatePerson(PersonDto personDto){
		return personDao.saveOrUpdatePerson(personDto);
	}

	public String deletePerson(int id){
		return personDao.deletePerson(id);
	}

	public List<PersonDto> searchPerson(String lastName, String firstName, String middleName, String role) throws HibernateException{
		return personDao.searchPerson(lastName, firstName, middleName, role);
	}

	public List<RoleDto> getRoles(){
		return personDao.getRoles();
	}

	public PersonDto createPersonDto(String id, String firstName, String middleName, String lastName, String gender, String birthdate,
		String employed, String gwa, String street, String houseNo, String barangay, String subdivision,
		String city, String zipCode, String[] contactTypeList, String[] contactValueList, String[] contactId,
		String[] roleList){

		System.out.println("---------------");
		System.out.println(id);
		System.out.println(firstName);
		System.out.println(gender);
		System.out.println(birthdate);
		System.out.println(contactTypeList);
		System.out.println(contactValueList);
		System.out.println(contactId);
		System.out.println(roleList);
		
		PersonDto personDto = new PersonDto();
		if(!id.isEmpty()){
			if(id != "0"){
				personDto.setId(Integer.parseInt(id));	
			}
		}
		if(!birthdate.isEmpty()){
			DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
			Date date = null;
			try{
				date = df.parse(birthdate);
				personDto.setBirthdate(date);
			}catch(ParseException | NumberFormatException e){
				e.printStackTrace();
			}
		}
		personDto.setFirstName(firstName);
		personDto.setMiddleName(middleName);
		personDto.setLastName(lastName);
		if(gender != null){
			personDto.setGender(Gender.valueOf(gender.toUpperCase()));
		}
		if(employed != null){
			if(employed.equals("yes")){
				personDto.setEmployed(true);
			}
			else{
				personDto.setEmployed(false);
			}	
		}
		if(!gwa.isEmpty()){
			if(gwa.matches("[1-9]\\d*(\\.\\d+)?$")){
				personDto.setGwa(Float.parseFloat(gwa));	
			}
		}
		Set<RoleDto> roleDtos = new HashSet<>();
		if(roleList != null){
			for(String role : roleList){
				RoleDto roleDto = new RoleDto(role);
				roleDtos.add(roleDto);
			}
			personDto.setRoleDtos(roleDtos);
		}
		
		//address
		AddressDto addressDto = new AddressDto();
		addressDto.setStreet(street);
		addressDto.setBarangay(barangay);
		addressDto.setSubdivision(subdivision);
		addressDto.setCity(city);
		addressDto.setZipCode(zipCode);
		if(!houseNo.isEmpty()){
			if(houseNo.matches("\\d+")){
				addressDto.setHouseNo(Integer.parseInt(houseNo));	
			}	
		}
		personDto.setAddressDto(addressDto);
		Set<ContactDto> contactDtos = new HashSet<>();
		if(contactTypeList != null && contactValueList.length > 0){
			for (int i=0; i<contactTypeList.length; i++){
				ContactDto contactDto = new ContactDto();
				if(contactId != null){
					if(contactId[i] != null){
						contactDto.setId(Integer.parseInt(contactId[i]));	
					}
				}
				contactDto.setType(Type.valueOf(contactTypeList[i].toUpperCase()));
				contactDto.setValue(contactValueList[i]);
				contactDtos.add(contactDto);
			}	
		}
		personDto.setContactDtos(contactDtos);
		return personDto;
	}

	public boolean isRequired(String firstName, String middleName, String lastName, String gender, String employed,
		String street, String barangay, String subdivision, String city, String zipCode,
		String[] contactTypeList, String[] contactValueList, String[] roleList){

		if(firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || gender == null ||
			street.isEmpty() || barangay.isEmpty() || city.isEmpty() || zipCode.isEmpty() || roleList == null){
			return false;
		}
		else{
			return true;
		}
	}

	public boolean isDate(String strDate){
		boolean isValid = false;
		if(!strDate.isEmpty()){
			DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
			Date date = null;
			try{
				date = df.parse(strDate);
				isValid = true;
			}catch(ParseException|NumberFormatException e){
				e.printStackTrace();
				isValid = false;
			}
		}
		else{
			isValid = false;
		}
		return isValid;
	}

	public boolean isNumber(String strNo){
		if(strNo.matches("\\d+")){
			return true; 
		}
		else{
			return false;
		}
	}

	public boolean isDecimal(String strDecimal){
		if(strDecimal.matches("[1-9]\\d*(\\.\\d+)?$")){
			return true; 
		}
		else{
			return false;
		}	
	}

}