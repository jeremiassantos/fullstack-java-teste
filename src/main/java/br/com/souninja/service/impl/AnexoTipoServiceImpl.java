package br.com.souninja.service.impl;

import java.util.List;

import br.com.souninja.dao.AnexoTipoDAO;
import br.com.souninja.dao.DAOFactory;
import br.com.souninja.entity.AnexoTipo;
import br.com.souninja.service.AnexoTipoService;

public class AnexoTipoServiceImpl implements AnexoTipoService {

	private AnexoTipoDAO anexoTipoDAO;
	
	public AnexoTipoServiceImpl() {
		this.anexoTipoDAO = DAOFactory.getFactory().getAnexoTipoDAO();
	}
	
	@Override
	public AnexoTipo save(AnexoTipo entity) {
		return this.anexoTipoDAO.save(entity);		
	}

	@Override
	public void delete(AnexoTipo entity) {
		this.anexoTipoDAO.delete(entity);		
	}

	@Override
	public List<AnexoTipo> findAll() {	
		return this.anexoTipoDAO.findAll();
	}

}
