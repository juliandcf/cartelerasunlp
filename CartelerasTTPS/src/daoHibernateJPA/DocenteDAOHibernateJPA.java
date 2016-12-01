package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.DocenteDAO;
import modelo.Administrador;
import modelo.Docente;
import modelo.Usuario;

@Repository
public class DocenteDAOHibernateJPA extends UsuarioDAOHibernateJPA<Docente> implements DocenteDAO {

	public DocenteDAOHibernateJPA() {
		super(Docente.class);
	}
}
