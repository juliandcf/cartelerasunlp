package serviciosInt;

import dto.AlumnoVO;
import dto.GenericDTO;
import modelo.Alumno;

public interface AlumnoService extends UsuarioService<Alumno,AlumnoVO> {

	GenericDTO recuperarNombreUsuarioVO(String nombreUsuario);

}
