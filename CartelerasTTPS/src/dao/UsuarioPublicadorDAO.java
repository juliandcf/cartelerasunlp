package dao;

import java.util.List;

import modelo.UsuarioPublicador;

public interface UsuarioPublicadorDAO extends UsuarioDAO<UsuarioPublicador> {
	
	public UsuarioPublicador recuperar(String usuario);

	public List<UsuarioPublicador> recuperarTodosExcepto(Long id);

}
