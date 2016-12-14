package dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Cartelera;
import modelo.Publicacion;

public class CarteleraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Long id;
	public String nombre;   
    public String descripcion;
    
    
    // Ignorar nulos http://www.baeldung.com/jackson-ignore-null-fields
    @JsonInclude(Include.NON_NULL)
    public List<PublicacionDTO> publicacionesDTO;
   
	public CarteleraDTO(){
    	
    }
    
    public CarteleraDTO(Cartelera cartelera){
    	this.setId(cartelera.getId());
    	this.setNombre(cartelera.getNombre());
    	this.setDescripcion(cartelera.getDescripcion());
    }
    
	public CarteleraDTO(Long id,String nombre, String descripcion) {
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
    
	public List<PublicacionDTO> getPublicacionesDTO() {
		return publicacionesDTO;
	}

	public void setPublicacionesDTO(List<PublicacionDTO> publicacionesDTO) {
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

	public Cartelera agregarPublicaciones(Cartelera carteleraRecuperar) {
		for (Publicacion p: carteleraRecuperar.getPublicaciones()) {
			this.getPublicacionesDTO().add(new PublicacionDTO(p));
		}
		return null;
	}
    
}
