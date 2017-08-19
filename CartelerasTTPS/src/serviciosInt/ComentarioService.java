package serviciosInt;

import dto.ComentarioVO;
import dto.GenericDTO;
import modelo.Comentario;

public interface ComentarioService extends GenericService<Comentario> {

	public GenericDTO altaVO(Long idCartelera, Long idPublicacion, ComentarioVO comentarioVO);

	public GenericDTO modificarVO(Long idCartelera, Long idPublicacion, Long idComentario, ComentarioVO comentarioVO);

	public GenericDTO eliminarVO(Long idCartelera, Long idPublicacion, Long idComentario);

	
}
