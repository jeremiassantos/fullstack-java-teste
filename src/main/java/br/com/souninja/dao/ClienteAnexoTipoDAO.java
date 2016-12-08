package br.com.souninja.dao;

import java.util.List;

import br.com.souninja.entity.Cliente;
import br.com.souninja.entity.ClienteAnexoTipo;

public interface ClienteAnexoTipoDAO extends GenericDAO<ClienteAnexoTipo, Integer> {

	List<ClienteAnexoTipo> findByCliente(Cliente cliente);
}
