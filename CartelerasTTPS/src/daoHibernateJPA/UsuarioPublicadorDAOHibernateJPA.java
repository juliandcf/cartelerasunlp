package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.UsuarioPublicadorDAO;
import modelo.Administrador;
import modelo.UsuarioPublicador;

@Repository
public class UsuarioPublicadorDAOHibernateJPA extends UsuarioDAOHibernateJPA<UsuarioPublicador>
		implements UsuarioPublicadorDAO {

	public UsuarioPublicadorDAOHibernateJPA() {
		super(UsuarioPublicador.class);
	}

	@Override
	public boolean existe(UsuarioPublicador entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
