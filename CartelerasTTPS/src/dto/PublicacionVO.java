package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Cartelera;
import modelo.Multimedia;
import modelo.Publicacion;
import modelo.UsuarioPublicador;

public class PublicacionVO extends GenericVO implements Serializable {


	private static final long serialVersionUID = 1L;
	public Long id;
	public String titulo;
	public String texto;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	public Date fecha;
	@JsonInclude(Include.NON_NULL)
	public Long idPublicador;
	@JsonInclude(Include.NON_NULL)
	public String nombreAutor;
	@JsonInclude(Include.NON_NULL)
	public boolean habilitarComentarios;
	@JsonInclude(Include.NON_NULL)
	public UsuarioVO autor;
	@JsonInclude(Include.NON_NULL)
	public List<MultimediaVO> multimedias;
	@JsonInclude(Include.NON_NULL)
	public CarteleraVO cartelera;
	@JsonInclude(Include.NON_NULL)
	public List<ComentariosVO> comentarios;
	
	public PublicacionVO(){
		
	}
	
	public PublicacionVO(Publicacion p){
		this.setId(p.getId());
		this.setTitulo(p.getTitulo());
		this.setTexto(p.getTexto());
		this.setFecha(p.getFecha());
		this.setHabilitarComentarios(p.getHabilitarComentarios());
		this.setNombreAutor(p.getAutor().getNombre()+", "+p.getAutor().getApellido());
		this.setIdPublicador(p.getAutor().getId());
		this.cargarMultimediasEnDTO(p);
		//cargar comentarios
	}
	
	
	public PublicacionVO(Publicacion p, List<Multimedia> multimedias) {
		this.setId(p.getId());
		this.setTitulo(p.getTitulo());
		this.setTexto(p.getTexto());
		this.setFecha(p.getFecha());
		this.setHabilitarComentarios(p.getHabilitarComentarios());
		this.setNombreAutor(p.getAutor().getNombre()+", "+p.getAutor().getApellido());
		this.setIdPublicador(p.getAutor().getId());
		this.cargarMultimediasEnDTO(multimedias);
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
	public UsuarioVO getAutor() {
		return autor;
	}
	public void setAutor(UsuarioVO autor) {
		this.autor = autor;
	}
	public List<MultimediaVO> getMultimedias() {
		return multimedias;
	}
	public void setMultimedias(List<MultimediaVO> multimedias) {
		this.multimedias = multimedias;
	}
	public CarteleraVO getCartelera() {
		return cartelera;
	}
	public void setCartelera(CarteleraVO cartelera) {
		this.cartelera = cartelera;
	}
	public List<ComentariosVO> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<ComentariosVO> comentarios) {
		this.comentarios = comentarios;
	}
	
	public Long getIdPublicador() {
		return idPublicador;
	}

	public void setIdPublicador(Long idPublicador) {
		this.idPublicador = idPublicador;
	}

	public Publicacion toEntidad(Cartelera cartelera, UsuarioPublicador usuario){
		
		Publicacion publicacion = new Publicacion(this.getTitulo(),this.getTexto(),new Date(),this.isHabilitarComentarios(),usuario, cartelera); 
		return this.agregarMultimediasAPublicacion(publicacion);
	}

	private Publicacion agregarMultimediasAPublicacion(Publicacion publicacion){
		if(this.getMultimedias() != null && !this.getMultimedias().isEmpty()){
			List<Multimedia> multimediasConvertidas = new ArrayList<Multimedia>();
			for (MultimediaVO m : this.getMultimedias()) {
				multimediasConvertidas.add(m.toEntidad());
			}
			publicacion.setMultimedias(multimediasConvertidas);
		}
		return publicacion;
	}

	public Publicacion copiarAtributos(Publicacion publicacionRecuperada) {
		publicacionRecuperada.setTexto(this.getTexto());
		publicacionRecuperada.setTitulo(this.getTitulo());
		publicacionRecuperada.setHabilitarComentarios(this.isHabilitarComentarios());
		this.agregarMultimediasAPublicacion(publicacionRecuperada);
		return publicacionRecuperada;
	}

	public void cargarMultimediasEnDTO(Publicacion publicacionRecuperar) {
		if(!publicacionRecuperar.getMultimedias().isEmpty()){
			this.setMultimedias(new ArrayList<MultimediaVO>());
			for (Multimedia m: publicacionRecuperar.getMultimedias()){
				if(!m.isBorrado())
					this.getMultimedias().add(new MultimediaVO(m));
			}
		}	
	}
	
	private void cargarMultimediasEnDTO(List<Multimedia> multimedias) {
		if(!multimedias.isEmpty()){
			this.setMultimedias(new ArrayList<MultimediaVO>());
			for(Multimedia m: multimedias)
				this.getMultimedias().add(new MultimediaVO(m));
		}
	}
	
	
	
	
	
	
	
	
}
