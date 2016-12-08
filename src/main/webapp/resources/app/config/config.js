/**
 * 
 */

(function(){
	
	'use strict';
	
	angular.module("SouNinja").config(	
	function Router($stateProvider, $urlRouterProvider, $httpProvider){
		
		 $httpProvider.interceptors.push("loadingInterceptor");
		
		 $urlRouterProvider.otherwise('/');
		 
		 $stateProvider
		 	.state('home',{
		 		url:'/',
		 		templateUrl:'resources/app/views/home.html',
		 		controller : 'HomeController',
		 		controllerAs : 'hc'
		 	})
		 	.state('cliente',{
		 		abstract: true,
		 		url:'/cliente',
		 		templateUrl:'resources/app/views/cliente/template.html',
		 		controller : 'ClienteController',
		 		controllerAs : 'cc'
		 	})
			 	.state('cliente.consultar',{
			 		url:'/cliente-consultar',
			 		templateUrl:'resources/app/views/cliente/clienteConsultar.html'
			 	})
		 	
	});
	
})();