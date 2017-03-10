package serviciosImpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UsuarioPublicadorDAO;
import dto.CarteleraVO;
import dto.GenericDTO;
import modelo.Cartelera;
import modelo.UsuarioPublicador;
import serviciosInt.CarteleraService;
import serviciosInt.UsuarioPublicadorService;

@Transactional
@Service
public class UsuarioPublicadorServiceRealImpl extends GenericServiceImpl<UsuarioPublicador,UsuarioPublicadorDAO>  implements UsuarioPublicadorService{
	
	@Autowired
	private UsuarioPublicadorService usuarioPublicadorService;
		


	public UsuarioPublicadorService getUsuarioPublicadorService() {
		return usuarioPublicadorService;
	}


	public void setUsuarioPublicadorService(UsuarioPublicadorService usuarioPublicadorService) {
		this.usuarioPublicadorService = usuarioPublicadorService;
	}



}
