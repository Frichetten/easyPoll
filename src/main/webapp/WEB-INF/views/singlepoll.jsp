<!DOCTYPE html>
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
			<script>
			$(document).ready(function () {
				$('#landing-title').fadeIn(3000).removeClass('hidden');								
				$(".poll-ad-container").hover(function(){
					$(this).addClass(".vote-in-poll");
					$('.vote-in-poll').show();
				},function(){
					$('.vote-in-poll').hide();
				});
				
			});
			</script>
			<script language="javascript"><!--
			function myFunction() {
				//do stuff
			}
		//--></script>
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

	<div class="poll-header">
		<div class="container">
			<h1>Poll Name</h1>
		</div>
	</div>
	<div class="container poll-content">
		<div class="col-md-9">
			<div class="poll" id="poll-answer">
				Poll Questions
			</div>
		</div>
		<div class="col-md-3">
			<div class="poll poll-side-data">
				<div class="col-md-12">
					data
				</div>
			</div>
			<div class="poll poll-side-data">
				<div class="col-md-12">
					data
				</div>
			</div>
		</div>
		
	</div>
	<div class="container">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#">Additional Info</a></li>
		</ul>
		<div class="col-md-12">
			It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
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
	
	
	
	
	
	
	</body>
</html>