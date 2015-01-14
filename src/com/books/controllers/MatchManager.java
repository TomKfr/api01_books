package com.books.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import com.books.model.Evaluation;
import com.books.model.Tmatch;
import com.books.utilities.HibernateUtil;
import com.books.utilities.MailUtil;
import com.books.utilities.MatchByScore;
import com.books.utilities.MatchingAlgo;

/**
 * Servlet implementation class MatchManager
 */
@WebServlet("/MatchManager")
public class MatchManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchingAlgo mtch;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchManager() {
        super();
        this.updateMatchingAlgo("by score");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if(action.equals("index")){
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			List<Tmatch> list = (List<Tmatch>) sess.createCriteria(Tmatch.class).list();
			sess.close();
			
			request.setAttribute("matches", list);
			
			request.getRequestDispatcher("./admin/admin_match_mgmt.jsp").forward(request, response);
		}
		
		if(action.equals("update")){
			
			String user = request.getParameter("user");
			String book = request.getParameter("book");
			
			Tmatch match;
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			
			List<Tmatch> list = (List<Tmatch>) sess.createQuery("from Tmatch where user = :user and book = :book").setString("user", user).setString("book", book).list();
			
			Evaluation eval = (Evaluation) sess.get(Evaluation.class, Integer.parseInt(request.getParameter("eval")));
			
			String closestuser = this.mtch.getClosestUser(user, eval);
			String farthestuser = this.mtch.getFarthestUser(user, eval);
			
			sess.beginTransaction();
			
			if(list.size()==0){
				
				System.out.println("pas d'ancien match");
				
				match = new Tmatch();
				
				match.setBook(eval.getBook());
				match.setClosestuser(closestuser);
				match.setFarthestuser(farthestuser);
				match.setUser(user);
				
			}
			else{
				match = list.get(0);
				System.out.println("old closest :"+match.getClosestuser());
				System.out.println("new closest :"+closestuser);
				if((match.getClosestuser()!=closestuser) || (match.getFarthestuser()!=farthestuser)){
					//MailUtil.sendMessage("Un nouveau match a été trouvé pour votre profil", "Votre évaluation pour le livre n°"+eval.getBook()+" a matché avec de nouveaux utilisateurs :\nUtilisateur le plus proche : "+closestuser+"\nUtilisateur le plus éloigné : "+farthestuser, user, "tkieffer67@gmail.com");
					System.out.println("envoi de mail : nouveaux matchs !");
				}
				
				match.setClosestuser(closestuser);
				match.setFarthestuser(farthestuser);
			}
			
			sess.saveOrUpdate(match);
			sess.getTransaction().commit();
			sess.close();
			request.setAttribute("result", "Okay, c'est bon !");
			request.getRequestDispatcher("EvalManager?action=index").forward(request, response);
		}
		
		if(action.equals("matchalgo")){
			this.updateMatchingAlgo(request.getParameter("matchalgo"));
		}
		
		if(action.equals("delete")){
			String num = request.getParameter("num");
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			Query qry = sess.createQuery("delete Tmatch where num = :num");
			qry.setString("num", num).executeUpdate();
			sess.getTransaction().commit();
			sess.close();
			
			request.getRequestDispatcher("MatchManager?action=index").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void updateMatchingAlgo(String a){
		switch(a){
		case "by score" : this.mtch = new MatchByScore();
		}
	}
}
