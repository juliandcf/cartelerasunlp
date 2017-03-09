package serviciosImpl;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AdministradorDAO;
import dto.CarteleraVO;
import dto.GenericDTO;
import dto.UsuarioVO;
import jwt.TokenManagerSecurity;
import modelo.Administrador;
import serviciosInt.AdministradorService;

@Transactional
@Service
public class AdministradorServiceImpl extends GenericServiceImpl<Administrador,AdministradorDAO> implements AdministradorService{

	@Inject
	private TokenManagerSecurity tokenManagerSecurity;
	
	public AdministradorServiceImpl(){
	}
	

	@Override
	public Administrador alta(Administrador entity) {
		Administrador adminReturn = null;
		if(!this.existe(entity))
			adminReturn = super.alta(entity);
		return adminReturn;
	}
	
//	@Override
//	public boolean existe(Administrador entity) {
//		return (this.getDao().existe(entity));
//	}
	
	public boolean baja(Administrador entity){
		return (this.getDao().borrar(entity.getId()));
	}

	@Override
	public GenericDTO login(UsuarioVO usuarioVO) {
		GenericDTO dto = new GenericDTO();
		if (this.getDao().recuperar(usuarioVO.getUsuario()) != null){
			Administrador usuario = (Administrador) this.getDao().login(usuarioVO.getUsuario(), usuarioVO.getContrasena());  
			if (usuario != null){
				try {
					String token = tokenManagerSecurity.createJWT(new UsuarioVO(usuario));
					//dto.setObjeto("LOGUEADO! el token: "+token);
					dto.setObjeto(token);
				} catch (Exception e) {
					dto.setCodigo(HttpStatus.UNAUTHORIZED.value());
					dto.setMensaje("Hubo un error en la creacion del token");
				}
				
			}
			else{
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("La contraseña ingresada no es correcta");
			}
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario "+usuarioVO.getUsuario()+" no existe");
		}
		return dto;
		
	}
	
	public TokenManagerSecurity getTokenManagerSecurity() {
		return tokenManagerSecurity;
	}


	public void setTokenManagerSecurity(TokenManagerSecurity tokenManagerSecurity) {
		this.tokenManagerSecurity = tokenManagerSecurity;
	}

	
	
	
}
