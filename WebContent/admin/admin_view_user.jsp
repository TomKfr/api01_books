<!DOCTYPE html>
<%@page import="com.books.model.Tmatch"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.books.model.Evaluation"%>
<%@page import="java.util.List"%>
<%@page import="com.books.model.User"%>
<html lang="fr">
  <head>
    <title>MatchBooks</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
  </head>
  <body>
  <%
  	User u = (User) request.getSession().getAttribute("user");
  	User vieweduser = (User) request.getAttribute("vieweduser");
  	List<Evaluation> list = (List<Evaluation>) request.getAttribute("evalsuser");
  	List<Tmatch> listmatch = (List<Tmatch>) request.getAttribute("matchsuser") ;
  %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=login">MatchBooks</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="UserManager?action=index">Gestion des utilisateurs</a></li>
				<li><a href="BookMgmt?action=index">Gestion des livres</a></li>
				<li><a href="EvalManager?action=index">Gestion des évaluations</a></li>
				<li><a href="MatchManager?action=index">Gestion des matches</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LoginController?action=logout">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
		<header class="page-header">
			<h1> Informations de <%=vieweduser.getName() %> </h1>
		</header>
		<div class="col-lg-12">
			<div class="col-lg-12">
				<table class="table">
					<tr><th align=center>Email</th><th align=center>Nom</th><th align=center>Mot de passe</th><th align=center>Adresse</th><th>Téléphone</th><th align=center>Date de création</th><th align=center>Statut du compte</th><th align=center>Administrateur</th></tr>
					<tr><td align=center><%=vieweduser.getEmail() %></td><td align=center><%=vieweduser.getName() %></td><td align=center><%=vieweduser.getPwd() %></td><td align=center><%=vieweduser.getAddress() %></td><td align=center><%=vieweduser.getTel() %></td><td align=center><%=vieweduser.getCreationDate() %></td><td align=center><%=vieweduser.getAccountStatus() %></td><td align=center><%=vieweduser.getIsAdmin() %></td></tr>
				</table>
			</div>
			<div class="col-lg-12">
				<h2>Livres évalués</h2>
				<table class="table">
				<%
					if(list!=null){
						Iterator<Evaluation> it = list.iterator();
						out.println("<tr><th>Utilisateur</th><th>Livre</th><th>Qualité</th><th>Intérêt sujet</th><th>Désir continuer</th><th>Lire auteur</th><th>Recommender</th><th>Score</th></tr>");
						while(it.hasNext()){
							Evaluation e = it.next();
							%>
							<tr><td align=center><%=e.getUser() %></td><td align=center><%=e.getBook() %></td><td align=center><%=e.getQuality() %></td><td align=center><%=e.getSubject() %></td><td align=center><%=e.getDesire() %></td><td align=center><%=e.getReadAuthor() %></td><td align=center><%=e.getRecommend() %></td><td align=center><%=e.getScore() %></td></tr>
							<%
						}
					}
					else{
						out.println("<tr><td>Pas d'evaluation.</td></tr>");
					}
				%>
				</table>
			</div>
			
			<div class="col-lg-12">
				<h2>Matches</h2>
				<table class="table">
				<%
					if(listmatch!=null){
						Iterator<Tmatch> it = listmatch.iterator();
						out.println("<tr><th align=center>Utilisateur</th><th align=center>Utilisateur le plus proche</th><th align=center>Utilisateur le plus éloigné</th><th align=center>Livre</th></tr>");
						while(it.hasNext()){
							Tmatch e = it.next();
							%>
							<tr><td align=center><%=e.getUser() %></td><td align=center><%=e.getClosestuser() %></td><td align=center><%=e.getFarthestuser() %></td><td align=center><%=e.getBook() %></td></tr>
							<%
						}
					}
					else{
						out.println("<tr><td>Pas de match.</td></tr>");
					}
				%>
				</table>
			</div>
		</div>
	</div>
	<script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>