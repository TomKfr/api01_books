<!DOCTYPE html>
<%@page import="java.util.Iterator"%>
<%@page import="com.books.model.Evaluation"%>
<%@page import="java.util.List"%>
<%@page import="com.books.model.User"%>
<html lang="fr">
  <head>
    <title>MatchBooks - Accueil administrateur</title>
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
				<a class="navbar-brand" href="LoginController?action=login">MatchBooks</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="UserManager?action=index">Gestion des utilisateurs</a></li>
				<li><a href="BookMgmt?action=index">Gestion des livres</a></li>
				<li class="active"><a href="EvalManager?action=index">Gestion des évaluations</a></li>
				<li><a href="MatchManager?action=index">Gestion des Matches</a></li>
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
	${result }
	<div class="container">
		<div class="col-lg-12">
			<h3>Rechercher par utilisateur</h3>
			<form method="get" action="EvalManager">
				<div class="form-group">
				Email : <input type="text" class="formy-control" name="email" placeholder="email"/>
				<input type="hidden" name="action" value="filter">
				<input class="btn btn-primary" type="submit"/>
				</div>
			</form>
		</div>
		<div class="col-lg-12">
			<table class="table">
				<tr><th>Utilisateur</th><th>Livre</th><th>Qualité</th><th>Intérêt pour le sujet</th><th>Désir de continuer</th><th>Lire du même auteur</th><th>Recommander à un ami</th><th>Score</th><th>Validée</th><th>Action</th></tr>
				<%
					if(list!=null){
						while(it.hasNext()){
							Evaluation evl = it.next();
							out.println("<tr><td align=center>"+evl.getUser()+"</td><td align=center>"+evl.getBook()+"</td><td align=center>"+evl.getQuality()+"</td><td align=center>"+evl.getSubject()+"</td><td align=center>"+evl.getDesire()+"</td><td align=center>"+evl.getReadAuthor()+"</td><td align=center>"+evl.getRecommend()+"</td><td align=center>"+evl.getScore()+"</td><td align=center>"+evl.getIsvalidated()+"</td>");
							out.println("<td><a href='MatchManager?action=update&user="+evl.getUser()+"&book="+evl.getBook()+"&eval="+evl.getNum()+"' class='btn btn-primary'>Update Matches</a>  <a href='EvalManager?action=delete&num="+evl.getNum()+"' class='btn btn-danger'>Supprimer</a></td></tr>");
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