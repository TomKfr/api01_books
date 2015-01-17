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
import com.books.utilities.MailUtil;
import com.books.model.Tmatch;
/**
 * Servlet implementation class MatchesHistory
 */
@WebServlet("/MatchesHistory")
public class MatchesHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchesHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action").equals("seeMatches")) {
			Integer nbpages = (Integer) request.getAttribute("nbpages"); 
			Integer  pagination = Integer.parseInt(request.getParameter("page"));
			User u = (User) request.getSession().getAttribute("user");
			if(pagination==null) pagination = 2;
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			String email=u.getEmail();
			List<Tmatch> res=null;
			try{
				/*res = sess.createQuery("select eval.num, eval.user, book.titre, eval.quality, eval.subject, eval.desire, eval.read_author, eval.recommend, eval.score, eval.isvalidated from com.books.model.Evaluation as eval, com.books.model.User as user, com.books.model.Books as book where eval.user=user.email and book.isbn=eval.book and eval.isvalidated= 1")
						.list();*/
				res=sess.createCriteria(Tmatch.class).add(Restrictions.eq("user", email)).list();
			} catch (HibernateException he){
				System.out.println("echec récupération des matchs "+ he);
			}
			
			//Liste d'association isbn - titre
			Map<String,String> titres = new HashMap<String,String>();
			//récupération des titres :
			for(Tmatch e : res){
				Books bk = (Books) sess.get(Books.class, e.getBook());
				titres.put(bk.getIsbn(), bk.getTitre());
			}
			request.setAttribute("titres", titres);
			
			if(nbpages==null) nbpages = Math.round(res.size()/20);
			request.setAttribute("nbpages", nbpages);
			request.setAttribute("page", pagination);
			System.out.println("num page : "+pagination);
			
			if(!res.isEmpty()){
				if(pagination!=nbpages){
						request.setAttribute("match", res.subList(pagination*20, pagination*20+19));
				}
				else{
					System.out.println("blop");
						request.setAttribute("match", res.subList(pagination*20, res.size()-1));
				}
				request.getRequestDispatcher("user/reader_history_matches.jsp").forward(request, response);
			}
			else{
				System.out.println("pas de matches");
				request.getRequestDispatcher("user/reader_history_matches.jsp").forward(request, response);
			}
			
			sess.close();
		}
		else { 
			if(request.getParameter("action").equals("update")) {
				User u = (User) request.getSession().getAttribute("user");
				String message = "Bonjour, l'utilisateur " + u.getName() +" souhaiterait une mise à jour des matches. ";
				MailUtil.sendMessage("Demande de MAJ", message, "tkieffer67@gmail.com", u.getEmail());
				request.getRequestDispatcher("user/reader_history_matches.jsp").forward(request,  response);
			} else {
				request.getRequestDispatcher("user/reader_history_matches.jsp").forward(request,  response);
			}
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
