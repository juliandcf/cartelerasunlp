package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="id_docente")
public class Docente extends UsuarioPublicador {


	private static final long serialVersionUID = 1L;
	
	 @ElementCollection(fetch=FetchType.EAGER)
	 @CollectionTable(name="Anios",joinColumns=@JoinColumn(name="docente_id"))
	 @Column(name="anio")
	 public List<Integer> anios;

	 public String email;

	    public Docente() {
	    	
	    }


	public Docente(String usuario, String contrasena, String nombre, String apellido) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.setAnios(new ArrayList<Integer>());
		this.setPublicaciones(new ArrayList<Publicacion>());
		this.setPermisosCarteleras(new HashSet<Cartelera>());
	}
	
	public Docente(String usuario, String nombre, String apellido, Multimedia fotoPerfil, List<Integer> anios, String email) {
		super();
		this.setUsuario(usuario);
		this.nombre = nombre;
		this.apellido = apellido;
		this.setEmail(email);
		this.setFotoPerfil(fotoPerfil);
		this.setAnios(anios);
		this.setPublicaciones(new ArrayList<Publicacion>());
		this.setPermisosCarteleras(new HashSet<Cartelera>());
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