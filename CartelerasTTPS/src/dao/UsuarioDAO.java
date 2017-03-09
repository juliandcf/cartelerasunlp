package dao;

import java.io.Serializable;

import modelo.Usuario;

public interface UsuarioDAO<T> extends GenericDAO<T> {
	public Usuario recuperar(String usuario);
	public Usuario login(String usuario, String password);
}
