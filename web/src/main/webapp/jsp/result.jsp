<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html ng-app="webAlbum">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring MVC - File Upload</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link href="jumbotron-narrow.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.min.js"></script>
    <script type="text/javascript" src="webjars/angularjs/1.3.8/angular-resource.min.js"></script>

</head>

<body ng-controller="PhotoController">

<div class="container">

    <!-- Static navbar -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Web album</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/web-album/picture.html">File Upload</a></li>
                    <li><a href="/web-album/result.html">Photo</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</div>

<div align="center">
   <a href="/web-album/picture.html"></a>
   <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th>Photo name</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
            <tr ng-repeat="photo in list">
                <td>{{ photo.name }}</td>
                <td>{{ photo.additionDate }}</td>
            </tr>
        </tbody>
    </table>
</div>

<script src="js/app.js"></script>

</body>
</html>

