package serviciosInt;

import java.io.Serializable;

import dto.CarteleraVO;
import dto.GenericDTO;
import modelo.Cartelera;

public interface CarteleraService extends GenericService<Cartelera> {
	public GenericDTO recuperarTodosVO();
	public GenericDTO recuperarVO(Serializable id);
	public GenericDTO altaVO(CarteleraVO carteleraVO);
	public GenericDTO modificarVO(Serializable id, CarteleraVO carteleraVO);
	public GenericDTO borrarVO(Serializable id);
	public GenericDTO recuperarNombreCarteleraVO(String nombreCartelera);
	
}
