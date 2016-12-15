package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.AdministradorDAO;
import modelo.Administrador;
import modelo.Cartelera;

@Repository
public class AdministradorDAOHibernateJPA extends UsuarioDAOHibernateJPA<Administrador> implements AdministradorDAO {

	public AdministradorDAOHibernateJPA() {
		super(Administrador.class);
	}
	
	@Override
	public boolean existe(Administrador administrador) {
		String usuario = administrador.getUsuario();
		Query consulta = this.getEntityManager()
				.createQuery("SELECT COUNT(e.id) FROM " + this.getPersistentClass().getSimpleName() + " e where e.usuario = :usuario");
		consulta.setParameter("usuario", usuario);
		return (((int) (long) consulta.getSingleResult()) > 0);
		}
}
