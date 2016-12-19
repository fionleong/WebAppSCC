var skillsApp = angular.module('skillsApp', ['ngRoute']);

skillsApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/login', {
        templateUrl: 'login.html',
        controller: 'LoginController'
    }).when('/register', {
        templateUrl: 'signup.html',
        controller: 'RegisterController'
    }).when('/success', {
        templateUrl: 'signup_successful.html',
        controller: 'SuccessController'
    }).when('/contact', {
        templateUrl: 'contact.html',
        controller: 'ContactController'
    }).otherwise({
        redirectTo: '/login'
    });
}]);

// Creates SkillsController and calls
skillsApp.controller('SkillsController', ['$scope', '$http', '$location', '$window', '$filter',
		function ($scope, $http, $location, $window, $filter) {

        var uID = $window.sessionStorage.getItem("uID");

        var serviceUrl = './rest/getUserSkills/' + uID;
        var request = {
            method: 'GET',
            url: serviceUrl
        };
        $http(request).then(

            function successCallback(response) { // success`
                console.log(response.data);
                $scope.parkinsons = response.data.parkinsons;
                $scope.alzheimers = response.data.alzheimers;
                $scope.stroke = response.data.stroke;
                $scope.cancer = response.data.cancer;
                $scope.hospital = response.data.hospitalSitter;
                $scope.resume = response.data.resume;

            },
            function errorCallback(response) { // error
                console.log("failed");
            });

        var serviceUrl = './rest/getUserSchedule/' + uID;
        var request = {
            method: 'GET',
            url: serviceUrl
        };
        $http(request).then(

            function successCallback(response) { // success`
                console.log(response.data);
                $scope.monday = response.data.availableMo;
                $scope.tuesday = response.data.availableTu;
                $scope.wednesday = response.data.availableWe;
                $scope.thursday = response.data.availableTh;
                $scope.friday = response.data.availableFi;
                $scope.saturday = response.data.availableSa;
                $scope.sunday = response.data.availableSu;

            },
            function errorCallback(response) { // error
                console.log("failed");
            });

        $scope.logout = function () {
            $window.sessionStorage.clear();
            $window.location.href = "./index.html";
        }
}]);
