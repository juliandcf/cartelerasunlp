package daoHibernateJPA;

import java.util.List;

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

	@Override
	public List<UsuarioPublicador> recuperarTodosExcepto(Long id) {
		List<UsuarioPublicador> resultado = null;
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM " + this.getPersistentClass().getSimpleName() + " e where e.id <> :id AND borrado = :borrado");
		consulta.setParameter("borrado", false);
		consulta.setParameter("id", id);
		resultado = consulta.getResultList();
		return resultado;
	}
}
