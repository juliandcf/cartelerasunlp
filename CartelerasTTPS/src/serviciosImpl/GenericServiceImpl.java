package serviciosImpl;

import java.io.Serializable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.GenericDAO;
import modelo.Cartelera;
import serviciosInt.GenericService;
@Transactional
@Service
/*T es la Clase del servicio que se esta realizando
 *G es el DAO que va a usar el servicio
 */
public abstract class GenericServiceImpl<T,G> implements GenericService<T> {
	
	@Autowired
	protected G dao;

	public GenericServiceImpl(){
		
	}
	
	public GenericServiceImpl(G dao){
		this.setDao(dao);
	}
	
	public G getDao() {
		return dao;
	}


	public void setDao(G dao){
		this.dao = dao;
	};

	@Override
	public T alta(T entity) {
		return ((GenericDAO<T>) this.getDao()).persistir(entity);
		
	}

	@Override
	public T modificar(T entity) {
		return ((GenericDAO<T>) this.getDao()).actualizar(entity);
	}

	
	public boolean baja(Serializable id) {
		return ((GenericDAO<T>) this.getDao()).borrar(id);
	}

	@Override
	public T recuperar(Serializable id) {
		return ((GenericDAO<T>) this.getDao()).recuperar(id);
	}

	@Override
	public List<T> recuperarTodos() {
		return ((GenericDAO<T>) this.getDao()).recuperarTodos();
	}


	@Override
	public boolean existe(T entity){
		return ((GenericDAO<T>) this.getDao()).existe(entity);
	};
    
	

}
