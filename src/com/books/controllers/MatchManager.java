package com.books.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.books.model.Evaluation;
import com.books.model.Tmatch;
import com.books.utilities.HibernateUtil;
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
			String closenum = request.getParameter("closenum");
			String farnum = request.getParameter("farnum");
			
			Tmatch closematch;
			Tmatch farmatch;
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			
			Evaluation eval = (Evaluation) sess.get(Evaluation.class, request.getParameter("eval"));
			
			String closestuser = this.mtch.getClosestUser(user, eval);
			String farthestuser = this.mtch.getFarthestUser(user, eval);
			
			sess.beginTransaction();
			
			if(closenum==null){ //Faire gaffe ici !!!!!!!
				closematch = new Tmatch();
				farmatch = new Tmatch();
				
				closematch.setBook(eval.getBook());
				closematch.setClosest(true);
				closematch.setUser1(user);
				closematch.setUser2(closestuser);
				
				farmatch.setBook(eval.getBook());
				farmatch.setClosest(false);
				farmatch.setUser1(user);
				farmatch.setUser2(farthestuser);
			}
			else{
				closematch = (Tmatch) sess.get(Tmatch.class, closenum);
				farmatch = (Tmatch) sess.get(Tmatch.class, farnum);
				
				closematch.setUser2(closestuser);
				farmatch.setUser2(farthestuser);
			}
			
			sess.saveOrUpdate(closematch);
			sess.saveOrUpdate(farmatch);
			sess.getTransaction().commit();
			sess.close();
			
			request.getRequestDispatcher("").forward(request, response);
		}
		
		if(action.equals("matchalgo")){
			this.updateMatchingAlgo(request.getParameter("matchalgo"));
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
