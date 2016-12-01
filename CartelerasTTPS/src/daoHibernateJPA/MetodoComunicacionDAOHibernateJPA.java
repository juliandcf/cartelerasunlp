package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import dao.MetodoComunicacionDAO;
import modelo.Administrador;
import modelo.MetodoComunicacion;

public class MetodoComunicacionDAOHibernateJPA extends GenericDAOHibernateJPA<MetodoComunicacion>
		implements MetodoComunicacionDAO {

	public MetodoComunicacionDAOHibernateJPA() {
		super(MetodoComunicacion.class);
	}
}
