app.factory("LoginService", function() {

	var self = this;

	self.loggedIn = false;

	return {

		isLoggedIn : function() {
			return self.loggedIn;
		},
		setLoggIn : function(loggedIn) {
			self.loggedIn = loggedIn;
		}
	}

});