package com.books.model;

// Generated 7 janv. 2015 16:18:41 by Hibernate Tools 3.4.0.CR1

/**
 * Evaluation est la classe qui représente les évaluations saisies par les utilisateurs pour les livres qu'ils ont lus (JavaBean)
 * @author Morgane et Thomas
 */
public class Evaluation implements java.io.Serializable {

	private Integer num; /** numéro de l'évaluation (identifiant unique) */
	private String user; /**utilisateur qui a saisi l'évaluation*/
	private String book; /**livre évalué*/
	private Integer quality; /**qualité de l'écriture*/
	private Integer subject; /**intérêt pour le sujet*/
	private Integer desire; /**désir de poursuivre la lecture*/
	private Integer readAuthor; /**volonté d elire un livre du même auteur*/
	private Integer recommend; /** recommander le livre à un ami*/
	private Double score; /**note globale*/
	private Boolean isvalidated; /**indique si l'évaluation est terminée ou non*/

	/** constructeur sans argument de la classe **/
	public Evaluation() {
	}
	/**
	 * constructeur avec tous les arguments qui définissent un objet de type Evaluation
	 * @param user
	 * @param book
	 * @param quality
	 * @param subject
	 * @param desire
	 * @param readAuthor
	 * @param recommend
	 * @param score
	 * @param isvalidated
	 */
	public Evaluation(String user, String book, Integer quality,
			Integer subject, Integer desire, Integer readAuthor,
			Integer recommend, Double score, Boolean isvalidated) {
		this.user = user;
		this.book = book;
		this.quality = quality;
		this.subject = subject;
		this.desire = desire;
		this.readAuthor = readAuthor;
		this.recommend = recommend;
		this.score = score;
		this.isvalidated = isvalidated;
	}
	/** 
	 * accesseur en lecture à l'attribut num
	 * @return la valeur de l'attribut num de l'objet appelant
	 */
	public Integer getNum() {
		return this.num;
	}
	/**
	 * accesseur en écriture à l'attribut num
	 * @param num
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * accesseur en lecture à l'attribut user
	 * @return la valeur de l'attribut user de l'objet appelant
	 */
	public String getUser() {
		return this.user;
	}
	/**
	 * accesseur en écriture à l'attribut user
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * accesseur en lecture à l'attribut book
	 * @return la valeur de l'attribut book de l'objet appelant
	 */
	public String getBook() {
		return this.book;
	}
	/**
	 * accesseur en écriture à l'attribut book
	 * @param book
	 */
	public void setBook(String book) {
		this.book = book;
	}
	/**
	 * accesseur en lecture à l'attribut quality
	 * @return la valeur de l'attribut quality de l'objet appelant
	 */
	public Integer getQuality() {
		return this.quality;
	}
	/**
	 * accesseur en écriture à l'attribut quality
	 * @param quality
	 */
	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	/**
	 * accesseur en lecture à l'attribut subject
	 * @return la valeur de l'attribut subject de l'objet appelant
	 */
	public Integer getSubject() {
		return this.subject;
	}
	/**
	 * accesseur en écriture à l'attribut subject
	 * @param subject
	 */
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	/**
	 * accesseur en lecture à l'attribut desire
	 * @return la valeur de l'attribut desire d el'objet appelant
	 */
	public Integer getDesire() {
		return this.desire;
	}
	/**
	 * accesseur en écriture à l'attribut desire
	 * @param desire
	 */
	public void setDesire(Integer desire) {
		this.desire = desire;
	}
	/**
	 * accesseur en lecture à l'attribut read_author
	 * @return la valeur de l'attribut read_author de l'objet appelant
	 */
	public Integer getReadAuthor() {
		return this.readAuthor;
	}
	/**
	 * accesseur en écriture à l'attribut read_author
	 * @param readAuthor
	 */
	public void setReadAuthor(Integer readAuthor) {
		this.readAuthor = readAuthor;
	}
	/**
	 * accesseur en lecture à l'attribut recommend
	 * @return la valeur de l'attribut recommend de l'objet appelant
	 */
	public Integer getRecommend() {
		return this.recommend;
	}
	/**
	 * accesseur en écriture à l'attribut recommend
	 * @param recommend
	 */
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	/**
	 * accesseur en lecture à l'attribut score
	 * @return la valeur de l'attribut score d el'objet appelant
	 */
	public Double getScore() {
		return this.score;
	}
	/**
	 * accesseur en écriture à l'attribut score
	 * @param score
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	/**
	 * accesseur en lecture à l'attribut isvalidated
	 * @return valeur de l'attribut isvalidated de l'objet appelant
	 */
	public Boolean getIsvalidated() {
		return this.isvalidated;
	}
	/**
	 * accesseur en écriture à l'attribut isvalidated
	 * @param isvalidated
	 */
	public void setIsvalidated(Boolean isvalidated) {
		this.isvalidated = isvalidated;
	}

}
