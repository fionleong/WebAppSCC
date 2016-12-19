var loginApp = angular.module('loginApp', ['ngRoute']);

loginApp.config(['$routeProvider', function ($routeProvider) {
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


// url encoding for post requests
var jsonToUrlEncoded = function (o) {
    var str = [];
    for (var p in o)
        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(o[p]));
    return str.join("&");
};

// Creates loginController and calls login() 
loginApp.controller('LoginController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {
    
	$scope.login = function () {
        console.log("CALLED LOGIN()");

        var loginInfo = {
            email: $scope.email,
            pwd: $scope.password
        };

        $http.post('./rest/login', loginInfo)
            .then(function (response) {
                //sucessful callback
                console.log(response);

                if (response.data.status == "ok") {
                		$window.sessionStorage.clear();
                		$window.sessionStorage.setItem("email", loginInfo.email);
                		$window.sessionStorage.setItem("pass", loginInfo.pwd);
                    $window.location.href = "./user_profile_page.html";
                } else {
                    alert("USER NOT FOUND OR PASSWORD IS WRONG");
                }

                console.log("Resonse: " + response.data.status);
            }, function (err) {
                //error callback
                console.log(err);
            });
    };
    
}]);

loginApp.controller('LogoutController', function ($scope, $http, $window, $cookies) {
    $window.sessionStorage.clear();
    $window.location.href = "/login";
});

loginApp.controller('SuccessController', ['$scope', function ($scope) {
    $scope.message = "Success!!!";
}]);

/*
var loginController = function($scope, $http) {
	$scope.search = function() {
		var request = {
			method: 'GET',
			url: serviceUrl + $scope.user + "/" + $scope.pass
		};
		$http(request).then(
				
		function successCallback(response) { //success`	
			$scope.email = response.data.email;
		},
		function errorCallback(response) { //error
			$scope.email = serviceUrl + $scope.user + "/" + $scope.pass;
		});
	} //search
} //loginController
loginApp.controller('loginController', loginController);*/
