<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Test</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="static/css/styles.css"/>

</head>
<body>
    <div class="form">
        <form>
            <div class="form-group">
                <label for="fname">First Name</label>
                <input type="text" class="form-control" id="fname" placeholder="First Name">
            </div>
            <div class="form-group">
                <label for="sname">Second Name</label>
                <input type="text" class="form-control" id="sname" placeholder="Second Name">
            </div>
            <div class="form-group">
                <label for="birthday">Birthday</label>
                <input type="text" class="form-control" id="birthday" placeholder="Birthday">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</body>
</html>
