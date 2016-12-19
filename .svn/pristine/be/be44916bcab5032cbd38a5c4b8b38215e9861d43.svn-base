var contactApp = angular.module('contactApp', ['ngRoute']);

contactApp.config(['$routeProvider', function ($routeProvider) {
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

// create angular controller
contactApp.controller('ContactController', function($scope) {

  // function to submit the form after all validation has occurred            
  $scope.submitForm = function(isValid) {

    // check to make sure the form is completely valid
    if (isValid) {
        //var message = "Thank you for contacting us, we will get back to you as soon as possible.";
        //$scope.thanku = message;
    }

  };

});