var registerApp = angular.module('registerApp', ['ngRoute']);

registerApp.config(['$routeProvider', function ($routeProvider) {
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

// Creates RegisterController and calls register() 
registerApp.controller('RegisterController', ['$scope', '$http','$location', '$window',function($scope, $http, $location, $window) {
  
  $scope.register = function() {
      console.log("CALLED REGISTER()");
      
      var registerInfo = {
          firstName: $scope.user.firstName,
          lastName: $scope.user.lastName,
          gender: $scope.user.gender,
          birthday: $scope.user.dob,
          address: $scope.user.address,
          city: $scope.user.city,
          state: $scope.user.state,
          zipCode: $scope.user.zipCode,
          email: $scope.user.email,
          password: $scope.user.password,
          accType: $scope.user.accType,
      };
//      alert("birthday" + $scope.user.dob);
    $http.post('./rest/signup/register', registerInfo)
        .then(function(response){
        //sucessful callback
        console.log("Response: " + response.data.status);
        
        if(response.data.status == "Successful"){
            $window.location.href="./signup_successful.html";
        }else{
            alert("Unable to insert user.");
        }    
    }, function(err){
        //error callback
        console.log(err);
    });
  };
}]);