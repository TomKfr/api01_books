<%@page import="com.books.model.User"%>
<%@page import="com.books.model.Evaluation"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <title>Monsite</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/index.css" rel="stylesheet">
  </head>
  <body>
  	<%
  		User usr = (User) request.getAttribute("user");
		Evaluation eval = (Evaluation) request.getAttribute("modifeval");
		if(eval==null){
			eval=new Evaluation();
			eval.setBook("");
			eval.setDesire(null);
			eval.setIsvalidated(false);
			eval.setQuality(null);
			eval.setReadAuthor(null);
			eval.setRecommend(null);
			eval.setScore(null);
			eval.setSubject(null);
			eval.setUser("");
		}
  	%>
  	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="logout.jsp">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/BooksController?action=index">Find a book</a></li>
				<li><a href="${pageContext.request.contextPath}/EvalHistory?action=seeHistory&page=0">My books</a></li>
				<li><a href="MatchesHistory?action=seeMatches&page=0">My matches</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.jsp">Déconnexion</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Evaluation</h1>
	</header>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h2>Enregistrer une évaluation</h2>
				<form method="get" action="EvalHistory?action=send" class="col-lg-offset-3 col-lg-6">
				<div class="form-group">
						<label for="num">Numéro</label>
						<input type="text" class="form-control" id="num" value="<%=eval.getNum()%>" disabled name="num">
					</div>
					<div class="form-group">
						<label for="user">Utilisateur</label>
						<input type="text" class="form-control" id="user" value="<%=usr.getEmail().isEmpty()?'"':usr.getEmail()+'"'+" disabled" %> name="email"> 
					</div>
					
					<div class="form-group">
						<label for="book">Livre</label>
						<input type="text" class="form-control" id="book" value="<%=eval.getBook() %>" disabled name="book">
					</div>
					<div class="form-group">
						<label for="quality">Qualité d'écriture :</label>
						<INPUT type= "radio" name="quality" value="0" checked=<%=eval.getQuality()==0?"checked":"" %>> 0
						<INPUT type= "radio" name="quality" value="1" checked=<%=eval.getQuality()==1?"checked":"" %>> 1
						<INPUT type= "radio" name="quality" value="2" checked=<%=eval.getQuality()==2?"checked":"" %>> 2
						<INPUT type= "radio" name="quality" value="3" checked=<%=eval.getQuality()==3?"checked":"" %>> 3
						<INPUT type= "radio" name="quality" value="4" checked=<%=eval.getQuality()==4?"checked":"" %>> 4
					</div>
					<div class="form-group">
						<label for="titre">Intérêt pour le sujet du livre :</label>
						<INPUT type= "radio" name="subject" value=0 checked=<%=eval.getSubject()==0?"checked":"" %>> 0
						<INPUT type= "radio" name="subject" value=1 checked=<%=eval.getSubject()==1?"checked":"" %>> 1
						<INPUT type= "radio" name="subject" value=2 checked=<%=eval.getSubject()==2?"checked":"" %>> 2
						<INPUT type= "radio" name="subject" value=3 checked=<%=eval.getSubject()==3?"checked":"" %>> 3
						<INPUT type= "radio" name="subject" value=4 checked=<%=eval.getSubject()==4?"checked":"" %>> 4
					</div>
					<div class="form-group">
						<label for="titre">Désir de lire jusqu'à la fin :</label>
						<INPUT type= "radio" name="desire" value="0" checked=<%=eval.getDesire()==0?"checked":"" %>> 0
						<INPUT type= "radio" name="desire" value="1" checked=<%=eval.getDesire()==1?"checked":"" %>> 1
						<INPUT type= "radio" name="desire" value="2" checked=<%=eval.getDesire()==2?"checked":"" %>> 2
						<INPUT type= "radio" name="desire" value="3" checked=<%=eval.getDesire()==3?"checked":"" %>> 3
						<INPUT type= "radio" name="desire" value="4" checked=<%=eval.getDesire()==4?"checked":"" %>> 4
					</div>
					<div class="form-group">
						<label for="titre">Désir de lire un livre du même auteur :</label>
						<INPUT type= "radio" name="read_author" value="0" checked=<%=eval.getReadAuthor()==0?"checked":"" %>> 0
						<INPUT type= "radio" name="read_author" value="1" checked=<%=eval.getReadAuthor()==1?"checked":"" %>> 1
						<INPUT type= "radio" name="read_author" value="2" checked=<%=eval.getReadAuthor()==2?"checked":"" %>> 2
						<INPUT type= "radio" name="read_author" value="3" checked=<%=eval.getReadAuthor()==3?"checked":"" %>> 3
						<INPUT type= "radio" name="read_author" value="4" checked=<%=eval.getReadAuthor()==4?"checked":"" %>> 4
					</div>
					<div class="form-group">
						<label for="titre">Désir de recommander le livre à un ami :</label>
						<INPUT type= "radio" name="recommend" value="0" checked=<%=eval.getRecommend()==0?"checked":"" %>> 0
						<INPUT type= "radio" name="recommend" value="1" checked=<%=eval.getRecommend()==1?"checked":"" %>> 1
						<INPUT type= "radio" name="recommend" value="2" checked=<%=eval.getRecommend()==2?"checked":"" %>> 2
						<INPUT type= "radio" name="recommend" value="3" checked=<%=eval.getRecommend()==3?"checked":"" %>> 3
						<INPUT type= "radio" name="recommend" value="4" checked=<%=eval.getRecommend()==4?"checked":"" %>> 4
					</div>
					<input type="hidden" name="num" value="<%=eval.getNum()%>"/>
					<input type="hidden" name="book" value="<%=eval.getBook()%>"/>
					<input type="hidden" name="user" value="<%=usr.getEmail()%>"/>
					<input type="hidden" name="action" value="send"/>
					<div class="col-lg-3">
						<input type="submit" class="btn btn-primary" value="Soumettre">
					</div>	
				</form>
			</div>
    </div>
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>