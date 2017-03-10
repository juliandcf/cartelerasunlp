package serviciosImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PublicadorExternoDAO;
import dto.GenericDTO;
import dto.PublicadorExternoVO;
import dto.UsuarioVO;
import jwt.TokenManagerSecurity;
import modelo.PublicadorExterno;
import modelo.UsuarioPublicador;
import serviciosInt.PublicadorExternoService;
import serviciosInt.UsuarioPublicadorService;

@Transactional
@Service
public class PublicadorExternoServiceImpl extends GenericServiceImpl<PublicadorExterno,PublicadorExternoDAO> implements PublicadorExternoService{

	@Inject
	private TokenManagerSecurity tokenManagerSecurity;
	
	@Autowired
	private UsuarioPublicadorService usuarioPublicadorService;
	
	
	public PublicadorExternoServiceImpl(){
	}
	

	@Override
	public PublicadorExterno alta(PublicadorExterno entity) {
		PublicadorExterno publicadorReturn = null;
		if(!this.existeUsuarioPublicador(entity))
			publicadorReturn = super.alta(entity);
		return publicadorReturn;
	}
	
	private boolean existeUsuarioPublicador(UsuarioPublicador usuario){
		return this.getUsuarioPublicadorService().existe(usuario);
	}
	
	public boolean baja(PublicadorExterno entity){
		return (this.getDao().borrar(entity.getId()));
	}

	@Override
	public GenericDTO login(UsuarioVO usuarioVO) {
		GenericDTO dto = new GenericDTO();
		if (this.getDao().recuperar(usuarioVO.getUsuario()) != null){
			PublicadorExterno usuario = (PublicadorExterno) this.getDao().login(usuarioVO.getUsuario(), usuarioVO.getContrasena());  
			if (usuario != null){
				try {
					UsuarioVO userVO= new UsuarioVO(usuario);
					userVO.usuarioConRolParaToken("PUBLICADOR_EXTERNO");
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
	public GenericDTO altaVO(PublicadorExternoVO publicadorExternoVO) {
		GenericDTO dto = new GenericDTO();
		PublicadorExterno publicador = publicadorExternoVO.toEntidad();
		PublicadorExterno publicadorCreado = this.alta(publicador);
		if(publicadorCreado == null){
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("Ya existe un usuario publicador con ese nombre");
		}else{
			PublicadorExternoVO publicadorVOReturn = new PublicadorExternoVO(publicadorCreado);
			publicadorVOReturn.setContrasena(null);
			dto.setObjeto(publicadorVOReturn);
		}
		return dto;		
}


	@Override
	public GenericDTO borrarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		PublicadorExterno publicadorBorrar = this.recuperar(id);
		if (publicadorBorrar != null){
			if (!this.baja(id)){
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("Hubo un error al intentar borrar el publicador Externo");
			}
		}
		else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El publicador externo que desea borrar no existe");
		}
		return dto;
	}


	@Override
	public GenericDTO modificarVO(Long id, PublicadorExternoVO publicadorExternoVO) {
		GenericDTO dto = new GenericDTO();		
		PublicadorExterno publicadorRecuperar = this.recuperar(id);
		if (publicadorRecuperar == null){
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("No existe el administrador seleccionado");
		}else{
			PublicadorExterno publicadorRecibidoVO = publicadorExternoVO.toEntidad(); 
			if(!publicadorRecuperar.equals(publicadorRecibidoVO)){
				if(this.existeUsuarioPublicador(publicadorRecibidoVO)){
					dto.setCodigo(HttpStatus.CONFLICT.value());
					dto.setMensaje("El nombre de usuario "+publicadorRecibidoVO.getUsuario()+" ya existe");
				}else{
					PublicadorExterno publicadorModificado = publicadorExternoVO.copiarAtributosEn(publicadorRecuperar);
					this.modificar(publicadorModificado);
					dto.setObjeto(new PublicadorExternoVO(publicadorModificado));
				}
			}
		}
		return dto;
	}

	@Override
	public GenericDTO recuperarTodosVO() {
		GenericDTO dto = new GenericDTO();	
		
		List<PublicadorExternoVO> publicadoresVO = new ArrayList<PublicadorExternoVO>();
		List<PublicadorExterno> publicadores =this.recuperarTodos();
		if(!publicadores.isEmpty()){
			for (PublicadorExterno a : publicadores) {
				publicadoresVO.add(new PublicadorExternoVO(a));
			}
			dto.setObjeto(publicadoresVO);
		}		
		return dto;
	}

	@Override
	public GenericDTO recuperarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		PublicadorExterno publicadorRecuperar = this.recuperar(id);
		if (publicadorRecuperar != null){
			PublicadorExternoVO publicadorExternoVO = new PublicadorExternoVO(publicadorRecuperar);
			dto.setObjeto(publicadorExternoVO);
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El publicador externo con el id "+id+" no existe");
		}		
		return dto;
	}


	public UsuarioPublicadorService getUsuarioPublicadorService() {
		return usuarioPublicadorService;
	}


	public void setUsuarioPublicadorService(UsuarioPublicadorService usuarioPublicadorService) {
		this.usuarioPublicadorService = usuarioPublicadorService;
	}	
}
