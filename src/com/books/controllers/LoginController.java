package com.books.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.books.model.User;
import com.books.utilities.HibernateUtil;
import com.books.utilities.MailUtil;

/**
 * Servlet implementation class LoginController
 * gestion de la connexion au site
 */
@WebServlet({ "/LoginController", "/index" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		/**
		 * déconnexion
		 */
		if(action.equals("logout")){
			System.out.println("déconnexion ...");
			request.getSession().setAttribute("user", null);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
			/**
			 * login
			 */
			if(action.equals("login")){			
				if(request.getSession().getAttribute("user")!=null){
					User usr = (User) request.getSession().getAttribute("user");
					if(usr.getIsAdmin()){
						request.getRequestDispatcher("admin/admin_index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("action", "load");
						request.getRequestDispatcher("user/reader_index.jsp").forward(request, response);
					}
				}
				String log = request.getParameter("login");
				String pass = request.getParameter("pwd");
				User u = new User();
				
				Session sess = HibernateUtil.getSessionFactory().openSession();
				
				try{
					u = (User) sess.get("com.books.model.User", log);
				} 
				catch (HibernateException he) {
					System.out.println("login non trouvé : "+ he);
				}
				
				sess.close();
				
				if(u!=null){
					request.getSession().setAttribute("matchalgo", "by score");
					if(u.getPwd().compareTo(pass)==0){
						if(u.getIsAdmin()){
							request.getSession().setAttribute("user", u);
							request.getRequestDispatcher("admin/admin_index.jsp").forward(request, response);
						}
						else{
							request.getSession().setAttribute("user", u);
							request.setAttribute("action", "load");
							request.getRequestDispatcher("user/reader_index.jsp").forward(request, response);
						}
					}
					else{
						request.setAttribute("result", "fail");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}
				else{
					request.setAttribute("result", "fail");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}
			else{
				/**
				 * demande de création d'un compte
				 */
				if(action.equals("createaccount")){
					
					//MailUtil.sendMessage("Demande de création de compte", "Demande de création de compte", "tkieffer67@gmail.com", request.getParameter("email"));
					System.out.println("envoi de mail : création de compte");
					
					User u = new User();
					u.setEmail(request.getParameter("email"));
					u.setName(request.getParameter("nom"));
					u.setPwd(request.getParameter("pwd"));
					u.setAddress(request.getParameter("adrs"));
					u.setTel(request.getParameter("tel"));
					u.setAccountStatus(true);
					u.setIsAdmin(false);
					u.setCreationDate(new Date());
					
					Session sess = HibernateUtil.getSessionFactory().openSession();
					sess.beginTransaction();
					sess.save(u);
					sess.getTransaction().commit();
					sess.close();
					
					request.setAttribute("accountcr", "ok");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					
				}
				else{
					/**
					 * erreur de connexion
					 */
					request.setAttribute("result", "fail");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
