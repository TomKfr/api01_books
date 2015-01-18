<!DOCTYPE html>
<html lang="fr">
  <head>
    <title>Monsite - Inscription</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="index.css" rel="stylesheet">
  </head>
  <body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp">Mon beau site</a>
			</div>
		</div>
	</div>
	<div class="container">
	<header class="page-header">
		<h1>Inscription</h1>
	</header>
	</div>
	<div class="container">
		<div class="row">
			<form class="well well-lg col-lg-offset-4 col-lg-4" action="LoginController">
			<input type="hidden" name="action" value="createaccount"/>
			<div class="form-group">
					<label for="email">Email</label>
					<input type="email" class="form-control" id="email" placeholder="email" name="email">
				</div>
				<div class="form-group">
					<label for="nom">Nom</label>
					<input type="text" class="form-control" id="nom" placeholder="nom" name="nom">
				</div>
				<div class="form-group">
					<label for="pwd">Password</label>
					<input type="password" class="form-control" id="pwd" placeholder="password" name="pwd">
				</div>
				<div class="form-group">
					<label for="adrs">Adresse</label>
					<input type="text" class="form-control" id="adrs" placeholder="adresse" name="adrs">
				</div>
				<div class="form-group">
					<label for="tel">Téléphone</label>
					<input type="tel" class="form-control" id="tel" placeholder="téléphone" name="tel">
				</div>
				<div class="col-lg-3">
					<input type="submit" class="btn btn-primary" value="Demander un compte">
				</div>
			</form>
		</div>
    </div>
	
	<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
