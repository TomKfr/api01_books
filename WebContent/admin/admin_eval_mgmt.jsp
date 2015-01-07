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
  	List<Evaluation> list = (List<Evaluation>) request.getAttribute("evals");
  	Iterator<Evaluation> it = null;
  	if(list!=null){
  		it = list.iterator();
  	}

  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=login">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="UserManager?action=index">User Management</a></li>
				<li><a href="BookMgmt?action=index">Book Management</a></li>
				<li class="active"><a href="EvalManager?action=index">Evaluation Management</a></li>
				<li><a href="MatchManager?action=index">Match Management</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LoginController?action=logout">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Gestion des évaluations </h1>
	</header>
	<div class="container">
		<div class="col-lg-12">
			<table class="table">
				<tr><th>Utilisateur</th><th>Livre</th><th>Qualité</th><th>Intérêt sujet</th><th>Désir continuer</th><th>Lire auteur</th><th>Recommender</th><th>Score</th><th>Validée</th></tr>
				<%
					if(list!=null){
						while(it.hasNext()){
							out.println("<tr><td>"+it.next().getUser()+"</td><td>"+it.next().getBook()+"</td><td>"+it.next().getQuality()+"</td><td>"+it.next().getSubject()+"</td><td>"+it.next().getDesire()+"</td><td>"+it.next().getReadAuthor()+"</td><td>"+it.next().getRecommend()+"</td><td>"+it.next().getScore()+"</td><td>"+it.next().getIsvalidated()+"</td><td></tr>");
						}
					}
					else{
						out.println("<tr><td>Pas d'évaluation enregistrée</td></tr>");
					}
				%>
			</table>
		</div>
	</div>
	
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>