package dto;

import modelo.Multimedia;

public class MultimediaDTO {

	private String url;
	private String tipoEnlace;
	
	public MultimediaDTO(){
		
	}
	
	public MultimediaDTO(Multimedia m){
		this.setTipoEnlace(m.getTipoEnlace());
		this.setUrl(m.getUrl());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTipoEnlace() {
		return tipoEnlace;
	}

	public void setTipoEnlace(String tipoEnlace) {
		this.tipoEnlace = tipoEnlace;
	}
	
	public Multimedia toEntidad(){
		return new Multimedia(this.getUrl(), this.getTipoEnlace());
		
	}
	
}
