<!DOCTYPE html>
<%@page import="java.util.Map"%>
<%@page import="com.books.model.Books"%>
<%@page import="com.books.model.Tmatch"%>
<%@page import="com.books.model.Evaluation" %>
<%@page import="com.books.model.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html lang="fr">
  <head>
    <title>MatchBooks - Accueil lecteur</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
  </head>
  <body>
  <%
  	User u = (User) request.getSession().getAttribute("user");
  	List<Tmatch> match = (List<Tmatch>) request.getAttribute("match");
  	Map<String,String> titres = (Map<String,String>) request.getAttribute("titres");
	Iterator<Tmatch> it=null;
	if(match!=null) it = match.iterator();
	Integer pg = (Integer) request.getAttribute("page");
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="logout.jsp">MatchBooks</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/BooksController?action=index">Trouver un livre</a></li>
				<li><a href="${pageContext.request.contextPath}/EvalHistory?action=seeHistory&page=1">Mes évaluations</a></li>
				<li class="active"><a href="${pageContext.request.contextPath}/MatchesHistory?action=seeMatches&page=0">Mes matches</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Historique de vos matches </h1>
	</header>
	</div>
	<div> <% out.println("<a href='MatchesHistory?action=update"+"' class='btn btn-primary'> Demander une MAJ</a> "); %>
	<div class="col-lg-offset-2 col-lg-8">
		<table class="table">
			<tr><th align=center>Numéro</th><th align=center>Plus proche </th><th align=center>Plus éloigné</th><th align=center>Livre</th></tr>
			<%
				if(match!=null){
				while(it.hasNext()){
					Tmatch m = (Tmatch) it.next();
					out.println("<tr><td align=center>"+m.getNum()+"</td><td align=center>"+m.getClosestuser()+"</td><td align=center>"+m.getFarthestuser()+"</td><td align=center>"+titres.get(m.getBook())+"</td>");
				}}
				else{
					out.println("<tr><td>Aucun match.</td></tr>");
				}
			%>
		</table>
	</div>
	<div class="col-lg-6">
			<div class="col-lg-12">
				<form method="get" action="EvalHistory" class="col-lg-offset-3 col-lg-6">
					<div class="form-group">
					
					</div>
				</form>
			</div>
		</div>
		<div class="col-lg-offset-5 col-lg-2">
		<nav>
  			<ul class="pagination">
  				<%
  					if(match!=null){
  					for(int i=0;i<(Integer)request.getAttribute("nbpages");i++){
  						out.println("<li");
  						if(i==pg) out.println("class='active'");
  						out.println("><a href=?page="+i+">"+(i+1)+"</a></li>");
  					}}
  				%>
  			</ul>
  		</nav>
	</div>
		</table>
	</div>
	
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>