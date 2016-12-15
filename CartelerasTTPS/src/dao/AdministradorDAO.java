package dao;

import modelo.Administrador;

public interface AdministradorDAO extends UsuarioDAO<Administrador> {
	public boolean existe(Administrador administrador);
}
