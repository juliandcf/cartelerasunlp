package serviciosInt;

import java.io.Serializable;

import dto.CarteleraVO;
import dto.GenericDTO;
import dto.UsuarioVO;

public interface UsuarioPublicadorServiceJerarquia<T,G> extends UsuarioService<T,G>{ 
	GenericDTO login(UsuarioVO usuarioVO);
	
}
