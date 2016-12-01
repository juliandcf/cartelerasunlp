package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.CarteleraDAO;
import modelo.Administrador;
import modelo.Cartelera;

@Repository
public class CarteleraDAOHibernateJPA extends GenericDAOHibernateJPA<Cartelera> implements CarteleraDAO {
	public CarteleraDAOHibernateJPA() {
		super(Cartelera.class);
	}
}
