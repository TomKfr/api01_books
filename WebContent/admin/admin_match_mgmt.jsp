<!DOCTYPE html>
<%@page import="java.util.Iterator"%>
<%@page import="com.books.model.Tmatch"%>
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
  	List<Tmatch> list = (List<Tmatch>) request.getAttribute("matches"); 
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=login">MatchBooks</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="UserManager?action=index">Gestion des utilisateurs</a></li>
				<li><a href="BookMgmt?action=index">Gestion des livres</a></li>
				<li><a href="EvalManager?action=index">Gestion des évaluations</a></li>
				<li class="active"><a href="MatchManager?action=index">Gestion des matches</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LoginController?action=logout">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Gestion des matches</h1>
	</header>
	</div>
	<div class="container">
		<div class="control-group">
			<form class="form-horizontal" method="get" action="MatchManager">
				<label id="label-1">Choisir l'algorithme d'association :</label>
				<select class="form-control" name="matchalgo">
					<option value="byscore" <%=request.getSession().getAttribute("matchalgo")=="byscore"?"selected":"" %>>By score</option>
					<option value="random" <%=request.getSession().getAttribute("matchalgo")=="random"?"selected":"" %>>Randomly</option>
				</select>
				<input type="hidden" name="action" value="matchalgo"/>
				<input type="submit" class="btn btn-info"/>
			</form>
		</div>
		<div class="col-lg-11">
			<h2>Liste des associations :</h2>
		</div>
		<div class="col-lg-1">
			<a href="MatchManager?action=updateall" class="btn btn-danger">Tout mettre à jour !</a>
		</div>
		<div class="col-lg-12">
			<table class="table">
				<tr><th align=center>Utilisateur</th><th align=center>Livre</th><th align=center>Utilisateur le plus proche</th><th align=center>Utilisateur le plus éloigné</th><th align=center>Action</th></tr>
				<%
					if(list!=null){
						Iterator<Tmatch> it = list.iterator();
						while(it.hasNext()){
							Tmatch m = it.next();
							%>
							<tr><td align=center><%=m.getUser() %></td><td align=center><%=m.getBook() %></td><td align=center><%=m.getClosestuser() %></td><td align=center><%=m.getFarthestuser() %></td><td align=center><a href="MatchManager?action=delete&num=<%=m.getNum() %>" class="btn btn-danger">Supprimer</a></td></tr>
							<%
						}
					}
				%>
			</table>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>