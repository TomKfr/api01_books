<%@page import="java.util.Iterator"%>
<%@page import="com.books.model.Books"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
  	List<Books> books = (List<Books>) request.getAttribute("books");
  	Iterator<Books> it=null;
  	if(books!=null) it = books.iterator();
  	Integer pg = (Integer) request.getAttribute("page");
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./logout.jsp">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/BooksController?action=index">Find a book</a></li>
				<li class="active"><a href="${pageContext.request.contextPath}/EvalHistory?action=seeHistory&page=1">My books</a></li>
				<li><a href="${pageContext.request.contextPath}/MatchesHistory?action=seeMatches&page=0">My matches</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="./logout.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Les livres de <%=u.getName()%> :</h1>
	</header>
	</div>
	<div class="col-lg-offset-2 col-lg-8">
		<table class="table">
			<tr><th>ISBN</th><th>Titre</th><th>Auteur</th><th>Genre</th><th>Action</th></tr>
			<%
				if(books!=null){
				while(it.hasNext()){
					Books bk = (Books) it.next();
					out.println("<tr><td>"+bk.getIsbn()+"</td><td>"+bk.getTitre()+"</td><td>"+bk.getAuteur()+"</td><td>"+bk.getGenre()+"</td>");
					out.println("<td><a class='btn btn-danger' href='EvalManager?action=delete&isbn="+bk.getIsbn()+"'>Supprimer</a><a class='btn btn-info' href='EvalManager?action=modify&isbn="+bk.getIsbn()+"'>Voir/Modifier</a></td></tr>");
				}}
				else{
					out.println("<tr><td>Pas de livres lus</td></tr>");
				}
			%>
		</table>
	</div>
	<div class="col-lg-offset-5 col-lg-2">
		<nav>
  			<ul class="pagination">
  				<%
  					if(books!=null){
  					for(int i=0;i<(Integer)request.getAttribute("nbpages");i++){
  						out.println("<li");
  						if(i==pg) out.println("class='active'");
  						out.println("><a href=?page="+i+">"+(i+1)+"</a></li>");
  					}}
  				%>
  			</ul>
  		</nav>
	</div>
	<input type="hidden" name="action" value="searchbooks"/>
	<script src="../bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>