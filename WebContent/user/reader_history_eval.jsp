<!DOCTYPE html>
<%@page import="java.util.Map"%>
<%@page import="com.books.model.Books"%>
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
  	List<Evaluation> eval = (List<Evaluation>) request.getAttribute("eval");
  	List<Evaluation> noteval=(List<Evaluation>) request.getAttribute("noteval");
  	Map<String,String> titres = (Map<String,String>) request.getAttribute("titres");
	Iterator<Evaluation> it=null;
	Iterator<Evaluation> it2=null;
	if(eval!=null) it = eval.iterator();
	if(noteval!=null) it2=noteval.iterator();
	Integer pg = (Integer) request.getAttribute("page");
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="logout.jsp">MatchBooks</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/BooksController?action=index">Trouver un livre</a></li>
				<li class="active"><a href="${pageContext.request.contextPath}/EvalHistory?action=seeHistory&page=1">Mes évaluations</a></li>
				<li><a href="${pageContext.request.contextPath}/MatchesHistory?action=seeMatches&page=0">Mes matches</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Historique de vos évaluations </h1>
	</header>
	<div class="col-lg-12">
		<table class="table">
			<tr><th align=center>Numéro</th><th align=center>Livre</th><th align=center>Qualité</th><th align=center>Intérêt pour le sujet</th><th align=center>Désir de lire jusqu'à la fin</th><th align=center>Lire du même auteur</th><th align=center>Recommander à un ami</th><th align=center>Score</th></tr>
			<%
				if(eval!=null){
				while(it.hasNext()){
					Evaluation e = (Evaluation) it.next();
					out.println("<tr><td align=center>"+e.getNum()+"</td><td align=center>"+titres.get(e.getBook())+"</td><td align=center>"+e.getQuality()+"</td><td align=center>"+e.getSubject()+"</td><td align=center>"+e.getDesire()+"</td><td align=center>"+e.getReadAuthor()+"</td><td align=center>"+e.getRecommend()+"</td><td align=center>"+e.getScore()+"</td>");
				}}
				else{
					out.println("<tr><td>Aucun livre évalué.</td></tr>");
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
  					if(eval!=null){
  					for(int i=0;i<(Integer)request.getAttribute("nbpages");i++){
  						out.println("<li");
  						if(i==pg) out.println("class='active'");
  						out.println("><a href=?page="+i+">"+(i+1)+"</a></li>");
  					}}
  				%>
  			</ul>
  		</nav>
	</div>
	<div class="col-lg-12">
	<h1> Evaluations à terminer </h1>
	</div>
	<div class="col-lg-12">
		<table class="table">
			<tr><th>Numéro</th><th>Livre</th></tr>
			<%
				if(noteval!=null){
				while(it2.hasNext()){
					Evaluation e2 = (Evaluation) it2.next();
					out.println("<tr><td align=center>"+e2.getNum()+"</td><td align=center>"+titres.get(e2.getBook())+"</td>");
					out.println("<td align='center'><a class='btn btn-info' href='EvalHistory?action=submitEval&numeval="+e2.getNum()+"'>Poursuivre l'évaluation</a>");
				}}
				else{
					out.println("<tr><td>Aucune évaluation en cours.</td></tr>");
				}
			%>
		</table>
	</div>
	</div>
		
	<input type="hidden" name="action" value="seeHistory"/>
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
