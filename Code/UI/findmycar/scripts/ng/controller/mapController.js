app.controller('mapController', function($scope, $http,NgMap) {

/*	$scope.map;
	NgMap.getMap().then(function(map) {
		$scope.map = map;
	});
	*/
	$scope.userLat = 40.74;
	$scope.userLog = -74.18;
	$scope.cordinates = "[19.143322, 72.9949816]";
	$scope.positions = [{lat:19.143322,lng:72.9949816}];
	$scope.zoom = 4;
	$scope.mayTypeId = "TERRAIN";
	$scope.lostLocation = "test";
	$scope.cities = {
		      mumbai: {population:2714856, position: [19.078044, 72.8644754]},
		      newyork: {population:8405837, position: [19.1164856, 72.8977961]},
		      losangeles: {population:3857799, position: [34.052234, -118.243684]},
		      vancouver: {population:603502, position: [49.25, -123.1]},
	};
	
	$scope.getRadius = function(num) {
	      return 1000;
	};

	 $scope.placeMarker = function(e) {
	   	 console.log(e.latLng.lat() + " " + e.latLng.lng());
	 /*  	 $scope.lostLocation = e.latLng.lat() + " " + e.latLng.lng();
	     var marker = new google.maps.Marker({position: e.latLng, map: $scope.map});
	     $scope.map.panTo(e.latLng);*/
	   	 var ll = e.latLng;
	   	$scope.positions.length = 0;
	   	$scope.positions.push({lat:ll.lat(), lng: ll.lng()});
	      
	 };
	 
	 $scope.clearLocation = function(){
		 $scope.map.panTo(e.latLng);
	 };

	 
	 
});