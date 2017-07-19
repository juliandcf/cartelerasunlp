package serviciosImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UsuarioPublicadorDAO;
import dto.CarteleraVO;
import dto.GenericDTO;
import dto.PermisoCarteleraVO;
import dto.UsuarioPublicadorVO;
import dto.UsuarioVO;
import jwt.TokenManagerSecurity;
import modelo.Cartelera;
import modelo.PermisoCartelera;
import modelo.UsuarioPublicador;
import serviciosInt.CarteleraService;
import serviciosInt.PermisoCarteleraService;
import serviciosInt.UsuarioPublicadorService;

@Transactional
@Service
public class UsuarioPublicadorServiceImpl extends GenericServiceImpl<UsuarioPublicador,UsuarioPublicadorDAO>  implements UsuarioPublicadorService{
	
	@Autowired
	private PermisoCarteleraService permisoCarteleraService;
	
	@Autowired
	private CarteleraService carteleraService;
	
	@Inject
	private TokenManagerSecurity tokenManagerSecurity;
	
	public TokenManagerSecurity getTokenManagerSecurity() {
		return tokenManagerSecurity;
	}


	public void setTokenManagerSecurity(TokenManagerSecurity tokenManagerSecurity) {
		this.tokenManagerSecurity = tokenManagerSecurity;
	}
	
	@Override
	public UsuarioPublicador alta(UsuarioPublicador entity) {
		UsuarioPublicador userReturn = null;
		if(!this.existe(entity))	
			userReturn = super.alta(entity);
		return userReturn;
	}

	@Override
	public GenericDTO altaVO(UsuarioPublicadorVO usuarioVO) {
		GenericDTO dto = new GenericDTO();
		if (getPermisoCarteleraService().existen(usuarioVO.getPermisosCarteleras())){
			UsuarioPublicador usuario = usuarioVO.toEntidad();
			usuario = agregarPermisos(usuarioVO, usuario);
			UsuarioPublicador usuarioCreado = this.alta(usuario);
			if(usuarioCreado == null){
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("Ya existe un usuario con ese nombre");
			}else{
				UsuarioPublicadorVO usuarioVOReturn = new UsuarioPublicadorVO(usuarioCreado);
				usuarioVOReturn.setContrasena(null);
				usuarioVOReturn.setPermisosCarteleras(usuarioVO.getPermisosCarteleras());
				dto.setObjeto(usuarioVOReturn);
			}
		}else{
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("Los permisos ingresados no son correctos");
		}
		return dto;
	}

	private UsuarioPublicador agregarPermisos(UsuarioPublicadorVO usuarioVO, UsuarioPublicador usuario){
//		for (String nombrePermiso : usuarioVO.getPermisosCarteleras()) {
//			PermisoCartelera perm = this.getPermisoCarteleraService().recuperarPorNombre(nombrePermiso);
//			usuario.getPermisosCarteleras().add(perm);
//		}
//		return usuario;
	for (Long idPermiso : usuarioVO.getPermisosCarteleras()) {
		PermisoCartelera perm = this.getPermisoCarteleraService().recuperar(idPermiso);
		usuario.getPermisosCarteleras().add(perm);
	}
	return usuario;
	}

