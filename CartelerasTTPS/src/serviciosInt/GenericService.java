package serviciosInt;

import java.io.Serializable;
import java.util.List;

import dto.CarteleraVO;
import dto.GenericDTO;

public interface GenericService<G> {
	public G alta(G entity);
	public G modificar(G entity);
	//public boolean baja(G entity);
	public boolean baja(Serializable id);
	public G recuperar(Serializable id);
	public List<G> recuperarTodos();
	public boolean existe(G entity);



}
