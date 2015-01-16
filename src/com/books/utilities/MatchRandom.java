package com.books.utilities;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.books.model.Evaluation;

public class MatchRandom implements MatchingAlgo {
	private String user;
	private String book;
	private String closestuser;
	private String farthestuser;
	
	public MatchRandom() {
		this.user=null;
		this.book=null;
		this.closestuser=null;
		this.farthestuser=null;
	}
	
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
