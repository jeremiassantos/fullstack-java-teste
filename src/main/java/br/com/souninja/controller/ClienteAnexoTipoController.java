package br.com.souninja.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.souninja.entity.ClienteAnexoTipo;
import br.com.souninja.service.ClienteAnexoTipoService;
import br.com.souninja.service.impl.ClienteAnexoTipoServiceImpl;

@Path("/clienteAnexoTipo")
public class ClienteAnexoTipoController {
	
	private ClienteAnexoTipoService clienteAnexoTipoService;
	
	public ClienteAnexoTipoController() {
		this.clienteAnexoTipoService = new ClienteAnexoTipoServiceImpl();
	}
	
	@GET
	@Path("/findByCliente")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ClienteAnexoTipo> findByCliente() {
		return this.clienteAnexoTipoService.findByCliente();
	}

}
