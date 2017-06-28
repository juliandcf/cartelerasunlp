package serviciosImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PermisoCarteleraDAO;
import dto.GenericDTO;
import dto.PermisoCarteleraVO;
import modelo.PermisoCartelera;
import serviciosInt.PermisoCarteleraService;

@Transactional
@Service
public class PermisoCarteleraServiceImpl extends GenericServiceImpl<PermisoCartelera,PermisoCarteleraDAO>  implements PermisoCarteleraService{

	
	@Override
	public PermisoCartelera alta(PermisoCartelera permisoCartelera) {
		PermisoCartelera permisoReturn = null;	
		if (!this.existe(permisoCartelera)){
			permisoReturn = this.getDao().persistir(permisoCartelera);
		}
		return permisoReturn;
	}
	
	@Override
	public GenericDTO recuperarTodosVO() {
		GenericDTO dto = new GenericDTO();
		List<PermisoCartelera> permisos = this.recuperarTodos();
		if(!permisos.isEmpty()){
			List<PermisoCarteleraVO> permisosVO = new ArrayList<>();
			for (PermisoCartelera p: permisos) {
				permisosVO.add(new PermisoCarteleraVO(p));
			}
			dto.setObjeto(permisosVO);
		}else{
			dto.setCodigo(HttpStatus.NO_CONTENT.value());
			dto.setMensaje("No se encontraron permisos");
		}
		return dto;
	}

	@Override
	public GenericDTO recuperarVO(Long id) {
		GenericDTO dto = new GenericDTO();
		PermisoCartelera permiso = this.recuperar(id);
		if(permiso!=null){
			PermisoCarteleraVO vo = new PermisoCarteleraVO(permiso);
			dto.setObjeto(vo);
		}else{
			dto.setCodigo(HttpStatus.NO_CONTENT.value());
			dto.setMensaje("No existe el permiso con id "+id);
		}
		return dto;
	}

	@Override
	public GenericDTO altaVO(PermisoCarteleraVO permisoCarteleraVO) {
		PermisoCartelera permiso = permisoCarteleraVO.toEntidad();
		GenericDTO dto = new GenericDTO();
		PermisoCartelera permisoAgregado = this.alta(permiso);
		if(permisoAgregado!=null){
			dto.setCodigo(HttpStatus.CREATED.value());
			dto.setObjeto(new PermisoCarteleraVO(permiso));
		}else{
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("Ya existe un permiso con ese nombre");
		}
		return dto;
	}

	@Override
	public GenericDTO modificarVO(Long id, PermisoCarteleraVO permisoCarteleraVO) {
		GenericDTO dto = new GenericDTO();
		PermisoCartelera permisoActualizar = this.recuperar(id);
		if(permisoActualizar==null){
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El id del permiso ingresado no existe");
		}else{
			PermisoCartelera permisoRecibido = permisoCarteleraVO.toEntidad();
			//Si intento modificar el nombre Y el nombre que elegí ya existe
			if(!permisoRecibido.equals(permisoActualizar) && this.existe(permisoRecibido)){
					dto.setCodigo(HttpStatus.CONFLICT.value());
					dto.setMensaje("Ya existe un permiso con ese nombre");
			}else{
				PermisoCartelera permisoModificado = permisoCarteleraVO.copiarAtributos(permisoActualizar);
				this.modificar(permisoModificado);
				dto.setObjeto(new PermisoCarteleraVO(permisoModificado));
			}
		}
		return dto;
	}

	@Override
	public GenericDTO borrarVO(Long id) {
		PermisoCartelera permiso = this.recuperar(id);
		GenericDTO dto = new GenericDTO();
		if(permiso==null){
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El id del permiso ingresado no existe");
		}else{
			if(this.baja(id))
				dto.setMensaje("El permiso fue borrada correctamente");
		}
		return dto;	
	}

	@Override
	public GenericDTO cargarPermisosPorDefecto() {
		List<PermisoCartelera> permisos = new ArrayList<>();
		permisos.add(new PermisoCartelera("ADMINISTRADOR", "Usuario que tiene permisos para administrar todo el sitio"));
		permisos.add(new PermisoCartelera("PRIMER AÑO", "Usuario que tiene permisos para publicar en carteleras de primer año"));
		permisos.add(new PermisoCartelera("SEGUNDO AÑO", "Usuario que tiene permisos para publicar en carteleras de segundo año"));
		permisos.add(new PermisoCartelera("TERCER AÑO", "Usuario que tiene permisos para publicar en carteleras de tercer año"));
		permisos.add(new PermisoCartelera("CUARTO AÑO", "Usuario que tiene permisos para publicar en carteleras de cuarto año"));
		permisos.add(new PermisoCartelera("QUINTO AÑO", "Usuario que tiene permisos para publicar en carteleras de quinto año"));
		permisos.add(new PermisoCartelera("INSTITUCIONAL", "Usuario que tiene permisos para publicar en carteleras institucioonales año"));
		permisos.add(new PermisoCartelera("PUBLICADOR EXTERNO", "Usuario que tiene permisos para publicar en carteleras externas (trabajos, empresas, etc)"));
		for (PermisoCartelera p : permisos) {
			this.alta(p);	
		}
		GenericDTO dto = new GenericDTO();
		dto.setMensaje("se precargaron los roles");
		return dto;
	}

	@Override
	public boolean existen(List<String> nombresPermisos) {
		for (String nombre : nombresPermisos) {
			if(!this.getDao().existeConNombre(nombre)){
				return false;
			}			
		}
		return true;		
	}

	@Override
	public List<PermisoCartelera> recuperarPermisosDeUsuario(Long id) {
		return this.getDao().getPermisosParaUsuario(id);
	}

	@Override
	public PermisoCartelera recuperarPorNombre(String nombrePermiso) {
		return this.getDao().getPermisoPorNombre(nombrePermiso);
	}

	@Override
	public boolean existenId(Set<Long> permisosCartelerasId) {
		for (Long id : permisosCartelerasId) {
			if(!this.getDao().existe(id)){
				return false;
			}			
		}
		return true;
	}
}
