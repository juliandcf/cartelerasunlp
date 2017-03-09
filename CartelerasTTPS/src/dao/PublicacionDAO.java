package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Cartelera;
import modelo.Publicacion;

public interface PublicacionDAO extends GenericDAO<Publicacion> {

	public List<Publicacion> recuperarPublicacionesDeCartelera(Serializable idCartelera);

}
