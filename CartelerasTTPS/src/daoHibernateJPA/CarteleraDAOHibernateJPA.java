package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.CarteleraDAO;
import modelo.Administrador;
import modelo.Cartelera;

@Repository
public class CarteleraDAOHibernateJPA extends GenericDAOHibernateJPA<Cartelera> implements CarteleraDAO {
	public CarteleraDAOHibernateJPA() {
		super(Cartelera.class);
	}

	@Override
	public boolean existe(Cartelera cartelera) {
		String nombre = cartelera.getNombre();
		Query consulta = this.getEntityManager()
				.createQuery("SELECT COUNT(e.id) FROM " + this.getPersistentClass().getSimpleName() + " e where e.nombre = :nombre and e.borrado = :borrado");
		consulta.setParameter("borrado", false);
		consulta.setParameter("nombre", nombre);
		return (((int) (long) consulta.getSingleResult()) > 0);
	}
}
