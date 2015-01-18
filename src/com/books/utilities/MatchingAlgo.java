package com.books.utilities;

import com.books.model.Evaluation;
/**
 * interface qui sera implémentée par les classes MatchingAlgo et MatchRandom dans le cadre d'un design pattern Strategy pour changer dynamiquement d'algorithme
 * @author Morgane
 *
 */
public interface MatchingAlgo {
	/**
	 * trouver l'utilisateur le plus proche
	 * @param user
	 * @param eval
	 * @return l'email de l'utilisateur le plus proche
	 */
	public String getClosestUser(String user, Evaluation eval);
	/**
	 * trouver l'utilisateur le plus éloigné
	 * @param user
	 * @param eval
	 * @return l'email de l'utilisateur le plus éloigné
	 */
	public String getFarthestUser(String user, Evaluation eval);
	
}
