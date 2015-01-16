package com.books.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.books.model.Evaluation;
import com.books.model.Tmatch;
import com.books.model.User;
import com.books.utilities.HibernateUtil;
import com.books.utilities.MailUtil;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/UserManager")
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManager() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equals("add")){
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			
			User u = new User();
			u.setEmail(request.getParameter("email"));
			u.setName(request.getParameter("nom"));
			u.setPwd(request.getParameter("pwd"));
			u.setAddress(request.getParameter("adrs"));
			u.setTel(request.getParameter("tel"));
			u.setAccountStatus(request.getParameter("isactive")!=null);
			u.setIsAdmin(request.getParameter("isadmin")!=null);
			u.setCreationDate(new Date());
			
			sess.beginTransaction();
			sess.saveOrUpdate(u);
			sess.getTransaction().commit();
			sess.close();
			
			request.setAttribute("result", "success");
			request.getRequestDispatcher("./admin/user_mgmt.jsp").forward(request, response);
			
			String message = "Bonjour "+u.getName()+",\nVotre compte a été créé/modifié. Voici vos informations de connexion :\nEmail :"+u.getEmail()+"\nMot de passe : "+u.getPwd()+"\nCordialement,\nAdmin.";
			MailUtil.sendMessage("Notification de création de compte", message, u.getEmail(), "tkieffer67@gmail.com");
			System.out.println("envoi de mail : création/update user");
		}
		
		if(action.equals("delete")){
			
			String email = request.getParameter("email");
			System.out.println("supprimer :"+email);
			
			if(email!=null){
				Session sess = HibernateUtil.getSessionFactory().openSession();
				
				sess.beginTransaction();
				Query qry = sess.createQuery("delete User where email = :email");
				qry.setString("email", email).executeUpdate();
				
				qry = sess.createQuery("delete Evaluation where user = :email");
				qry.setString("email", email).executeUpdate();
				
				qry = sess.createQuery("delete Tmatch where user1 = :email1 or user2 = :email2");
				qry.setString("email1", email).setString("email2", email);
				
				sess.getTransaction().commit();
				sess.close();
				
				request.getRequestDispatcher("./admin/user_mgmt.jsp").forward(request, response);
			}
		}

		if(action.equals("startmodif")){
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			String email = request.getParameter("email");
			
			User u = (User) sess.get(User.class, email);
			
			request.setAttribute("modifuser", u);
			
			request.getRequestDispatcher("./admin/user_mgmt.jsp").forward(request, response);
		}
		
		if(action.equals("search")){
			System.out.println("searching ...");
			
			String nbpagesstr = request.getParameter("nbpages");
			String pagestr = request.getParameter("page");
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			
			List<User> list = new ArrayList<User>();
			
			String email = request.getParameter("email");
			System.out.println("email : "+email);
			if(!email.isEmpty()){
				User u = (User) sess.get(User.class, email);
				list.add(u);
			}
			else{
				String nom = request.getParameter("nom");
				if(!nom.isEmpty()){
					list = sess
							.createCriteria(User.class)
							.add(Restrictions.like("name", "%"+nom+"%"))
							.list();
				}
				else{
					if(request.getParameter("isadmin")!=null){
						list = sess
								.createCriteria(User.class)
								.add(Restrictions.eq("isAdmin", true))
								.list();
					}
					else{
						list = sess.createCriteria(User.class).list();
					}
				}
			}

			Integer nbpages;
			if(nbpagesstr==null) nbpages = Math.round(list.size()/7);
			else nbpages = Integer.parseInt(nbpagesstr);
			Integer page;
			if(pagestr==null) page = 1;
			else page = Integer.parseInt(pagestr);
			
			list = list.subList((page-1)*7+1, Math.min(list.size(), (page*7)));
			
			request.setAttribute("search", list);
			request.setAttribute("nbpages", nbpages);
			request.setAttribute("page", page);
			
			sess.getTransaction().commit();
			sess.close();
			System.out.println("search finished");
			
			request.getRequestDispatcher("./admin/user_mgmt.jsp").forward(request, response);
		}
		
		if(action.equals("view")){
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			String email = request.getParameter("email");
			
			User usr = (User) sess.get(User.class, email);
			List<Evaluation> listeval = sess.createCriteria(Evaluation.class).add(Restrictions.eq("user", email)).list();
			List<Tmatch> listmatch = sess.createCriteria(Tmatch.class).add(Restrictions.or(Restrictions.eq("user", email),Restrictions.eq("user2", email))).list();
			
			sess.close();
			
			request.setAttribute("vieweduser", usr);
			request.setAttribute("evalsuser", listeval);
			request.setAttribute("matchsuser", listmatch);
			
			request.getRequestDispatcher("./admin/admin_view_user.jsp").forward(request, response);			
		}
		
		if(action.equals("index")){
			request.getRequestDispatcher("./admin/user_mgmt.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
