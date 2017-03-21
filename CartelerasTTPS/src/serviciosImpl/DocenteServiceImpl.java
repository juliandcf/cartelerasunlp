package serviciosImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.DocenteDAO;
import dto.DocenteVO;
import dto.GenericDTO;
import dto.UsuarioVO;
import jwt.TokenManagerSecurity;
import modelo.Docente;
import serviciosInt.DocenteService;

@Transactional
@Service
public class DocenteServiceImpl extends GenericServiceImpl<Docente,DocenteDAO> implements DocenteService{

	@Inject
	private TokenManagerSecurity tokenManagerSecurity;
	
	
	public DocenteServiceImpl(){
	}
	

	@Override
	public Docente alta(Docente entity) {
			return super.alta(entity);
	}
		
	public boolean baja(Docente entity){
		return (this.getDao().borrar(entity.getId()));
	}
	
	public Docente recuperar(String usuario){
		return (Docente) (this.getDao().recuperar(usuario));
	}
		
	public TokenManagerSecurity getTokenManagerSecurity() {
		return tokenManagerSecurity;
	}


	public void setTokenManagerSecurity(TokenManagerSecurity tokenManagerSecurity) {
		this.tokenManagerSecurity = tokenManagerSecurity;
	}

	

	@Override
	public GenericDTO altaVO(DocenteVO docenteVO) {
		GenericDTO dto = new GenericDTO();
		Docente docente = docenteVO.toEntidad();
		Docente docenteCreado = this.alta(docente);
		DocenteVO docenteVOreturn = new DocenteVO(docenteCreado);
		dto.setObjeto(docenteVOreturn);
		return dto;		
	}

	@Override
	public GenericDTO borrarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		Docente docenteBorrar = this.recuperar(id);
		if (docenteBorrar != null){
			if (!this.baja(id)){
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("Hubo un error al intentar borrar el docente");
			}
		}
		else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario docente que desea borrar no existe");
		}
		return dto;
	}


	@Override
	public GenericDTO modificarVO(Long id, DocenteVO docenteVO) {
		GenericDTO dto = new GenericDTO();		
		Docente docenteRecuperar = this.recuperar(id);
		if (docenteRecuperar == null){
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("No existe el docente seleccionado");
		}else{
			Docente docenteModificado = docenteVO.copiarAtributosEn(docenteRecuperar);
			this.modificar(docenteModificado);
			dto.setObjeto(new DocenteVO(docenteModificado));

		}
		return dto;
	}

	@Override
	public GenericDTO recuperarTodosVO() {
		GenericDTO dto = new GenericDTO();	
		List<DocenteVO> docentesVO = new ArrayList<DocenteVO>();
		List<Docente> docentes =this.recuperarTodos();
		if(!docentes.isEmpty()){
			for (Docente d : docentes) {
				docentesVO.add(new DocenteVO(d));
			}
			dto.setObjeto(docentesVO);
		}		
		return dto;
	}


	@Override
	public GenericDTO recuperarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		Docente docenteRecupear = this.recuperar(id);
		if (docenteRecupear != null){
			DocenteVO docenteVO = new DocenteVO(docenteRecupear);
			dto.setObjeto(docenteVO);
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El docente con el id "+id+" no existe");
		}		
		return dto;
	}

	@Override
	public GenericDTO recuperarNombreUsuarioVO(String nombreUsuario) {
		GenericDTO dto = new GenericDTO();
		Docente docenteRecupear = this.recuperar(nombreUsuario);
		if (docenteRecupear != null){
			DocenteVO docenteVO = new DocenteVO(docenteRecupear);
			docenteVO.setRol("profesores");
			dto.setObjeto(this.generateToken(docenteVO));
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El docente con el nombre de usuario "+nombreUsuario+" no existe");
		}		
		return dto;
	}

	public String generateToken(DocenteVO docenteVO){
		String token = null;
		try {
			token = tokenManagerSecurity.createJWT(docenteVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	@Override
	public GenericDTO login(UsuarioVO usuarioVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
