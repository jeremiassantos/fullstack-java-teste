package br.com.souninja.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.souninja.entity.AnexoTipo;
import br.com.souninja.service.AnexoTipoService;
import br.com.souninja.service.impl.AnexoTipoServiceImpl;

@Path("/anexoTipo")
public class AnexoTipoController {
	
	private AnexoTipoService anexoTipoService;
	
	public AnexoTipoController() {
		this.anexoTipoService = new AnexoTipoServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AnexoTipo> findAll() {
		return this.anexoTipoService.findAll();
	}

}
