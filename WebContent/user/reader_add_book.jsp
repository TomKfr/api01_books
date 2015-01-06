<%@page import="com.books.model.Books"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="com.books.model.User"%>
<html lang="fr">
  <head>
    <title>Monsite - Accueil lecteur</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
  </head>
  <body>
  <%
  	User u = (User) request.getSession().getAttribute("user");
  	List<Books> books = (List<Books>) request.getAttribute("books");
  	Iterator<Books> it=null;
  	if(books != null) it = books.iterator();
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=logout">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li  class="active"><a href="#">Find a book</a></li>
				<li><a href="${pageContext.request.contextPath}/BooksController?action=list&page=0">My books</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LoginController?action=logout">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Ajouter une éval pour <%=u.getName()%> :</h1>
	</header>
	</div>
	<div class="col-lg-12">
		<div class="col-lg-6">
			<div class="col-lg-12">
				<h2>Rechercher des livres</h2>
				<form method="get" action="${pageContext.request.contextPath}/EvalManager" class="col-lg-offset-3 col-lg-6">
					<div class="form-group">
						<label for="isbn">ISBN</label>
						<input type="text" class="form-control" id="isbn" placeholder="ISBN" name="isbn">
					</div>
					<div class="form-group">
						<label for="titre">Titre</label>
						<input type="text" class="form-control" id="titre" placeholder="Titre" name="titre">
					</div>
					<input type="hidden" name="action" value="search"/>
					<div class="col-lg-3">
						<input type="submit" class="btn btn-primary" value="Chercher">
					</div>	
				</form>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="col-lg-12">
				<%
					if(books!=null){
						out.println("<div class='col-lg-12'><br></div><h4>Résultats</h4>");
						out.println("<table class='table'><tr><th>ISBN</th><th>Titre</th><th>Auteur</th><th>Genre</th><th>Action</th></tr>");
						while(it.hasNext()){
							Books bk = (Books) it.next();
							out.println("<tr><td>"+bk.getIsbn()+"</td><td>"+bk.getTitre()+"</td><td>"+bk.getAuteur()+"</td><td>"+bk.getGenre()+"</td>");
							out.println("<td><a class='btn btn-info' href='EvalManager?action=add&isbn="+bk.getIsbn()+"'>Evaluer</a></td></tr>");
						}
						out.println("</table>");
					}
				%>
			</div>
		</div>
	</div>
	
	
	<script src="../bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>