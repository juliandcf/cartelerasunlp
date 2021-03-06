package daoHibernateJPA;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.UsuarioDAO;
import modelo.Usuario;

@Repository
public class UsuarioDAOHibernateJPA<T> extends GenericDAOHibernateJPA<T> implements UsuarioDAO<T> {

	public UsuarioDAOHibernateJPA() {
		super((Class<T>) Usuario.class);
	}

	public UsuarioDAOHibernateJPA(Class<T> class1) {
		super((Class<T>) class1);
	}


	@Override
	public boolean existe(T entity) {
		String usuario = ((Usuario) entity).getUsuario();
		Query consulta = this.getEntityManager()
				.createQuery("SELECT COUNT(e.id) FROM " + this.getPersistentClass().getSimpleName() + " e where e.usuario = :usuario AND borrado = :borrado");
		consulta.setParameter("borrado", false);
		consulta.setParameter("usuario", usuario);
		return (((int) (long) consulta.getSingleResult()) > 0);
	}

	@Override
	public Usuario recuperar(String usuario) {
		Usuario respuesta = null;
		Query consulta = this.getEntityManager().createQuery("from " + this.getPersistentClass().getSimpleName() + " where usuario = :usuario AND borrado = :borrado");
		consulta.setMaxResults(1);
		consulta.setParameter("borrado", false);
		consulta.setParameter("usuario", usuario);
		if(consulta.getResultList().size() >= 1){
			respuesta = (Usuario) consulta.getResultList().get(0);
		}	
		return respuesta;
	}

	@Override
	public Usuario login(String usuario, String password) {
		Usuario respuesta = null;
		Query consulta = this.getEntityManager().createQuery("from " + this.getPersistentClass().getSimpleName() + " where usuario = :usuario AND contrasena = :contrasena AND borrado = :borrado ");
		consulta.setMaxResults(1);
		consulta.setParameter("usuario", usuario);
		consulta.setParameter("contrasena", password);
		consulta.setParameter("borrado", false);
		if(consulta.getResultList().size() >= 1){
			respuesta = (Usuario) consulta.getResultList().get(0);
		}	
		return respuesta;
	}
	


}
