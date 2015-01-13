package com.books.model;

// Generated 7 janv. 2015 16:18:41 by Hibernate Tools 3.4.0.CR1

/**
 * Tmatch est la classe qui repr�sente un match entre 2 utilisateurs pour un livre particulier
 * @author Morgane et Thomas
 */
public class Tmatch implements java.io.Serializable {

	private Integer num; /** num�ro du match (identifiant unique) */
	private String user1; /** premier utilisateur concern� par le match */
	private String user2; /** deuxi�me utilisateur concern� par le match */
	private String book; /** livre concern� par le match */
	private Boolean closest; /** indique s'il s'agit du match le plus �loign� ou le plus proche */

	/**
	 * constructeur sans argument
	 */
	public Tmatch() {
	}
	/**
	 * constructeur qui reprend tous els arguments qui d�finissent un objet de type Tmatch
	 * @param user1
	 * @param user2
	 * @param book
	 * @param closest
	 */
	public Tmatch(String user1, String user2, String book, Boolean closest) {
		this.user1 = user1;
		this.user2 = user2;
		this.book = book;
		this.closest = closest;
	}
	/**
	 * accesseur en lecture � l'attribut num
	 * @return la valeur de l'attribut num de l'objet appelant
	 */
	public Integer getNum() {
		return this.num;
	}
	/**
	 * accesseur en �criture � l'attribut num
	 * @param num
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * accesseur en lecture � l'attribut user1
	 * @return la valeur de l'attribut user1 de l'objet appelant
	 */
	public String getUser1() {
		return this.user1;
	}
	/**
	 * accesseur en �criture � l'attribut user1
	 * @param user1
	 */
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	/**
	 * accesseur en lecture � l'attribut user2
	 * @return la valeur de l'attribut user2 de l'objet appelant
	 */
	public String getUser2() {
		return this.user2;
	}
	/**
	 * accesseur en �criture � l'attribut user2
	 * @param user2
	 */
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	/**
	 * accesseur en lecture � l'attribut book
	 * @return la valeur de l'attribut book de l'aobjet appelant
	 */
	public String getBook() {
		return this.book;
	}
	/**
	 * accesseur en �criture � l'attribut book
	 * @param book
	 */
	public void setBook(String book) {
		this.book = book;
	}
	/**
	 * accesseur en lecture � l'attribut closest
	 * @return la valeur de 'lattribut closest de l'objet appelant
	 */
	public Boolean getClosest() {
		return this.closest;
	}
	/**
	 * accesseur en �criture � l'attribut closest
	 * @param closest
	 */
	public void setClosest(Boolean closest) {
		this.closest = closest;
	}

}
