<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Welcome!</title>
<style type="text/css">
div.form {
	display: block;
	text-align: center;
}

form {
	display: inline-block;
	margin-left: auto;
	margin-right: auto;
	text-align: left;
	margin-top: 5%;
}

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
	color: #3DD2EA;
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
# Footer
--------------------------------------------------------------*/
#footer {
	background: rgba(19, 23, 26, 0.9);
	color: #fff;
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	text-align: center;
}

#footer .credits {
	padding-top: 10px;
	text-align: center;
	font-size: 13px;
	color: #ccc;
}

.register {
	margin-left: 5%;
}
</style>
<script type="text/javascript">
	function validate() {
		var status = false;
		var letters = /^[A-Za-z]+$/;
		var username = document.loginForm.username.value;
		var password = document.loginForm.password.value;
		if (username === null || username === '' || !(username.match(letters))
				|| username.length <= 2) {
			document.getElementById("username").innerHTML = "<b><p>Username cannot be empty</p></b>";
			status = false;
		}
		else if(password === null || password === ''){
			document.getElementById("password").innerHTML = "<b><p>Password cannot be empty</p></b>";
			status = false;
		}
		else {
			status = true;
		}
		return status;
	}
</script>
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
			<!-- #nav-menu-container -->
		</div>
	</header>
	<!-- End Header -->
	<section>
		<div class='form'>
			<form method="POST" model="AuthenticationRequest" name="loginForm"
				action="/authenticate" onsubmit="return validate()">
				<b><p class="text-danger">${loginMessage}</p></b>
				<div class="form-group">
					<label for="exampleInputEmail1">Username</label> <input type="text"
						class="form-control" id="userId" placeholder="Username"
						name="username"><span class="text-danger" id="username"></span>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						placeholder="Password" name="password"><span class="text-danger" id="password"></span>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
			<p class="register">
				Don't have an account, <a href="/register">register here!</a>
		</div>
	</section>
	<!-- ======= Footer ======= -->
	<footer id="footer">
		<div class="footer-top">
			<div class="container"></div>
		</div>

		<div class="container">
			<div class="credits">2021-2022</div>
		</div>
	</footer>
	<!-- End Footer -->
</body>

</html>