<!DOCTYPE html>
<%@page import="com.books.model.User"%>
<html lang="fr">
  <head>
    <title>Monsite - Accueil lecteur</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="index.css" rel="stylesheet">
  </head>
  <body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="logout.jsp">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="find_books.html">Find a book</a></li>
				<li><a href="reader_books.html">My books</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Bonjour, <%=((User)request.getAttribute("user")).getName()%> </h1>
	</header>
	</div>
	<div class="col-lg-offset-3 col-lg-8">
	<p>email : <%=((User)request.getAttribute("user")).getEmail()%></p>
	<p>Adresse : <%=((User)request.getAttribute("user")).getAddress()%></p>
	<p>Password : <%=((User)request.getAttribute("user")).getPwd()%></p>
	<p>Role : <%=((User)request.getAttribute("user")).getIsAdmin()?"Admin":"User"%></p>
	</div>
	
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>