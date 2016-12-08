package br.com.souninja.service;

import java.util.List;

import br.com.souninja.entity.ClienteAnexoTipo;

public interface ClienteAnexoTipoService {

	List<ClienteAnexoTipo> findByCliente();
}
