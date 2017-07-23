package serviciosInt;

import dto.AlumnoVO;
import dto.CarteleraVO;
import dto.GenericDTO;
import modelo.Alumno;

public interface AlumnoService extends UsuarioService<Alumno,AlumnoVO> {

	GenericDTO recuperarNombreUsuarioVO(String nombreUsuario);

	GenericDTO registrarInteresVO(Long idAlumno, CarteleraVO carteleraVO);

	GenericDTO eliminarInteresVO(Long idAlumno, Long idCartelera);

}
