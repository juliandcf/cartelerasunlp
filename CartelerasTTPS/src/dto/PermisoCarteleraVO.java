package dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.PermisoCartelera;

@JsonInclude(Include.NON_NULL)
public class PermisoCarteleraVO extends GenericVO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	public Long id;
	public String nombre;
	public String descripcion;
	
	public PermisoCarteleraVO(){
		
	}
	
	public PermisoCarteleraVO(PermisoCartelera p){
		this.setId(p.getId());
		this.setNombre(p.getNombre());
		this.setDescripcion(p.getDescripcion());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public PermisoCartelera toEntidad(){
		return new PermisoCartelera(nombre, descripcion);
	}
	
	public PermisoCartelera copiarAtributos(PermisoCartelera permisoActualizar) {
		permisoActualizar.setNombre(this.getNombre());
		permisoActualizar.setDescripcion(this.getDescripcion());
		return permisoActualizar;
	}

}
