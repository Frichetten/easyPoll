<!DOCTYPE html>
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
					<li><a href="/test/home">Home</a></li>
					<li><a href="#">My Polls</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id="logged-out">
					<li><a href="../navbar-static-top/" data-toggle="modal" data-target="#login-modal">Login</a></li>
					<li><a href="../navbar-fixed-top/" data-toggle="modal" data-target="#create-account-modal">Signup</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id="user-logged-in">
					<li><a>Welcome User</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	 <div class="container">
            <div class="page-header">
                <h1>Communtiy Polls</h1>
            </div>
        <p>Click on a poll to vote!</p>
        <table id="tableId" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Poll 1</td>
                    <td>lorem ipsum</td>
                </tr>
                <tr>
                    <td>Poll 2</td>
                    <td>lorem ipsum</td>
                </tr>
                <tr>
                    <td>Poll 3</td>
                    <td>lorem ipsum</td>
                </tr>
            </tbody>
        </table>
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
						<form onsubmit="myFunction(); return false;">
						<input id="txtEmail" type="email" placeholder="Email">
						<input id="txtPassword" type="password" placeholder="Password">
						<input type="submit" id="btnLogin" class="btn btn-success" value="Login">
						</form>
						<div class="login-help">
						<a href="#" id="btnSignup">Register</a> | <a href="#" id="Logout">Forgot Password</a>
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
						<form onsubmit="myFunction(); return false;">
						<input id="txtEmail" type="email" placeholder="Email">
						<input id="txtPassword" type="password" placeholder="Password">
						<input type="submit" id="btnLogin" class="btn btn-success" value="Create Account">
						</form>
					</div>
						<div class="modal-footer"></div>
				</div>
			</div>
		</div>

	
	
	
	<!--Adds a click event to each table row-->
    <script type="text/javascript">
        function addRowHandlers() {
            var table = document.getElementById("tableId");
            var rows = table.getElementsByTagName("tr");
            for (i = 0; i < rows.length; i++) {
                var currentRow = table.rows[i];
                var createClickHandler =
                    function (row) {
                        return function () {
                            var cell = row.getElementsByTagName("td")[0];
                            var id = cell.innerHTML;
                            alert("id:" + id);
                        };
                    };

                currentRow.onclick = createClickHandler(currentRow);
            }
        }
        window.onload = addRowHandlers();
    </script>
	</body>
</html>