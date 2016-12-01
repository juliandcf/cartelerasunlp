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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="id_docente")
public class Docente extends UsuarioPublicador {


	private static final long serialVersionUID = 1L;
	
	 @ElementCollection
	 @CollectionTable(name="Anios", joinColumns=@JoinColumn(name="docente_id"))
	 @Column(name="anio")
	 @Enumerated(EnumType.STRING)
	 public List<Anio> anios;


	    public Docente() {
	    	
	    }


	public Docente(String usuario, String contrasena, String nombre, String apellido) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.setAnios(new ArrayList<Anio>());
		this.setPublicaciones(new ArrayList<Publicacion>());
		this.setPermisosCarteleras(new HashSet<Cartelera>());
		
	}
    
	public List<Anio> getAnios() {
		return anios;
	}


	public void setAnios(List<Anio> anios) {
		this.anios = anios;
	}
}