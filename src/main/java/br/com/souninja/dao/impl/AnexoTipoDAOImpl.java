package br.com.souninja.dao.impl;

import br.com.souninja.dao.AnexoTipoDAO;
import br.com.souninja.dao.HibernateDAO;
import br.com.souninja.entity.AnexoTipo;

public class AnexoTipoDAOImpl extends HibernateDAO<AnexoTipo, Integer> implements AnexoTipoDAO {

	public AnexoTipoDAOImpl() {
		super(AnexoTipo.class);
	}

}
