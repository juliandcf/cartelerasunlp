package dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Comentario;
import modelo.Usuario;

@JsonInclude(Include.NON_NULL)
public class ComentarioVO extends GenericVO implements Serializable {


	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
    public String texto;
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm a", timezone="America/Argentina/Buenos_Aires")
    public Date fecha;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm a", timezone="America/Argentina/Buenos_Aires")
    public Date fechaEdicion;
	@JsonInclude(Include.NON_NULL)
    public UsuarioVO usuario;
	@JsonInclude(Include.NON_NULL)
    public Long idUsuario;
	  
    
    public ComentarioVO(Comentario c) {
    	this.setTexto(c.getTexto());
    	this.setFecha(c.getFecha());
    	this.setFechaEdicion(c.getFechaEdicion());
    	this.setUsuario(new UsuarioVO(c.getUsuario()));
    	
    }
	public ComentarioVO() {
		// TODO Auto-generated constructor stub
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
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Date getFechaEdicion() {
		return fechaEdicion;
	}
	
	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}
	
	public Comentario toEntidad(Usuario usuario) {
		Comentario c = new Comentario(this.getTexto(),usuario);
		return c;
		
	}
	  
	  

}
