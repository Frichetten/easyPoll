<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Welcome to EasyPoll!</title>

			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
    		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" type="text/css"/>
			<script src="${pageContext.request.contextPath}/resources/scripts/Chart.js"></script>
	 <!--Hides poll and show poll stats-->
        <script type="text/javascript">
            $(document).ready(function () {
                $('#pollQA').hide();
                drawchart();
                $('#submitPoll').on('click', function () {
                    $('#pollQA').hide();
                    $('#pollStats').show();
                    drawchart();
                });
            });
           
        </script>
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

	<div class="poll-header">
		<div class="container">
			<h1>${pollName}</h1>
		</div>
	</div>
	<div class="container">
        <div class="row">
            <!--Poll that people vote in-->
            <div class="col-md-6" id="pollQA">
                <div class="poll-panel" id="">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center poll-heading">
                            <h3 class="panel-title">${pollQuestion}</h3>
                        </div>
                        <div class="panel-body ">
                            <form>
                                <div class="row">
                                    <div class="form-group col-md-offset-5 col-sm-offset-5 col-xs-5">
                                        ${builder}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-md-offset-5 col-sm-offset-5 col-xs-5">
                                        <button type="button" id="submitPoll" name="submitPoll" class="btn btn-default"> Submit </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!--End poll that people vote in-->
            <!--Poll Statistics-->
            <div class="col-md-6" id="pollStats">
                <canvas id="myChart"></canvas>
                
            </div>
            <!--End Poll Statistics-->
            <!--More Poll Data-->
            <div class="col-md-6">
                <div class="poll-panel" >
                    <!--Poll Created By-->
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Poll Created By</h3>
                            </div>
                            <div class="panel-body">
                                ${posterUsername}
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <!--More Poll Data-->
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Data</h3>
                            </div>
                            <div class="panel-body">
                                Data about poll
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
        <!--Additional Info-->
	<div class="container">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#">Additional Info</a></li>
		</ul>
		<div class="col-md-12">
			It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
		</div>
	</div>
        <!--Footer-->
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
            function drawchart()
            {
                var ctx = document.getElementById("myChart");
                var myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: [${optionsList}],
                        datasets: [{
                            label: '# of Votes',
                            data: [${valuesList}],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(75, 192, 192, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255,99,132,1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(75, 192, 192, 1)',
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            }
        </script>
	</body>
</html>