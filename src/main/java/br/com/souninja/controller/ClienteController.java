package br.com.souninja.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.souninja.entity.Cliente;
import br.com.souninja.messages.ValidationMessage;
import br.com.souninja.service.ClienteService;
import br.com.souninja.service.impl.ClienteServiceImpl;

@Path("/cliente")
public class ClienteController {

	private ClienteService clienteService;

	public ClienteController() {
		this.clienteService = new ClienteServiceImpl();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response save(Cliente cliente, @Context HttpServletRequest req) {
		if(cliente.hasInvalid()) {
			return Response.status(Status.BAD_REQUEST).entity(ValidationMessage.CAMPO_OBRIGATORIO).build();
		}
		return Response.status(Status.OK).entity(clienteService.save(cliente, req)).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Cliente> findAll() {
		return clienteService.findAll();
	}

	@GET
	@Path("/isValidCnpj")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response isValidCnpj(@QueryParam("cnpj") String cnpj) {
		if (clienteService.isValidCnpj(cnpj)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.BAD_REQUEST).entity(ValidationMessage.CNPJ_INVALIDO).build();
	}

	@GET
	@Path("/isValidEmail")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response isValidEmail(@QueryParam("email") String email) {
		if (clienteService.isValidEmail(email)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.BAD_REQUEST).entity(ValidationMessage.EMAIL_INVALIDO).build();
	}
	
	@GET
	@Path("/initByCnpj")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response initByCnpj(@QueryParam("cnpj") String cnpj, @Context HttpServletRequest req) {
		Cliente c = this.clienteService.initByCpj(cnpj, req);
		if (c != null) {
			return Response.status(Status.OK).entity(c).build();
		}
		return Response.status(Status.BAD_REQUEST).entity(ValidationMessage.USER_NOT_FOUND).build();
	}

}
