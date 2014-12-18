<!DOCTYPE html>
<%@page import="com.books.model.User"%>
<html lang="fr">
  <head>
    <title>Monsite - Accueil admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="index.css" rel="stylesheet">
  </head>
  <body>
  <%
  	User u = (User) request.getSession().getAttribute("user");
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="user_management.html">User Management</a></li>
				<li><a href="BookMgmt?action=index">Book Management</a></li>
				<li><a href="evaluation_management.html">Evaluation Management</a></li>
				<li><a href="match_management.html">Match Management</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp">Déconnexion</a></li>
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