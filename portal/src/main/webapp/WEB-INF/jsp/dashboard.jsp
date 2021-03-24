<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Insureity</title>
    <meta content="" name="description">
    <meta content="" name="keywords">


    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
        rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Template Main CSS File -->
    <style>
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
padding-top:10px;
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
  
  .nav-menu > li {
    float: left;
  }
  
  .nav-menu li:hover > ul {
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
  
  .nav-menu > li {
    margin-left: 10px;
  }
  
  .nav-menu > li > a:before {
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
  
  .nav-menu a:hover:before, .nav-menu li:hover > a:before, .nav-menu .menu-active > a:before {
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


#button{
padding-left:15%;
padding-top:5px;
}

/*--------------------------------------------------------------
# Footer
--------------------------------------------------------------*/
#footer {
    background: rgba(19, 23, 26, 0.9);
    padding: 30px 0;
    color: #fff;
    font-size: 14px;
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
  
  </style>

</head>

<body>

    <!-- ======= Header ======= -->
    <header id="header">
        <div class="container">
            <div id="logo" class="pull-left">
               <a href="/dashboard"> <img class="mainlogo" src="https://drive.google.com/thumbnail?id=1bsS_JaYsoGYpUh90rPlXUBUfri3Bt_Mz" height="80" width="80"></a>
            </div>
            <nav id="nav-menu-container">
                <ul class="nav-menu">
                    <li class="menu-active"><a href="/dashboard">Home</a></li>
                    <li><a href="/logout">Log out</a></li>
                </ul>
            </nav><!-- #nav-menu-container -->
        </div>
    </header><!-- End Header -->
    <div class="flex-wrapper">
	    <!-- ======= Start Card ======= -->
	    <section class="subscribe">
	        <div class="container">
	            <div class="row" id="row1">
	                <div class="col-sm-3">
	                    <div class="card">
	                        <div class="card-body">
	                           <a href="/getQuote"> <img src="https://as2.ftcdn.net/jpg/00/24/87/83/500_F_24878378_YMHC6vpRLxYKGLW0UvjBzDYeBJVYINth.jpg" class="card-img-top" alt="viewbus.png"></a>
	                           <div id="button"><a href="/getQuote"  class="btn btn-primary">GET A QUOTE</a></div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-sm-3">
	                    <div class="card">
	                        <div class="card-body">
	                            <a href="/submitNewQuote"> <img src="https://quoteandsubmit.com/wp-content/uploads/2019/09/quote-and-submit-logo-2.png" class="card-img-top" alt="viewpro.png"></a>
	                           <div id="button"><a href="/submitNewQuote"  class="btn btn-primary">SUBMIT QUOTE</a></div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-sm-3">
	                    <div class="card">
	                        <div class="card-body">
	                            <a href="/createbusiness"> <img src="https://lh3.googleusercontent.com/proxy/Dte3gOnfM-Ln9tLf6TrGP9M2dMmKP8MAh1p3G3-4N8U1a0pd1RbEbYZ6kXnwhCw0Bz3Zlg-EEkaQYFo0BMcuVPjrZvoVpw0-Qj7XQpL9NhtOyabSgmbCAGjSKnKoeOXwEe5H-ZfzJw" class="card-img-top" alt="ADDNEWBUSINESS.png"></a>
	                           <div id="button"><a href="/createbusiness"  class="btn btn-primary">RETRIEVE QUOTE</a></div>
	                        </div>
	                    </div>
	                </div>
	
	</div>
	
	        </div>
	    </section><!-- ======= End Card ======= -->
	
	    <!-- ======= Footer ======= -->
	    <footer id="footer">
	        <div class="footer-top">
	            <div class="container">
	
	            </div>
	        </div>
	
	        <div class="container">
	            <div class="credits">
	                2020-2021
	            </div>
	        </div>
	    </footer><!-- End Footer -->
    </div>
    <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

    <!-- Vendor JS Files -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

        <script>
</script>
</body>

</html>