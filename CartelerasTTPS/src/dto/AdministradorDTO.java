package dto;

import java.io.Serializable;

import modelo.Administrador;
import modelo.Multimedia;
import modelo.TipoEnlace;

public class AdministradorDTO extends UsuarioDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministradorDTO(){
		
	}
	
	public AdministradorDTO(Administrador a) {
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
