package modelo;

import java.net.URI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Multimedia {

	@Id
	@GeneratedValue
	@Column(name = "id_multimedia")
	private Long id;
	private String url;
	private String tipoEnlace;

	public Multimedia() {
	}

	public Multimedia(String url, String tipoEnlace) {
		super();
		this.url = url;
		this.tipoEnlace = tipoEnlace;
	}

	public String getTipoEnlace() {
		return tipoEnlace;
	}

	public void setTipoEnlace(String tipoEnlace) {
		this.tipoEnlace = tipoEnlace;
	}

}