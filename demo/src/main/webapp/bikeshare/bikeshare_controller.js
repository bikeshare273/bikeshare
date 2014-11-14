
var bikeshareapp = angular.module('bikeshareapp', ['ngRoute', 'ngResource']);

// configure our routes
    bikeshareapp.config(function($routeProvider) {
        $routeProvider

            // route for the login page
            .when('/', {
                templateUrl : 'login.html',
                controller  : 'loginController'
            })

            // route for the signup page
            .when('/signup', {
                templateUrl : 'signup.html',
                controller  : 'signupController'
            })

             // route for the authentication page
            .when('/home', {
                templateUrl : 'home.html',
                controller  : 'authenticatecontroller'
            })

            .otherwise({ redirectTo: '/' });
    });


    /*//interceptor
    bikeshareapp.factory('httpInterceptor', function httpInterceptor ($q, $window, $location) {
		  return function (promise) {
		      var success = function (response) {
		          return response;
		      };

		      var error = function (response) {
		          if (response.status === 401 || response.status === 400) {
		              $location.url('/');
		          }

		          return $q.reject(response);
		      };

		      return promise.then(success, error);
	  		};
	});*/

 // create the controller and inject Angular's $scope
    bikeshareapp.controller('loginController', function($scope) {
        // create a message to display in our view
        console.log('main');
        $scope.homeactive = 'active';
    });

    bikeshareapp.controller('signupController', function($scope) {
        console.log('signup');
        $scope.signupactive = 'active';
    });

    bikeshareapp.controller('loginformcontroller', function($scope, $http, $location, $q){
        console.log("1");
         $scope.loginform_getAuthenticated = function(item, event) {
           var data = {
              username : $scope.loginform_username,
              password  : $scope.loginform_password
           };
            var response = $http.post("../../api/v1/login", data, {});
               response.success(function(dataFromServer, status, headers, config) {
                  if(dataFromServer.sessionId != null){
                    $location.url('/home');
                  }else{
                    $scope.loginform_error = "Invalid Username/Password";
                  }
                  
               });
                response.error(function(data, status, headers, config) {
                  if (response.status === 401 || response.status === 400) {
                      $scope.loginform_error = "Invalid Username/Password";
                      $location.url('/');
                      return $q.reject(response);
                  }
               });
        };
    });

    bikeshareapp.controller('authenticatecontroller', function($scope){
      console.log("home");
       
    });

    bikeshareapp.controller('signupformcontroller', function($scope, $http, $location, $q){
        console.log("1");
         $scope.signupform_signup = function(item, event) {
           console.log("--> Submitting form "+$scope.signupform_name+" "+$scope.signupform_email);
           console.log("--> Submitting form "+$scope.signupform_phone+" "+$scope.signupform_address);
           console.log("--> Submitting form "+$scope.signupform_zipcode+" "+$scope.signupform_username);
           console.log("--> Submitting form "+$scope.signupform_password);
           var data = {
              name : $scope.signupform_name,
              email  : $scope.signupform_email,
              phone : $scope.signupform_phone,
              address  : $scope.signupform_address,
              zipcode : $scope.signupform_zipcode,
              username  : $scope.signupform_username,
              password : $scope.signupform_password
           };
            var response = $http.post("../../api/v1/users", data, {});
               response.success(function(dataFromServer, status, headers, config) {
                  console.log("bhen"+dataFromServer.message);
                  if(dataFromServer.message == null || dataFromServer.message == ""){
                    console.log("sala ithe");
                     $scope.signupform_success = "User created successfully";
                  }else{
                    $scope.signupform_error = dataFromServer.message;
                  }
                  
               });
                response.error(function(data, status, headers, config) {
                  if (response.status === 401 || response.status === 400) {
                      $scope.loginform_error = "Invalid request";
                      $location.url('/');
                      return $q.reject(response);
                  }
               });
        };
    });