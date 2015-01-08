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

import com.books.model.Books;
import com.books.model.Evaluation;
import com.books.model.User;
import com.books.utilities.HibernateUtil;

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
		
		String action = request.getParameter("action");
		
		if(action.equals("delete")){
			
			String num = request.getParameter("num");
			sess.beginTransaction();
			sess.createQuery("delete from com.books.model.Evaluation where num = :num")
				.setString("num", num)
				.executeUpdate();
			sess.getTransaction().commit();
			
			sess.close();
			request.getRequestDispatcher("EvalManager?action=index").forward(request, response);
		}
		if(action.equals("index")){ 
			
			List<Evaluation> list = new ArrayList<Evaluation>();
			list = sess.createCriteria(Evaluation.class).list();
			request.setAttribute("evals", list);
			request.getRequestDispatcher("./admin/admin_eval_mgmt.jsp").forward(request, response);
		}

		if(action.equals("newEval")){
			int q = Integer.parseInt(request.getParameter("quality"));
			int s = Integer.parseInt(request.getParameter("subject"));
			int d = Integer.parseInt(request.getParameter("desire"));
			int ra = Integer.parseInt(request.getParameter("read_author"));
			int r = Integer.parseInt(request.getParameter("recommend"));
			double note= (double) (q+s+d+ra+r)/5;
			
			Evaluation eval=new Evaluation();
			eval.setBook(request.getParameter("book"));
			eval.setUser( u.getEmail());
			eval.setQuality(q);
			eval.setSubject(s);
			eval.setDesire(d);
			eval.setReadAuthor(ra);
			eval.setRecommend(r);
			eval.setScore(note);
			if(request.getParameter("bouton").equals("Enregistrer")){
				eval.setIsvalidated(false);
			} else {
				eval.setIsvalidated(true);
			}
		
			sess.beginTransaction();
			sess.saveOrUpdate(eval);
			sess.getTransaction().commit();
			
			sess.close();
			
			request.getRequestDispatcher("EvalManager?action=search&isbn=").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
