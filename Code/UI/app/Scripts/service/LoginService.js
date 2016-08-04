app.service("LoginService", function() {

	this.userLoggedIn = false;

	this.user = null;

	this.loginStatus = function(){
		return this.userLoggedIn;
	};

	this.setLoginStatus = function(status){

		this.userLoggedIn = status;
	};

	this.setUser = function(user){
		this.user = user;
	};

	this.getUser = function(){
		return this.user;
	};
});
