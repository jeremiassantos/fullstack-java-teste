package br.com.souninja.dao;

import java.time.LocalDate;
import java.util.List;

import br.com.souninja.entity.Cliente;
import br.com.souninja.entity.Imposto;

public interface ImpostoDAO extends GenericDAO<Imposto, Integer> {

	List<Imposto> findByClienteAndReferencia(Cliente cliente, LocalDate referencia);
	
	void deleteByClienteAndReferencia(Cliente cliente, LocalDate referencia);
	
	void updateFromPagamentoCompleted(Cliente cliente, Integer idImposto);
	
	void updateFromPagamentoCompleted(Cliente cliente, List<Integer> idImpostoList);
	
}
