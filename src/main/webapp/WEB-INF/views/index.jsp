<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html >
  <head>
    <meta charset="UTF-8">
    <title>easyPoll Alpha</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
    $(document).ready(function () {
        $('#landing-title').fadeIn(3000).removeClass('hidden');
    });
    </script>
    <script language="javascript"><!--
function myFunction() {
    //do stuff
}
//--></script>


  </head>
  <body>
  <!--Landing Page-->
    <div class="container-fluid text-right">
      <a href="/test/home">Skip the Signup Process
      <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
    </div>
    <div class="container">
      <div class="container landing-padding">
        <h1 id="landing-title" class="title hidden">easy<span>Poll</span></h1>
        <nav>
        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#login-modal">Join Our Community</button>
        </nav>
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
              <form:form method="POST" action="/test/addUser">
              <form:input id="txtEmail" type="text" placeholder="Username" path="username"/>
              <form:input id="txtPassword" type="password" placeholder="Password" path="password"/>
              <input type="submit" id="btnLogin" class="btn btn-success" value="Login">
              </form:form>
              <div class="login-help">
              <a href="#" id="btnSignup">Register</a> | <a href="#" id="Logout">Forgot Password</a>
              </div>
            </div>
              <div class="modal-footer"></div>
          </div>
        </div>
      </div>

  </body>
</html>
