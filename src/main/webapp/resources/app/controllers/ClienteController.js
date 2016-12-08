
(function(){
	
	
	angular.module("SouNinja").controller("ClienteController", ClienteController);
	
	function ClienteController($state, $scope, ClienteAnexoTipoFactory, NotaFiscalFactory, ImpostoFactory){
		
		var cc = this;
		cc.clienteAnexoTipoList = [];
		cc.notaFiscalEntity = {};
		cc.notaFiscalList = {};
		cc.impostoSelect = [];
		
		$scope.$on('$viewContentLoaded', function(event){			
			$('#preco').maskMoney();			
		});
		
		_getNotaFiscalList();
		_buildMes();
		_setDataReferencia();
		_buildAno();
		_getImposto();
				
		function _setDataReferencia() {
			var now = new Date();		
			cc.dataReferencia = {
					mes : now.getMonth() + 1,
					ano : now.getFullYear()
					};
		}
		
		cc.openModalIncluirImposto = function() {
			if(!cc.clienteAnexoTipoList.length > 0) {
				ClienteAnexoTipoFactory.findByCliente().success(function(data) {
					cc.clienteAnexoTipoList = data;
				});
			}
			delete cc.validate;			
			cc.notaFiscalEntity = {};
			cc.notaFiscalEntity.dataEmissao = _dateFormat(new Date());
			$('#dateEmissao').parent().addClass('is-dirty');
			componentHandler.upgradeAllRegistered();
			$('#modalInserirImposto').modal();
			
		}
		
		cc.incluirNotaFiscal = function() {
			cc.notaFiscalEntity.dataEmissao = _dateUtis('#dateEmissao');
			NotaFiscalFactory.save(cc.notaFiscalEntity).success(function() {
				$('#modalInserirImposto').modal('hide');
				_getNotaFiscalListDateReference();
				toastr.success("Nota fiscal inclúida com sucesso!");
			});
		}
		
		cc.modalMesAnoReferencia = function() {
			cc.dataReferenciaOld = angular.copy(cc.dataReferencia);
			$('#modalDataReferencia').modal();
			
		}
		
		cc.alterarMesAnoReferencia = function() {
			cc.dataReferencia = angular.copy(cc.dataReferenciaOld);
			$('#modalDataReferencia').modal('hide');
			_getNotaFiscalListDateReference();
			_getImpostoDateReference();
			
		}
		
		cc.pagamentoCompleted = function(item) {
			swal({
				  title: "Tem certeza que deseja marcar como pago este imposto ?",
				  text: "Esta ação não poderá ser desfeita!",
				  type: "warning",
		          showCancelButton: true,
		          confirmButtonColor: "#f8ac59",
				  confirmButtonText: "Confirmar",
				  cancelButtonText: "Cancelar",
				  closeOnConfirm: true,
				  closeOnCancel: true
				},
				function(){				  
					ImpostoFactory.pagamentoCompleted(item.id).success(function() {
						_getImpostoDateReference();
						toastr.success("Pagamento de imposto efetuado com sucesso!");
					});
				});
		}
		
		cc.pagamentoCompletedList = function() {
			swal({
				  title: "Tem certeza que deseja efetuar o pagamento ?",
				  text: "Esta ação não poderá ser desfeita!",
				  type: "warning",
		          showCancelButton: true,
		          confirmButtonColor: "#f8ac59",
				  confirmButtonText: "Confirmar",
				  cancelButtonText: "Cancelar",
				  closeOnConfirm: true,
				  closeOnCancel: true
				},
				function(){				  
					ImpostoFactory.pagamentoCompletedList(cc.impostoSelect).success(function() {
						_getImpostoDateReference();
						toastr.success("Pagamento de imposto efetuado com sucesso!");
						cc.impostoSelect = [];
					});
				});
		}
		
		cc.calcularImposto = function() {
			
			if(cc.imposto.length) {
				swal({
					  title: "Tem certeza que deseja recalcular o imposto ?",
					  text: "O cálculo anterior será excluido!",
					  type: "warning",
			          showCancelButton: true,
			          confirmButtonColor: "#f8ac59",
					  confirmButtonText: "Confirmar",
					  cancelButtonText: "Cancelar",
					  closeOnConfirm: true,
					  closeOnCancel: true
					},
					function(){				  
						ImpostoFactory.calcularImposto(cc.dataReferencia.mes, cc.dataReferencia.ano).success(function() {
							_getImpostoDateReference();
							toastr.success("Imposto gerado com sucesso!");
						});
					});
				return;
			}
			  
			ImpostoFactory.calcularImposto(cc.dataReferencia.mes, cc.dataReferencia.ano).success(function() {
				_getImpostoDateReference();				
				toastr.success("Imposto gerado com sucesso!");
			});
			
		}
		
		cc.deletarNota = function(item) {
			swal({
				  title: "Tem certeza que deseja excluir esta nota fiscal?",
				  text: "Esta ação não poderá ser desfeita!",
				  type: "warning",
		          showCancelButton: true,
		          confirmButtonColor: "#f8ac59",
				  confirmButtonText: "Confirmar",
				  cancelButtonText: "Cancelar",
				  closeOnConfirm: true,
				  closeOnCancel: true
				},
				function(){	
					NotaFiscalFactory.deleteById(item.id).success(function() {
						toastr.success("Nota fiscal excluida com sucesso!");
						_getNotaFiscalListDateReference();
					});
				});
		}
		
		cc.impostoSelection = function(idImposto) {
		    var idx = cc.impostoSelect.indexOf(idImposto);

		    // is currently selected
		    if (idx > -1) {
		    	cc.impostoSelect.splice(idx, 1);
		    }

		    // is newly selected
		    else {
		    	cc.impostoSelect.push(idImposto);
		    }
		    console.log(cc.impostoSelect);
		    
		 };
		
		function _buildMes() {
			cc.mesArray = [];
			cc.mesArray.push({key : 1, value : 'Janeiro'});
			cc.mesArray.push({key : 2, value : 'Fevereiro'});
			cc.mesArray.push({key : 3, value : 'Março'});
			cc.mesArray.push({key : 4, value : 'Abril'});
			cc.mesArray.push({key : 5, value : 'Maio'});
			cc.mesArray.push({key : 6, value : 'Junho'});
			cc.mesArray.push({key : 7, value : 'Julho'});
			cc.mesArray.push({key : 8, value : 'Agosto'});
			cc.mesArray.push({key : 9, value : 'Setembro'});
			cc.mesArray.push({key : 10, value : 'Outubro'});
			cc.mesArray.push({key : 11, value : 'Novembro'});
			cc.mesArray.push({key : 12, value : 'Dezembro'});
		}
		
		function _buildAno() {
			var init = 1950;
			cc.anoArray = [];
			for(var x= 0; x <= 200; x++) {
				cc.anoArray.push({value : init + x});
			}
			
		}
		
		function _getNotaFiscalList() {
			NotaFiscalFactory.findByMesAndAno().success(function(data) {
				cc.notaFiscalList = data;
			});
		};
		
		function _getNotaFiscalListDateReference() {
			NotaFiscalFactory.findByMesAndAno(cc.dataReferencia.mes, cc.dataReferencia.ano).success(function(data) {
				cc.notaFiscalList = data;
			});
		};
		
		function _getImposto() {
			ImpostoFactory.findByReferencia().success(function(data) {
				cc.imposto = data;
			});
		};
		
		function _getImpostoDateReference() {
			ImpostoFactory.findByReferencia(cc.dataReferencia.mes, cc.dataReferencia.ano).success(function(data) {
				componentHandler.upgradeAllRegistered();
				cc.imposto = data;
			});
		};
		
		cc.prepareDate = function() {
			var dataEmi = $('#dataEmi').val();
			if(dataEmi == '__/__/____') {
				cc.notaFiscalEntity.dataEmissao = null;
			}
			$('#dataEmi').parent().addClass('is-dirty');
			cc.notaFiscalEntity.dataEmissao = dataEmi;
		}
		
		function _dateUtis(selector) {
			
			if($(selector).val() != ''){
				var ano = $(selector).val().substring(6,10);
				var mes = parseInt($(selector).val().substring(3,5)) -1;
				var dia = $(selector).val().substring(0,2);
				return new Date(ano, mes, dia);
			}			
			
			return null;
		};
		
		cc.validaData = function(obj) {
			delete cc.validate;			 
		    var data = $('#dateEmissao').val();
		    var dia = data.substring(0,2)
		    var mes = data.substring(3,5)
		    var ano = data.substring(6,10)
		 
		    //Criando um objeto Date usando os valores ano, mes e dia.
		    var novaData = new Date(ano,(mes-1),dia);
		 
		    var mesmoDia = parseInt(dia,10) == parseInt(novaData.getDate());
		    var mesmoMes = parseInt(mes,10) == parseInt(novaData.getMonth())+1;
		    var mesmoAno = parseInt(ano) == parseInt(novaData.getFullYear());
		 
		    if (!((mesmoDia) && (mesmoMes) && (mesmoAno)))
		    {
		    	cc.validate = {data : {isInvalid : true}};
		        return;
		    } 		   
		};
		
		function _dateFormat(data){
		    if(data != null) {
			    var dia = data.getDate();
			    if (dia.toString().length == 1)
			      dia = "0"+dia;
			    var mes = data.getMonth()+1;
			    if (mes.toString().length == 1)
			      mes = "0"+mes;
			    var ano = data.getFullYear();  
			    return dia+"/"+mes+"/"+ano;
		    }   
		};
	}//end controller		
})();
