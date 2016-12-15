package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table
public class Publicacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_publicacion")
	private Long id;

	private String titulo;

	private String texto;

	private Date fecha;

	private boolean habilitarComentarios;
	
	private boolean borrado;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuarioPublicador_fk")
	private UsuarioPublicador autor;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "publicacion_fk", referencedColumnName = "id_publicacion", nullable=true)
	@JoinTable(name = "publicacion_multimedia", joinColumns = {
			@JoinColumn(name = "publicacion_id") }, inverseJoinColumns = { @JoinColumn(name = "multimedia_id") })
	private List<Multimedia> multimedias;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cartelera_fk")
	private Cartelera cartelera;

	@OneToMany(mappedBy = "publicacion", cascade=CascadeType.ALL)
	private List<Comentario> comentarios;

	public Publicacion() {
	}
	

	public Publicacion(String titulo, String texto, Date fecha, Boolean habilitarComentarios, UsuarioPublicador autor,
			Cartelera cartelera) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.fecha = fecha;
		this.habilitarComentarios = habilitarComentarios;
		this.autor = autor;
		this.cartelera = cartelera;
		this.comentarios=new ArrayList<Comentario>();
		this.multimedias=new ArrayList<Multimedia>();
	}

	public Publicacion(String titulo, String texto, Date fecha, Boolean habilitarComentarios, UsuarioPublicador autor,
			List<Multimedia> multimedias, Cartelera cartelera, List<Comentario> comentarios) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.fecha = fecha;
		this.habilitarComentarios = habilitarComentarios;
		this.autor = autor;
		this.multimedias = multimedias;
		this.cartelera = cartelera;
		this.comentarios = comentarios;
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

	public boolean getHabilitarComentarios() {
		return habilitarComentarios;
	}

	public void setHabilitarComentarios(boolean habilitarComentarios) {
		this.habilitarComentarios = habilitarComentarios;
	}

	public UsuarioPublicador getAutor() {
		return autor;
	}

	public void setAutor(UsuarioPublicador autor) {
		this.autor = autor;
	}

	public List<Multimedia> getMultimedias() {
		return multimedias;
	}

	public void setMultimedias(List<Multimedia> multimedias) {
		this.multimedias = multimedias;
	}

	public Cartelera getCartelera() {
		return cartelera;
	}

	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Long getId() {

		return this.id;
	}


	public boolean isBorrado() {
		return borrado;
	}


	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

//	@Override
//	public String toString() {
//		return "Publicacion [id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", fecha=" + fecha
//				+ ", habilitarComentarios=" + habilitarComentarios + ", autor=" + autor + ", multimedias=" + multimedias
//				+ ", cartelera=" + cartelera + ", comentarios=" + comentarios + "]";
//	}

}