package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.PublicacionDAO;
import modelo.Publicacion;

@Repository
public class PublicacionDAOHibernateJPA extends GenericDAOHibernateJPA<Publicacion> implements PublicacionDAO {

	public PublicacionDAOHibernateJPA() {
		super(Publicacion.class);
	}

	@Override
	public boolean existe(Publicacion entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Publicacion> recuperarPublicacionesDeCartelera(Serializable idCartelera){
		List<Publicacion> resultado = null;
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM " + this.getPersistentClass().getSimpleName() + " e where borrado = :borrado and cartelera_fk = :id_cartelera order by e.fecha desc");
		consulta.setParameter("borrado", false);
		consulta.setParameter("id_cartelera", idCartelera);
		resultado = consulta.getResultList();
		return resultado;
		
		
	}

	@Override
	public List<Publicacion> recuperarPublicacionesDeCartelera(Long idCartelera, Long idUsuario) {
		List<Publicacion> resultado = null;
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM " + this.getPersistentClass().getSimpleName() + " e where borrado = :borrado and cartelera_fk = :id_cartelera and usuarioPublicador_fk = :id_usuario order by e.fecha desc");
		consulta.setParameter("borrado", false);
		consulta.setParameter("id_cartelera", idCartelera);
		consulta.setParameter("id_usuario", idUsuario);
		
		resultado = consulta.getResultList();
		return resultado;
	}

}
