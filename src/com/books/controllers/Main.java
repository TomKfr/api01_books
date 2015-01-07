package com.books.controllers;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.books.model.User;
import com.books.utilities.HibernateUtil;
import com.books.utilities.MailUtil;


public class Main {

	public static void main(String[] args) {
		System.out.println("Debut");
		
		MailUtil.sendMessage("Coucou !", "Ceci est un essai d'envoi de mail", "morgane.becret@gmail.com ", "tkieffer67@gmail.com");
		
		System.out.println("Fin");
	}

}
