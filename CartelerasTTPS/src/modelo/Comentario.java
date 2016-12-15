package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id	
	@GeneratedValue
	@Column(name = "id_comentario")
	private Long id;
    public String texto;
    public Date fecha;
    private boolean borrado;
    
    @ManyToOne(optional=false, fetch=FetchType.EAGER)
    @JoinColumn(name="publicacion_fk")
    public Publicacion publicacion;
    

    @ManyToOne(optional=false)
    @JoinColumn(name="usuario_fk")
    public Usuario usuario;
    
    public Comentario() {
    }

	public Comentario(String texto, Date fecha, Publicacion publicacion, Usuario usuario) {
		super();
		this.texto = texto;
		this.fecha = fecha;
		this.publicacion = publicacion;
		this.usuario = usuario;
	}
	
	public Comentario(String texto) {
		super();
		this.texto = texto;
		this.fecha = new Date();
	}

	public Comentario(String texto, Usuario usuario) {
		super();
		this.texto = texto;
		this.fecha = new Date();
		this.usuario = usuario;
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

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}


}