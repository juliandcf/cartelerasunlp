package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Alumno;
import modelo.Cartelera;
import modelo.MetodoComunicacion;
import modelo.Multimedia;
import modelo.TipoEnlace;
@JsonInclude(Include.NON_NULL)
public class AlumnoVO extends UsuarioVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String legajo;
	public String mail;
	public Set<Cartelera> cartelerasDeInteres;
	public List<MetodoComunicacion> interes;
	
	public AlumnoVO(){
		
	}
	
	public AlumnoVO(Alumno a) {
		this.setId(a.getId());
		this.setUsuario(a.getUsuario());
    	this.setNombre(a.getNombre());
    	this.setApellido(a.getApellido());
    	this.setFotoPerfil(a.getUrlFotoPerfil());
    	this.setLegajo(a.getLegajo());
    	this.setMail(a.getMail());
    	//falta setear carteleras y metodo interes
	}

	public Alumno toEntidad(){
		Alumno a;
		Multimedia multimedia= new Multimedia(this.getFotoPerfil(),TipoEnlace.IMAGEN.name());
		a = new Alumno(this.getLegajo(), this.getMail(), this.getUsuario(), this.getNombre(), this.getApellido(), multimedia);
		return a;
	}

	public Alumno copiarAtributosEn(Alumno alumnoRecuperar) {
		alumnoRecuperar.setNombre(this.getNombre());
		alumnoRecuperar.setUsuario(this.getUsuario());
		alumnoRecuperar.setApellido(this.getApellido());
		alumnoRecuperar.setURlFotoPerfil(this.getFotoPerfil());
		alumnoRecuperar.setMail(this.getMail());
		alumnoRecuperar.setLegajo(this.getLegajo());
		return alumnoRecuperar;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set<Cartelera> getCartelerasDeInteres() {
		return cartelerasDeInteres;
	}

	public void setCartelerasDeInteres(Set<Cartelera> cartelerasDeInteres) {
		this.cartelerasDeInteres = cartelerasDeInteres;
	}

	public List<MetodoComunicacion> getInteres() {
		return interes;
	}

	public void setInteres(List<MetodoComunicacion> interes) {
		this.interes = interes;
	}	
	
	
}
