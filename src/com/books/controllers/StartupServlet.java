package com.books.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.books.utilities.HibernateUtil;

@WebServlet(description = "Lancement au démarrage", urlPatterns = { "/StartupServlet" })
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StartupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		HibernateUtil.getSessionFactory();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
