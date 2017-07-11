package dao;

import modelo.Cartelera;

public interface CarteleraDAO extends GenericDAO<Cartelera> {

	public boolean existe(Cartelera cartelera);
	public boolean existeConNombre(String nombreCartelera);
}
