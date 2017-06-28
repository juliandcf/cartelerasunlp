package daoHibernateJPA;

import dao.MetodoComunicacionDAO;
import modelo.MetodoComunicacion;

public class MetodoComunicacionDAOHibernateJPA extends GenericDAOHibernateJPA<MetodoComunicacion>
		implements MetodoComunicacionDAO {

	public MetodoComunicacionDAOHibernateJPA() {
		super(MetodoComunicacion.class);
	}

	@Override
	public boolean existe(MetodoComunicacion entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