	@Override
	public GenericDTO borrarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		UsuarioPublicador usuarioBorrar = this.recuperar(id);
		if (usuarioBorrar != null){
			if (!this.baja(id)){
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("Hubo un error al intentar borrar el usuario");
			}
		}
		else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario que desea borrar no existe");
		}
		return dto;
	}


	@Override
	public GenericDTO modificarVO(Long id, UsuarioPublicadorVO usuarioVO) {
		GenericDTO dto = new GenericDTO();
		if (getPermisoCarteleraService().existen(usuarioVO.getPermisosCarteleras())){
			UsuarioPublicador usuarioRecuperar = this.recuperar(id);
			if (usuarioRecuperar == null){
				dto.setCodigo(HttpStatus.NOT_FOUND.value());
				dto.setMensaje("No existe el usuario publicador seleccionado");
			}else{
				UsuarioPublicador usuarioRecibidoVO = usuarioVO.toEntidad(); 
				//if(!usuarioRecuperar.equals(usuarioRecibidoVO)){
					if(this.existe(usuarioRecibidoVO) && !usuarioRecuperar.equals(usuarioRecibidoVO)){
						dto.setCodigo(HttpStatus.CONFLICT.value());
						dto.setMensaje("El nombre de usuario "+usuarioRecibidoVO.getUsuario()+" ya existe");
					}else{
						UsuarioPublicador usuarioModificado = usuarioVO.copiarAtributosEn(usuarioRecuperar);
						usuarioModificado = agregarPermisos(usuarioVO, usuarioModificado);
						this.modificar(usuarioModificado);
						dto.setObjeto(new UsuarioPublicadorVO(usuarioModificado));
					}
				//}
			}
		}else{
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("Los permisos ingresados no son correctos");
		}
		return dto;
	}

	@Override
	public GenericDTO recuperarTodosVO() {
		GenericDTO dto = new GenericDTO();	
		List<UsuarioPublicadorVO> usuariosVO = new ArrayList<UsuarioPublicadorVO>();
		List<UsuarioPublicador> usuariosPublicadores = this.recuperarTodos();
		if(!usuariosPublicadores.isEmpty()){
			for (UsuarioPublicador u : usuariosPublicadores) {
				UsuarioPublicadorVO uVO = new UsuarioPublicadorVO(u);
				uVO.setPermisosCartelerasVO(this.getPermisosDeUsuario(u.getId()));
				usuariosVO.add(uVO);
			}
			dto.setObjeto(usuariosVO);
		}		
		return dto;
	}
	// recupera usuarios publicadores excepto el id que te llega osea el usuario admin logueado
	@Override
	public GenericDTO recuperarPublicadoresVO(Long id) {
		GenericDTO dto = new GenericDTO();	
		List<UsuarioPublicadorVO> usuariosVO = new ArrayList<UsuarioPublicadorVO>();
		List<UsuarioPublicador> usuariosPublicadores = this.recuperarTodos();
		if(!usuariosPublicadores.isEmpty()){
			for (UsuarioPublicador u : usuariosPublicadores) {
				if (u.getId() != id) {
					boolean ok = true;
					for (PermisoCartelera permiso : u.getPermisosCarteleras()) {
						if (permiso.getNombre().matches("PRIMER AÑO|SEGUNDO AÑO|TERCER AÑO|CUARTO AÑO|QUINTO AÑO")) {
							ok = false;
						}

					}
					if (ok) {
						UsuarioPublicadorVO uVO = new UsuarioPublicadorVO(u);
						uVO.setPermisosCartelerasVO(this.getPermisosDeUsuario(u.getId()));
						usuariosVO.add(uVO);
					}

				}
				dto.setObjeto(usuariosVO);
			}
		}
		return dto;
	}


	private List<PermisoCarteleraVO> getPermisosDeUsuario(Long id) {
		List<PermisoCarteleraVO> permisosVO = new ArrayList<PermisoCarteleraVO>();
		List<PermisoCartelera> permisos = this.getPermisoCarteleraService().recuperarPermisosDeUsuario(id);
		for (PermisoCartelera perm: permisos) {
			permisosVO.add(new PermisoCarteleraVO(perm));
		}
		return permisosVO;
	}


	@Override
	public GenericDTO recuperarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		UsuarioPublicador usuarioRecuperar = this.recuperar(id);
		if (usuarioRecuperar != null){
			UsuarioPublicadorVO usuarioPublicadorVO = new UsuarioPublicadorVO(usuarioRecuperar);
			usuarioPublicadorVO.setContrasena(null);
			usuarioPublicadorVO.setPermisosCartelerasVO(this.getPermisosDeUsuario(usuarioRecuperar.getId()));
			dto.setObjeto(usuarioPublicadorVO);
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario con el id "+id+" no existe");
		}		
		return dto;
	}


	@Override
	public GenericDTO login(UsuarioVO usuarioVO) {
		GenericDTO dto = new GenericDTO();
		if (this.getDao().recuperar(usuarioVO.getUsuario()) != null){
			UsuarioPublicador usuarioPublicador = (UsuarioPublicador) this.getDao().login(usuarioVO.getUsuario(), usuarioVO.getContrasena());  
			if (usuarioPublicador != null){
				try {
					UsuarioPublicadorVO userVO= new UsuarioPublicadorVO(usuarioPublicador);
					userVO.setContrasena(null);
					userVO.setPermisosCartelerasVO(this.getPermisosDeUsuario(usuarioPublicador.getId()));
					userVO.prepareToken();
					String token = tokenManagerSecurity.createJWT(userVO);
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


	public PermisoCarteleraService getPermisoCarteleraService() {
		return permisoCarteleraService;
	}


	public void setPermisoCarteleraService(PermisoCarteleraService permisoCarteleraService) {
		this.permisoCarteleraService = permisoCarteleraService;
	}


	@Override
	public GenericDTO recuperarNombreUsuarioVO(String nombreUsuario) {
		GenericDTO dto = new GenericDTO();
		UsuarioPublicador usuarioPubRecuperar = this.recuperarPorNombre(nombreUsuario);
		if (usuarioPubRecuperar != null){
			UsuarioPublicadorVO usuarioPublicadorVO = new UsuarioPublicadorVO(usuarioPubRecuperar);
			usuarioPublicadorVO.agregarPermisos(usuarioPubRecuperar.getPermisosCarteleras());
			dto.setObjeto(this.generateToken(usuarioPublicadorVO));
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario publicador con el nombre de usuario "+nombreUsuario+" no existe");
		}		
		return dto;
	}
	
	private UsuarioPublicador recuperarPorNombre(String nombreUsuario) {
		return this.getDao().recuperar(nombreUsuario);
	}


	public String generateToken(UsuarioPublicadorVO usuarioPublicadorVO){
		String token = null;
		try {
			token = tokenManagerSecurity.createJWT(usuarioPublicadorVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	


	

}
