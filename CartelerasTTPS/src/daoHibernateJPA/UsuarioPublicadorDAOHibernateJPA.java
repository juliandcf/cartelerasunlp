package daoHibernateJPA;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.UsuarioPublicadorDAO;
import modelo.Usuario;
import modelo.UsuarioPublicador;

@Repository
public class UsuarioPublicadorDAOHibernateJPA extends UsuarioDAOHibernateJPA<UsuarioPublicador>
		implements UsuarioPublicadorDAO {

	public UsuarioPublicadorDAOHibernateJPA() {
		super(UsuarioPublicador.class);
	}
	
	@Override
	public UsuarioPublicador recuperar(String usuario) {
		UsuarioPublicador respuesta = null;
		Query consulta = this.getEntityManager().createQuery("from " + this.getPersistentClass().getSimpleName() + " where usuario = :usuario AND borrado = :borrado");
		consulta.setMaxResults(1);
		consulta.setParameter("borrado", false);
		consulta.setParameter("usuario", usuario);
		if(consulta.getResultList().size() >= 1){
			respuesta = (UsuarioPublicador) consulta.getResultList().get(0);
		}	
		return respuesta;
	}
}
