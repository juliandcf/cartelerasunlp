package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.UsuarioDAO;
import modelo.Administrador;
import modelo.Docente;
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
	public Usuario recuperar(String nombre, String apellido, String usuario) {
		Usuario respuesta = null;
		Query consulta = this.getEntityManager().createQuery("from Usuario where nombre = :nombre and apellido = :apellido and usuario = :usuario");
		consulta.setMaxResults(1);
		consulta.setParameter("nombre", nombre);
		consulta.setParameter("apellido", apellido);
		consulta.setParameter("usuario", usuario);
		if(consulta.getResultList().size() >= 1){
			respuesta = (Usuario) consulta.getResultList().get(0);
		}	
		return respuesta;
		
	}

	@Override
	public boolean existe(T entity) {
		String usuario = ((Usuario) entity).getUsuario();
		Query consulta = this.getEntityManager()
				.createQuery("SELECT COUNT(e.id) FROM " + this.getPersistentClass().getSimpleName() + " e where e.usuario = :usuario AND e.borrado = :borrado");
		consulta.setParameter("borrado", false);
		consulta.setParameter("usuario", usuario);
		return (((int) (long) consulta.getSingleResult()) > 0);
	}


}
