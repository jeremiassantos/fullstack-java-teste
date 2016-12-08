package br.com.souninja.service;

import java.util.List;

import br.com.souninja.entity.NotaFiscal;

public interface NotaFiscalService extends BaseService<NotaFiscal, Integer> {

	List<NotaFiscal> findByClienteAndDataEmissao(Integer month, Integer year);
}
