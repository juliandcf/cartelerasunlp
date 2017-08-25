package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
    /*Estos tuve que incluirlos porque el token parsea y retorna un usuarioVO, quizas podria ver crear un usuarioTokenVo*/
    @JsonInclude(Include.NON_NULL)
    public List<PermisoCarteleraVO> permisosCartelerasVO;
    @JsonInclude(Include.NON_NULL)
	public List<Long> permisosCarteleras;
    @JsonInclude(Include.NON_NULL)
    public String legajo;
    @JsonInclude(Include.NON_NULL)
	public String mail;
    @JsonInclude(Include.NON_NULL)
    public Set<CarteleraVO> cartelerasDeInteres;

    
	
    public UsuarioVO(String usuario, String contrasena, String nombre, String apellido) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
	}

    public UsuarioVO(String usuario, String contrasena, String nombre, String apellido,String tipoUsuario) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
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

    public void prepareToken(){
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

	public List<PermisoCarteleraVO> getPermisosCartelerasVO() {
		return permisosCartelerasVO;
	}

	public void setPermisosCartelerasVO(List<PermisoCarteleraVO> permisosCartelerasVO) {
		this.permisosCartelerasVO = permisosCartelerasVO;
	}

	public List<Long> getPermisosCarteleras() {
		return permisosCarteleras;
	}

	public void setPermisosCarteleras(List<Long> permisosCarteleras) {
		this.permisosCarteleras = permisosCarteleras;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set<CarteleraVO> getCartelerasDeInteres() {
		return cartelerasDeInteres;
	}

	public void setCartelerasDeInteres(Set<CarteleraVO> cartelerasDeInteres) {
		this.cartelerasDeInteres = cartelerasDeInteres;
	}
	
	
	
	
}
