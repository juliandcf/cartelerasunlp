package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id_usuarioPublicador")
@Inheritance(strategy=InheritanceType.JOINED)
public class UsuarioPublicador extends Usuario {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="autor")
    public List<Publicacion> publicaciones;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "usuarioPublicador_permiso", 
	joinColumns = { @JoinColumn(name = "usuarioPublicador_id") }, 
	inverseJoinColumns = {@JoinColumn(name = "permisos_id") })
	public Set<PermisoCartelera> permisosCarteleras;

    public UsuarioPublicador() {

    }


	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}


	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}


	public Set<PermisoCartelera> getPermisosCarteleras() {
		return permisosCarteleras;
	}


	public void setPermisosCarteleras(Set<PermisoCartelera> permisosCarteleras) {
		this.permisosCarteleras = permisosCarteleras;
	}
	
	
	
	private void initialize() {
		this.setPublicaciones(new ArrayList<Publicacion>());
		this.setPermisosCarteleras(new HashSet<PermisoCartelera>());
		this.setComentarios(new ArrayList<Comentario>());
	}
	
    public UsuarioPublicador (String usuario, String contrasena, String nombre, String apellido, String tipoUsuario) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		initialize();
	}
    
    public UsuarioPublicador (String usuario, String contrasena, String nombre, String apellido, Multimedia fotoPerfil, String tipoUsuario) {
  		super();
  		this.usuario = usuario;
  		this.contrasena = contrasena;
  		this.nombre = nombre;
  		this.apellido = apellido;
  		this.setFotoPerfil(fotoPerfil);
  		initialize();
  	}


	public UsuarioPublicador(String usuario, String contrasena, String nombre, String apellido, Multimedia multimedia,
			String tipoUsuario, Set<PermisoCartelera> permisosCarteleras) {
  		super();
  		this.usuario = usuario;
  		this.contrasena = contrasena;
  		this.nombre = nombre;
  		this.apellido = apellido;
  		this.setFotoPerfil(fotoPerfil);
  		this.setPermisosCarteleras(permisosCarteleras);
  		initialize();
	}


	public UsuarioPublicador(String usuario, String contrasena, String nombre, String apellido) {
		super();
  		this.usuario = usuario;
  		this.contrasena = contrasena;
  		this.nombre = nombre;
  		this.apellido = apellido;
  		initialize();
	}

    
}