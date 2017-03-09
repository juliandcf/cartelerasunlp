package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Multimedia;

public interface MultimediaDAO extends GenericDAO<Multimedia> {

	public List<Multimedia> recuperarMultimediaDePublicacion(Serializable idPublicacion);
}
