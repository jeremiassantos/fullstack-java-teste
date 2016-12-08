
(function() {

	'use strict';

	angular.module("SouNinja").factory("AnexoTipoFactory", AnexoTipoFactory);

	function AnexoTipoFactory($http, Base) {

		var _findAll = function() {				
			return	$http.get(Base.path + '/anexoTipo');
		};
				
		return {
			findAll : _findAll
		};
	}
	;

})();
