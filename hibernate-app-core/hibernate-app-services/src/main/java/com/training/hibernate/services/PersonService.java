package com.training.hibernate.services;

import com.training.hibernate.model.Person;
import com.training.hibernate.model.Address;
import com.training.hibernate.dto.*;
import com.training.hibernate.dao.PersonDao;
import java.util.*;
import org.hibernate.HibernateException;

public class PersonService {
	private PersonDao personDao = new PersonDao();

	public List<PersonDto> getAllPersons() throws HibernateException {
		return personDao.getAllPersons();
	}

	public PersonDto getPersonById(int id) throws HibernateException {
		return personDao.getPersonById(id);
	}

	public String addPerson(PersonDto personDto){
		return personDao.addPerson(personDto);
	}

	public String updatePerson(PersonDto personDto){
		return personDao.updatePerson(personDto);
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

}