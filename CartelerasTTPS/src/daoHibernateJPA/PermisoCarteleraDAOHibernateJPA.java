package daoHibernateJPA;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.PermisoCarteleraDAO;
import modelo.PermisoCartelera;
import modelo.Publicacion;

@Repository
public class PermisoCarteleraDAOHibernateJPA extends GenericDAOHibernateJPA<PermisoCartelera> implements PermisoCarteleraDAO {

	public PermisoCarteleraDAOHibernateJPA() {
		super(PermisoCartelera.class);
	}

	@Override
	public boolean existe(PermisoCartelera permisoCartelera) {
		String nombre = permisoCartelera.getNombre();
		Query consulta = this.getEntityManager()
				.createQuery("SELECT COUNT(e.id) FROM " + this.getPersistentClass().getSimpleName() + " e where e.nombre = :nombre and e.borrado = :borrado");
		consulta.setParameter("borrado", false);
		consulta.setParameter("nombre", nombre);
		return (((int) (long) consulta.getSingleResult()) > 0);
	}

	@Override
	public List<PermisoCartelera> getPermisosParaUsuario(Long id) {
		List<PermisoCartelera> resultado = null;
		Query consulta = this.getEntityManager()
				.createQuery("SELECT pc "
						+ "FROM UsuarioPublicador usPub inner join usPub.permisosCarteleras pc WHERE"
						+ " pc.borrado = :borrado AND"
						+ " usPub.borrado = :borrado AND"
						+ " usPub.id = :id ");
		consulta.setParameter("borrado", false);
		consulta.setParameter("id", id);
		resultado = consulta.getResultList();
		return resultado;		
	}

	@Override
	public PermisoCartelera getPermisoPorNombre(String nombrePermiso) {
		Query consulta = this.getEntityManager()
				.createQuery("FROM " + this.getPersistentClass().getSimpleName() + " e"
						+ " WHERE e.nombre = :nombre"
						+ " AND e.borrado = :borrado");
		consulta.setParameter("borrado", false);
		consulta.setParameter("nombre", nombrePermiso);
		return (PermisoCartelera) consulta.getSingleResult();
	}

	@Override
	public boolean existeConNombre(String nombre) {
		return (this.getPermisoPorNombre(nombre) != null);
	}
}
