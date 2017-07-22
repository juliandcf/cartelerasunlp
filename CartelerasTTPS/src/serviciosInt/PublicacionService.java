package serviciosInt;

import java.io.Serializable;
import java.util.List;

import dto.CarteleraVO;
import dto.GenericDTO;
import dto.PublicacionVO;
import modelo.Publicacion;

public interface PublicacionService extends GenericService<Publicacion> {

	List<Publicacion> getPublicaciones(Long idCartelera);
	public GenericDTO recuperarTodosVO(Long idCatelera);
	public GenericDTO recuperarVO(Long idCartelera, Long idPublicacion);
	public GenericDTO altaVO(PublicacionVO publicacionVO, Serializable idCartelera);
	public GenericDTO modificarVO(Serializable id, PublicacionVO publicacionVO);
	public GenericDTO borrarVO(Long idPublicacion);
	public List<Publicacion> getPublicacionesDeUsuario(Long id, Long id2);
	
}
