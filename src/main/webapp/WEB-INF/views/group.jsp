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

    <div class="container">
        <div class="page-header">
            <h1>${groupNameAndPollName}</h1>
        </div>
        <form:form method="POST" action="/test/addusertogroup">
        <input type="text" id="groupNum" name="groupNUM" value="${groupNum}" hidden="true"/>
        <input type="text" id="userName" name="usernameString" placeholder="Username to Add"/>
        <input type="submit" id="btnLogin" class="btn btn-success" value="Add User">
        </form:form>
        <form:form method="GET" action="/test/singlepoll/${pollNum}">
        <input type="submit" id="btnFind" class="btn btn-success" value="Go To Poll">
        </form:form>
        <table id="groupUsers" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>UserName</th>
                    <th>${tableHeader}</th>
                </tr>
            </thead>
            <tbody>
                ${groupMembers}
            </tbody>
        </table>
        <div class='container' ${creatorHide}>
	        <form:form method="POST" action="/test/deletegroup/${groupNum}">
	        <input type="text" id="groupNum" name="groupNUM" value="${groupNum}" hidden="true"/>
	        <input type='submit' id='bt' class='btn btn-success' value='DELETE POLL GROUP'>
	        </form:form>
	    </div>
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
                            <form:input id="txtUsername" type="text" placeholder="Username" path="username" />
                            <form:input id="txtEmail" type="email" placeholder="Email Address" path="email" />
                            <form:input id="txtPassword" type="password" placeholder="Password" path="password" />
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
                            <form:input id="txtEmail" type="email" placeholder="Email Address" path="email" />
                            <form:input id="txtPassword" type="password" placeholder="Password" path="password" />
                            <input type="submit" id="forgot-password-button" class="btn btn-success" value="Login">
                        </form:form>
                    </div>
                    <div class="modal-footer"></div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
function addRowHandlers() {
            var table = document.getElementById("groupUsers");
            var rows = table.getElementsByTagName("tr");
            for (i = 0; i < rows.length; i++) {
                var currentRow = table.rows[i];
                var createClickHandler =
                    function (row) {
                        return function () {
                        	var cell = row.getElementsByTagName("input")[0];
                            var id = cell.value;
                            cell = row.getElementsByTagName("input")[1];
                            var name = cell.value;
                            var accept = "accept";
                            if (accept == "${access}t"){
                            	document.location = "/test/deleteuserfromgroup/"+id+"/"+name;
                            } else {
                            	
                            }
                        };
                    };

                currentRow.onclick = createClickHandler(currentRow);
            }
        }
        window.onload = addRowHandlers();
</script>
</html>
	