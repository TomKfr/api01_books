package com.books.model;

// Generated 17 déc. 2014 18:17:49 by Hibernate Tools 3.4.0.CR1

/**
 * Books est la classe représentant les livres (JavaBean)
 * @author Morgane et Thomas
 * 
 */
public class Books implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7345401874970693833L;
	private String isbn; /** numéro isbn (identifiant unique) */
	private String titre; /** titre */
	private String auteur; /**auteur */
	private String genre; /**genre  */

	/**constructeur sans argument  */
	public Books() {
	}

	/**
	 * Constructeur avec l'argument isbn qui permet d'identifier un livre de manière unique
	 * @param isbn
	 */
	public Books(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Constructeur avec tous les arguments qui définissent un objet de type Book
	 * @param isbn
	 * @param titre
	 * @param auteur
	 * @param genre
	 */
	public Books(String isbn, String titre, String auteur, String genre) {
		this.isbn = isbn;
		this.titre = titre;
		this.auteur = auteur;
		this.genre = genre;
	}

	/**
	 * accesseur en lecture de l'attribut isbn 
	 * @return la valeur de l'attribut isbn de l'objet appelant la méthode
	 */
	public String getIsbn() {
		return this.isbn;
	}
	/**
	 * accesseur en écriture de l'attribut isbn 
	 * @param isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/** 
	 * accesseur en lecture de l'attribut titre 
	 * @return la valeur de l'attribut titre de l'objet appelant
	 */
	public String getTitre() {
		return this.titre;
	}
	/**
	 * accesseur en écriture de l'attribut titre 
	 * @param titre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
/**
 * accesseur en lecture à l'attribut auteur 
 * @return la valeur de l'attribut auteur de l'objet appelant 
 */
	public String getAuteur() {
		return this.auteur;
	}
	/**
	 * accesseur en écriture de l'attribut auteur
	 * @param auteur
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
/**
 * accesseur en lecture à l'attribut genre
 * @return la valeur de l'attribut genre de l'objet appelant
 */
	public String getGenre() {
		return this.genre;
	}
/**
 * accesseur en écriture de l'attribut genre
 * @param genre
 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

}
