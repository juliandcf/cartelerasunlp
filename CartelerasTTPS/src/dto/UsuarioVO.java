package dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import modelo.Usuario;

public class UsuarioVO extends GenericVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private Long id;
	@JsonInclude(Include.NON_NULL)
	public String usuario;
	@JsonInclude(Include.NON_NULL)
    public String contrasena;
    @JsonInclude(Include.NON_NULL)
    public String nombre;
    @JsonInclude(Include.NON_NULL)
    public String apellido;
    @JsonInclude(Include.NON_NULL)
    public String fotoPerfil;
    @JsonInclude(Include.NON_NULL)
    public String rol;
	
    public UsuarioVO(String usuario, String contrasena, String nombre, String apellido) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
	}

    public UsuarioVO(String usuario, String contrasena, String nombre, String apellido,String rol) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
	}
    
	public UsuarioVO(){
    	
    }
    
    public UsuarioVO(Usuario usuario){
    	this.setId(usuario.getId());
    	this.setUsuario(usuario.getUsuario());
    	this.setContrasena(usuario.getContrasena());
    	this.setNombre(usuario.getNombre());
    	this.setApellido(usuario.getApellido());
    	this.setFotoPerfil(usuario.getUrlFotoPerfil());
    }

    public void usuarioConRolParaToken(String rol){
    	this.setRol(rol);
    	this.setContrasena(null);
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}    
	
	
}
