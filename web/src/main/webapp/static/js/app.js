
var app = angular.module('webAlbum', []);
app.controller('photoCtrl', function($scope, $http) {
    $scope.list = null;
    $http.get("result.json")
    .success(function (data) {$scope.list = data;});
    });