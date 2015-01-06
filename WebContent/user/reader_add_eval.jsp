<!DOCTYPE html>
<%@page import="com.books.model.Books"%>
<%@page import="com.books.model.User"%>
<html lang="fr">
  <head>
    <title>Monsite - Accueil lecteur</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
  </head>
  <body>
  <%
  	User u = (User) request.getSession().getAttribute("user");
  	Books bk = (Books) request.getAttribute("book");
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="logout.jsp">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/user/reader_add_book.jsp">Find a book</a></li>
				<li><a href="BooksController?action=list&page=0">My books</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Evaluer <%=bk.getTitre()%> </h1>
	</header>
	</div>
	
	<div class="col-lg-6">
			<div class="col-lg-12">
				<form method="get" action="${pageContext.request.contextPath}/EvalManager" class="col-lg-offset-3 col-lg-6">
					<div class="form-group">
						<label for="isbn">Qualité d'écriture</label>
						<input type="text" class="form-control" id="isbn" placeholder="ISBN" name="isbn">
					</div>
					<div class="form-group">
						<label for="titre">Intérêt pour le livre</label>
						<input type="text" class="form-control" id="titre" placeholder="Titre" name="titre">
					</div>
					<div class="form-group">
						<label for="titre">Intérêt pour le sujet</label>
						<input type="text" class="form-control" id="titre" placeholder="Titre" name="titre">
					</div>
					<input type="hidden" name="action" value="search"/>
					<div class="col-lg-3">
						<input type="submit" class="btn btn-primary" value="Chercher">
					</div>	
				</form>
			</div>
		</div>
	
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>