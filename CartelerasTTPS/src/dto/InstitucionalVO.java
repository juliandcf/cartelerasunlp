package dto;

import java.io.Serializable;

import modelo.Institucional;
import modelo.Multimedia;
import modelo.PublicadorExterno;
import modelo.TipoEnlace;

public class InstitucionalVO extends UsuarioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InstitucionalVO(){
		
	}
	
	public InstitucionalVO(Institucional p) {
		super(p);
	}

	public Institucional toEntidad(){
		Institucional p;
		Multimedia multimedia= new Multimedia(this.getFotoPerfil(),TipoEnlace.IMAGEN.name());
		p = new Institucional(this.getUsuario(), this.getContrasena(), 
			this.getNombre(), this.getApellido(), multimedia);
		return p;
	}
	public Institucional copiarAtributosEn(Institucional institucionalRecuperar) {
		institucionalRecuperar.setUsuario(this.getUsuario());
		institucionalRecuperar.setContrasena(this.getContrasena());
		institucionalRecuperar.setNombre(this.getNombre());
		institucionalRecuperar.setApellido(this.getApellido());
		institucionalRecuperar.setURlFotoPerfil(this.getFotoPerfil());
		return institucionalRecuperar;
	}
}
