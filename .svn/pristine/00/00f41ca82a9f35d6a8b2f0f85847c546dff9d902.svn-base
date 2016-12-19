var userProfileApp = angular.module('userProfileApp', ['ngRoute']);

userProfileApp.config(['$routeProvider', function ($routeProvider) {
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

// Creates UserProfileController and calls 
userProfileApp.controller('UserProfileController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

    
}]);