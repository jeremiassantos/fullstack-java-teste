package br.com.souninja.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.souninja.dao.ClienteDAO;
import br.com.souninja.dao.DAOFactory;
import br.com.souninja.entity.Cliente;
import br.com.souninja.infrastructure.ClienteSession;
import br.com.souninja.service.ClienteService;

public class ClienteServiceImpl implements ClienteService {

	private ClienteDAO clienteDAO;

	public ClienteServiceImpl() {
		// get iintace factory
		this.clienteDAO = DAOFactory.getFactory().getClienteDAO();
	}

	@Override
	public Cliente save(Cliente entity, HttpServletRequest req) {
		Cliente c = clienteDAO.saveWithClienteAnexoTipos(entity);

		HttpSession session = req.getSession();

		session.setAttribute(ClienteSession.SESSION, c);

		return c;
	}

	@Override
	public void delete(Cliente entity) {
		this.clienteDAO.delete(entity);
	}

	@Override
	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

	@Override
	public Boolean isValidCnpj(String cnpj) {
		//
		// if(!CNPJValidation.isValidCNPJ(cnpj)) {
		// return Boolean.FALSE;
		// }

		Cliente c = clienteDAO.findByCnpj(cnpj);

		if (c != null) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	@Override
	public Boolean isValidEmail(String email) {
		Cliente c = clienteDAO.findByEmail(email);

		if (c != null) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	@Override
	public Cliente save(Cliente entity) {
		return clienteDAO.saveWithClienteAnexoTipos(entity);
	}

	@Override
	public Cliente findById(Integer id) {
		return this.clienteDAO.findById(id);
	}

	@Override
	public Cliente initByCpj(String cnpj, HttpServletRequest req) {
		Cliente c = clienteDAO.findByCnpj(cnpj);
		
		HttpSession session = req.getSession();

		session.setAttribute(ClienteSession.SESSION, c);

		return c;
	}

}
