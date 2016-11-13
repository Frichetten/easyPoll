<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reported Polls</title>

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
        <div class="page-header">
            <h1>Reported Polls</h1>
        </div>
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