package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.AdministradorDAO;
import modelo.Administrador;
import modelo.Cartelera;

@Repository
public class AdministradorDAOHibernateJPA extends UsuarioDAOHibernateJPA<Administrador> implements AdministradorDAO {

	public AdministradorDAOHibernateJPA() {
		super(Administrador.class);
	}
	

}
