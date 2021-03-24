<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Quote!</title>
<style type="text/css">
/*--------------------------------------------------------------
# General
--------------------------------------------------------------*/
body {
	background: #fff;
	color: #666666;
	font-family: "Open Sans", sans-serif;
}

a {
	color: rgb(115, 134, 245);
}

.card-img-top {
	width: 100%;
	height: 15vw;
	object-fit: cover;
}

a:hover, a:active, a:focus {
	color: #2dca98;
	outline: none;
	text-decoration: none;
}

p {
	padding: 0;
	margin: 0 0 30px 0;
}

h1, h2, h3, h4, h5, h6 {
	font-family: "Poppins", sans-serif;
	font-weight: 400;
	margin: 0 0 20px 0;
	padding: 0;
}

/*--------------------------------------------------------------
  # Header
  --------------------------------------------------------------*/
#header {
	padding: 10px 0;
	height: 100px;
	position: relative;
	left: 0;
	top: 0;
	right: 0;
	background-color: #1a1a1a;
}

#header.header-fixed {
	background: rgba(19, 23, 26, 0.9);
	padding: 30px 0;
	height: 80px;
}

.pull-left {
	float: left;
}

/*--------------------------------------------------------------
# Navigation Menu
--------------------------------------------------------------*/
/* Nav Menu Essentials */
.nav-menu, .nav-menu * {
	margin: 0;
	padding: 0;
	padding-top: 10px;
	list-style: none;
}

.nav-menu ul {
	position: absolute;
	display: none;
	top: 100%;
	left: 0;
}

.nav-menu li {
	position: relative;
	white-space: nowrap;
}

.nav-menu>li {
	float: left;
}

.nav-menu li:hover>ul {
	display: block;
}

.nav-menu ul ul {
	top: 0;
	left: 100%;
}

.nav-menu ul li {
	min-width: 180px;
}

/* Nav Meu Container */
#nav-menu-container {
	float: right;
	margin: 0;
}

/* Nav Meu Styling */
.nav-menu a {
	padding: 0 8px 10px 8px;
	text-decoration: none;
	display: inline-block;
	color: rgb(115, 134, 245);
	font-family: "Poppins", sans-serif;
	font-weight: 400;
	text-transform: uppercase;
	font-size: 13px;
	outline: none;
}

.nav-menu>li {
	margin-left: 10px;
}

.nav-menu>li>a:before {
	content: "";
	position: absolute;
	width: 100%;
	height: 2px;
	bottom: 0;
	left: 0;
	background-color: rgb(255, 255, 255);
	visibility: hidden;
	transform: scaleX(0);
	transition: all 0.3s ease-in-out 0s;
}

.nav-menu a:hover:before, .nav-menu li:hover>a:before, .nav-menu .menu-active>a:before
	{
	visibility: visible;
	transform: scaleX(1);
}

/*  pop -up*/
/*--------------------------------------------------------------
 Card section
--------------------------------------------------------------*/
.subscribe {
	background: #fff;
	margin-top: 6%;
}

#row1 {
	margin-bottom: 7%;
}

#row2 {
	margin-bottom: 7%;
}

#row3 {
	margin-bottom: 7%;
}

#button {
	padding-left: 15%;
	padding-top: 5px;
}

/*--------------------------------------------------------------
# Footer
--------------------------------------------------------------*/
#footer {
	background: rgba(19, 23, 26, 0.9);
	color: #fff;
	font-size: 14px;
	position: fixed;
	padding: 10px 10px 0px 10px;
	bottom: 0;
	width: 100%;
	/* Height of the footer*/
	height: 40px;
}

#footer .credits {
	padding-top: 10px;
	text-align: center;
	font-size: 13px;
	color: #ccc;
}

.flex-wrapper {
	display: flex;
	min-height: 100vh;
	flex-direction: column;
	justify-content: space-between;
}

div.form {
	display: block;
	text-align: center;
}

form {
	display: inline-block;
	margin-left: auto;
	margin-right: auto;
	text-align: left;
	margin-top: 2%;
}
</style>
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- ======= Header ======= -->
	<header id="header">
		<div class="container">
			<div id="logo" class="pull-left">
				<a href="/dashboard"> <img class="mainlogo"
					src="https://drive.google.com/thumbnail?id=1bsS_JaYsoGYpUh90rPlXUBUfri3Bt_Mz"
					height="80" width="80"></a>
			</div>
			<nav id="nav-menu-container">
				<ul class="nav-menu">
					<li class="menu-active"><a href="/dashboard">Home</a></li>
					<li><a href="/logout">Log out</a></li>
				</ul>
			</nav>
			<!-- #nav-menu-container -->
		</div>
	</header>
	<!-- End Header -->
	<div>
		<section class="jumbotron jumbotron-fluid">
			<div class="form p-3">
				<form action="/quoteResult" method="get" model="CustomerDetails">
					<h4 class="text-primary">Get a quote!</h4>
					<div class="form-group">
						<label for="age">Age </label> <input type="number"
							placeholder="Enter your age" min=1 max=100 class="form-control"
							name="age" />
					</div>
					<p>Are you a drinker or not?</p>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="drinker"
							id="inlineRadio1" value="YES"> <label
							class="form-check-label" for="inlineRadio1">YES</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="drinker"
							id="inlineRadio2" value="NO"> <label
							class="form-check-label" for="inlineRadio2">NO</label>
					</div>
					<p>Are you a smoker or not?</p>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="smoker"
							id="inlineRadio3" value="YES"> <label
							class="form-check-label" for="inlineRadio3">YES</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="smoker"
							id="inlineRadio4" value="NO"> <label
							class="form-check-label" for="inlineRadio4">NO</label>
					</div>
					<div class="fornm-group">
						<input type="submit" class="btn btn-primary" />
					</div>
				</form>
			</div>
		</section>
		<footer id="footer">
			<div class="footer-top">
				<div class="container"></div>
			</div>

			<div class="container">
				<div class="credits">2021-2022</div>
			</div>
		</footer>
		<!-- End Footer -->
	</div>
</body>
</html>