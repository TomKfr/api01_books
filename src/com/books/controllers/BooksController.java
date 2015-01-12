package com.books.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.TypedValue;

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
		
		String action = request.getParameter("action");
		
		if(action.equals("searchBooks")) {
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
		
		if(action.equals("add")) {
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
			
			request.getRequestDispatcher("BooksController?action=search").forward(request, response);
		}
		
		if(action.equals("search")){
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			List<Books> list = new ArrayList<Books>();
			
			if(!request.getParameter("isbn").isEmpty()){
				
				list = sess.createCriteria(Books.class)
									.add(Restrictions.eq("isbn", request.getParameter("isbn")))
									.list();
				System.out.println("recherche par isbn : "+request.getParameter("isbn"));
			}
			else{
				
				list = sess.createCriteria(Books.class)
							.add(Restrictions.like("titre", "%"+request.getParameter("titre")+"%"))
							.list();
				System.out.println("recherche par titre : "+request.getParameter("titre"));
			}
			
			request.setAttribute("books", list);
			request.getRequestDispatcher("./user/reader_add_book.jsp").forward(request, response);
		}
		
		if(action.equals("index")){
			List<Books> list = new ArrayList<Books>();
			request.setAttribute("books", list);
			request.getRequestDispatcher("./user/reader_add_book.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
