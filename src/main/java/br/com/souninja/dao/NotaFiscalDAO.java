package br.com.souninja.dao;

import java.time.LocalDate;
import java.util.List;

import br.com.souninja.entity.Cliente;
import br.com.souninja.entity.NotaFiscal;

public interface NotaFiscalDAO extends GenericDAO<NotaFiscal, Integer> {

	List<NotaFiscal> findByClienteAndDataEmissao(Cliente cliente, LocalDate dataEmissao);
}
