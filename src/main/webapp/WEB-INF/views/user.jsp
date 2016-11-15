<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>easyPoll Alpha</title>
</head>
<body>

<h2>easyPoll Login</h2>
<form:form method="POST" action="/test/addUser">
   <table>
    <tr>
        <td><form:label path="username">Username: </form:label></td>
        <td><form:input path="username" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Password: </form:label></td>
        <td><form:input type="password" path="password" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
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
						<form:form method="POST" action="/test/addUser">
						<form:input id="txtEmail" type="text" placeholder="Email" path="username"/>
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