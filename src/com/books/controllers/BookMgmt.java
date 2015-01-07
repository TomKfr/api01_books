package com.books.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.books.hibernate.BooksHome;
import com.books.model.Books;
import com.books.utilities.HibernateUtil;

/**
 * Servlet implementation class BookMgmt
 */
@WebServlet("/BookMgmt")
public class BookMgmt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMgmt() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equals("index")){
			System.out.println("forwarding ...");
		}
		if(action.equals("add")){
			System.out.println("adding ...");
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			
			Books bk = new Books();
			bk.setIsbn(request.getParameter("isbn"));
			bk.setTitre(request.getParameter("titre"));
			bk.setGenre(request.getParameter("genre"));
			bk.setAuteur(request.getParameter("auteur"));
			
			sess.save(bk);
			sess.getTransaction().commit();
			sess.close();
			System.out.println("added !");
			
			request.setAttribute("exec", "success");
		}
		if(action.equals("serach")){
			System.out.println("searching ...");
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			
			List<Books> list = new ArrayList<Books>();
			
			String isbn = request.getParameter("isbn");
			System.out.println("isbn : "+request.getParameter("isbn"));
			if(!isbn.isEmpty()){
				Books bk = (Books) sess.get("com.books.model.Books", isbn);
				list.add(bk);
			}
			else{
				list = sess
						.createCriteria(Books.class)
						.add(Restrictions.like("titre", "%"+request.getParameter("titre")+"%"))
						.list();
			}
			request.setAttribute("search", list);
			
			sess.getTransaction().commit();
			sess.close();
			System.out.println("search finished");
		}
		else{
		}
		request.getRequestDispatcher("./admin/book_mgmt.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
