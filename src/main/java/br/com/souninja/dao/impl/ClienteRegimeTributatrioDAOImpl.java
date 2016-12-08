package br.com.souninja.dao.impl;

import br.com.souninja.dao.ClienteRegimeTributatrioDAO;
import br.com.souninja.dao.HibernateDAO;
import br.com.souninja.entity.ClienteRegimeTributario;

public class ClienteRegimeTributatrioDAOImpl extends HibernateDAO<ClienteRegimeTributario, Integer>
		implements ClienteRegimeTributatrioDAO {

	public ClienteRegimeTributatrioDAOImpl() {
		super(ClienteRegimeTributario.class);
	}

}
