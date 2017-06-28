package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Cartelera;
import modelo.PermisoCartelera;
import modelo.Publicacion;
@JsonInclude(Include.NON_NULL)
public class CarteleraVO extends GenericVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Long id;
	public String nombre;   
    public String descripcion;
    public Set<Long> permisosCarteleras;
    
    
    public List<PublicacionVO> publicacionesDTO;
   
	public CarteleraVO(){
		this.setPermisosCarteleras(new HashSet<Long>());
    	
    }
    
    public CarteleraVO(Cartelera cartelera){
    	this.setId(cartelera.getId());
    	this.setNombre(cartelera.getNombre());
    	this.setDescripcion(cartelera.getDescripcion());
		this.setPermisosCarteleras(new HashSet<Long>());
    	for(PermisoCartelera p :cartelera.getPermisosPublicadores()){
    		this.getPermisosCarteleras().add(p.getId());
    	}
    }
    
	public CarteleraVO(Long id,String nombre, String descripcion) {
		super();
		this.id = id;
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
    
	public List<PublicacionVO> getPublicacionesDTO() {
		return publicacionesDTO;
	}

	public void setPublicacionesDTO(List<PublicacionVO> publicacionesDTO) {
		this.publicacionesDTO = publicacionesDTO;
	}
		
	public Set<Long> getPermisosCarteleras() {
		return permisosCarteleras;
	}

	public void setPermisosCarteleras(Set<Long> permisosCarteleras) {
		this.permisosCarteleras = permisosCarteleras;
	}

	public Cartelera toEntidad(){
		return (new Cartelera(this.getNombre(), this.getDescripcion()));
	}

	public Cartelera copiarAtributos(Cartelera carteleraActualizar) {
		carteleraActualizar.setNombre(this.getNombre());
		carteleraActualizar.setDescripcion(this.getDescripcion());
		return carteleraActualizar;
	}

	public void agregarPublicaciones(Cartelera carteleraRecuperar) {
		if(!carteleraRecuperar.getPublicaciones().isEmpty()){
			this.setPublicacionesDTO(new ArrayList<PublicacionVO>());
			for (Publicacion p: carteleraRecuperar.getPublicaciones()){
				if(!p.isBorrado())
					this.getPublicacionesDTO().add(new PublicacionVO(p));
			}
		}	
	}
	
	public void agregarPublicaciones(List<Publicacion> publicaciones) {
		if(!publicaciones.isEmpty()){
			this.setPublicacionesDTO(new ArrayList<PublicacionVO>());
			for (Publicacion p: publicaciones){
					this.getPublicacionesDTO().add(new PublicacionVO(p));
			}
		}
	}
    
}
