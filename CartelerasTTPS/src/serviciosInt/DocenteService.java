package serviciosInt;

import dto.DocenteVO;
import dto.GenericDTO;
import modelo.Docente;

public interface DocenteService extends UsuarioPublicadorServiceJerarquia<Docente,DocenteVO>{


	public GenericDTO recuperarNombreUsuarioVO(String nombreUsuario);
}
