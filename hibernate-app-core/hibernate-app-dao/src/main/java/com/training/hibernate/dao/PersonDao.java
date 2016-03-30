package com.training.hibernate.dao;

import com.training.hibernate.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import com.training.hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.*;
import java.io.*;

public class PersonDao {

	public List<Person> getAllPersons(){
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
		List<Person> persons = new ArrayList<>();
		try{
			tx = session.beginTransaction();
			Criteria cr = null;
				cr = session.createCriteria(Person.class);
				persons = cr.list();
		}catch (RuntimeException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return persons;
	}

	public Person getPersonById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Person person = null;
		try{
			tx = session.beginTransaction();
			String hql = "from Person where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id",id);
			person = (Person) query.uniqueResult();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return person;
	}

	public List<Person> searchPerson(String lastName, String firstName, String middleName, String role){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Person> persons = new ArrayList<>();
		try{
			tx = session.beginTransaction();
			Criteria criteria = null;
			criteria = session.createCriteria(Person.class);
			if (!lastName.isEmpty()){
				criteria.add(Restrictions.eq("lastName",lastName));	
			}
			if(!firstName.isEmpty()){
				criteria.add(Restrictions.eq("firstName",firstName));	
			}
			if(!middleName.isEmpty()){
				criteria.add(Restrictions.eq("middleName",middleName));	
			}
			/*if (role != null){
				criteria.add(Restrictions.eq("role",role));	
			}*/
			persons = criteria.list();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return persons;	
	}

	public void addPerson(Person person){
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			session.save(person);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void updatePerson(Person person){
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			session.update(person);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void deletePerson(int id){
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			Person person = (Person)session.get(Person.class, id);
			session.delete(person);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}