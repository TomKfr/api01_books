package com.books.utilities;

import com.books.model.Evaluation;

public interface MatchingAlgo {
	
	public String getClosestUser(String user, Evaluation eval);
	
	public String getFarthestUser(String user, Evaluation eval);
	
}
