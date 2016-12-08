
(function() {

	'use strict';

	angular.module("SouNinja").factory("NotaFiscalFactory", NotaFiscalFactory);

	function NotaFiscalFactory($http, Base) {

		var _save = function(entity) {				
			return	$http.post(Base.path + '/notaFiscal', entity);
		};
		
		var _deleteById = function(id) {				
			return	$http({
			    method: 'DELETE',
			    url: Base.path + '/notaFiscal/' + id				    
			});
		};
		
		var _findByMesAndAno = function(mes, ano) {	
			if(mes == undefined  && ano == undefined) {
				return	$http.get(Base.path + '/notaFiscal/findByMesAndAno');
			}
			return	$http.get(Base.path + '/notaFiscal/findByMesAndAno?mes=' + mes + '&ano=' + ano);
		};
				
		return {
			save : _save,
			findByMesAndAno : _findByMesAndAno,
			deleteById : _deleteById
		};
	}
	;

})();
