package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Publicacion;

public class PublicacionDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	public Long id;
	public String titulo;
	public String texto;
	public Date fecha;
	@JsonInclude(Include.NON_NULL)
	public String nombreAutor;
	@JsonInclude(Include.NON_NULL)
	public boolean habilitarComentarios;
	@JsonInclude(Include.NON_NULL)
	public AutorDTO autor;
	@JsonInclude(Include.NON_NULL)
	public List<MultimediaDTO> multimedias;
	@JsonInclude(Include.NON_NULL)
	public CarteleraDTO cartelera;
	@JsonInclude(Include.NON_NULL)
	public List<ComentariosDTO> comentarios;
	
	public PublicacionDTO(){
		
	}
	
	public PublicacionDTO(Publicacion p){
		this.setId(p.getId());
		this.setTitulo(p.getTitulo());
		this.setTexto(p.getTexto());
		this.setFecha(p.getFecha());
		this.setNombreAutor(p.getAutor().getNombre()+", "+p.getAutor().getApellido());
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreAutor() {
		return nombreAutor;
	}
	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}
	public boolean isHabilitarComentarios() {
		return habilitarComentarios;
	}
	public void setHabilitarComentarios(boolean habilitarComentarios) {
		this.habilitarComentarios = habilitarComentarios;
	}
	public AutorDTO getAutor() {
		return autor;
	}
	public void setAutor(AutorDTO autor) {
		this.autor = autor;
	}
	public List<MultimediaDTO> getMultimedias() {
		return multimedias;
	}
	public void setMultimedias(List<MultimediaDTO> multimedias) {
		this.multimedias = multimedias;
	}
	public CarteleraDTO getCartelera() {
		return cartelera;
	}
	public void setCartelera(CarteleraDTO cartelera) {
		this.cartelera = cartelera;
	}
	public List<ComentariosDTO> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<ComentariosDTO> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
	
	
}
