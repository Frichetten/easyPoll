<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us!</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/font-awesome-4.6.3/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="chartScript/Chart.js"></script> <!--Charts for Poll Data-->
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
                    <li><a href="#">About</a></li>
                    <li><a href="#">Contact</a></li>
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
            <h1>Contact</h1>
        </div>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Contact the Developers</h3>
                </div>
                <div class="panel-body">
                    <p>Nick Frichette: </p>
                    <p>Matthew Fletcher: </p>
                    <p>Nick Messina: </p>
                    <p>Kevin Dalle: </p>
                    <p>Casey Cook: clcoo10@ilstu.edu</p>
                </div>
            </div>
        </div>
       
    </div>

</body>
</html>
