package com.books.utilities;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.books.model.Evaluation;
/**
 * classe qui implémente l'interface MatchingAlgo et qui permet de faire des matches en étudiant les scores attribués aux livres
 * @author Morgane et Thomas
 *
 */
public class MatchByScore implements MatchingAlgo {
	
	private String user; /** utilisateur qui a demandé le match */
	private String book; /** livre concerné par le match */
	private String closestuser; /** utilisateur dont le score est le plus proche */
	private String farthestuser; /** utilisateur dont le score ets le plus éloigné */
	/**
	 * constructeur sans argument 
	 */
	public MatchByScore() {
		this.user=null;
		this.book=null;
		this.closestuser=null;
		this.farthestuser=null;
	}
	/**
	 * l'algorithme qui étudie le score attribué par un utilisateur à un livre et recherche les utilisateurs qui ont mis le score le + proche et le + éloigné pour ce même livre (en valeur absolue)
	 * @param user
	 * @param eval
	 */
	private void calculate(String user, Evaluation eval){
		
		this.user = user;
		this.book = eval.getBook();
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		List<Evaluation> list = sess.createCriteria(Evaluation.class).add(Restrictions.eq("book", this.book)).add(Restrictions.not(Restrictions.eq("user", user))).list();
		if(list.size()>0){
			Double score = eval.getScore();
			Iterator<Evaluation> it = list.iterator();
			Evaluation e;
			Double c_eloign = (double) 1000;
			Double f_eloign = (double) 0;
			do{
				e = it.next();
				System.out.println("eval : "+e.getNum()+" user : "+e.getUser());
				if(c_eloign>Math.abs(eval.getScore()-e.getScore())){
					this.closestuser = e.getUser();
					c_eloign = Math.abs(eval.getScore()-e.getScore());
				}
				if(f_eloign<Math.abs(eval.getScore()-e.getScore())){
					this.farthestuser = e.getUser();
					f_eloign = Math.abs(eval.getScore()-e.getScore());
				}
			}while(it.hasNext());
		}
	}

	@Override
	public String getClosestUser(String user, Evaluation eval) {
		
		System.out.println("getting closest user by score ...");
		
		if(this.user!=user || this.book!=eval.getBook()){
			this.calculate(user, eval);
		}
		
		return this.closestuser;
	}

	@Override
	public String getFarthestUser(String user, Evaluation eval) {
		
		if(this.user!=user || this.book!=eval.getBook()){
			this.calculate(user, eval);
		}
		
		return this.farthestuser;
	}

}
