
(function () {
  "use strict";

  var app = angular.module('validationApp', []);
    app.directive('valueMatches', ['$parse', function ($parse) {
    return {
      require: 'ngModel',
        link: function (scope, elm, attrs, ngModel) {
          var originalModel = $parse(attrs.valueMatches),
              secondModel = $parse(attrs.ngModel);
          // Watch for changes to this input
          scope.$watch(attrs.ngModel, function (newValue) {
            ngModel.$setValidity(attrs.name, newValue === originalModel(scope));
          });
          // Watch for changes to the value-matches model's value
          scope.$watch(attrs.valueMatches, function (newValue) {
            ngModel.$setValidity(attrs.name, newValue === secondModel(scope));
          });
        }
      };
    }]);
    
    
    var signUpController = function($scope, $http) {
    		
    		$scope.user = {
    				userId: 1,
    				email:"",
    				password:"",
    				firstName:"",
    				lastName:"",
    				dob:new Date(),
    				gender:"Female",
    				address:"",
    				city:"",
    				state:"",
    				zipCode:0,
    				caregiver:0,
    				verifiedEmail: 0,
    				verifiedCaregiver: 0
    		};
    		
    		$scope.register = function(){
    			window.alert("hello2");
    			//window.alert(angular.toJson($scope.user));
    			window.alert("hello3");
    			window.alert("stringify:" + JSON.stringify($scope.user));
    			
    			var request= {
    					method: 'POST',
    					url:'http://localhost:8080/optiplex/rest/register',
    					data:"helloSomething",
    					headers : {  
                            'Content-Type' : 'text/plain'
                        }
    			};
    			$http(request).then(
    					function successCallback(response) { //success`	
    						//window.alert("success");
    					},
    					function errorCallback(response) { //error
    						window.alert("helloFailed");
    						//window.alert(response.data);
    					});
    		};
    };
    app.controller('signUpController', signUpController);
    
}());