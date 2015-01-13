package com.books.model;

// Generated 17 d�c. 2014 18:17:49 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Users est la classe qui repr�sente les utilisateurs de l'application (JavaBean)
 * @author Morgane et Thomas
 */
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3183763185477386593L;
	private String email; /** adresse email de l'utilisateur (identifiant unique) */
	private String name; /** nom de l'utilisateur */
	private String pwd; /** mot de passe du compte */
	private String address; /** adresse postale de l'utilisateur */
	private String tel; /** num�ro de t�l�phone de l'utilisateur */
	private Date creationDate; /** date de cr�ation du compte */
	private Boolean accountStatus; /** statut du compte: actif ou inactif */
	private Boolean isAdmin; /** indique si l'utilisateur est administrateur ou non */

	/**
	 * constructeur sans argument
	 */
	public User() {
	}

	/**
	 * constructeur avec un argument, l'identifiant unique d el'utilisateur
	 * @param email
	 */
	public User(String email) {
		this.email = email;
	}

	/**
	 * constructeur qui reprend l'ensemble des arguments qui d�finissent un objet de type User
	 * @param email
	 * @param name
	 * @param pwd
	 * @param address
	 * @param tel
	 * @param creationDate
	 * @param accountStatus
	 * @param isAdmin
	 */
	public User(String email, String name, String pwd, String address,
			String tel, Date creationDate, Boolean accountStatus,
			Boolean isAdmin) {
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.address = address;
		this.tel = tel;
		this.creationDate = creationDate;
		this.accountStatus = accountStatus;
		this.isAdmin = isAdmin;
	}

	/**
	 * accesseur en lecture � l'attribut email
	 * @return la valeur de l'attribut email de l'objet appelant
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * accesseur en �criture � l'attribut email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * accesseur en lecture � 'lattribut name
	 * @return la valeur de l'attribut name de l'objet appelant
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * accesseur en �criture � l'attribut name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * accesseur en lecture � l'attribut pwd
	 * @return la valeur de l'attribut pwd de l'objet appelant
	 */
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * accesseur en �criture � l'attribut pwd
	 * @param pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * accesseur en lecture � l'attribut address
	 * @return la valeur de l'attribut address de l'objet appelant
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * accesseur en �criture � l'attribut address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * accesseur en lecture � l'attribut tel
	 * @return la valeur de l'attribut tel de l'objet appelant
	 */
	public String getTel() {
		return this.tel;
	}

	/**
	 * accesseur en �criture � l'attribut tel
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * accesseur en lecture � l'attribut creationDate
	 * @return la valeur de l'attribut creationDate de l'objet appelant
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}

	/**
	 * accesseur en �criture � l'attribut creationDate
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * accesseur en lecture � l'attribut accountStatus
	 * @return la valeur de l'attribut accountStatus de l'objet appelant
	 */

	public Boolean getAccountStatus() {
		return this.accountStatus;
	}

	/**
	 * accesseur en �criture � l'attribut accountStatus
	 * @param accountStatus
	 */
	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * accesseur en lecture � l'attribut isAdmin
	 * @return la valeur de l'attribut isAdmin de l'objet appelant
	 */
	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	/**
	 * accesseur en �criture � l'attribut isAdmin
	 * @param isAdmin
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
