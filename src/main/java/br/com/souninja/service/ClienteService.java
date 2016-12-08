package br.com.souninja.service;

import javax.servlet.http.HttpServletRequest;

import br.com.souninja.entity.Cliente;

public interface ClienteService extends BaseService<Cliente, Integer> {

	Cliente save(Cliente entity, HttpServletRequest req);
	Boolean isValidCnpj(String cnpj);
	Boolean isValidEmail(String email);
	Cliente findById(Integer id);
	Cliente initByCpj(String cnpj, HttpServletRequest req);
}
