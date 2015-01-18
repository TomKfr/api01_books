package com.books.controllers;

import java.io.IOException;
import java.util.Iterator;
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
import com.books.utilities.MatchRandom;
import com.books.utilities.MatchingAlgo;

/**
 * Servlet implementation class MatchManager
 * gestion des matches par l'administrateur
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
        this.updateMatchingAlgo("byscore");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		/**
		 * page d'accueil du menu avec l'ensemble des matches
		 */
		if(action.equals("index")){
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			List<Tmatch> list = (List<Tmatch>) sess.createCriteria(Tmatch.class).list();
			sess.close();
			
			request.setAttribute("matches", list);
			
			request.getRequestDispatcher("./admin/admin_match_mgmt.jsp").forward(request, response);
		}
		/**
		 * mise à jour des matches pour un utilisateur et un livre particulier
		 */
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
			
			System.out.println("*********** size : "+list.size());
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
				if(!(closestuser.equals(match.getClosestuser()) && farthestuser.equals(match.getFarthestuser()))){
					//MailUtil.sendMessage("Un nouveau match a été trouvé pour votre profil", "Votre évaluation pour le livre n°"+eval.getBook()+" a matché avec de nouveaux utilisateurs :\nUtilisateur le plus proche : "+closestuser+"\nUtilisateur le plus éloigné : "+farthestuser, user, "tkieffer67@gmail.com");
					System.out.println("envoi de mail : nouveaux matchs !");
					match.setClosestuser(closestuser);
					match.setFarthestuser(farthestuser);
				}
			}
			
			sess.saveOrUpdate(match);
			sess.getTransaction().commit();
			sess.close();
			request.setAttribute("result", "Okay, c'est bon !");
			request.getRequestDispatcher("EvalManager?action=index").forward(request, response);
		}
		/**
		 * changement de l'algorithme de matches
		 */
		if(action.equals("matchalgo")){
			this.updateMatchingAlgo(request.getParameter("matchalgo"));
			System.out.println("changement d'algo de macth : "+request.getParameter("matchalgo"));
			request.getSession().setAttribute("matchalgo", request.getParameter("matchalgo"));
			request.getRequestDispatcher("MatchManager?action=index").forward(request, response);
		}
		/**
		 * suppression d'un match
		 */
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
		/**
		 * MAJ de tous les matches
		 */
		if(action.equals("updateall")){
			this.update_all_matches();
			request.getRequestDispatcher("MatchManager?action=index").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * méthode pour le choix de l'algorithme à implémenter pour les matches
	 * @param a
	 */
	private void updateMatchingAlgo(String a){
		switch(a){
		case "byscore" : { this.mtch = new MatchByScore(); System.out.println("matchalgo = byscore");break;}
		case "random" : {this.mtch = new MatchRandom(); System.out.println("matchalgo = random");break;}
		}
	}
	/**
	 * méthode pour lancer la mise à jour de tous les matches
	 */
	private void update_all_matches(){
		System.out.println("mise à jour de toutes les match !!!");
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		sess.beginTransaction();
		
		List<Evaluation> list = sess.createCriteria(Evaluation.class).list();
		System.out.println("nombre d'evals : "+list.size());
		Iterator<Evaluation> it = list.iterator();
		
		Evaluation e;
		String user;
		String book;
		String closestuser;
		String farthestuser;
		Tmatch existing_match;
		while(it.hasNext()){
			
			e = it.next();
			user = e.getUser();
			book = e.getBook();
			existing_match = null;
			existing_match = (Tmatch) sess.createQuery("from Tmatch where user = :user and book = :book").setString("user", user).setString("book", book).uniqueResult();
			
			closestuser = this.mtch.getClosestUser(user, e);
			farthestuser = this.mtch.getFarthestUser(user, e);
			
			if(existing_match==null){
				
				System.out.println("pas d'ancien match");
				
				existing_match = new Tmatch();
				
				existing_match.setBook(e.getBook());
				existing_match.setClosestuser(closestuser);
				existing_match.setFarthestuser(farthestuser);
				existing_match.setUser(user);
				
			}
			else{
				System.out.println("old closest :"+existing_match.getClosestuser());
				System.out.println("new closest :"+closestuser);
				if(!(closestuser.equals(existing_match.getClosestuser()) && farthestuser.equals(existing_match.getFarthestuser()))){
					//MailUtil.sendMessage("Un nouveau match a été trouvé pour votre profil", "Votre évaluation pour le livre n°"+eval.getBook()+" a matché avec de nouveaux utilisateurs :\nUtilisateur le plus proche : "+closestuser+"\nUtilisateur le plus éloigné : "+farthestuser, user, "tkieffer67@gmail.com");
					System.out.println("envoi de mail : nouveaux matchs !");
					existing_match.setClosestuser(closestuser);
					existing_match.setFarthestuser(farthestuser);
				}
			}
			
			sess.saveOrUpdate(existing_match);
		}
		
		sess.getTransaction().commit();
		sess.close();
		System.out.println("fin de la mise à jour de tous les matchs");
	}
}
