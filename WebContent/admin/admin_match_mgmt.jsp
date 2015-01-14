<!DOCTYPE html>
<%@page import="java.util.Iterator"%>
<%@page import="com.books.model.Tmatch"%>
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
  	List<Tmatch> list = (List<Tmatch>) request.getAttribute("matches"); 
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=login">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="UserManager?action=index">User Management</a></li>
				<li><a href="BookMgmt?action=index">Book Management</a></li>
				<li><a href="EvalManager?action=index">Evaluation Management</a></li>
				<li class="active"><a href="MatchManager?action=index">Match Management</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LoginController?action=logout">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Gestion des associations</h1>
	</header>
	</div>
	<div class="container">
		<div class="control-group">
			<form class="form-horizontal" method="get" action="MatchManager">
				<label id="label-1">Choisir l'algorithme d'association :</label>
				<select class="form-control" name="matchalgo">
					<option value="by score" <%=request.getSession().getAttribute("matchalgo")=="by score"?"selected":"" %>>By score</option>
				</select>
				<input type="hidden" name="action" value="matchalgo"/>
			</form>
		</div>
		<div class="col-lg-12">
			<h2>Liste des associations :</h2>
		</div>
		<div class="col-lg-12">
			<table class="table">
				<tr><th>Utilisateur</th><th>Utilisateur le plus proche</th><th>Utilisateur le plus éloigné</th><th>Livre</th><th>Action</th></tr>
				<%
					if(list!=null){
						Iterator<Tmatch> it = list.iterator();
						while(it.hasNext()){
							Tmatch m = it.next();
							%>
							<tr><td><%=m.getUser() %></td><td><%=m.getClosestuser() %></td><td><%=m.getFarthestuser() %></td><td><%=m.getBook() %></td><td><a href="" class="btn btn-danger">Supprimer</a></td></tr>
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