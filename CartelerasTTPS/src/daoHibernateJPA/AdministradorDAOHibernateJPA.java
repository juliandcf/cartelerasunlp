package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.AdministradorDAO;
import modelo.Administrador;

@Repository
public class AdministradorDAOHibernateJPA extends UsuarioDAOHibernateJPA<Administrador> implements AdministradorDAO {

	public AdministradorDAOHibernateJPA() {
		super(Administrador.class);
	}



}
