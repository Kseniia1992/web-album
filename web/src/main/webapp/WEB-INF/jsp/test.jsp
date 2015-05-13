<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html ng-app>
<head>
    <title>Test</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
    <link rel="stylesheet" href="static/css/styles.css"/>

    <script type="text/javascript">
        function Test($scope){
            $scope.arr = [];
            $scope.addData=function(){
                $scope.arr.push({firstname:$scope.fname,
                                 secondname:$scope.sname,
                                 birth:$scope.birthday});
          /*      $scope.arr.push($scope.fname);
                $scope.arr.push($scope.sname);
                $scope.arr.push($scope.birthday); */
            }
        }
    </script>
</head>

<body>
    <div class="form" ng-controller="Test">
        <form>
            <div class="form-group">
                <label for="fname">First Name</label>
                <input type="text" class="form-control" id="fname" placeholder="First Name" ng-model="fname">
            </div>
            <div class="form-group">
                <label for="sname">Second Name</label>
                <input type="text" class="form-control" id="sname" placeholder="Second Name" ng-model="sname">
            </div>
            <div class="form-group">
                <label for="birthday">Birthday</label>
                <input type="text" class="form-control" id="birthday" placeholder="Birthday" ng-model="birthday">
            </div>
            <button type="submit" class="btn btn-default" ng-click="addData()">Submit</button>
        </form>


    <table class="table table-bordered table-hover table-striped" data-ng-app>
        <thead>
        <tr>
            <th>First Name</th>
            <th>Second Name</th>
            <th>Birthday</th>
        </tr>
        </thead>
        <tbody ng-repeat="user in arr">
        <tr>
            <td>{{user.firstname}}</td>
            <td>{{user.secondname}}</td>
            <td>{{user.birth}}</td>
        </tr>
        </tbody>
    </table>
    </div>

</body>

</html>
