package dto;

import java.io.Serializable;

import modelo.Administrador;
import modelo.Multimedia;
import modelo.TipoEnlace;

public class AdministradorVO extends UsuarioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministradorVO(){
		
	}
	
	public AdministradorVO(Administrador a) {
		super(a);
	}

	public Administrador toEntidad(){
		Administrador a;
		Multimedia multimedia= new Multimedia(this.getFotoPerfil(),TipoEnlace.IMAGEN.name());
		a = new Administrador(this.getUsuario(), this.getContrasena(), 
			this.getNombre(), this.getApellido(), multimedia);
		return a;
	}

	public Administrador copiarAtributos(Administrador adminRecuperar) {
		adminRecuperar.setUsuario(this.getUsuario());
		adminRecuperar.setContrasena(this.getContrasena());
		adminRecuperar.setNombre(this.getNombre());
		adminRecuperar.setApellido(this.getApellido());
		adminRecuperar.setURlFotoPerfil(this.getFotoPerfil());
		return adminRecuperar;
	}
}
