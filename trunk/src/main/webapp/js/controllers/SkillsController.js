var skillsApp = angular.module('skillsApp', ['ngRoute']);

skillsApp.config(['$routeProvider', function ($routeProvider) {
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

// Creates SkillsController and calls 
skillsApp.controller('SkillsController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

    
}]);