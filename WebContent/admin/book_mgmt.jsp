<%@page import="java.util.Iterator"%>
<%@page import="com.books.model.Books"%>
<%@page import="java.util.List"%>
<%@page import="com.books.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <title>Monsite - Accueil admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
  </head>
  <body>
  <%
  	User u = (User) request.getSession().getAttribute("user");
  
	Books b = (Books) request.getAttribute("modifbook");
	%>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=login">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="UserManager?action=index">User Management</a></li>
				<li class="active"><a href="BookMgmt?action=index">Book Management</a></li>
				<li><a href="EvalManager?action=index">Evaluation Management</a></li>
				<li><a href="MatchManager?action=index">Match Management</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Gestion des livres </h1>
	</header>
	</div>
		<div class="col-lg-6">
			<div class="col-lg-12">
				<h2>Ajouter un livre</h2>
				<form method="get" action="BookMgmt" class="col-lg-offset-4 col-lg-6">
					<div class="form-group">
						<label for="isbn">ISBN</label>
						<input type="text" class="form-control" id="isbn" placeholder="ISBN" name="isbn" <%=b!=null?"value='"+b.getIsbn()+"' disabled":"" %>>
					</div>
					<div class="form-group">
						<label for="titre">Titre</label>
						<input type="text" class="form-control" id="titre" placeholder="Titre" name="titre" <%=b!=null?"value='"+b.getTitre()+"'":"" %>>
					</div>
					<div class="form-group">
						<label for="genre">Genre</label>
						<input type="text" class="form-control" id="genre" placeholder="Genre" name="genre" <%=b!=null?"value='"+b.getGenre()+"'":"" %>>
					</div>
					<div class="form-group">
						<label for="auteur">Auteur</label>
						<input type="text" class="form-control" id="auteur" placeholder="Auteur" name="auteur" <%=b!=null?"value='"+b.getAuteur()+"'":"" %>>
					</div>
					<input type="hidden" name="action" value="add"/>
					<%=b!=null?"<input type='hidden' name='isbn' value='"+b.getIsbn()+"'/>":"" %>
					<div class="col-lg-3">
						<input type="submit" class="btn btn-primary" value="Enregistrer">
					</div>	
				</form>
			</div>
			<div class="col-lg-12">
				<h2>Rechercher des livres</h2>
				<form method="get" action="BookMgmt" class="col-lg-offset-4 col-lg-6">
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
					if(request.getAttribute("exec")=="success"){
						out.println("<div class='col-lg-6'><br><br><br><br><br><br></div><div class='col-lg-8  alert alert-success' role='alert'>Ajout/modification OK !</div>");
					}
				%>
			</div>
			<div class="col-lg-12">
				<%
					List<Books> list = (List<Books>) request.getAttribute("search");
					if(list!=null && !list.isEmpty()){
						out.println("<div class='col-lg-12'><br></div><h4>Résultats</h4>");
						out.println("<table class='table'><tr><th>ISBN</th><th>Titre</th><th>Auteur</th><th>Genre</th><th>Action</th></tr>");
						Iterator<Books> it = list.iterator();
						while(it.hasNext()){
							Books bk = (Books) it.next();
							out.println("<tr><td>"+bk.getIsbn()+"</td><td>"+bk.getTitre()+"</td><td>"+bk.getAuteur()+"</td><td>"+bk.getGenre()+"</td>");
							out.println("<td align='center'><a class='btn btn-info' href='BookMgmt?action=modify&book="+bk.getIsbn()+"'>Modifier</a><a class='btn btn-danger' href='BookMgmt?action=delete&book="+bk.getIsbn()+"'>Supprimer</a>");
							out.println("<a class='btn btn-success' href='EvalManager?action=index&isbn="+bk.getIsbn()+"'>Voir Evals</a></td></tr>");
						}
						out.println("</table>");
					}
					
				%>
			</div>
		</div>
		
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
