package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Where;

@Entity
@Table
@Where(clause="borrado=0")
public class Cartelera implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_cartelera")
	private Long id;
	public String nombre;   
    public String descripcion;
    private boolean borrado;
  
    @ManyToMany
    @JoinTable(
    		name="cartelera_alumno", 
    		joinColumns=@JoinColumn(name="cartelera_id",referencedColumnName="id_cartelera"),
    		inverseJoinColumns=@JoinColumn(name="alumno_id", referencedColumnName="id_alumno")
    ) 
    public Set<Alumno> alumnos;
    
    @OneToMany(mappedBy="cartelera", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    public List<Publicacion> publicaciones;
    
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "cartelera_permiso", 
			   joinColumns = { @JoinColumn(name = "cartelera_id", referencedColumnName="id_cartelera") }, 
			   inverseJoinColumns = {@JoinColumn(name = "permisos_id", referencedColumnName="id_permiso")},
			   uniqueConstraints=@UniqueConstraint(columnNames = { "cartelera_id", "permisos_id"})	
			)
	public Set<PermisoCartelera> permisosPublicadores;
    
	public Long getId() {
		return id;
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

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	public Set<PermisoCartelera> getPermisosPublicadores() {
		return permisosPublicadores;
	}

	public void setPermisosPublicadores(Set<PermisoCartelera> permisosPublicadores) {
		this.permisosPublicadores = permisosPublicadores;
	}
	
    public Cartelera() {
    }
    
	public Cartelera(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		initialize();
	}

	public Cartelera(String nombre, String descripcion, PermisoCartelera permiso) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		initialize();
		this.getPermisosPublicadores().add(permiso);
	}

	private void initialize() {
		this.setAlumnos(new HashSet<Alumno>());
		this.setPublicaciones(new ArrayList<Publicacion>());
		this.setPermisosPublicadores(new HashSet<PermisoCartelera>());
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartelera other = (Cartelera) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	

}