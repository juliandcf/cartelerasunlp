package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {
	public T actualizar(T entity);

	public boolean borrar(T entity);

	public T borrar(Serializable id);

	public boolean existe(Serializable id);

	public T persistir(T entity);

	public T recuperar(Serializable id);
	
	public List<T> recuperarTodos(String columnOrder);
	
	public int contar();
	
}
