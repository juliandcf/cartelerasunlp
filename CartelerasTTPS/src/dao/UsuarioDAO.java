package dao;

import java.io.Serializable;

import modelo.Usuario;

public interface UsuarioDAO<T> extends GenericDAO<T> {
	public Usuario recuperar(String nombre, String apellido, String usuario);
}
