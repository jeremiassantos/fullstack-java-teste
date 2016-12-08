
(function() {

	'use strict';

	angular.module("SouNinja").factory("ImpostoFactory", ImpostoFactory);

	function ImpostoFactory($http, Base) {

		var _findByReferencia = function(mes, ano) {	
			if(mes == undefined  && ano == undefined) {
				return	$http.get(Base.path + '/imposto/findByReferencia');
			}
			return	$http.get(Base.path + '/imposto/findByReferencia?mes=' + mes + '&ano=' + ano);
		};
		
		var _calcularImposto = function(mes, ano) {	
			return	$http.get(Base.path + '/imposto/calcularImposto?mes=' + mes + '&ano=' + ano);
		};
		
		
		var _pagamentoCompleted = function(idImposto) {	
			return	$http.put(Base.path + '/imposto/pagamentoCompleted?idImposto=' + idImposto);
		};
		
		var _pagamentoCompletedList = function(idImpostoList) {	
			return	$http.put(Base.path + '/imposto/pagamentoCompletedList', idImpostoList);
		};
				
		return {
			findByReferencia : _findByReferencia,
			calcularImposto : _calcularImposto,
			pagamentoCompleted : _pagamentoCompleted,
			pagamentoCompletedList : _pagamentoCompletedList
		};
	}
	;

})();
