package serviciosInt;

import dto.GenericDTO;
import dto.UsuarioVO;
import modelo.Administrador;

public interface UsuarioPublicadorService<T> extends GenericService<T>{ 
	GenericDTO login(UsuarioVO usuarioVO);
	
}
