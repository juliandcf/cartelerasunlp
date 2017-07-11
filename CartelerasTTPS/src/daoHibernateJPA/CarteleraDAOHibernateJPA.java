package daoHibernateJPA;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.CarteleraDAO;
import modelo.Cartelera;

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

}
