<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us!-easyPoll</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/scripts/Chart.js"></script> <!--Charts for Poll Data-->
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar" id="main-nav">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar top-bar"></span>
                    <span class="icon-bar middle-bar"></span>
                    <span class="icon-bar bottom-bar"></span>
                </button>
                <span class="title">easy<span>Poll</span></span>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li><a href="/test/home">Home</a>
					<li><a href="/test/communitypolls">Community</a></li>
					<li><a href="/test/mypolls">My Polls</a></li>
					<li><a href="/test/groupmanager">My Groups</a></li>
					<li><a href="/test/about">About</a></li>
					<li><a href="/test/contact">Contact</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>${login}</li>
                    <li>${signup}</li>
                </ul>
            </div>
        </div>
    </nav>
    <!--End Navigation-->

    <div class="container">
        <div class="page-header">
            <h1>Contact</h1>
        </div>
        <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Contact the Developers</h3>
                </div>
                <div class="panel-body">
                    <p>Nick Frichette: nfriche@ilstu.edu</p>
                    <p>Matthew Fletcher: mfletch1@ilstu.edu</p>
                    <p>Nick Messina: nmessi1@ilstu.edu</p>
                    <p>Kevin Dalle: kwdalle@ilstu.edu</p>
                    <p>Casey Cook: clcoo10@ilstu.edu</p>
                </div>
            </div>
        </div>
        </div>
       
    </div>
    <footer id="footer" class="">
        <div class="container">
            <div class="col-md-9">
                &copy;EasyPoll 2016
            </div>
            <div class="col-md-3">
                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-google" aria-hidden="true"></i></a>
            </div>
        </div>
    </footer>
    
	<!--Login Modal -->
	<div id="login-modal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="loginmodal-container text-center">
						<h2>Login to Your Account</h2><br>
						<form:form method="POST" action="/test/login">
						<form:input id="txtEmail" type="email" placeholder="Email" path="email"/>
						<form:input id="txtPassword" type="password" placeholder="Password" path="password"/>
						<input type="submit" id="btnLogin" class="btn btn-success" value="Login">
						</form:form>
						<div class="login-help">
						<a data-toggle="modal" data-target="#create-account-modal" id="register-link">Register</a> | <a data-toggle="modal" data-target="#forgot-password-modal" id="forgot-password-link">Forgot Password</a>
						</div>
					</div>
						<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		</div>
	<!--Create Account Modal -->
	<div id="create-account-modal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="loginmodal-container text-center">
						<h2>Create an Account</h2><br>
						<form:form method="POST" action="/test/register">
             		 	<form:input id="txtUsername" type="text" placeholder="Username" path="username"/>
             		 	<form:input id="txtEmail" type="email" placeholder="Email Address" path="email"/>
              			<form:input id="txtPassword" type="password" placeholder="Password" path="password"/>
              			<input type="submit" id="btnLogin" class="btn btn-success" value="Login">
              			</form:form>
					</div>
						<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		</div>
			<!--Forgot Password Modal -->
	<div id="forgot-password-modal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="forgotmodal-container text-center">
						<h2>Forgot Password</h2><br>
						<form:form method="POST" action="/test/register">
             		 	<form:input id="txtEmail" type="email" placeholder="Email Address" path="email"/>
              			<form:input id="txtPassword" type="password" placeholder="Password" path="password"/>
              			<input type="submit" id="forgot-password-button" class="btn btn-success" value="Login">
              			</form:form>
					</div>
						<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		</div>
		
		

</body>
</html>
