package br.com.souninja.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.souninja.entity.NotaFiscal;
import br.com.souninja.service.NotaFiscalService;
import br.com.souninja.service.impl.NotaFiscalServiceImpl;

@Path("/notaFiscal")
public class NotaFiscalController {

	private NotaFiscalService notaFiscalService;

	public NotaFiscalController() {
		notaFiscalService = new NotaFiscalServiceImpl();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void save(NotaFiscal notaFiscal) {
		this.notaFiscalService.save(notaFiscal);
	}

	@GET
	@Path("/findByMesAndAno")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<NotaFiscal> findByClienteAndDataEmissao(@QueryParam(value = "mes") Integer mes,
			@QueryParam(value = "ano") Integer ano) {
		return this.notaFiscalService.findByClienteAndDataEmissao(mes, ano);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer idNotaFiscal) {
		this.notaFiscalService.delete(new NotaFiscal(idNotaFiscal));
		return Response.status(Status.OK).build();
	}
}
