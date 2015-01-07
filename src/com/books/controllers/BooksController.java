package com.books.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.books.model.Books;
import com.books.model.User;
import com.books.utilities.HibernateUtil;

/**
 * Servlet implementation class BooksController
 */
@WebServlet("/BooksController")
public class BooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer nbpages = (Integer) request.getAttribute("nbpages"); 
		Integer  pagination = Integer.parseInt(request.getParameter("page"));
		User u = (User) request.getSession().getAttribute("user");
		if(pagination==null) pagination = 2;
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		List<Books> res = null;
		try{
			//res = sess.createCriteria("com.books.model.Books").list();
			res = sess.createQuery("select books from com.books.model.Books as books, com.books.model.Evaluation eval, com.books.model.User as user where books.isbn=eval.book and eval.user= :mail")
					.setString("mail", u.getEmail())
					.list();
		}
		catch (HibernateException he){
			System.out.println("echec recup bouquins "+ he);
		}
		
		if(nbpages==null) nbpages = Math.round(res.size()/20);
		request.setAttribute("nbpages", nbpages);
		request.setAttribute("page", pagination);
		System.out.println("num page : "+pagination);
		
		if(!res.isEmpty()){
			if(pagination!=nbpages){
				request.setAttribute("books", res.subList(pagination*20, pagination*20+19));
			}
			else{
				System.out.println("blop");
				request.setAttribute("books", res.subList(pagination*20, res.size()-1));
			}
			request.getRequestDispatcher("user/reader_books.jsp").forward(request, response);
		}
		else{
			System.out.println("pas de livres");
			request.getRequestDispatcher("user/reader_books.jsp").forward(request, response);
		}
		
		sess.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
