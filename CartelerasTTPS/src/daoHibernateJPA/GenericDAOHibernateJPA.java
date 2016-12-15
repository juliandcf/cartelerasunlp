package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.transaction.annotation.Transactional;

import dao.GenericDAO;

@Transactional
public class GenericDAOHibernateJPA<T> implements GenericDAO<T> {

	protected Class<T> persistentClass;
	@PersistenceContext
	private EntityManager entityManager;

	public GenericDAOHibernateJPA(Class<T> persistentClass) {
		this.setPersistentClass(persistentClass);
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public T persistir(T entity) {
		this.getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public T actualizar(T entidad) {
		return this.getEntityManager().merge(entidad);
	}


	@Override
	public boolean borrar(Serializable id) {
//		T entidad = this.recuperar(id);
//		if (entidad != null)
//			this.getEntityManager().remove(entidad);
//
//		return entidad;
		T entidad = this.recuperar(id);
		int ejecutado = 0;
		if (entidad != null){
			Query consulta = this.getEntityManager()
					.createQuery("UPDATE " + this.getPersistentClass().getSimpleName() + " e SET e.borrado = :borrado WHERE e.id = :id AND borrado <> :borrado");
			consulta.setParameter("borrado", true);
			consulta.setParameter("id", id);
			ejecutado = consulta.executeUpdate();
		}
		return (ejecutado > 0);
	}

	@Override
	public List<T> recuperarTodos() {
		List<T> resultado = null;
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM " + this.getPersistentClass().getSimpleName() + " e where borrado = :borrado");
		consulta.setParameter("borrado", false);
		resultado = consulta.getResultList();
		return resultado;
	}

	public List<T> recuperarTodos(String columnOrder) {

		Query consulta = this.getEntityManager()
				.createQuery("select e from " + getPersistentClass().getSimpleName() + " e where borrado = :borrado order by e." + columnOrder);
						consulta.setParameter("borrado", false);
		List<T> resultado = (List<T>) consulta.getResultList();
		return resultado;
	}

	@Override
	public boolean existe(Serializable id) {
		return (this.recuperar(id) != null);
	}

	@Override
	public T recuperar(Serializable id) {
		Query consulta = this.getEntityManager()
				.createQuery("select e from " + getPersistentClass().getSimpleName() + " e where borrado = :borrado and e.id = :id");
		consulta.setParameter("borrado", false);
		consulta.setParameter("id", id);
		@SuppressWarnings("unchecked")
		T entity;
		try{
			entity = (T) consulta.getSingleResult();
		}catch(NoResultException e){
			entity = null;
		}
		return entity;
	}

	public int contar() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT COUNT(e.id) FROM " + this.getPersistentClass().getSimpleName() + " e where borrado = :borrado");
		consulta.setParameter("borrado", false);
		
		return (int) (long) consulta.getSingleResult();
	}

}
