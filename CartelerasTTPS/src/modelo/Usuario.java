package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue
	@Column(name = "id_usuario")
	private Long id;
	public String usuario;
    public String contrasena;
    public String nombre;
    public String apellido;
    private boolean borrado;
    @OneToMany(mappedBy="usuario",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    public List<Comentario> comentarios;
    
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="multimedia_fk")
    public Multimedia fotoPerfil;
    //public String tipoUsuario;
    
	public Usuario() {
		comentarios=new ArrayList<Comentario>();
    }

	public Long getId(){
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Multimedia getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(Multimedia fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

		
	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	public String getUrlFotoPerfil(){
		String url = null;
		if(this.getFotoPerfil() != null)
			url =  this.getFotoPerfil().getUrl();
		return url;
	}
	
	public void setURlFotoPerfil(String urlFotoPerfil) {
		if(this.getFotoPerfil() != null)
			this.getFotoPerfil().setUrl(urlFotoPerfil);
		
		
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", contrasena=" + contrasena + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", comentarios=" + comentarios + ", fotoPerfil=" + fotoPerfil + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}



	
    
}