package com.training.hibernate.dao;

import com.training.hibernate.model.*;
import com.training.hibernate.dto.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import com.training.hibernate.util.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.beanutils.BeanUtils;
import java.util.*;
import java.io.*;

public class PersonDao {

	public List<PersonDto> getAllPersons() throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
		Criteria criteria = session.createCriteria(Person.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Person> persons = criteria.list();
		List<PersonDto> personDtos = new ArrayList<>();
		for(Person person : persons){
			personDtos.add(toDto(person));
		}
		session.getTransaction().commit();
		session.close();
		return personDtos;
	}

	public PersonDto getPersonById(int id) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Person.class).add(Restrictions.eq("id",id));
		Person person = (Person)criteria.uniqueResult();
		PersonDto personDto = toDto(person);
		session.getTransaction().commit();
		session.close();
		return personDto;
	}

	public List<PersonDto> searchPerson(String lastName, String firstName, String middleName, String role) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Person.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (!lastName.isEmpty()){
			criteria.add(Restrictions.eq("lastName",lastName));	
		}
		if(!firstName.isEmpty()){
			criteria.add(Restrictions.eq("firstName",firstName));	
		}
		if(!middleName.isEmpty()){
			criteria.add(Restrictions.eq("middleName",middleName));	
		}
		if (!role.isEmpty()){
			criteria.createAlias("roles","role");
			criteria.add(Restrictions.in("role.role",role));	
		}
		List<Person> persons = criteria.list();
		List<PersonDto> personDtos = new ArrayList<>();
		for(Person person : persons){
			personDtos.add(toDto(person));
		}
		session.getTransaction().commit();
		session.close();
		return personDtos;	
	}

	public String addPerson(PersonDto personDto) {
		Person person = toEntity(personDto);
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		String message;
		System.out.println(person.toString());
		
		for (Contact contact : person.getContacts()){
			System.out.println(contact.toString());	
		}
		System.out.println(person.getAddress().toString());
		try{
			transaction = session.beginTransaction();
			session.save(person);
			transaction.commit();
			message = "success";
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
			message = "error";
		}finally{
			session.close();
		}
		return message;
	}

	public String updatePerson(PersonDto personDto){
		Person person = toEntity(personDto);
		String message;
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println(person.toString());
		
		for (Contact contact : person.getContacts()){
			System.out.println(contact.toString());	
		}
		System.out.println(person.getAddress().toString());
		try{
			transaction = session.beginTransaction();
			session.update(person);
			transaction.commit();
			message = "success";
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
			message = "error";
		}finally{
			session.close();
		}
		return message;
	}

	public String deletePerson(int id){
		String message;
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			Person person = (Person)session.get(Person.class, id);
			session.delete(person);
			transaction.commit();
			message = "success";
		}catch(HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
			message = "error";
		}finally{
			session.close();
		}
		return message;
	}

	public Role getRoleByName(String name){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Role role = null;
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Role.class);
			criteria.add(Restrictions.eq("role",name));
			role = (Role)criteria.uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return role;
	}

	public List<RoleDto> getRoles(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<RoleDto> roleDtos = new ArrayList<>();
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Role.class);
			roleDtos = criteria.list();
			session.getTransaction().commit(); 
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return roleDtos;
	}

	public PersonDto toDto(Person person){
		PersonDto personDto = new PersonDto();
		AddressDto addressDto = new AddressDto();
		Set<ContactDto> contactDtos = new HashSet<>();
		Set<RoleDto> roleDtos = new HashSet<>();
		try{
			BeanUtils.copyProperties(personDto,person);
			BeanUtils.copyProperties(addressDto,person.getAddress());
			for (Contact contact : person.getContacts()){
				ContactDto contactDto = new ContactDto();
				BeanUtils.copyProperties(contactDto,contact);
				contactDtos.add(contactDto);
			}
			
			for(Role role : person.getRoles()){
				RoleDto roleDto = new RoleDto();
				BeanUtils.copyProperties(roleDto,role);
				roleDtos.add(roleDto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		personDto.setAddressDto(addressDto);
		personDto.setContactDtos(contactDtos);
		personDto.setRoleDtos(roleDtos);
		return personDto;
	}

	public Person toEntity(PersonDto personDto){
		Person person = new Person();
		Address address = new Address();
		Set<Contact> contacts = new HashSet<>();
		Set<Role> roles = new HashSet<>();
		try{
			BeanUtils.copyProperties(person,personDto);
			BeanUtils.copyProperties(address,personDto.getAddressDto());
			for (ContactDto contactDto : personDto.getContactDtos()){
				Contact contact = new Contact();
				BeanUtils.copyProperties(contact,contactDto);
				contact.setPerson(person);
				contacts.add(contact);
			}
			for(RoleDto roleDto : personDto.getRoleDtos()){
				Role role = getRoleByName(roleDto.getRole());
				int id = role.getId();
				BeanUtils.copyProperties(role,roleDto);
				role.setId(id);
				roles.add(role);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		person.setAddress(address);
		person.setContacts(contacts);
		person.setRoles(roles);
		return person;	
	}
}