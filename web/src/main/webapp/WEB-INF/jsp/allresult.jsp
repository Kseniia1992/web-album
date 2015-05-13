<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring MVC - File Upload</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link href="jumbotron-narrow.css" rel="stylesheet">
    <link rel="stylesheet" href="static/css/styles.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>

<body>

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
                    <li><a href="/web-album/result.html">Photo - Doesn't work</a></li>
                    <li><a href="/web-album/allresult.html">Photo - Works</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</div>

<div  class="content" align="center">
    <table class="table table-bordered table-hover table-striped" data-ng-app>
        <thead>
        <tr>
            <th>Photo name</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${photos}" var="photo">
            <tr>
                <td>
                        ${photo.name}
                </td>
                <td>
                        ${photo.additionDate}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>