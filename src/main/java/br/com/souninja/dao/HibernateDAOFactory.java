package br.com.souninja.dao;

import br.com.souninja.dao.impl.AnexoTipoDAOImpl;
import br.com.souninja.dao.impl.ClienteAnexoTipoDAOImpl;
import br.com.souninja.dao.impl.ClienteDAOImpl;
import br.com.souninja.dao.impl.ClienteRegimeTributatrioDAOImpl;
import br.com.souninja.dao.impl.ImpostoDAOImpl;
import br.com.souninja.dao.impl.NotaFiscalDAOImpl;

public class HibernateDAOFactory extends DAOFactory {

	@Override
	public ClienteDAO getClienteDAO() {
		return new ClienteDAOImpl();
	}

	@Override
	public ClienteRegimeTributatrioDAO getClienteRegimeTributatrioDAO() {		
		return new ClienteRegimeTributatrioDAOImpl();
	}

	@Override
	public AnexoTipoDAO getAnexoTipoDAO() {		
		return new AnexoTipoDAOImpl();
	}

	@Override
	public ClienteAnexoTipoDAO getClienteAnexoTipoDAO() {
		return new ClienteAnexoTipoDAOImpl();
	}

	@Override
	public NotaFiscalDAO getNotaFiscalDAO() {
		return new NotaFiscalDAOImpl();
	}

	@Override
	public ImpostoDAO getImpostoDAO() {
		return new ImpostoDAOImpl();
	}
	
}