<%@page import="com.books.model.User"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <title>MatchBooks</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-table.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
  </head>
  <body>
  	<%
  		String result = "style="+'"'+"display:none"+'"';
		String res = (String)request.getAttribute("result");
		if(res != null) {
			result = "";
		}
		
		User usr = (User) request.getAttribute("modifuser");
		if(usr==null){
			usr=new User();
			usr.setEmail("");
			usr.setName("");
			usr.setPwd("");
			usr.setTel("");
			usr.setAddress("");
			usr.setAccountStatus(false);
			usr.setIsAdmin(false);
		}
  	%>
  	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=login">MatchBooks</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="UserManager?action=index">Gestion des utilisateurs</a></li>
				<li><a href="BookMgmt?action=index">Gestion des livres</a></li>
				<li><a href="EvalManager?action=index">Gestion des �valuations</a></li>
				<li><a href="MatchManager?action=index">Gestion des matches</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LoginController?action=logout">D�connexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Gestion des utilisateurs</h1>
	</header>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<%
					List<User> list = (List<User>) request.getAttribute("search");
					if(list!=null){
						out.println("<table class='table' data-toggle='table' data-striped='true' data-pagination='true' data-page-size='5' data-search='true'><thead><tr><th data-align='center' data-sortable='true'>Email</th><th data-align='center' data-sortable='true'>Nom</th><th data-align='center' data-sortable='true'>Mot de passe</th><th data-align='center' data-sortable='true'>Adresse</th><th data-align='center' data-sortable='true'>T�l�phone</th><th data-align='center' data-sortable='true'>Date de cr�ation</th><th data-align='center' data-sortable='true'>Compte actif</th><th data-align='center' data-sortable='true'>Administrateur</th><th data-align='center'>Action</th></tr></thead>");
						Iterator<User> it = list.iterator();
						while(it.hasNext()){
							User u = (User) it.next();
							out.println("<tr><td >"+u.getEmail()+"</td><td >"+u.getName()+"</td><td >"+u.getPwd()+"</td><td >"+u.getAddress()+"</td><td >"+u.getTel()+"</td><td >"+u.getCreationDate()+"</td><td >"+u.getAccountStatus()+"</td><td >"+u.getIsAdmin()+"</td>");
							out.println("<td><a class='btn btn-success' href='UserManager?action=view&email="+u.getEmail()+"'>Visualiser</a>");
							out.println("<a class='btn btn-info' href='UserManager?action=startmodif&email="+u.getEmail()+"'>Modifier</a>");
							out.println("<a class='btn btn-danger' href='UserManager?action=delete&email="+u.getEmail()+"'>Supprimer</a></td></tr>");
						}
						out.println("</table>");
					}
				%>
			</div>
			<div class="col-lg-offset-2 col-lg-8">
				<h2>Ajouter un utilisateur</h2>
				<form method="get" action="UserManager" class="col-lg-10">
					<div class="form-group">
						<label for="mail">Email</label>
						<input type="text" class="form-control" id="mail" value="<%=usr.getEmail().isEmpty()?'"':usr.getEmail()+'"'+" disabled" %> name="email">
					</div>
					<div class="form-group">
						<label for="nom">Nom</label>
						<input type="text" class="form-control" id="nom" value="<%=usr.getName() %>" name="nom">
					</div>
					<div class="form-group">
						<label for="pwd">Mot de passe</label>
						<input type="text" class="form-control" id="pwd" value="<%=usr.getPwd() %>" name="pwd">
					</div>
					<div class="form-group">
						<label for="adrs">Adresse</label>
						<input type="text" class="form-control" id="adrs" value="<%=usr.getAddress() %>" name="adrs">
					</div>
					<div class="form-group">
						<label for="tel">T�l�phone</label>
						<input type="text" class="form-control" id="tel" value="<%=usr.getTel() %>" name="tel">
					</div>
					<div class="form-group">
						<label for="account">Compte actif</label>
						<input type="checkbox" class="form-control" id="account"  name="isactive" value="true" <%if(usr.getAccountStatus()) out.println("checked"); %>>
					</div>
					<div class="form-group">
						<label for="admin">Administrateur</label>
						<input type="checkbox" class="form-control" id="admin"  name="isadmin" value="true" <%if(usr.getIsAdmin()) out.println("checked"); %>>
					</div>
					<input type="hidden" name="action" value="add"/>
					<%=usr.getEmail().isEmpty()?"":"<input type='hidden' name='email' value='"+usr.getEmail()+"'/>" %>
					<div class="col-lg-3">
						<input type="submit" class="btn btn-primary" value="Enregistrer">
					</div>	
				</form>
				<div class="col-lg-6 alert alert-success" <%=result %>>
					Ajout de l'utilisateur <%=request.getParameter("email") %> r�ussie !
				</div>
			</div>
			</div>	
			<!-- <div class="col-lg-12">
				<h2>Rechercher un utilisateur</h2>
				<form method="get" action="UserManager" class="col-lg-offset-4 col-lg-6">
					<div class="form-group">
						<label for="mail">Email</label>
						<input type="text" class="form-control" id="mail" placeholder="email" name="email">
					</div>
					<div class="form-group">
						<label for="nom">Nom</label>
						<input type="text" class="form-control" id="nom" placeholder="nom" name="nom">
					</div>
					<div class="form-group">
						<label for="admin">Administrateur</label>
						<input type="checkbox" class="form-control" id="admin"  name="isadmin">
					</div>
					<input type="hidden" name="action" value="search"/>
					<div class="col-lg-3">
						<input type="submit" class="btn btn-primary" value="Chercher">
					</div>	
				</form>
			</div> -->
			</div>
			<%-- <div class="col-lg-offset-4 col-lg-4 centered">
			<nav>
  				<ul class="pagination">
  				<%
  					if(list!=null){
  					Integer pg = (Integer) request.getAttribute("page");
  					System.out.println("nbpages :"+request.getAttribute("nbpages"));
  					for(int i=1;i<((Integer) request.getAttribute("nbpages"))+1;i++){
  						out.println("<li");
  						if(i==pg) out.println("class='active'");
  						out.println("><a href='?page="+i+"&isbn="+request.getParameter("isbn")+"&titre="+request.getParameter("titre")+"&action=search'>"+i+"</a></li>");
  					}}
  				%>
  			</ul>
  			</nav>
		</div> --%>
		
		
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table-fr-FR.js"></script>
  </body>
</html>