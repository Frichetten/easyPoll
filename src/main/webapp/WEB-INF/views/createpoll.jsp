<!DOCTYPE html>
	<html>
	<head>
		<title>EasyPoll: Create A Poll</title>
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
					$('#answer').clone().attr('id','answer' + $(this).index()).insertBefore('#answer');		
					$('#answer').attr("placeholder","Answer " + i);
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
		<div class="container">
					<div class="page-header">
						<h1><i class="fa fa-bar-chart fa icon-color" aria-hidden="true"></i>Create a Poll</h1>
					</div>
									<p><tt id="results"></tt></p>
					<form>
						<div class="col-md-9">
							<div class="form-group row">
								<label for="PollName" class="control-label">Poll Name</label>
								<input type="text" class="form-control" id="PollName" name="PollName" placeholder="Name" />
							</div>
					  		<div class="form-group row">
								<label for="PollQuestion" class="control-label">Question</label>
								<input type="text" class="form-control" id="PollQuestion" name="PollQuestion" placeholder="Question" />
							</div>
							<div class="form-group row">
								<label for="AnswerType" class="control-label">Answer Type</label>
								<div class="radio">
									<label>
										<input type="radio" name="AnswerType" id="one-answer" value="One"/>
										One Answer
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="AnswerType" id="multiple-answers" value="multiple"/>
										Multiple Answers
									</label>
								</div>
							</div>
							<div class="form-group row">
								<label for="pollview" class="control-label">Is this a Public or Private Poll?</label>
								<div class="radio">
									<label>
										<input type="radio" name="pollview" id="public" value="public"/>
										Public
									</label>
								</div>
								<div class="radio">
									<label>
										<input type="radio" name="pollview" id="private" value="private"/>
										Private
									</label>
								</div>
							</div>
							<div class="form-group row">
								<label for="Answers" class="control-label">Answers</label>
								<input type="text" class="form-control" id="answer" name ="answer" placeholder="Answer 1" />
							 </div>
							 <div class="form-group row">
								<button type="button" id="add-answer" class="btn btn-default"><i class="fa fa-plus" aria-hidden="true"></i> 										Add Answer
								</button>
							</div>
						<div style="padding-top:5px; padding-bottom:20px;">
							<div class="bottom-page">
							</div>
						</div>
						<div class="form-group row">
							<button type="submit" class="btn btn-success btn-create" id="create-poll">Create Poll</button>
						</div>
					</form>
			</div>
		
			<div class="col-md-3">
				<div class="createpoll-sidebar">
					<div class="sidebar-container">
						<span> easyPoll has had </span>
						<div class="featured-text-contain">
							<h3 class="featured-text">572</h3>
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
		  function showValues() {
		    var str = $( "form" ).serialize();
		    $( "#results" ).text( str );
		  }
		  $( "input[type='checkbox'], input[type='radio']" ).on( "click", showValues );
		  $( "input[type='text']" ).keypress(showValues);
		  $( "#create-poll" ).click(showValues);
		  $( "select" ).on( "change", console.log(showValues));
		  showValues();
 
/* 		  $( "#create-poll" ).click(function(){
 */			  $.ajax({
			   type: "POST",
			   url: "/test/",
			   data: showValues,
			   contentType: "application/json; charset=utf-8",
			   dataType: "json",
			   success: function(msg) {
			   alert('Data Sent to create Poll' + showValues);
			   }
/* 		  	});
 */		  });
		</script>

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
