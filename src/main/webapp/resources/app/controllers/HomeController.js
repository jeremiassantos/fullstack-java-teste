
(function(){
	
	
	angular.module("SouNinja").controller("HomeController", HomeController);
	
	function HomeController(AnexoTipoFactory, ClienteFactory, $rootScope, $state, $cookieStore){
		
		var hc = this;
				
		hc.clienteEntity = {};
		hc.anexoTiposSelection = [];
		hc.anexoTipoList = [];
		
		init();
		
		AnexoTipoFactory.findAll().success(function(response) {
			componentHandler.upgradeAllRegistered();
			hc.anexoTipoList = response;
			
	    });
		
		function init() {
			 delete $rootScope.user;
			 $cookieStore.remove('user');
		};
		
		hc.clienteAnexoTiposSelection = function(anexoTipo) {
		    var idx = hc.anexoTiposSelection.indexOf(anexoTipo);

		    // is currently selected
		    if (idx > -1) {
		    	hc.anexoTiposSelection.splice(idx, 1);
		    }

		    // is newly selected
		    else {
		    	hc.anexoTiposSelection.push(anexoTipo);
		    }
		    
		 };
		 
		 hc.saveUsuario = function() {
			 
			 if(hc.clienteEntity.clienteRegimeTributario.id == 1) {
				 hc.clienteEntity.clienteAnexoTipos = [];
				 for(x in hc.anexoTiposSelection) {
					 var clienteAnexoTipo = {};					 
					 clienteAnexoTipo.anexoTipo = hc.anexoTiposSelection[x];
					 hc.clienteEntity.clienteAnexoTipos.push(clienteAnexoTipo);
				 }
			 }
			 hc.clienteEntity.cnpj = prepareCnpj(hc.clienteEntity.cnpj);
			 ClienteFactory.save(hc.clienteEntity).success(function(response) {
				 $rootScope.user = response;
				 $cookieStore.put('user', response);
				 $state.go('cliente.consultar');
			 });
			 
		 }
		 
		 hc.setLucroPresumido = function() {
			 hc.clienteEntity.clienteRegimeTributario = {id : 2};
		 }
		 
		 hc.setSimplesNacional = function() {
			 hc.clienteEntity.clienteRegimeTributario = {id : 1};
			
		 }
		 
		 hc.openModal = function() {
			 $('#modalCadastroCategoriaItemRestaurante').modal();
		 }
		 
		 hc.initByCnpj = function() {
			 delete hc.validateInit;
			 ClienteFactory.initByCnpj(prepareCnpj(hc.clienteInitEntity.cnpj)).success(function(response) {
				 $rootScope.user = response;
				 $cookieStore.put('user', response);
				 $state.go('cliente.consultar');
			 }).error(function(data) {
				 hc.validateInit = {isInvalid : true, message : data.message};
			 });
		 }
		 
		 
		 hc.setCnpj = function(){	
			    hc.validate = {cnpj : null};
				var cnpj = $('#cnpj').val();
				if(cnpj == '__.___.___/____-__') {
					$('#cnpj').parent().removeClass('is-dirty');
					hc.clienteEntity.cnpj = null;
					return;
				}
				$('#cnpj').parent().addClass('is-dirty'); 
				hc.clienteEntity.cnpj = cnpj;
				
				ClienteFactory.isValidCnpj(prepareCnpj(cnpj)).error(function(data) {
					hc.validate = { cnpj:{isInvalid : true, message : data.message}}; 
				});
				
		  };
		  
		  hc.validEmail = function() {
			  hc.validate = {email : null};
			  ClienteFactory.isValidEmail(hc.clienteEntity.email).error(function(data) {
				  hc.validate = { email:{isInvalid : true, message : data.message}};
			  });
		  }
		  
		  hc.setCnpjInit = function() {
			  var cnpj = $('#cnpjInit').val();
				if(cnpj == '__.___.___/____-__') {
					$('#cnpjInit').parent().removeClass('is-dirty');
					hc.clienteInitEntity = {cnpj : null};
					return;
				}
				$('#cnpjInit').parent().addClass('is-dirty'); 
				hc.clienteInitEntity = {cnpj : cnpj};
		  }
		  
		  function prepareCnpj(cnpj) {
			    cnpj = cnpj.replace('.','');
			    cnpj = cnpj.replace('.','');
			    cnpj = cnpj.replace('-','');
			    cnpj = cnpj.replace('/','');
			    return cnpj;
			}
		
		
	}//end controller		
})();
