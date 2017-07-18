package serviciosInt;

import java.io.Serializable;
import java.util.Set;

import dto.CarteleraVO;
import dto.GenericDTO;
import modelo.Cartelera;
import modelo.PermisoCartelera;

public interface CarteleraService extends GenericService<Cartelera> {
	public GenericDTO recuperarTodosVO();
	public GenericDTO recuperarVO(Serializable id);
	public GenericDTO altaVO(CarteleraVO carteleraVO);
	public GenericDTO modificarVO(Serializable id, CarteleraVO carteleraVO);
	public GenericDTO borrarVO(Serializable id);
	public GenericDTO recuperarNombreCarteleraVO(String nombreCartelera);
	public GenericDTO recuperarConPermisos(Set<PermisoCartelera> permisosCarteleras);
	
}
