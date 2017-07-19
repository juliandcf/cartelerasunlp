package serviciosInt;

import dto.GenericDTO;
import dto.UsuarioPublicadorVO;
import dto.UsuarioVO;
import modelo.UsuarioPublicador;

public interface UsuarioPublicadorService extends UsuarioService<UsuarioPublicador,UsuarioPublicadorVO>{ 
	GenericDTO login(UsuarioVO usuarioVO);

	GenericDTO recuperarNombreUsuarioVO(String usuario);

	

	GenericDTO recuperarPublicadoresVO(Long id);
	
	
	
}
