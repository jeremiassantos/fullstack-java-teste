package br.com.souninja.service.impl;

import java.time.LocalDate;
import java.util.List;

import br.com.souninja.dao.DAOFactory;
import br.com.souninja.dao.NotaFiscalDAO;
import br.com.souninja.entity.NotaFiscal;
import br.com.souninja.infrastructure.ClienteSession;
import br.com.souninja.service.NotaFiscalService;

public class NotaFiscalServiceImpl implements NotaFiscalService {

	private NotaFiscalDAO notaFiscalDAO;

	public NotaFiscalServiceImpl() {
		this.notaFiscalDAO = DAOFactory.getFactory().getNotaFiscalDAO();
	}

	@Override
	public NotaFiscal save(NotaFiscal entity) {
		entity.setCliente(ClienteSession.getCliente());
		return this.notaFiscalDAO.save(entity);
	}

	@Override
	public void delete(NotaFiscal entity) {
		this.notaFiscalDAO.delete(entity);
	}

	@Override
	public List<NotaFiscal> findAll() {
		return this.notaFiscalDAO.findAll();
	}

	@Override
	public List<NotaFiscal> findByClienteAndDataEmissao(Integer month, Integer year) {
		if (month != null && year != null) {
			return this.notaFiscalDAO.findByClienteAndDataEmissao(ClienteSession.getCliente(),
					LocalDate.of(year, month, 1));
		}
		return this.notaFiscalDAO.findByClienteAndDataEmissao(ClienteSession.getCliente(), LocalDate.now());
	}

}
