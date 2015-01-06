package com.books.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.books.hibernate.HibernateUtil;
import com.books.model.User;

/**
 * Servlet implementation class LoginController
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
		
		if(request.getParameter("action")!=null){
			System.out.println("déconnexion ...");
			request.getSession().setAttribute("user", null);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
