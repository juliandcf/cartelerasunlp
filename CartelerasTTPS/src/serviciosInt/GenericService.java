package serviciosInt;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T> {
	public T alta(T entity);
	public T modificar(T entity);
	public boolean baja(T entity);
	public T recuperar(Serializable id);
	public List<T> recuperarTodos();
	public boolean existe(T entity);

}
