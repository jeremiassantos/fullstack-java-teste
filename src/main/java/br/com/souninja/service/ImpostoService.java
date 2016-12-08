package br.com.souninja.service;

import java.util.List;

import br.com.souninja.entity.Imposto;

public interface ImpostoService {

	void calcularImposto(Integer mes, Integer ano);
	
	List<Imposto> findByClienteAndReferencia(Integer mes, Integer ano);
	
	void updateFromPagamentoCompleted(Integer idImposto);
	
	void updateFromPagamentoCompleted(List<Integer> idImpostoList);
}
