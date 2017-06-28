package dao;

import modelo.UsuarioPublicador;

public interface UsuarioPublicadorDAO extends UsuarioDAO<UsuarioPublicador> {
	
	public UsuarioPublicador recuperar(String usuario);

}
