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
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-table.css" rel="stylesheet">
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
		<!-- <div class="col-lg-12">
			<h3>Rechercher par utilisateur</h3>
			<form method="get" action="EvalManager">
				<div class="form-group">
				Email : <input type="text" class="formy-control" name="email" placeholder="email"/>
				<input type="hidden" name="action" value="filter">
				<input class="btn btn-primary" type="submit"/>
				</div>
			</form>
		</div> -->
		<div class="col-lg-12">
			<table class='table' data-toggle='table' data-striped='true' data-pagination='true' data-page-size='5' data-search='true'>
				<thead><tr><th data-align="center" data-sortable='true'>Utilisateur</th><th data-align="center" data-sortable='true'>Livre</th><th data-align="center" data-sortable='true'>Qualité</th><th data-align="center" data-sortable='true'>Intérêt<br>pour le sujet</th><th data-align="center" data-sortable='true'>Désir de continuer</th><th data-align="center" data-sortable='true'>Lire du même auteur</th><th data-align="center" data-sortable='true'>Recommander<br>à un ami</th><th data-align="center" data-sortable='true'>Score</th><th data-align="center" data-sortable='true'>Validée</th><th data-align="center">Action</th></tr></thead>
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
	
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table-fr-FR.js"></script>
  </body>
</html>