<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>easyPoll Alpha</title>

			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
			<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" /> 
			<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
			<script>
			$(document).ready(function () {
				$('#landing-title').fadeIn(3000).removeClass('hidden');								
				
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
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="../navbar-static-top/">Login</a></li>
					<li><a href="../navbar-fixed-top/">Signup</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="slider-container">
		<div class="container">
		<div class="col-md-12 slide-text-container landing-padding">
			<h2 class="slide-text">easyPoll lets you engage create polls, vote in polls, and view statistics in real time</h2>
			<a href="file:///Users/nicholasmessina/Documents/easypoll/easyPoll/home.html#home-vote"><button type="button" class="btn btn-info" data-toggle="modal" data-target="#login-modal">Vote in Community Polls</button></a>
		</div>
	    </div>
	<a name="home-vote" style="display:block;margin-top:20px;"></a>
	</div>
	</div>
	<div class="call-to-action">
		<h1>Welcome to Our Polling Community!</h1>
		<h2>Vote in polls that interest you</h2>
	</div>
	<div class="container main-content">
	<div class="col-md-6">
		<div class="poll-ad-container">
			<div class="poll-description">
				<h3>Poll Name</h3>
				<span> Poll Description asd asd asd </span>
			</div>
			<div class="vote-in-poll">
				<span>Vote in this Poll</span>
				<i class="fa fa-bar-chart" aria-hidden="true"></i>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="poll-ad-container">
			<div class="poll-description">
				<h3>Poll Name</h3>
				<span> Poll Description asd asd asd </span>
			</div>
			<div class="vote-in-poll">
				<span>Vote in this Poll</span>
				<i class="fa fa-bar-chart" aria-hidden="true"></i>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="poll-ad-container">
			<div class="poll-description">
				<h3>Poll Name</h3>
				<span> Poll Description asd asd asd </span>
			</div>
			<div class="vote-in-poll">
				<span>Vote in this Poll</span>
				<i class="fa fa-bar-chart" aria-hidden="true"></i>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="poll-ad-container">
			<div class="poll-description">
				<h3>Poll Name</h3>
				<span>Poll Description asd asd asd </span>
			</div>
			<div class="vote-in-poll">
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
	
	
	
	
	
	
	</body>
</html>