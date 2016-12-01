package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table
public class Cartelera implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue
	@Column(name = "id_cartelera")
	private Long id;
	
    public Long getId() {
		return id;
	}

	public String nombre;   
    public String descripcion;
  
    @ManyToMany
    @JoinTable(
    		name="cartelera_alumno", 
    		joinColumns=@JoinColumn(name="cartelera_id",referencedColumnName="id_cartelera"),
    		inverseJoinColumns=@JoinColumn(name="alumno_id", referencedColumnName="id_alumno")
    ) 
    public Set<Alumno> alumnos;
    
    @OneToMany(mappedBy="cartelera", cascade=CascadeType.ALL)
    public List<Publicacion> publicaciones;
    
    public Cartelera() {
    }
    
    

	public Cartelera(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.setAlumnos(new HashSet<Alumno>());
		this.setPublicaciones(new ArrayList<Publicacion>());
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

}