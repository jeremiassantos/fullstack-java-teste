
(function() {

	'use strict';

	angular.module("SouNinja").factory("ClienteFactory", ClienteFactory);

	function ClienteFactory($http, Base) {

		var _save = function(entity) {				
			return	$http.post(Base.path + '/cliente', entity);
		};
		
		var _initByCnpj = function(cnpj) {				
			return	$http.get(Base.path + '/cliente/initByCnpj?cnpj=' + cnpj);
		};
		
		var _isValidCnpj = function(cnpj) {				
			return	$http.get(Base.path + '/cliente/isValidCnpj?cnpj=' + cnpj);
		};
		
		var _isValidEmail = function(email) {				
			return	$http.get(Base.path + '/cliente/isValidEmail?email=' + email);
		};
				
		return {
			save : _save,
			initByCnpj : _initByCnpj,
			isValidEmail : _isValidEmail,
			isValidCnpj : _isValidCnpj
		};
	}
	;

})();
