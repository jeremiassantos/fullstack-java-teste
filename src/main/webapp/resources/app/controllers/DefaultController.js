
(function(){
	
	
	angular.module("SouNinja").controller("DafaultController", DafaultController);
	
	function DafaultController($state, $rootScope, $cookieStore){
		
		var dc = this;
		
		dc.sair = function() {
			delete $rootScope.user;
		    $cookieStore.remove('user');
			$state.go('home');
		}
		
		
	}//end controller		
})();
