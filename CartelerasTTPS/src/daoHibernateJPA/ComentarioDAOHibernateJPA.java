package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.ComentarioDAO;
import modelo.Administrador;
import modelo.Comentario;

@Repository
public class ComentarioDAOHibernateJPA extends GenericDAOHibernateJPA<Comentario> implements ComentarioDAO {

	public ComentarioDAOHibernateJPA() {
		super(Comentario.class);
	}

	@Override
	public boolean existe(Comentario entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
