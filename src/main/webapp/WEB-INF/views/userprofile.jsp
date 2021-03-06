<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile-easyPoll</title>

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
        <div class=page-header>
            <h1>User Statistics</h1>
        </div>
        <div class="col-md-12">

            <div class="col-md-3 col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Number Of Polls Created</h3>
                    </div>
                    <div class="panel-body">
                        ${numPolls}
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Number Of Polls Voted In</h3>
                    </div>
                    <div class="panel-body">
                        ${numVoted}
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Most Popular Poll</h3>
                    </div>
                    <div class="panel-body">
                        ${fave}
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Date Joined</h3>
                    </div>
                    <div class="panel-body">
                        11/12/16
                    </div>
                </div>
            </div>
        <div class="page-header">
            <h1>User Account</h1>
        </div>
        <div class="col-md-12">
            <form:form method="POST" action="/test/updateaccount">
                <div class="col-md-12">
                    <div class="form-group row form-inline">
                        <label for="email" class="control-label">Email:</label>
                        <input type="text" class="form-control" id="email" name="email" value='${email}' />
                    </div>
                    <div class="form-group row form-inline">
                        <label for="email" class="control-label">Password:</label>
                        <input type="text" class="form-control" id="email" name="password" placeholder="password" />
                    </div>
                    <div class="form-group row">
                        <input type="submit" id="btnUpdateAccount" class="btn btn-default" value="Update Account">
                    </div>
                </div>
            </form:form>
        </div>
            <div class="col-md-12">
            <div class="col-md-12">
            	<div class="form-group row" class="form-inline">
	            	<form:form method="POST" action="/test/deleteaccount">
	            	<input id="txtPassword" type="password" hidden="true" placeholder="Password" />
	            	<input type="submit" id="btnLogin" class="btn btn-danger" value="Delete Account">
	            	</form:form>
	            </div>
	        </div>  
   			</div>
   		<div class="col-md-12">
   			<button type="button" class="btn btn-info" data-toggle="modal" data-target="#send-ticket-modal">Send Support Ticket</button>
   		</div>


        </div>

    </div>
<!-- Send Support Ticket -->
    <div id="send-ticket-modal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <div class="forgotmodal-container text-center">
                        <h2>Send Support Ticket</h2><br>
                        <form:form method="POST" action="/test/sendsupportticket">
                        <textarea id="groupNameText" name="supportText" style="width: 500px; height: 300px;" placeholder="Type Your Feedback Here" required></textarea>
                        <input type="submit" id="for" class="btn btn-success" value="Send Support Ticket">
                        </form:form>                 
                    </div>
                    <div class="modal-footer"></div>
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
</body>
</html>
