package com.training.hibernate.dao;

import com.training.hibernate.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.*;
import com.training.hibernate.util.HibernateUtil;

public class ContactDao {

	public void deleteContactById(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			String hql = "delete from Contact where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id",id);
			query.executeUpdate();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public Set<Contact> getContactsByPersonId(int personId){
		List<Contact> contacts = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			String hql = "from Contact where person_id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id",personId);
			contacts = query.list();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return new HashSet<Contact>(contacts);
	}
}