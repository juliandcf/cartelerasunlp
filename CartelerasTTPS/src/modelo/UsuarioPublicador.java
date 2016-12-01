package modelo;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id_usuarioPublicador")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class UsuarioPublicador extends Usuario {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="autor")
    public List<Publicacion> publicaciones;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "usuarioPublicador_cartelera", joinColumns = { @JoinColumn(name = "usuarioPublicador_id") }, inverseJoinColumns = {
			@JoinColumn(name = "permisosCarteleras_id") })
	public Set<Cartelera> permisosCarteleras;

    public UsuarioPublicador() {

    }


	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}


	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}


	public Set<Cartelera> getPermisosCarteleras() {
		return permisosCarteleras;
	}


	public void setPermisosCarteleras(Set<Cartelera> permisosCarteleras) {
		this.permisosCarteleras = permisosCarteleras;
	}

    
}