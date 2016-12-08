
angular.module('SouNinja').run(function($rootScope, $cookieStore, $location, $timeout){
	
	$rootScope.user = $cookieStore.get('user');
	
	$rootScope.$on('$viewContentLoaded', function(event){
		$timeout(function() {
	        componentHandler.upgradeAllRegistered();
	    },0);
				
		$('#preco').maskMoney();
	});
});