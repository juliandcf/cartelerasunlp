package serviciosInt;

import java.io.Serializable;

import dto.CarteleraVO;
import dto.GenericDTO;
import dto.UsuarioVO;

public interface UsuarioPublicadorService<T,G> extends GenericService<T>{ 
	GenericDTO login(UsuarioVO usuarioVO);
	public GenericDTO altaVO(G usuarioVO);
	public GenericDTO borrarVO(Long id);
	public GenericDTO modificarVO(Long id, G usuarioVO);
	public GenericDTO recuperarTodosVO();
	public GenericDTO recuperarVO(Long id);
	
}
