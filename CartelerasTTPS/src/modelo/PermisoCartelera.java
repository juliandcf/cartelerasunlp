package modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class PermisoCartelera {

	@Id
	@GeneratedValue
	@Column(name = "id_permiso")
	private Long id;
	private String nombre;
	private String descripcion;
    private boolean borrado;
    
    @ManyToMany(mappedBy = "permisosPublicadores", cascade = CascadeType.ALL)
    public Set<Cartelera> cartelerasConPermiso;

    @ManyToMany(mappedBy = "permisosCarteleras", cascade = CascadeType.ALL)
    public Set<UsuarioPublicador> usuariosConPermiso;
	
	public PermisoCartelera() {
		
	}
	
	public PermisoCartelera(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Cartelera> getCartelerasConPermiso() {
		return cartelerasConPermiso;
	}

	public void setCartelerasConPermiso(Set<Cartelera> cartelerasConPermiso) {
		this.cartelerasConPermiso = cartelerasConPermiso;
	}

	public Set<UsuarioPublicador> getUsuariosConPermiso() {
		return usuariosConPermiso;
	}

	public void setUsuariosConPermiso(Set<UsuarioPublicador> usuariosConPermiso) {
		this.usuariosConPermiso = usuariosConPermiso;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermisoCartelera other = (PermisoCartelera) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
