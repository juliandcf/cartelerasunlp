package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.InstitucionalDAO;
import modelo.Administrador;
import modelo.Institucional;

@Repository
public class InstitucionalDAOHibernateJPA extends UsuarioDAOHibernateJPA<Institucional> implements InstitucionalDAO {

	public InstitucionalDAOHibernateJPA() {
		super(Institucional.class);
	}
}
