package daoHibernateJPA;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.CarteleraDAO;
import modelo.Cartelera;
import modelo.PermisoCartelera;

@Repository
public class CarteleraDAOHibernateJPA extends GenericDAOHibernateJPA<Cartelera> implements CarteleraDAO {
	public CarteleraDAOHibernateJPA() {
		super(Cartelera.class);
	}

	@Override
	public boolean existe(Cartelera cartelera) {
		String nombre = cartelera.getNombre();
		return this.existeConNombre(nombre);
	}
	
	public boolean existeConNombre(String nombreCartelera) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT COUNT(e.id) FROM " + this.getPersistentClass().getSimpleName() + " e where e.nombre = :nombre and e.borrado = :borrado");
		consulta.setParameter("borrado", false);
		consulta.setParameter("nombre", nombreCartelera);
		return (((int) (long) consulta.getSingleResult()) > 0);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Cartelera> getCartelerasConPermiso(PermisoCartelera permisoCartelera){
		Query consulta = this.getEntityManager()
				.createQuery("SELECT c FROM PermisoCartelera p "
						+ "INNER JOIN p.cartelerasConPermiso c "
						+ "WHERE c.borrado = :borrado and "
						+ "p.id = :idPermiso");
					//.createQuery("SELECT * FROM Cartelera inner join cartelera_permiso on Cartelera.id_cartelera = cartelera_permiso.cartelera_id where Cartelera.id_cartelera = :idCartelera");			  
		consulta.setParameter("borrado", false);		
		consulta.setParameter("idPermiso", permisoCartelera.getId());
		List<Cartelera> cartelerasList = consulta.getResultList();
		Set<Cartelera> resultado = new HashSet<>(cartelerasList);
	    return resultado;
	}

}
