package serviciosInt;

import dto.GenericDTO;

public interface UsuarioService<T,G> extends GenericService<T> {
	public GenericDTO altaVO(G usuarioVO);
	public GenericDTO borrarVO(Long id);
	public GenericDTO modificarVO(Long id, G usuarioVO);
	public GenericDTO recuperarTodosVO();
	public GenericDTO recuperarVO(Long id);
}
