<!DOCTYPE html>
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
				<li><a href="MatchManager?action=index">Match Management</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LoginController?action=logout">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Bonjour, <%=u.getName()%> </h1>
	</header>
	</div>
	<div class="col-lg-offset-3 col-lg-8">
	<p>email : <%=u.getEmail()%></p>
	<p>Adresse : <%=u.getAddress()%></p>
	<p>Password : <%=u.getPwd()%></p>
	<p>Role : <%=u.getIsAdmin()?"Admin":"User"%></p>
	</div>
	
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>