<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>

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
            <h1>User Statistics</h1>
        </div>
        <div class="row">

            <div class="col-md-3 col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Number of polls created</h3>
                    </div>
                    <div class="panel-body">
                        5
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Number of polls voted in</h3>
                    </div>
                    <div class="panel-body">
                        10
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Most Popular Poll</h3>
                    </div>
                    <div class="panel-body">
                        Favorite Color
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

        </div>

    </div>

</body>
</html>
