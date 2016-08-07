app.run(function(LoginService,$http){

  $http.get("http://localhost/StolenVehicle/user").then(function(data) {
       LoginService.setLoginStatus(true);
       LoginService.setUser(data.data.user);
   }, function(data) {
      LoginService.setLoginStatus(false);
      LoginService.setUser(null);

   });

});
