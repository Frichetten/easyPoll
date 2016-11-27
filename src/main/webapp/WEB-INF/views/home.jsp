<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Welcome to EasyPoll!</title>

			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
			<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
    		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css"/>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
			<script>
			$(document).ready(function () {
				$('#landing-title').fadeIn(3000).removeClass('hidden');								
				$('#user-logged-in').hide();
				$(document).on("click", "#box0", function(){
					document.location = "/test/singlepoll/"+document.getElementById("run0").innerText;
				});
				$(document).on("click", "#box1", function(){
					document.location = "/test/singlepoll/"+document.getElementById("run1").innerText;
				});
				$(document).on("click", "#box2", function(){
					document.location = "/test/singlepoll/"+document.getElementById("run2").innerText;
				});
				$(document).on("click", "#box3", function(){
					document.location = "/test/singlepoll/"+document.getElementById("run3").innerText;
				});
				$(".poll-ad-container").hover(function(){
					$(this).find('.vote-in-poll').each(function(e){
						    $(this).show();
						});
					$(this).find('.poll-description').each(function(e){
							$(this).hide();
						});
				},function(){
					$('.vote-in-poll').hide();
					$('.poll-description').show();
				});	
				$("#register-link").click(function() {
				 	$('#login-modal').hide();
 					$('.modal-backdrop').hide();
				});
				$("#forgot-password-link").click(function() {
					$("#forgot-password-modal").show();
					$('.modal-backdrop').hide();
				 	$('#login-modal').hide();
				});
			});
			</script>
			<script language="javascript"><!--
			function myFunction() {

			}
		//--></script>
	</head>
	<body>
	<!-- Navigation -->
	<nav class="navbar navbar-fixed-top" id="main-nav">
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
				<ul class="nav navbar-nav navbar-right" id="logged-out">
					<li>${login}</li>
					<li>${signup}</li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id="user-logged-in">
					<li><a>Welcome User</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="slider-container">
		<div class="container">
		<div class="col-md-12 slide-text-container landing-padding">
			<h2 class="slide-text">easyPoll lets you engage create polls, vote in polls, and view statistics in real time</h2>
			<a href="#home-vote"><button type="button" class="btn btn-info" data-toggle="modal">Vote in Community Polls</button></a>
		</div>
	    </div>
	<a name="home-vote" style="display:block;margin-top:20px;"></a>
	</div>
	<div class="call-to-action">
		<h1>Welcome to Our Polling Community!</h1>
		<h2>Vote in polls that interest you</h2>
	</div>
	<div id="tableId" class="container main-content">
	<div class="col-md-6">
		<div class="poll-ad-container">
			<div class="poll-description">
				<h3>${title0}</h3>
				<h3 id="run0" hidden="true">${pollId0}</h3>
				<span> ${pollDesc0} </span>
			</div>
			<div id="box0" class="vote-in-poll">
				<span>Vote in this Poll</span>
				<i class="fa fa-bar-chart" aria-hidden="true"></i>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="poll-ad-container">
			<div class="poll-description">
				<h3>${title1}</h3>
				<h3 id="run1" hidden="true">${pollId1}</h3>
				<span> ${pollDesc1} </span>
			</div>
			<div id="box1" class="vote-in-poll">
				<span>Vote in this Poll</span>
				<i class="fa fa-bar-chart" aria-hidden="true"></i>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="poll-ad-container">
			<div class="poll-description">
				<h3>${title2}</h3>
				<h3 id="run2" hidden="true">${pollId2}</h3>
				<span> ${pollDesc2} </span>
			</div>
			<div id="box2" class="vote-in-poll">
				<span>Vote in this Poll</span>
				<i class="fa fa-bar-chart" aria-hidden="true"></i>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="poll-ad-container">
			<div class="poll-description">
				<h3>${title3}</h3>
				<h3 id="run3" hidden="true">${pollId3}</h3>
				<span> ${pollDesc3} </span>
			</div>
			<div id="box3" class="vote-in-poll">
				<span>Vote in this Poll</span>
				<i class="fa fa-bar-chart" aria-hidden="true"></i>
			</div>
		</div>
	</div>
	</div>
	<div class="call-to-action colored-cta">
		<h1>What do you think?</h1>
		<h2>Let us know!</h2>
		<button type="button" class="btn btn-info" data-toggle="modal" data-target="#login-modal">Give Feedback</button>		
	</div>
	
	<footer id="footer" class="home-page">
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
						<form:form method="POST" action="/test/forgotpassword">
             		 	<input id="txtEmail" type="email" name="email" placeholder="Email Address" />
              			<input type="submit" id="forgot-password-button" class="btn btn-success" value="Send Password Email">
              			</form:form>
					</div>
						<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>