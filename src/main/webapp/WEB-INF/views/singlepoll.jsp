<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Welcome to EasyPoll!</title>

			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
    		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css"/>
			<script src="${pageContext.request.contextPath}/resources/scripts/Chart.js"></script>
	 <!--Hides poll and show poll stats-->
        <script type="text/javascript">
            $(document).ready(function () {
                $('#submitPoll').on('click', function () {
                    
                });
            });
           
        </script>
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

	<div class="poll-header">
		<div class="container">
			<h1>${pollName}</h1>
		</div>
	</div>
	<div class="container">
        <div class="row">
            <!--Poll that people vote in-->
            <div class="col-md-6" id="pollQA">
                <div class="poll-panel" id="">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center poll-heading">
                            <h3 class="panel-title">${pollQuestion}</h3>
                        </div>
                        <div class="panel-body ">
                            
                             <div class="row">
                                 <div class="form-group col-md-offset-5 col-sm-offset-5 col-xs-5">
                                 <form:form method="GET" action="/test/singlepolldata/${pollID}">
                                     ${builder}
                                     <input type="submit" id="submitPoll" name="submitPoll" class="btn btn-default" value="Submit">
                                     </form:form>
                                 </div>
                             </div>
                             <div class="row">
                                 <div class="form-group col-md-offset-5 col-sm-offset-5 col-xs-5">
                                     </div>
                             </div>
                            
                        </div>
                    </div>
                </div>
            </div>
            <!--End poll that people vote in-->
            <!--Poll Statistics-->
            <!--End Poll Statistics-->
            <!--More Poll Data-->
            <div class="col-md-6">
                <div class="poll-panel" >
                    <!--Poll Created By-->
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Poll Created By</h3>
                            </div>
                            <div class="panel-body">
                                ${posterUsername}
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <!--More Poll Data-->
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Data</h3>
                            </div>
                            <div class="panel-body">
                                Data about poll
                            </div>
                        </div>
                    </div>
                    <!-- REPORT -->
                    <div class="col-md-12" ${hide}>
                        <div class="panel panel-default">
                            <div class="panel-heading" >
                                <h3 class="panel-title">Report Offensive Poll</h3>
                            </div>
							<div class="panel-body">
							<div class="row">
								<div class="col-md-3">
								<form:form method="POST" action="/test/report">
								<input type="button" name="dumb" id="dumbbutton" hidden='true'>
								<input type="submit" id="report" name="report" class="btn btn-success" value="Report">
								</form:form>
								</div>
								<div class="col-md-9">
								${reportStatus}
								</div>
								</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
        <!--Additional Info-->
	<div class="container">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#">Additional Info</a></li>
		</ul>
		<div class="col-md-12">		
			${pollDesc}
		</div>
	</div>
	<div class="container">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#">Recommend to a Friend</a></li>
		</ul>
		<div class="col-md-12">		
			<form:form method="POST" action="/test/recommend">
     		 	<input id="email" name="address" type="email" placeholder="Email Address" />
       			<input type="submit" id="sendEmail" class="btn btn-success" value="Send Email">
       		</form:form>
		</div>
	</div>
	<div class="container" ${creatorHide}>
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#">Modify Your Poll</a></li>
		</ul>
		<div class="col-md-12">		
			<form:form method="POST" action="/test/editPoll/${pollID}">
     		 	<input id="email" name="ad" type="email" hidden="true" placeholder="Email Address" />
       			<input type="submit" id="sendEmail" class="btn btn-success" value="Edit Poll">
       		</form:form>
		</div>
	</div>
	<div class="container" ${creatorHide}>
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#">Delete Your Poll</a></li>
		</ul>
		<div class="col-md-12">		
			<form:form method="POST" action="/test/deletePoll/${pollID}">
     		 	<input id="email" name="ad" type="email" hidden="true" placeholder="Email Address" />
       			<input type="submit" id="sendEmail" class="btn btn-success" value="Delete Poll">
       		</form:form>
		</div>
	</div>
	<div class="container" ${creatorHide}>
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#">Cancel Poll Early</a></li>
		</ul>
		<div class="col-md-12">		
			<form:form method="POST" action="/test/cancelPoll/${pollID}">
     		 	<input id="email" name="ad" type="email" hidden="true" placeholder="Email Address" />
       			<input type="submit" id="sendEmail" class="btn btn-success" value="Cancel Poll">
       		</form:form>
		</div>
	</div>
	<!--Login Modal -->
	<div id="login-modal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div class="loginmodal-container text-center">
						<h2>Login to Your Account</h2><br>
						<form:form method="POST" action="/test/login">
						<form:input id="txtEmail" type="email" name='email' placeholder="Email" path='email'/>
						<form:input id="txtPassword" type="password" name='password' placeholder="Password" path='password' />
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
             		 	<input id="txtUsername" type="text" placeholder="Username" />
             		 	<input id="txtEmail" type="email" placeholder="Email Address" />
              			<input id="txtPassword" type="password" placeholder="Password" />
              			<input type="submit" id="btnLogin" class="btn btn-success" value="Login">
              			</form:form>
					</div>
						<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		</div>
        <!--Footer-->
	<footer id="footer">
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
        
	</body>
</html>