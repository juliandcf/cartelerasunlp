package dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Usuario;

public abstract class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private Long id;
	public String usuario;
    public String contrasena;
    public String nombre;
    public String apellido;
    @JsonInclude(Include.NON_NULL)
    public String fotoPerfil;
	
    public UsuarioDTO(){
    	
    }
    
    public UsuarioDTO(Usuario usuario){
    	this.setId(usuario.getId());
    	this.setUsuario(usuario.getUsuario());
    	this.setContrasena(usuario.getContrasena());
    	this.setNombre(usuario.getNombre());
    	this.setApellido(usuario.getApellido());
    	this.setFotoPerfil(usuario.getUrlFotoPerfil());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}    
}
