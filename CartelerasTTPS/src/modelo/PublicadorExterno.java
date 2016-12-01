package modelo;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="id_publicadorExterno")
public class PublicadorExterno extends UsuarioPublicador {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PublicadorExterno() {
		this.setPublicaciones(new ArrayList<Publicacion>());
		this.setPermisosCarteleras(new HashSet<Cartelera>());
		this.setComentarios(new ArrayList<Comentario>());
    }
	
	public PublicadorExterno(String usuario, String contrasena, String nombre, String apellido){
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.setPublicaciones(new ArrayList<Publicacion>());
		this.setPermisosCarteleras(new HashSet<Cartelera>());
		this.setComentarios(new ArrayList<Comentario>());
	}

}