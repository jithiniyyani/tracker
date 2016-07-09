 app.controller('EventArgumentsController', function(NgMap) {
    var vm = this;
    NgMap.getMap().then(function(map) {
      vm.map = map;
    });
    vm.placeMarker = function(e) {
      var marker = new google.maps.Marker({position: e.latLng, map: vm.map});
      vm.map.panTo(e.latLng);
    }
  });