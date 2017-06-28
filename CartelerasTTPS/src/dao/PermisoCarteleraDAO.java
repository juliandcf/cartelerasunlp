package dao;

import java.util.List;

import modelo.PermisoCartelera;

public interface PermisoCarteleraDAO extends GenericDAO<PermisoCartelera> {

	List<PermisoCartelera> getPermisosParaUsuario(Long id);

	PermisoCartelera getPermisoPorNombre(String nombrePermiso);

	boolean existeConNombre(String nombre);

}
