var searchApp = angular.module('searchApp', ['ngRoute']);

searchApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
    when('/login', {
        templateUrl: 'login.html',
        controller: 'LoginController'
    }).
    when('/register', {
        templateUrl: 'signup.html',
        controller: 'RegisterController'
    }).
    when('/success', {
        templateUrl: 'signup_successful.html',
        controller: 'SuccessController'
    }).
    when('/contact', {
        templateUrl: 'contact.html',
        controller: 'ContactController'
    }).
    otherwise({
        redirectTo: '/login'
    });
}]);

// Creates SearchController and calls
searchApp.controller('SearchController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

    var serviceUrl = './rest/getAllUsers';
    var request = {
        method: 'GET',
        url: serviceUrl
    };
    $http(request).success(function (data) { //success`	

            $scope.user = data;
            //
            //            $window.sessionStorage.setItem("uID", response.data.userId);
        },
        function errorCallback(response) { //error
            console.log("failed");
        });

    $scope.moreInfo = function() {
//        $window.sessionStorage.clear();
//        $window.sessionStorage.setItem("caregiver_uID", uID);
        window.location.href = "./search_caregiver_details.html";
    }
}]);
