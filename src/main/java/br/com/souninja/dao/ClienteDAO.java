package br.com.souninja.dao;

import br.com.souninja.entity.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente, Integer> {

	Cliente saveWithClienteAnexoTipos(Cliente cliente);
	
	Cliente findByCnpj(String cnpj);
	
	Cliente findByEmail(String email);
	
	Cliente findById(Integer id);
}
