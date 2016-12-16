package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.MultimediaDAO;
import modelo.Administrador;
import modelo.Multimedia;

@Repository
public class MultimediaDAOHibernateJPA extends GenericDAOHibernateJPA<Multimedia> implements MultimediaDAO {

	public MultimediaDAOHibernateJPA() {
		super(Multimedia.class);
	}

	@Override
	public boolean existe(Multimedia entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
