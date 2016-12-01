package daoHibernateJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
	public T actualizar(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		T entityMerge;
		try{
		tx = em.getTransaction();
		tx.begin();
		entityMerge = em.merge(entity);
		tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e; 
		} finally {
			em.close();
		}
		return entityMerge;
	}

	@Override
	public boolean borrar(T entity) {
//		EntityManager em = EMF.getEMF().createEntityManager();
//		EntityTransaction tx = null;
//		T entityReference;
//		boolean exito=false;
//		try {
//			if (entity != null){
//				tx = em.getTransaction();
//				tx.begin();
//				entityReference = em.getReference(entityClass, primaryKey)
//				em.remove(entity);
//				tx.commit();
//				exito = true;
//			}
//		} catch (RuntimeException e) {
//			throw e;
//		} finally {
//			em.close();
//		}
//		return exito;
//		
		return true;
	}
	
	@Override
	public T borrar(Serializable id) {
		T entidad = this.recuperar(id);
		if(entidad != null)
			this.getEntityManager().remove(entidad);
		
		return entidad;
	}

	public List<T> recuperarTodos(String columnOrder) {
	
	Query consulta= this.getEntityManager().createQuery("select e from " + getPersistentClass().getSimpleName()+" e order by	e."+columnOrder);
	List<T> resultado = (List<T>)consulta.getResultList();
	return resultado;
	}

	@Override
	public boolean existe(Serializable id) {
		return (this.recuperar(id) != null);
	}

	@Override
	public T recuperar(Serializable id) {
		T entity = this.getEntityManager().find(this.getPersistentClass(), id);
		return entity;
	}
	
	public int contar() {
		Query consulta = this.getEntityManager().createQuery("SELECT COUNT(e.id) FROM " + this.getPersistentClass().getSimpleName() + " e");
		return (int)(long)consulta.getSingleResult();
	}


}
