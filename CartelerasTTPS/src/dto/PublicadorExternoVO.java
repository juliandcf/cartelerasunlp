package dto;

import java.io.Serializable;

import modelo.Administrador;
import modelo.Multimedia;
import modelo.PublicadorExterno;
import modelo.TipoEnlace;

public class PublicadorExternoVO extends UsuarioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PublicadorExternoVO(){
		
	}
	
	public PublicadorExternoVO(PublicadorExterno p) {
		super(p);
	}

	public PublicadorExterno toEntidad(){
		PublicadorExterno p;
		Multimedia multimedia= new Multimedia(this.getFotoPerfil(),TipoEnlace.IMAGEN.name());
		p = new PublicadorExterno(this.getUsuario(), this.getContrasena(), 
			this.getNombre(), this.getApellido(), multimedia);
		return p;
	}

	public PublicadorExterno copiarAtributosEn(PublicadorExterno publicadorRecuperar) {
		publicadorRecuperar.setUsuario(this.getUsuario());
		publicadorRecuperar.setContrasena(this.getContrasena());
		publicadorRecuperar.setNombre(this.getNombre());
		publicadorRecuperar.setApellido(this.getApellido());
		publicadorRecuperar.setURlFotoPerfil(this.getFotoPerfil());
		return publicadorRecuperar;
	}
}
