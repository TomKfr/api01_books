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
  		String result = "";
  		String res = (String)request.getAttribute("result");
  		if(res == null) {
  			result = "style='display:none'";
  		}
  	%>
  	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="LoginController?action=login">Mon beau site</a>
			</div>
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
					<input type="hidden" name="action" value="login">
					<input type="submit" class="btn btn-primary" value="Log in">
				</div>
				<div class="col-lg-offset-1 col-lg-8 alert alert-danger" <%= result %>>
					Echec de connexion !
				</div>
			</form>
		</div>
    </div>
    <script src="bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>