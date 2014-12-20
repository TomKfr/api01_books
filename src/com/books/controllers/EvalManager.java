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
import org.hibernate.criterion.Restrictions;

import com.books.hibernate.HibernateUtil;
import com.books.model.Books;
import com.books.model.User;

/**
 * Servlet implementation class EvalManager
 */
@WebServlet("/EvalManager")
public class EvalManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvalManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		System.out.println("action = "+request.getParameter("action"));
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		if(request.getParameter("action").equals("delete")){
			System.out.println("email :"+u.getEmail());
			System.out.println("isbn :"+request.getParameter("isbn"));
			
			sess.beginTransaction();
			sess.createQuery("delete from com.books.model.Evaluation eval where eval.user= :user and eval.book= :book")
				.setString("user", u.getEmail())
				.setString("book", request.getParameter("isbn"))
				.executeUpdate();
			sess.getTransaction().commit();
			
			sess.close();
			request.getRequestDispatcher("BooksController?action=list&page=0").forward(request, response);
		}
		if(request.getParameter("action").equals("search")){
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
			request.setAttribute("books", list);
			System.out.println("forwarding ...");
			request.getRequestDispatcher("./user/reader_add_book.jsp").forward(request, response);
		}
		if(request.getParameter("action").equals("add")){
			
			System.out.println("forwarding ...");
			request.getRequestDispatcher("reader_add_book.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
