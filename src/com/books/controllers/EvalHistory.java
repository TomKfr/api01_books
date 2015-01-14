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

import com.books.model.Evaluation;
import com.books.model.User;
import com.books.utilities.HibernateUtil;

/**
 * Servlet implementation class EvalHistory
 * menu qui gère l'historique des évaluations
 */
@WebServlet("/EvalHistory")
public class EvalHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvalHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action").equals("seeHistory")) {
			Integer nbpages = (Integer) request.getAttribute("nbpages"); 
			Integer  pagination = Integer.parseInt(request.getParameter("page"));
			User u = (User) request.getSession().getAttribute("user");
			if(pagination==null) pagination = 2;
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			List<Evaluation> toDo=null;
			List<Evaluation> res = null;
			try{
				/*res = sess.createQuery("select eval.num, eval.user, book.titre, eval.quality, eval.subject, eval.desire, eval.read_author, eval.recommend, eval.score, eval.isvalidated from com.books.model.Evaluation as eval, com.books.model.User as user, com.books.model.Books as book where eval.user=user.email and book.isbn=eval.book and eval.isvalidated= 1")
						.list();*/
				res=sess.createQuery("select eval from com.books.model.Evaluation as eval, com.books.model.User as user where user.email=eval.user and eval.isvalidated=1").list();
				toDo=sess.createQuery("select noteval from com.books.model.Evaluation as noteval, com.books.model.User as user where user.email=noteval.user and noteval.isvalidated=0").list();}
			catch (HibernateException he){
				System.out.println("echec récupération des évaluations "+ he);
			}
			
			if(nbpages==null) nbpages = Math.round(res.size()/20);
			request.setAttribute("nbpages", nbpages);
			request.setAttribute("page", pagination);
			System.out.println("num page : "+pagination);
			
			if(!res.isEmpty() || !toDo.isEmpty()){
				if(pagination!=nbpages){
					if(!res.isEmpty())
						request.setAttribute("eval", res.subList(pagination*20, pagination*20+19));
					if(!toDo.isEmpty())
						request.setAttribute("noteval", toDo.subList(pagination*20, pagination*20+19));
				}
				else{
					System.out.println("blop");
					if(!res.isEmpty())
						request.setAttribute("eval", res.subList(pagination*20, res.size()-1));
					if(!toDo.isEmpty())
						request.setAttribute("noteval", toDo.subList(pagination*20, toDo.size()-1));
				}
				request.getRequestDispatcher("user/reader_history_eval.jsp").forward(request, response);
			}
			else{
				System.out.println("pas d'évaluations");
				request.getRequestDispatcher("user/reader_books.jsp").forward(request, response);
			}
			
			sess.close();
		}
		if(request.getParameter("action").equals("submitEval")) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
