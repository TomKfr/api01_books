package com.books.controllers;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.books.hibernate.HibernateUtil;
import com.books.model.User;


public class Main {

	public static void main(String[] args) {
System.out.println("Debut");
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		User usr = new User("hellomyfriend");
		usr.setAccountStatus(true);
		usr.setName("Patrick");
		sess.save(usr);
		
		t.commit();
		
		//Pers result = (Pers) sess.get("Pers", "hello");
		
		sess.beginTransaction();
		User peep = (User) sess.get("com.books.model.User", "hellomyfriend");
		peep.setAddress("la bas");
		peep.setIsAdmin(false);
		peep.setTel("65453612");
		
		//sess.update(peep);
		sess.getTransaction().commit();
		//Pers result = psh.findById("hello");
		
		System.out.println(peep.getName());
		
		sess.flush();
		sess.close();
		
		System.out.println("Fin");
	}

}
