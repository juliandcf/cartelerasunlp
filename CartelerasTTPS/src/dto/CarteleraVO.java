package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Cartelera;
import modelo.Publicacion;

public class CarteleraVO extends GenericVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Long id;
	public String nombre;   
    public String descripcion;
    
    
    // Ignorar nulos http://www.baeldung.com/jackson-ignore-null-fields
    @JsonInclude(Include.NON_NULL)
    public List<PublicacionVO> publicacionesDTO;
   
	public CarteleraVO(){
    	
    }
    
    public CarteleraVO(Cartelera cartelera){
    	this.setId(cartelera.getId());
    	this.setNombre(cartelera.getNombre());
    	this.setDescripcion(cartelera.getDescripcion());
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
