package br.com.souninja.dao;

public abstract class DAOFactory {
	private static final Class<?> FACTORY_CLASS = HibernateDAOFactory.class;

	public static DAOFactory getFactory() {
		try {
			return (DAOFactory) FACTORY_CLASS.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public abstract ClienteDAO getClienteDAO();

	public abstract ClienteRegimeTributatrioDAO getClienteRegimeTributatrioDAO();
	
	public abstract AnexoTipoDAO getAnexoTipoDAO();
	
	public abstract ClienteAnexoTipoDAO getClienteAnexoTipoDAO();
	
	public abstract NotaFiscalDAO getNotaFiscalDAO();
	
	public abstract ImpostoDAO getImpostoDAO();
}