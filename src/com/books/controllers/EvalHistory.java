package com.books.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.books.model.Books;
import com.books.model.Evaluation;
import com.books.model.User;
import com.books.utilities.HibernateUtil;

/**
 * Servlet implementation class EvalHistory
 * historique et reprise d'une évaluation
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
		User usr = (User) request.getSession().getAttribute("user");
		/**
		 * affichage de l'historique des évaluations terminées et en cours
		 */
		if(request.getParameter("action").equals("seeHistory")) {
			Integer nbpages = (Integer) request.getAttribute("nbpages"); 
			Integer  pagination = Integer.parseInt(request.getParameter("page"));
			User u = (User) request.getSession().getAttribute("user");
			if(pagination==null) pagination = 1;
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			List<Evaluation> toDo=null;
			List<Evaluation> res = null;
			String email=u.getEmail();
			try{
				/*res = sess.createQuery("select eval.num, eval.user, book.titre, eval.quality, eval.subject, eval.desire, eval.read_author, eval.recommend, eval.score, eval.isvalidated from com.books.model.Evaluation as eval, com.books.model.User as user, com.books.model.Books as book where eval.user=user.email and book.isbn=eval.book and eval.isvalidated= 1")
						.list();*/
				res=sess.createCriteria(Evaluation.class).add(Restrictions.and(Restrictions.eq("user", email),Restrictions.eq("isvalidated", true))).list();
				toDo=sess.createCriteria(Evaluation.class).add(Restrictions.and(Restrictions.eq("user", email),Restrictions.eq("isvalidated", false))).list();
			}catch (HibernateException he){
				System.out.println("echec récupération des évaluations "+ he);
			}
			
			//Liste d'association isbn - titre
			Map<String,String> titres = new HashMap<String,String>();
			//récupération des titres :
			for(Evaluation e : res){
				Books bk = (Books) sess.get(Books.class, e.getBook());
				titres.put(bk.getIsbn(), bk.getTitre());
			}
			for(Evaluation e : toDo){
				Books bk = (Books) sess.get(Books.class, e.getBook());
				titres.put(bk.getIsbn(), bk.getTitre());
			}
			
			request.setAttribute("titres", titres);
			
			if(nbpages==null) nbpages = Math.round(res.size()/20);
			request.setAttribute("nbpages", nbpages);
			request.setAttribute("page", pagination);
			System.out.println("num page : "+pagination);
			
			if(!res.isEmpty() || !toDo.isEmpty()){
				if(!res.isEmpty())
					request.setAttribute("eval", res.subList((pagination-1)*20, Math.min(pagination*20, res.size())));
				if(!toDo.isEmpty())
					request.setAttribute("noteval", toDo.subList((pagination-1)*20, Math.min(pagination*20, toDo.size())));
				request.getRequestDispatcher("user/reader_history_eval.jsp").forward(request, response);
			}
			else{
				System.out.println("pas d'évaluations");
				request.getRequestDispatcher("user/EvalHistory?action=seeHistory&page=1").forward(request, response);
			}
			
			sess.close();
		}
		/**
		 * reprise d'une évaluation enregistrée
		 */
		if(request.getParameter("action").equals("submitEval")) {
			User u = (User) request.getSession().getAttribute("user");
			Session sess = HibernateUtil.getSessionFactory().openSession();
			sess.beginTransaction();
			int num = Integer.parseInt(request.getParameter("numeval"));
			
			Evaluation e = (Evaluation) sess.get(Evaluation.class, num);
			request.setAttribute("user",  u);
			request.setAttribute("modifeval", e);
			
			request.getRequestDispatcher("user/reader_save_eval.jsp").forward(request,  response);
		}
		/**
		 * enregistrement définitif d'une évaluation après reprise
		 */
		if(request.getParameter("action").equals("send")) {
		
			Session sess = HibernateUtil.getSessionFactory().openSession();
			int q = Integer.parseInt(request.getParameter("quality"));
			int s = Integer.parseInt(request.getParameter("subject"));
			int d = Integer.parseInt(request.getParameter("desire"));
			int ra = Integer.parseInt(request.getParameter("read_author"));
			int r = Integer.parseInt(request.getParameter("recommend"));
			double note= (double) (q+s+d+ra+r)/5;
			
			Evaluation eval=new Evaluation();
			int num= Integer.parseInt(request.getParameter("num"));
			eval.setNum(num);
			eval.setBook(request.getParameter("book"));
			eval.setUser(request.getParameter("user"));
			eval.setQuality(q);
			eval.setSubject(s);
			eval.setDesire(d);
			eval.setReadAuthor(ra);
			eval.setRecommend(r);
			eval.setScore(note);
			eval.setIsvalidated(true);
			
		
			sess.beginTransaction();
			sess.saveOrUpdate(eval);
			sess.getTransaction().commit();
			
			sess.close();
			
			request.getRequestDispatcher("EvalHistory?action=seeHistory&page=1").forward(request, response);
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
