package dao;

import java.util.Collection;
import java.util.Set;

import modelo.Cartelera;
import modelo.PermisoCartelera;

public interface CarteleraDAO extends GenericDAO<Cartelera> {

	public boolean existe(Cartelera cartelera);
	public boolean existeConNombre(String nombreCartelera);
	public Set<Cartelera> getCartelerasConPermiso(PermisoCartelera permisoCartelera);
}
