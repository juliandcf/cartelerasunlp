package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id_alumno")
public class Alumno extends Usuario {

	//
	private static final long serialVersionUID = 1L;

	public String legajo;

	public String mail;

	@ManyToMany(mappedBy = "alumnos", cascade = CascadeType.ALL)
	public Set<Cartelera> cartelerasDeInteres;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "alumno_metodoInteres", joinColumns = { @JoinColumn(name = "alumno_id") }, inverseJoinColumns = {
			@JoinColumn(name = "interes_id") })
	public List<MetodoComunicacion> interes;

	public Alumno() {
	}

	public Alumno(String legajo, String mail, List<MetodoComunicacion> interes) {
		super();
		this.legajo = legajo;
		this.mail = mail;
		this.interes = interes;
		this.cartelerasDeInteres = new HashSet<Cartelera>();
		this.setComentarios(new ArrayList<Comentario>());

	}

	public Alumno(String legajo, String mail) {
		super();
		this.legajo = legajo;
		this.mail = mail;
		this.interes = new ArrayList<MetodoComunicacion>();
		this.cartelerasDeInteres = new HashSet<Cartelera>();
		this.setComentarios(new ArrayList<Comentario>());
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

	public Set<Cartelera> getCartelerasDeInteres() {
		return cartelerasDeInteres;
	}

	public void setCartelerasDeInteres(Set<Cartelera> interes) {
		this.cartelerasDeInteres = interes;
	}

}