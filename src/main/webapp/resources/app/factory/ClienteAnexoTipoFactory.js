
(function() {

	'use strict';

	angular.module("SouNinja").factory("ClienteAnexoTipoFactory", ClienteAnexoTipoFactory);

	function ClienteAnexoTipoFactory($http, Base) {

		var _findByCliente = function() {				
			return	$http.get(Base.path + '/clienteAnexoTipo/findByCliente');
		};
				
		return {
			findByCliente : _findByCliente
		};
	}
	;

})();
