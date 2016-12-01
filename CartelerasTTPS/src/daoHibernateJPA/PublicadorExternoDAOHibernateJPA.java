package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.PublicadorExternoDAO;
import modelo.Administrador;
import modelo.PublicadorExterno;

@Repository
public class PublicadorExternoDAOHibernateJPA extends UsuarioDAOHibernateJPA<PublicadorExterno>
		implements PublicadorExternoDAO {

	public PublicadorExternoDAOHibernateJPA() {
		super(PublicadorExterno.class);
	}
}
