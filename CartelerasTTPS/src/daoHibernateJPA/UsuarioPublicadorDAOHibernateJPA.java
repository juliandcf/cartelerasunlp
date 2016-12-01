package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.UsuarioPublicadorDAO;
import modelo.Administrador;
import modelo.UsuarioPublicador;

@Repository
public class UsuarioPublicadorDAOHibernateJPA extends GenericDAOHibernateJPA<UsuarioPublicador>
		implements UsuarioPublicadorDAO {

	public UsuarioPublicadorDAOHibernateJPA() {
		super(UsuarioPublicador.class);
	}

}
