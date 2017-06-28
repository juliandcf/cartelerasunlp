package serviciosInt;

import java.util.List;
import java.util.Set;

import dto.GenericDTO;
import dto.PermisoCarteleraVO;
import modelo.PermisoCartelera;

public interface PermisoCarteleraService extends GenericService<PermisoCartelera>{

	GenericDTO recuperarTodosVO();

	GenericDTO recuperarVO(Long id);

	GenericDTO altaVO(PermisoCarteleraVO permisoCarteleraVO);

	GenericDTO modificarVO(Long id, PermisoCarteleraVO permisoCarteleraVO);

	GenericDTO borrarVO(Long id);

	GenericDTO cargarPermisosPorDefecto();

	boolean existen(List<String> permisosCartelerasId);
	
	boolean existenId(Set<Long> set);

	List<PermisoCartelera> recuperarPermisosDeUsuario(Long id);

	PermisoCartelera recuperarPorNombre(String nombrePermiso);
	
}
