package modelo;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="id_administrador")
public class Administrador extends UsuarioPublicador {

		
	private static final long serialVersionUID = 1L;

	public Administrador() {
		
    }
	
	
	
    public Administrador(String usuario, String contrasena, String nombre, String apellido) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		initialize();
	}
    
    public Administrador(String usuario, String contrasena, String nombre, String apellido, Multimedia fotoPerfil) {
  		super();
  		this.usuario = usuario;
  		this.contrasena = contrasena;
  		this.nombre = nombre;
  		this.apellido = apellido;
  		initialize();
  	}



	private void initialize() {
		this.setPublicaciones(new ArrayList<Publicacion>());
		this.setPermisosCarteleras(new HashSet<Cartelera>());
		this.setComentarios(new ArrayList<Comentario>());
	}




    
    
    
}