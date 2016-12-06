<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<html>
	<head>
		<title>Edit a Poll-easyPoll</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
			$(document).ready(function () {
				var i = 1;

				$('#add-answer').click(function() {
					i++;
					if(i < 11) {
					$('#answerParams').clone().attr('id','answer' + $(this).index()).insertBefore('#answerParams');

					$('#answerParams').attr("placeholder","Answer " + i);
					$('#answerParams').attr("value","");
					if(i == 10) {
						$('#add-answer').hide();
					}
					}
				})
			});
		
		</script>
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
					<ul class="nav navbar-nav navbar-right">
						<li>${login}</li>
						<li>${signup}</li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container">
					<div class="page-header">
						<h1><i class="fa fa-bar-chart fa icon-color" aria-hidden="true"></i>Modify a Poll</h1>
					</div>
									<p><tt id="results"></tt></p>
					<form:form method="POST" action="/test/updatePoll/${pollId}">
						<div class="col-md-9">
							<div class="form-group row">
								<label for="PollName" class="control-label">Poll Name</label>
								<input type="text" value="${pollName}" class="form-control" id="PollName" name="PollName" placeholder="Name" />
							</div>
					  		<div class="form-group row">
								<label for="PollQuestion" class="control-label">Question</label>
								<input type="text" value="${pollQuestion}" class="form-control" id="PollQuestion" name="pollQuestion" placeholder="Question"/>
							</div>
							<div class="form-group row">
								<label for="PollDescription" class="control-label">Description</label>
								<input type="text" value="${pollDesc}" class="form-control" id="PollDescription" name="pollDescription" placeholder="Description"/>
							</div>
							<div class="form-group row">
								<label for="pollview" class="control-label">Is this a Public or Private Poll?</label>
								<div class="radio">
									<label>
										<input type="radio" ${isPublic} name="pollType" id="Public" value="public"/>
										Public
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" ${isPrivate} name="pollType" id="Private" value="private"/>
										Private
									</label>
								</div>
							</div>
							<div class="form-group row">
								<label for="End at How Many Votes?" class="control-label">End at How Many Votes?</label>
								<input type="number" value="${endTotal}" class="form-control" id="endTotal" name ="endTotal" placeholder="Total Votes" />
							 </div>
						<div style="padding-top:5px; padding-bottom:20px;">
							<div class="bottom-page">
							</div>
						</div>
						<div class="form-group row">
							<input type="submit" id="btnLogin" class="btn btn-success" value="Edit Poll">
						</div>
							
					</form:form>
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
		
			<div class="col-md-3">
				<div class="createpoll-sidebar">
					<div class="sidebar-container">
						<span> easyPoll has had </span>
						<div class="featured-text-contain">
							<h3 class="featured-text">${numberPolls}</h3>
						</div>
						<span> Polls Posted </span>
						<h3>Cheers to one more!</h3>
					</div>
				</div>
			</div>
		</div>
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
		
		<script>
		  
		  
 		  $( "#create-poll" ).click(function(){
 			  $.ajax({
			   type: "POST",
			   url: "/test/createpollfunction",
			   data: showValues,
			   contentType: "application/json; charset=utf-8",
			   dataType: "json",
			   success: function(msg) {
			   alert('Data Sent to create Poll' + showValues);
			   }
		  	});
 		  });
		</script>

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
