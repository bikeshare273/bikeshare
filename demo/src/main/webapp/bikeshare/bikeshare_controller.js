var bikeshareapp = angular.module('bikeshareapp', [ 'ngRoute', 'ngResource',
		'ngMap', 'uiGmapgoogle-maps']);

// configure our routes
bikeshareapp.config(function($routeProvider) {
	$routeProvider

	// route for the login page
	.when('/', {
		templateUrl : 'template.html',
		controller : 'loginController'
	})

	// route for the signup page
	.when('/signup', {
		templateUrl : 'signup.html',
		controller : 'signupController'
	})

	// route for the authentication page
	.when('/home', {
		templateUrl : 'home.html',
		controller : 'authenticatecontroller'
	})

	.otherwise({
		redirectTo : '/'
	});
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

bikeshareapp.controller('loginformcontroller', function($scope, $http,
		$location, $q) {
	console.log("1");
	$scope.loginform_getAuthenticated = function(item, event) {
		var data = {
			username : $scope.loginform_username,
			password : $scope.loginform_password
		};
		var response = $http.post("../../api/v1/login", data, {});
		response.success(function(dataFromServer, status, headers, config) {
			if (dataFromServer.sessionId != null) {
				$location.url('/home');
			} else {
				$scope.loginform_error = "Invalid Username or Password";
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

bikeshareapp.controller('authenticatecontroller', function($scope,$filter) {
	console.log("home");
	$scope.displayForm = true;
	$scope.map = {
			center: {
				latitude: 45, 
				longitude: -73
			},
			zoom: 15,
		   };
	
	
	
	
	$scope.searchMapOnZipCode = function(zipcode){
		var lat = '';
	    var lng = '';
	    console.log(zipcode);
	    var address = zipcode;
	    geocoder = new google.maps.Geocoder();
	    geocoder.geocode( { 'address': address}, function(results, status) {
	      if (status == google.maps.GeocoderStatus.OK) {
	        //$scope.map.control.getGMap().setCenter(results[0].geometry.location);
	        lat = results[0].geometry.location.lat();
	        lng = results[0].geometry.location.lng();
	        console.log('Latitude: ' + lat + ' Logitude: ' + lng);
	        $scope.map.center = {
			        latitude: lat,
			        longitude: lng
			    };
			    $scope.$apply();
	        
	      } else {
	        alert('Geocode was not successful for the following reason: ' + status);
	      }
	    });
	    };
	
	
	//markers
	$scope.marker = {
		      id: 0,
		      coords: {
		        latitude: 37.3357468,
		        longitude: -121.8842035
		      },
		      options: { draggable: true },
		      events: {
		        click: function (marker, eventName, args) {
		        	console.log("clicked open form");
		        	
		        	$scope.location_id = "12345";
		        	$scope.location_name = "san jose state university";
		        	
		        	//set from time drop down
		        	$scope.timelist = [];
		        	var j = 0;
		        	for (i = $filter('date')(Date.now(), 'H'); i <= 24; i++) { 
		        		console.log(i);
		        		$scope.timelist[j] = {
		        		        value: i,
		        		        text: i
		        		    }; 
		        		j=j+1;
		        	}
		        	
		        	$scope.displayForm = false;
		        }
		      }
	};
	
	$scope.unlockToTime = function($fromtimeVal){
		console.log("to drop box "+$fromtimeVal.text);
		//set to time drop down
    	$scope.totimelist = [];
    	var j = 0;
		for (i = 1+parseInt($fromtimeVal.text); i < 25; i++) { 
    		console.log("totimelist"+i);
    		$scope.totimelist[j] ={
    		        value: i,
    		        text: i
    		    }; 
    		j=j+1;
    	}
	};
	

	$scope.getLocation = function(){
		console.log("mappppp");
		if(navigator.geolocation) {
			
			var onSuccess = function(position) {
			    $scope.map.center = {
			        latitude: position.coords.latitude,
			        longitude: position.coords.longitude
			    };
			    $scope.$apply();
			}
			function onError(error) {
			    console.log('code: '    + error.code    + '\n' + 'message: ' + error.message + '\n');
			}
			navigator.geolocation.getCurrentPosition(onSuccess, onError);
			
			
		   /* navigator.geolocation.getCurrentPosition(function(position) {

		      $scope.map = {center: {latitude: position.coords.latitude, longitude: position.coords.longitude }, zoom: 14 };
		      
		    }, function() {
		      handleNoGeolocation(true);
		    });*/
		  } else {
		    // Browser doesn't support Geolocation
		    handleNoGeolocation(false);
		  }
		
	};
	$scope.googlemap = "googlemap.html";
	
	console.log("parsed");
	
});

bikeshareapp
		.controller(
				'signupformcontroller',
				function($scope, $http, $location, $q) {
					console.log("1");
					$scope.signupform_signup = function(item, event) {
						console.log("--> Submitting form "
								+ $scope.signupform_name + " "
								+ $scope.signupform_email);
						console.log("--> Submitting form "
								+ $scope.signupform_phone + " "
								+ $scope.signupform_address);
						console.log("--> Submitting form "
								+ $scope.signupform_zipcode + " "
								+ $scope.signupform_username);
						console.log("--> Submitting form "
								+ $scope.signupform_password);
						var data = {
							name : $scope.signupform_name,
							email : $scope.signupform_email,
							phone : $scope.signupform_phone,
							address : $scope.signupform_address,
							zipcode : $scope.signupform_zipcode,
							username : $scope.signupform_username,
							password : $scope.signupform_password
						};
						var response = $http.post("../../api/v1/users", data,
								{});
						response
								.success(function(dataFromServer, status,
										headers, config) {
									if (dataFromServer.message == null
											|| dataFromServer.message == "") {
										$scope.signupform_success = "Congrats " +$scope.signupform_name+ ". You are successfully registered";
									} else {
										$scope.signupform_error = dataFromServer.message;
									}
								});
						response.error(function(data, status, headers, config) {
							if (response.status === 401
									|| response.status === 400) {
								$scope.loginform_error = "Invalid request";
								$location.url('/');
								return $q.reject(response);
							}
						});
					};
				});
