package serviciosImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.InstitucionalDAO;
import dto.GenericDTO;
import dto.InstitucionalVO;
import dto.UsuarioVO;
import jwt.TokenManagerSecurity;
import modelo.Institucional;
import modelo.PublicadorExterno;
import serviciosInt.InstitucionalService;
import serviciosInt.UsuarioPublicadorService;

@Transactional
@Service
public class InstitucionalServiceImpl extends GenericServiceImpl<Institucional,InstitucionalDAO> implements InstitucionalService{

	@Inject
	private TokenManagerSecurity tokenManagerSecurity;
	
	@Autowired
	private UsuarioPublicadorService usuarioPublicadorService;
	
	
	public InstitucionalServiceImpl(){
	}
	

	@Override
	public Institucional alta(Institucional entity) {
		Institucional institucionalReturn = null;
		if(!this.existeInstitucional(entity))
			institucionalReturn = super.alta(entity);
		return institucionalReturn;
	}
	
	private boolean existeInstitucional(Institucional usuario){
		return this.getInstitucionalService().existe(usuario);
	}
	
	public boolean baja(Institucional entity){
		return (this.getDao().borrar(entity.getId()));
	}

	@Override
	public GenericDTO login(UsuarioVO usuarioVO) {
		GenericDTO dto = new GenericDTO();
		if (this.getDao().recuperar(usuarioVO.getUsuario()) != null){
			Institucional usuario = (Institucional) this.getDao().login(usuarioVO.getUsuario(), usuarioVO.getContrasena());  
			if (usuario != null){
				try {
					UsuarioVO userVO= new UsuarioVO(usuario);
					userVO.usuarioConRolParaToken("institucional");
					String token = tokenManagerSecurity.createJWT(userVO);
					dto.setObjeto(token);
				} catch (Exception e) {
					dto.setCodigo(HttpStatus.UNAUTHORIZED.value());
					dto.setMensaje("Hubo un error en la creacion del token");
				}	
			}
			else{
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("La contraseña ingresada no es correcta");
			}
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario "+usuarioVO.getUsuario()+" no existe");
		}
		return dto;	
	}
	
	public TokenManagerSecurity getTokenManagerSecurity() {
		return tokenManagerSecurity;
	}


	public void setTokenManagerSecurity(TokenManagerSecurity tokenManagerSecurity) {
		this.tokenManagerSecurity = tokenManagerSecurity;
	}


	@Override
	public GenericDTO altaVO(InstitucionalVO institucionalVO) {
		GenericDTO dto = new GenericDTO();
		Institucional institucional = institucionalVO.toEntidad();
		Institucional institucionalCreado = this.alta(institucional);
		if(institucionalCreado == null){
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("Ya existe un usuario con ese nombre de usuario");
		}else{
			InstitucionalVO institucionalVOReturn = new InstitucionalVO(institucionalCreado);
			institucionalVOReturn.setContrasena(null);
			dto.setObjeto(institucionalVOReturn);
		}
		return dto;		
}

	@Override
	public GenericDTO borrarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		Institucional institucionalBorrar = this.recuperar(id);
		if (institucionalBorrar != null){
			if (!this.baja(id)){
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("Hubo un error al intentar borrar el usuario institucional");
			}
		}
		else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario institucional que desea borrar no existe");
		}
		return dto;
	}

	@Override
	public GenericDTO modificarVO(Long id, InstitucionalVO institucionalVO) {
		GenericDTO dto = new GenericDTO();		
		Institucional institucionalRecuperar = this.recuperar(id);
		if (institucionalRecuperar == null){
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("No existe el usuario institucional seleccionado");
		}else{
			Institucional institucionalRecibidoVO = institucionalVO.toEntidad(); 
			if(!institucionalRecuperar.equals(institucionalRecibidoVO)){
				if(this.existeInstitucional(institucionalRecibidoVO)){
					dto.setCodigo(HttpStatus.CONFLICT.value());
					dto.setMensaje("El nombre de usuario "+institucionalRecibidoVO.getUsuario()+" ya existe");
				}else{
					Institucional institucionalModificado = institucionalVO.copiarAtributosEn(institucionalRecuperar);
					this.modificar(institucionalModificado);
					dto.setObjeto(new InstitucionalVO(institucionalModificado));
				}
			}
		}
		return dto;
	}

	@Override
	public GenericDTO recuperarTodosVO() {
		GenericDTO dto = new GenericDTO();	
		List<InstitucionalVO> institucionalesVO = new ArrayList<InstitucionalVO>();
		List<Institucional> publicadores =this.recuperarTodos();
		if(!publicadores.isEmpty()){
			for (Institucional i : publicadores) {
				institucionalesVO.add(new InstitucionalVO(i));
			}
			dto.setObjeto(institucionalesVO);
		}		
		return dto;
	}

	@Override
	public GenericDTO recuperarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		Institucional institucionalRecuperar = this.recuperar(id);
		if (institucionalRecuperar != null){
			InstitucionalVO institucionalVO = new InstitucionalVO(institucionalRecuperar);
			dto.setObjeto(institucionalVO);
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El usuario institucional con el id "+id+" no existe");
		}		
		return dto;
	}

	public UsuarioPublicadorService getInstitucionalService() {
		return usuarioPublicadorService;
	}


	public void setInstitucionalService(UsuarioPublicadorService institucionalService) {
		this.usuarioPublicadorService = institucionalService;
	}

}
