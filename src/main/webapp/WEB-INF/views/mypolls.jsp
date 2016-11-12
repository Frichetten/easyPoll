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
                <button type="button" style="background-color:#2ecc71;"class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
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
                    <li><a href="/test/communitypolls">Community Polls</a></li>
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
                <h1>My Polls</h1>
            </div>
        <p>This page contains the polls you have created and been invited to</p>
        <button type="button" onclick="location.href='/test/createpoll'" class="btn btn-default">Create Poll</button>
        <table id="tableId" class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${Title0}</td>
                    <td hidden="true">${id0}</td>
                    <td>lorem ipsum</td>
                </tr>
                <tr>
                    <td>${Title1}</td>
                    <td hidden="true">${id1}</td>
                    <td>lorem ipsum</td>
                </tr>
                <tr>
                    <td>${Title2}</td>
                    <td hidden="true">${id2}</td>
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