package serviciosImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AlumnoDAO;
import dto.AlumnoVO;
import dto.GenericDTO;
import jwt.TokenManagerSecurity;
import modelo.Alumno;
import serviciosInt.AlumnoService;

@Transactional
@Service
public class AlumnoServiceImpl extends GenericServiceImpl<Alumno,AlumnoDAO> implements AlumnoService{

	@Inject
	private TokenManagerSecurity tokenManagerSecurity;
	
	
	public AlumnoServiceImpl(){
	}
	

	@Override
	public Alumno alta(Alumno entity) {
			return super.alta(entity);
	}
		
	public boolean baja(Alumno entity){
		return (this.getDao().borrar(entity.getId()));
	}
	
	public Alumno recuperar(String usuario){
		return (Alumno) (this.getDao().recuperar(usuario));
	}
		
	public TokenManagerSecurity getTokenManagerSecurity() {
		return tokenManagerSecurity;
	}


	public void setTokenManagerSecurity(TokenManagerSecurity tokenManagerSecurity) {
		this.tokenManagerSecurity = tokenManagerSecurity;
	}

	

	@Override
	public GenericDTO altaVO(AlumnoVO alumnoVO) {
		GenericDTO dto = new GenericDTO();
		Alumno alumno = alumnoVO.toEntidad();
		Alumno alumnoCreado = this.alta(alumno);
		AlumnoVO alumnoVOreturn = new AlumnoVO(alumnoCreado);
		dto.setObjeto(alumnoVOreturn);
		return dto;		
	}

	@Override
	public GenericDTO borrarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		Alumno alumnoBorrar = this.recuperar(id);
		if (alumnoBorrar != null){
			if (!this.baja(id)){
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("Hubo un error al intentar borrar el alumno");
			}
		}
		else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El alumno que desea borrar no existe");
		}
		return dto;
	}


	@Override
	public GenericDTO modificarVO(Long id, AlumnoVO alumnoVO) {
		GenericDTO dto = new GenericDTO();		
		Alumno alumnoRecuperar = this.recuperar(id);
		if (alumnoRecuperar == null){
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("No existe el alumno seleccionado");
		}else{
			Alumno alumnoModificado = alumnoVO.copiarAtributosEn(alumnoRecuperar);
			this.modificar(alumnoModificado);
			dto.setObjeto(new AlumnoVO(alumnoModificado));

		}
		return dto;
	}

	@Override
	public GenericDTO recuperarTodosVO() {
		GenericDTO dto = new GenericDTO();	
		List<AlumnoVO> alumnosVO = new ArrayList<AlumnoVO>();
		List<Alumno> alumnos =this.recuperarTodos();
		if(!alumnos.isEmpty()){
			for (Alumno a : alumnos) {
				alumnosVO.add(new AlumnoVO(a));
			}
			dto.setObjeto(alumnosVO);
		}		
		return dto;
	}


	@Override
	public GenericDTO recuperarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		Alumno alumnoRecuperar = this.recuperar(id);
		if (alumnoRecuperar != null){
			AlumnoVO alumnoVO = new AlumnoVO(alumnoRecuperar);
			dto.setObjeto(alumnoVO);
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El alumno con el id "+id+" no existe");
		}		
		return dto;
	}

	@Override
	public GenericDTO recuperarNombreUsuarioVO(String nombreUsuario) {
		GenericDTO dto = new GenericDTO();
		Alumno alumnoRecuperar = this.recuperar(nombreUsuario);
		if (alumnoRecuperar != null){
			AlumnoVO alumnoVO = new AlumnoVO(alumnoRecuperar);
			//alumnoVO.setRol("alumnos");
			dto.setObjeto(this.generateToken(alumnoVO));
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El alumno con el nombre de usuario "+nombreUsuario+" no existe");
		}		
		return dto;
	}

	public String generateToken(AlumnoVO alumnoVO){
		String token = null;
		try {
			token = tokenManagerSecurity.createJWT(alumnoVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	

	
}
