<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Administrator Portal</title>

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
        <div class="page-header" ${hide}>
            <h1 >Reported Polls</h1>
        </div>
        <table id="tableId" class="table table-striped table-hover" ${hide}>
            <thead>
                <tr>
                    <th ${hide}>Title</th>
                    <th ${hide}>Description</th>
                    <th ${hide}>Latest Reporter</th>
                    <th ${hide}># of Reports</th>
                </tr>
            </thead>
            <tbody>
                ${polls}
            </tbody>
        </table>
    </div>
    
    <div class="container" ${hide}>
    	<div class="page-header" ${hide}>
    		<h1>Newsletter</h1>
    	</div>
    		<form:form method="POST" action="/test/sendnewsletter" >
    			<textarea rows="4" cols="50" name="textarea" ></textarea>
    			<input type="submit" id="btnLogin"  class="btn btn-success" value="Send Newsletter" >
    			<h3>${sentNewsletter}</h3>
    		</form:form>
    </div>
    
    <div class="container">
        <div class="page-header" ${hide}>
            <h1 >Feedback Messages</h1>
        </div>
        <table id="feedbackTable" class="table table-striped table-hover" ${hide}>
            <thead>
                <tr>
                    <th ${hide}>Message</th>
                </tr>
            </thead>
            <tbody>
                ${feedback}
            </tbody>
        </table>
    </div>
    
    <div class="container">
        <div class="page-header" ${hide}>
            <h1 >Support Tickets</h1>
        </div>
        <table id="supportTable" class="table table-striped table-hover" ${hide}>
            <thead>
                <tr>
                    <th ${hide}>Ticket Id</th>
                    <th ${hide}>Username</th>
                    <th ${hide}>Ticket Message</th>
                </tr>
            </thead>
            <tbody>
                ${supportTicket}
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
						<h2>Admin Login</h2><br>
						<form:form method="POST" action="/test/adminLogin">
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
						<h2>Become an Admin</h2><br>
						<form:form method="POST" action="/test/adminregister">
						<input id="username" type="text" name="username" placeholder="Username">
						<input id="txtPassword" type="password" name="password" placeholder="Password">
						<input id="txtPassword" type="password" name="email" placeholder="Secret Admin Key">
						<input type="submit" id="btnLogin" class="btn btn-success" value="Create Account">
						</form:form>
					</div>
						<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		
		<!-- Forgot password modal -->
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
	</div>
    <!--Adds a click event to each table row-->
    
    <!-- Adds listeners to the reported polls table so that the admin can go directly to the poll -->
    <script type="text/javascript">
        function addRowHandlers() {
            var table = document.getElementById("tableId");
            var rows = table.getElementsByTagName("tr");
            for (i = 0; i < rows.length; i++) {
                var currentRow = table.rows[i];
                var createClickHandler =
                    function (row) {
                        return function () {
                            var cell = row.getElementsByTagName("td")[1];
                            var id = cell.innerHTML;
                            document.location = "/test/singlepoll/"+id;
                        };
                    };

                currentRow.onclick = createClickHandler(currentRow);
            }
        }
        window.onload = addRowHandlers();
    </script>
</body>
</html>