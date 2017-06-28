package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.MultimediaDAO;
import modelo.Multimedia;

@Repository
public class MultimediaDAOHibernateJPA extends GenericDAOHibernateJPA<Multimedia> implements MultimediaDAO {

	public MultimediaDAOHibernateJPA() {
		super(Multimedia.class);
	}

	@Override
	public boolean existe(Multimedia entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Multimedia> recuperarMultimediaDePublicacion(Serializable idPublicacion) {
		List<Multimedia> resultado = null;
		Query consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Publicacion p "
							+ "INNER JOIN p.multimedias m "
							+ "WHERE m.borrado = :borrado and p.id = :id_publicacion");
				//.createQuery("SELECT e FROM " + this.getPersistentClass().getSimpleName() + " m INNER JOIN publicacion_multimedia pm ON pm.multimedia_id = m.id_multimedia where m.borrado = :borrado and publicacion_fk = :id_publicacion");
		consulta.setParameter("borrado", false);
		consulta.setParameter("id_publicacion", idPublicacion);
		resultado = consulta.getResultList();
		return resultado;
	}

}
