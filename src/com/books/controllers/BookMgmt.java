package com.books.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.books.hibernate.BooksHome;
import com.books.model.Books;
import com.books.model.Evaluation;
import com.books.model.Tmatch;
import com.books.model.User;
import com.books.utilities.HibernateUtil;

/**
 * Servlet implementation class BookMgmt
 * servlet qui gère la partie Book Management de l'application 
 * @author Morgane et Thomas
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
		System.out.println("action : "+action);
		/**
		 * affichage de la page d'accueil du menu
		 */
		if(action.equals("index")){
			System.out.println("forwarding ...");
			request.getRequestDispatcher("./admin/book_mgmt.jsp").forward(request, response);
		}
		/**
		 * ajout d'un livre par l'administrateur
		 */
		if(action.equals("add")){
			System.out.println("adding ...");
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			
			Books bk = new Books();
			bk.setIsbn(request.getParameter("isbn"));
			bk.setTitre(request.getParameter("titre"));
			bk.setGenre(request.getParameter("genre"));
			bk.setAuteur(request.getParameter("auteur"));
			
			sess.saveOrUpdate(bk);
			sess.getTransaction().commit();
			sess.close();
			System.out.println("added !");
			
			request.setAttribute("exec", "success");
			request.getRequestDispatcher("./admin/book_mgmt.jsp").forward(request, response);
		}
		/**
		 * recherche d'un livre
		 */
		if(action.equals("search")){
			System.out.println("searching ...");
			
			String nbpagesstr = request.getParameter("nbpages");
			String pagestr = request.getParameter("page");
			
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
			
			sess.getTransaction().commit();
			sess.close();
			
			Integer nbpages;
			if(nbpagesstr==null) nbpages = Math.round(list.size()/7);
			else nbpages = Integer.parseInt(nbpagesstr);
			Integer page;
			if(pagestr==null) page = 1;
			else page = Integer.parseInt(pagestr);
			
			list = list.subList((page-1)*7, Math.min(list.size(), (page*7)));
			
			request.setAttribute("search", list);
			request.setAttribute("nbpages", nbpages);
			request.setAttribute("page", page);
			
			System.out.println("search finished");
			System.out.println("nbpages :"+request.getAttribute("nbpages"));
			request.getRequestDispatcher("./admin/book_mgmt.jsp").forward(request, response);
		}
		/**
		 * suppression d'un livre
		 */
		if(action.equals("delete")){
			
			String book = request.getParameter("book");
			System.out.println("book = "+book);
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			sess
				.createQuery("delete from com.books.model.Books where isbn = :isbn")
				.setString("isbn", book)
				.executeUpdate();
			
			//Suppression des evals liées au livre
			sess.createQuery("delete from Evaluation where book = :bk").setString("bk", book).executeUpdate();
			//Suppression des evals liées au livre
			sess.createQuery("delete from Tmatch where book = :bk").setString("bk", book).executeUpdate();
			
			sess.getTransaction().commit();
			sess.close();
			
			request.getRequestDispatcher("BookMgmt?action=search&isbn=").forward(request, response);
			
		}
		/**
		 * modification d'un livre
		 */
		if(action.equals("modify")){
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			String isbn = request.getParameter("book");
			
			Books bk = (Books) sess.get(Books.class, isbn);
			
			request.setAttribute("modifbook", bk);
			
			request.getRequestDispatcher("./admin/book_mgmt.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
