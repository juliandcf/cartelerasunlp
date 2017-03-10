package serviciosImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AdministradorDAO;
import dto.AdministradorVO;
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
					UsuarioVO userVO= new UsuarioVO(usuario);
					userVO.usuarioConRolParaToken("ADMINISTRADOR");
					String token = tokenManagerSecurity.createJWT(userVO);
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


	@Override
	public GenericDTO altaVO(AdministradorVO administradorVO) {
		GenericDTO dto = new GenericDTO();
		Administrador admin = administradorVO.toEntidad();
		Administrador adminCreado = this.alta(admin);
		if(adminCreado == null){
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("Ya existe un usuario administrador con ese nombre");
		}else{
			AdministradorVO adminVOReturn = new AdministradorVO(adminCreado);
			adminVOReturn.setContrasena(null);
			dto.setObjeto(adminVOReturn);
		}
		return dto;		
}


	@Override
	public GenericDTO borrarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		Administrador adminBorrar = this.recuperar(id);
		if (adminBorrar != null){
			if (!this.baja(id)){
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("Hubo un error al intentar borrar el administrador");
			}
		}
		else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario administrador que desea borrar no existe");
		}
		return dto;
	}


	@Override
	public GenericDTO modificarVO(Long id, AdministradorVO administradorVO) {
		GenericDTO dto = new GenericDTO();		
		Administrador adminRecuperar = this.recuperar(id);
		if (adminRecuperar == null){
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("No existe el administrador seleccionado");
		}else{
			Administrador adminRecibidoVO = administradorVO.toEntidad(); 
			if(!adminRecuperar.equals(adminRecibidoVO)){
				if(this.existe(adminRecibidoVO)){
					dto.setCodigo(HttpStatus.CONFLICT.value());
					dto.setMensaje("El nombre de usuario "+adminRecibidoVO.getUsuario()+" ya existe");
				}else{
					Administrador adminModificado = administradorVO.copiarAtributosEn(adminRecuperar);
					this.modificar(adminModificado);
					dto.setObjeto(new AdministradorVO(adminModificado));
				}
					
			}
			
		}
		return dto;
	}

	@Override
	public GenericDTO recuperarTodosVO() {
		GenericDTO dto = new GenericDTO();	
		
		List<AdministradorVO> administradoresVO = new ArrayList<AdministradorVO>();
		List<Administrador> administradores =this.recuperarTodos();
		if(!administradores.isEmpty()){
			for (Administrador a : administradores) {
				administradoresVO.add(new AdministradorVO(a));
			}
			dto.setObjeto(administradoresVO);
		}		
		return dto;
	}


	@Override
	public GenericDTO recuperarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		Administrador administradorRecuperar = this.recuperar(id);
		if (administradorRecuperar != null){
			AdministradorVO administradorVO = new AdministradorVO(administradorRecuperar);
			dto.setObjeto(administradorVO);
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El administrador con el id "+id+" no existe");
		}		
		return dto;
	}	
}
