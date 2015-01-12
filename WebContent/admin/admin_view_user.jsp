<!DOCTYPE html>
<%@page import="java.util.Iterator"%>
<%@page import="com.books.model.Evaluation"%>
<%@page import="java.util.List"%>
<%@page import="com.books.model.User"%>
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
  	User vieweduser = (User) request.getAttribute("vieweduser");
  	List<Evaluation> list = (List<Evaluation>) request.getAttribute("evalsuser");
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=login">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="UserManager?action=index">User Management</a></li>
				<li><a href="BookMgmt?action=index">Book Management</a></li>
				<li><a href="EvalManager?action=index">Evaluation Management</a></li>
				<li><a href="MatchManager?action=index">Match Management</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LoginController?action=logout">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
		<header class="page-header">
			<h1> Informations de  : <%=vieweduser.getName() %> </h1>
		</header>
		<div class="col-lg-12">
			<div class="col-lg-12">
				<table class="table">
					<tr><th>Email</th><th>Nom</th><th>Mot de passe</th><th>Adresse</th><th>Téléphone</th><th>Date de création</th><th>Statut du compte</th><th>Administrateur</th></tr>
					<tr><td><%=vieweduser.getEmail() %></td><td><%=vieweduser.getName() %></td><td><%=vieweduser.getPwd() %></td><td><%=vieweduser.getAddress() %></td><td><%=vieweduser.getTel() %></td><td><%=vieweduser.getCreationDate() %></td><td><%=vieweduser.getAccountStatus() %></td><td><%=vieweduser.getIsAdmin() %></td></tr>
				</table>
			</div>
			<div class="col-lg-12">
				<h2>Livres évalués</h2>
				<table class="table">
				<%
					if(list!=null){
						Iterator<Evaluation> it = list.iterator();
						out.println("<tr><th>Utilisateur</th><th>Livre</th><th>Qualité</th><th>Intérêt sujet</th><th>Désir continuer</th><th>Lire auteur</th><th>Recommender</th><th>Score</th></tr>");
						while(it.hasNext()){
							Evaluation e = it.next();
							%>
							<tr><td><%=e.getUser() %></td><td><%=e.getBook() %></td><td><%=e.getQuality() %></td><td><%=e.getSubject() %></td><td><%=e.getDesire() %></td><td><%=e.getReadAuthor() %></td><td><%=e.getRecommend() %></td><td><%=e.getScore() %></td></tr>
							<%
						}
					}
					else{
						out.println("<tr><td>Pas d'evals</td></tr>");
					}
				%>
				</table>
			</div>
			
			<div class="col-lg-12">
				<h2>Match</h2>
			</div>
		</div>
	</div>
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>