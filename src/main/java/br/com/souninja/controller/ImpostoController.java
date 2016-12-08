package br.com.souninja.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.souninja.entity.Imposto;
import br.com.souninja.service.ImpostoService;
import br.com.souninja.service.impl.ImpostoServiceImpl;

@Path("/imposto")
public class ImpostoController {

	private ImpostoService impostoService;

	public ImpostoController() {
		this.impostoService = new ImpostoServiceImpl();
	}

	@GET
	@Path("/calcularImposto")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response calcularImposto(@QueryParam("mes") Integer mes, @QueryParam("ano") Integer ano) {
		this.impostoService.calcularImposto(mes, ano);
		return Response.status(Status.OK).build();
	}

	@GET
	@Path("/findByReferencia")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Imposto> findByClienteAndReferencia(@QueryParam("mes") Integer mes, @QueryParam("ano") Integer ano) {
		return this.impostoService.findByClienteAndReferencia(mes, ano);
	}

	@PUT
	@Path("/pagamentoCompleted")
	public Response updateFromPagamentoCompleted(@QueryParam("idImposto") Integer idImposto) {
		this.impostoService.updateFromPagamentoCompleted(idImposto);
		return Response.status(Status.OK).build();
	}

	@PUT
	@Path("/pagamentoCompletedList")
	public Response updateFromPagamentoCompleted(List<Integer> idImpostoList) {
		this.impostoService.updateFromPagamentoCompleted(idImpostoList);
		return Response.status(Status.OK).build();
	}
}
