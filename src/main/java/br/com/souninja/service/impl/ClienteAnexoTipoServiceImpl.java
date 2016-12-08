package br.com.souninja.service.impl;

import java.util.List;

import br.com.souninja.dao.ClienteAnexoTipoDAO;
import br.com.souninja.dao.DAOFactory;
import br.com.souninja.entity.ClienteAnexoTipo;
import br.com.souninja.infrastructure.ClienteSession;
import br.com.souninja.service.ClienteAnexoTipoService;

public class ClienteAnexoTipoServiceImpl implements ClienteAnexoTipoService {
	
	private ClienteAnexoTipoDAO clienteAnexoTipoDAO;
	
	public ClienteAnexoTipoServiceImpl() {
		this.clienteAnexoTipoDAO = DAOFactory.getFactory().getClienteAnexoTipoDAO();
	}

	@Override
	public List<ClienteAnexoTipo> findByCliente() {
		return this.clienteAnexoTipoDAO.findByCliente(ClienteSession.getCliente());
	}

}
