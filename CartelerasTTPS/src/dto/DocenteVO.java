package dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Docente;
import modelo.Multimedia;
import modelo.TipoEnlace;
@JsonInclude(Include.NON_NULL)
public class DocenteVO extends UsuarioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Integer> anios;
	public String email;
	
	public DocenteVO(){
		
	}
	
	public DocenteVO(Docente d) {
		this.setId(d.getId());
		this.setUsuario(d.getUsuario());
    	this.setNombre(d.getNombre());
    	this.setApellido(d.getApellido());
    	this.setFotoPerfil(d.getUrlFotoPerfil());
    	this.setAnios(d.getAnios());
    	this.setEmail(d.getEmail());
	}

	public Docente toEntidad(){
		Docente d;
		Multimedia multimedia= new Multimedia(this.getFotoPerfil(),TipoEnlace.IMAGEN.name());
		d = new Docente(this.getUsuario(), this.getNombre(), this.getApellido(), multimedia, this.getAnios(), this.getEmail());
		return d;
	}

	public Docente copiarAtributosEn(Docente docenteRecuperar) {
		docenteRecuperar.setNombre(this.getNombre());
		docenteRecuperar.setUsuario(this.getUsuario());
		docenteRecuperar.setApellido(this.getApellido());
		docenteRecuperar.setURlFotoPerfil(this.getFotoPerfil());
		docenteRecuperar.setAnios(this.anios);
		docenteRecuperar.setEmail(this.getEmail());
		return docenteRecuperar;
	}

	public List<Integer> getAnios() {
		return anios;
	}

	public void setAnios(List<Integer> anios) {
		this.anios = anios;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
}
