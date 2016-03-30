package com.training.hibernate.services;

import com.training.hibernate.model.Person;
import com.training.hibernate.model.Address;
import com.training.hibernate.dto.PersonDto;
import com.training.hibernate.dto.AddressDto;
import com.training.hibernate.dao.PersonDao;
import java.util.*;
import org.apache.commons.beanutils.BeanUtils;

public class PersonService {
	private PersonDao personDao = new PersonDao();

	public List<PersonDto> getAllPersons(){
		return toDtos(personDao.getAllPersons());
	}

	public PersonDto getPersonById(int id){
		return toDto(personDao.getPersonById(id));
	}

	public void addPerson(PersonDto personDto){
		personDao.addPerson(toEntity(personDto));
	}

	public void updatePerson(PersonDto personDto){
		personDao.updatePerson(toEntity(personDto));
	}

	public void deletePerson(int id){
		personDao.deletePerson(id);
	}

	public List<PersonDto> searchPerson(String lastName, String firstName, String middleName, String role){
		return toDtos(personDao.searchPerson(lastName, firstName, middleName, role));
	}

	public PersonDto toDto(Person person){
		PersonDto personDto = new PersonDto();
		AddressDto addressDto = new AddressDto();
		try{
			BeanUtils.copyProperties(personDto,person);
			BeanUtils.copyProperties(addressDto,person.getAddress());
			personDto.setAddressDto(addressDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return personDto;
	}

	public List<PersonDto> toDtos(List<Person> persons){
		List<PersonDto> personDtos = new ArrayList<>();
		try{
			for (int i = 0; i < persons.size(); i++){
				PersonDto personDto = new PersonDto();
				AddressDto addressDto = new AddressDto();
				BeanUtils.copyProperties(personDto,persons.get(i));
				BeanUtils.copyProperties(addressDto,persons.get(i).getAddress());
				personDto.setAddressDto(addressDto);
				personDtos.add(personDto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return personDtos;
	}

   public Person toEntity(PersonDto personDto) {
      Person person = new Person();
      Address address = new Address();
      try{
         BeanUtils.copyProperties(person,personDto);
         BeanUtils.copyProperties(address,personDto.getAddressDto());
         person.setAddress(address);
      }catch(Exception e){
         e.printStackTrace();
      }
      return person;
   }

}