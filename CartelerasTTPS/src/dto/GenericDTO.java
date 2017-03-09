package dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class GenericDTO {
	public String mensaje;
	public int codigo;
	@JsonInclude(Include.NON_NULL)
	public Object objeto;	
	
	public GenericDTO(String mensaje, int codigo, Object objeto) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.objeto = objeto;
	}
	
	public GenericDTO() {
		this.setCodigo(HttpStatus.OK.value());
		this.setMensaje("La petición se procesó correctamente");
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Object getObjeto() {
		return objeto;
	}
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
	

}
