package com.books.utilities;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.books.model.Evaluation;
/**
 * classe qui implémente l'interface MatchingAlgo et qui permet de faire des matches de manière aléatoire
 * @author Morgane et Thomas
 *
 */
public class MatchRandom implements MatchingAlgo {
	private String user; /** l'utilisateur qui a demandé le match */
	private String book; /** le livre concerné par le match */
	private String closestuser; /** l'utilisateur le plus proche selon l'algo */
	private String farthestuser; /** l'utilisateur le plus éloigné selon l'algo */
	
	/**
	 * constructeur sans argument 
	 */
	public MatchRandom() {
		this.user=null;
		this.book=null;
		this.closestuser=null;
		this.farthestuser=null;
	}
	/**
	 * l'algorithme qui associe les utilisateurs de manière aléatoire
	 * @param user
	 * @param eval
	 */
	private void calculate(String user, Evaluation eval){
		
		this.user = user;
		this.book = eval.getBook();
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		List<Evaluation> list = sess.createCriteria(Evaluation.class).add(Restrictions.eq("book", this.book)).add(Restrictions.not(Restrictions.eq("user", user))).list();
		if(list.size()>0){
			
			double x = Math.random()*(list.size()-1);
			int index = (int) Math.floor(x);
			
			this.closestuser = list.get(index).getUser();
			if(list.size()>1){
				list.remove(index);
				
				x = Math.random()*(list.size()-1);
				index = (int) Math.floor(x);
				
				this.farthestuser = list.get(index).getUser();
			}
			else this.farthestuser=this.closestuser;
		}
	}

	@Override
	
	public String getClosestUser(String user, Evaluation eval) {
		
		System.out.println("getting closest user randomly ...");
		
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
