 app.run(function(loginService,$http){

	 
	 $http.get("http://localhost:8080/findMyCar/user").then(function(data) {
		    loginService.setLoginStatus(true);
		 	loginService.setUser(data.data.user);
		}, function(data) {
			console.log(data);
			 loginService.setLoginStatus(false);
			 loginService.setUser(null);
			
		});
	
});