<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <title>Monsite</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="index.css" rel="stylesheet">
  </head>
  <body>
  	<%
  		String result = "";
  		String res = (String)request.getAttribute("result");
  		if(res == null) {
  			result = "style="+'"'+"display:none"+'"';
  		}
  	%>
  	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html">Mon beau site</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="reader_index.html">Reader_index</a></li>
				<li><a href="administrator_index.html">Admin_index</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Connexion</h1>
	</header>
	</div>
	<div class="container">
		<div class="row">
			<form class="well well-lg col-lg-offset-4 col-lg-4" action="LoginController" method="post">
				<div class="form-group">
					<label for="login">Login</label>
					<input type="text" class="form-control" id="login" placeholder="login" name="login">
				</div>
				<div class="form-group">
					<label for="pwd">Password</label>
					<input type="password" class="form-control" id="pwd" placeholder="password" name="pwd">
				</div>
				<div class="col-lg-3">
					<input type="submit" class="btn btn-primary" value="Log in">
				</div>
				<div class="col-lg-offset-1 col-lg-8 alert alert-danger" <%= result %>>
					Echec de connexion !
				</div>
			</form>
		</div>
		<div class="row">
			<div class="col-md-offset-4 col-md-4">
				<p>Pas de compte ? C'est par ici  <span class="glyphicon glyphicon-arrow-right"></span>  
				<a class="btn btn-primary" href="signin.html">S'inscrire</a>
				</p>
			</div>
		</div>
    </div>
    <script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>