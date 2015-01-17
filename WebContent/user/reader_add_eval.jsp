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
  	System.out.println("isbn "+bk.getIsbn());
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="logout.jsp">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath}/BooksController?action=index">Find a book</a></li>
				<li><a href="${pageContext.request.contextPath}/EvalHistory?action=seeHistory&page=1">My books</a></li>
				<li><a href="${pageContext.request.contextPath}/MatchesHistory?action=seeMatches&page=0">My matches</a></li>
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
				<form method="get" action="EvalManager" class="col-lg-offset-3 col-lg-6">
					<div class="form-group">
						<label for="isbn">Qualité d'écriture :</label>
						<INPUT type= "radio" name="quality" value="0"> 0
						<INPUT type= "radio" name="quality" value="1"> 1
						<INPUT type= "radio" name="quality" value="2"> 2
						<INPUT type= "radio" name="quality" value="3"> 3
						<INPUT type= "radio" name="quality" value="4"> 4
					</div>
					<div class="form-group">
						<label for="titre">Intérêt pour le sujet du livre :</label>
						<INPUT type= "radio" name="subject" value=0> 0
						<INPUT type= "radio" name="subject" value=1> 1
						<INPUT type= "radio" name="subject" value=2> 2
						<INPUT type= "radio" name="subject" value=3> 3
						<INPUT type= "radio" name="subject" value=4> 4
					</div>
					<div class="form-group">
						<label for="titre">Désir de lire jusqu'à la fin :</label>
						<INPUT type= "radio" name="desire" value="0"> 0
						<INPUT type= "radio" name="desire" value="1"> 1
						<INPUT type= "radio" name="desire" value="2"> 2
						<INPUT type= "radio" name="desire" value="3"> 3
						<INPUT type= "radio" name="desire" value="4"> 4
					</div>
					<div class="form-group">
						<label for="titre">Désir de lire un livre du même auteur :</label>
						<INPUT type= "radio" name="read_author" value="0"> 0
						<INPUT type= "radio" name="read_author" value="1"> 1
						<INPUT type= "radio" name="read_author" value="2"> 2
						<INPUT type= "radio" name="read_author" value="3"> 3
						<INPUT type= "radio" name="read_author" value="4"> 4
					</div>
					<div class="form-group">
						<label for="titre">Désir de recommander le livre à un ami :</label>
						<INPUT type= "radio" name="recommend" value="0"> 0
						<INPUT type= "radio" name="recommend" value="1"> 1
						<INPUT type= "radio" name="recommend" value="2"> 2
						<INPUT type= "radio" name="recommend" value="3"> 3
						<INPUT type= "radio" name="recommend" value="4"> 4
					</div>
					<input type="hidden" name="action" value="newEval"/>
					<input type="hidden" name="book" value="<%=bk.getIsbn()%>"/>
					<div class="col-lg-3">
					</div>	
					<div class="btn-group">
  						<button type="submit" class="btn btn-success" value="Soumettre" name="bouton"> Soumettre </button>			
					</div>
					<div class="btn-group">
  						<button type="submit" class="btn btn-primary" value="Enregistrer" name="bouton"> Enregistrer </button>			
					</div>
				</form>
			</div>
		</div>
		
	
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>