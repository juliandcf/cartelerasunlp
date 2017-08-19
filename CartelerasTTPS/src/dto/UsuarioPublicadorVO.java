package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Multimedia;
import modelo.PermisoCartelera;
import modelo.TipoEnlace;
import modelo.UsuarioPublicador;

@JsonInclude(Include.NON_NULL)
public class UsuarioPublicadorVO extends UsuarioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Long> permisosCarteleras;
	public List<PermisoCarteleraVO> permisosCartelerasVO;
	public List<String> permisosCartelerasNombres;
	
	
	public UsuarioPublicadorVO(){
		
	}
	
	public UsuarioPublicadorVO(UsuarioPublicador u) {
		super(u);
		initialize();
	}


	public UsuarioPublicador toEntidad(){
		UsuarioPublicador u;
		u = new UsuarioPublicador(this.getUsuario(), this.getContrasena(),this.getNombre(), this.getApellido());
		if(this.getFotoPerfil() != null){		
			Multimedia multimedia= new Multimedia(this.getFotoPerfil(),TipoEnlace.IMAGEN.name());
			u.setFotoPerfil(multimedia);
		}
		return u;
	}

	public UsuarioPublicador copiarAtributosEn(UsuarioPublicador userRecuperar) {
		userRecuperar.setUsuario(this.getUsuario());
		userRecuperar.setContrasena(this.getContrasena());
		userRecuperar.setNombre(this.getNombre());
		userRecuperar.setApellido(this.getApellido());
//		if(userRecuperar.getFotoPerfil() == null) {
//			Multimedia multimedia= new Multimedia(this.getFotoPerfil(),TipoEnlace.IMAGEN.name());
//			userRecuperar.setFotoPerfil(multimedia);
//		}else {
			userRecuperar.setURlFotoPerfil(this.getFotoPerfil());
		//}
		//userRecuperar.setTipoUsuario(this.getTipoUsuario());
		// setear los permisos en los servicios, para poder llamar al service de roles.
		return userRecuperar;
	}

	public List<Long> getPermisosCarteleras() {
		return permisosCarteleras;
	}

	public void setPermisosCarteleras(List<Long> permisosCarteleras) {
		this.permisosCarteleras = permisosCarteleras;
	}
	
	private void initialize() {
		this.setPermisosCarteleras(new ArrayList<Long>());
	}

	public List<PermisoCarteleraVO> getPermisosCartelerasVO() {
		return permisosCartelerasVO;
	}

	public void setPermisosCartelerasVO(List<PermisoCarteleraVO> permisosCartelerasVO) {
		this.permisosCartelerasVO = permisosCartelerasVO;
	}
	

	public List<String> getPermisosCartelerasNombres() {
		return permisosCartelerasNombres;
	}

	public void setPermisosCartelerasNombres(List<String> permisosCartelerasNombres) {
		this.permisosCartelerasNombres = permisosCartelerasNombres;
	}

	public void agregarPermisos(Set<PermisoCartelera> permisosCartelerasParaAgregar) {
		this.setPermisosCartelerasVO(new ArrayList<PermisoCarteleraVO>());
		for (PermisoCartelera permiso : permisosCartelerasParaAgregar) {
			this.getPermisosCartelerasVO().add(new PermisoCarteleraVO(permiso));
		}	
	}
	
	
	
}
