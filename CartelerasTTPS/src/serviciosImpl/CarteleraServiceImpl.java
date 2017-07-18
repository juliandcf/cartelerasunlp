package serviciosImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CarteleraDAO;
import dto.CarteleraVO;
import dto.GenericDTO;
import modelo.Cartelera;
import modelo.PermisoCartelera;
import modelo.Publicacion;
import serviciosInt.CarteleraService;
import serviciosInt.PermisoCarteleraService;
import serviciosInt.PublicacionService;

@Transactional
@Service
public class CarteleraServiceImpl extends GenericServiceImpl<Cartelera,CarteleraDAO>  implements CarteleraService{
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private PermisoCarteleraService permisoCarteleraService;
	
	
	
	@Override
	public Cartelera alta(Cartelera cartelera) {
		Cartelera carteleraReturn = null;	
		if (!this.existe(cartelera)){
			carteleraReturn = this.getDao().persistir(cartelera);
		}
		return carteleraReturn;
	}


	public GenericDTO recuperarTodosVO(){
		GenericDTO dto = new GenericDTO();
		List<Cartelera> carteleras = this.recuperarTodos();
		List<CarteleraVO> cartelerasVO = new ArrayList<>();
		if(!carteleras.isEmpty()){
			carteleras.forEach((c)->cartelerasVO.add(new CarteleraVO(c)));
		}else{
			dto.setCodigo(HttpStatus.NO_CONTENT.value());
			dto.setMensaje("No se encontraron carteleras");
		}
		dto.setObjeto(cartelerasVO);
		return dto;
	}

	
	public GenericDTO recuperarVO(Serializable id){
		GenericDTO dto = new GenericDTO();
		Cartelera cartelera = this.recuperar(id);
		if(cartelera!=null){
			CarteleraVO vo = new CarteleraVO(cartelera);
			List<Publicacion> publicaciones = this.getPublicacionService().getPublicaciones(cartelera.getId());
			vo.agregarPublicaciones(publicaciones);
			dto.setObjeto(vo);
		}else{
			dto.setCodigo(HttpStatus.NO_CONTENT.value());
			dto.setMensaje("No existe la cartelera con id "+id);
		}
		return dto;
	}
	
	public GenericDTO altaVO(CarteleraVO carteleraVO){
		GenericDTO dto = new GenericDTO();
		if (getPermisoCarteleraService().existenId(carteleraVO.getPermisosCarteleras())){
			Cartelera cartelera = carteleraVO.toEntidad();
			cartelera = agregarPermisos(carteleraVO, cartelera);
			Cartelera carteleraAgregada = this.alta(cartelera);
			if(carteleraAgregada!=null){
				dto.setObjeto(new CarteleraVO(cartelera));
			}else{
				dto.setCodigo(HttpStatus.CONFLICT.value());
				dto.setMensaje("Ya existe una cartelera con ese nombre");
			}
		}else{
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("Los permisos ingresados no son correctos");
		}
		return dto;
	}
	
	@Override
	public GenericDTO modificarVO(Serializable id, CarteleraVO carteleraVO){
		GenericDTO dto = new GenericDTO();
		if (getPermisoCarteleraService().existenId(carteleraVO.getPermisosCarteleras())){
			Cartelera carteleraActualizar = this.recuperar(id);
			if(carteleraActualizar==null){
				dto.setCodigo(HttpStatus.NOT_FOUND.value());
				dto.setMensaje("El id de cartelera ingresado no existe");
			}else{
				Cartelera carteleraRecibida = carteleraVO.toEntidad();
				//Si el nombre de la cartelera que paso para modificar NO es igual a la cartelera que voy a modificar Y el nombre que voy a ponerle ya existe
				if(!carteleraRecibida.equals(carteleraActualizar) && this.existe(carteleraRecibida)){
						dto.setCodigo(HttpStatus.CONFLICT.value());
						dto.setMensaje("Ya existe una cartelera con ese nombre");
				}else{
					Cartelera carteleraModificada = carteleraVO.copiarAtributos(carteleraActualizar);
					carteleraModificada = agregarPermisos(carteleraVO, carteleraModificada);
					this.modificar(carteleraModificada);
					dto.setObjeto(new CarteleraVO(carteleraModificada));
				}
			}
		}else{
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("Los permisos ingresados no son correctos");
		}
		return dto;
	}
	
	@Override 
	public GenericDTO borrarVO(Serializable id){
		Cartelera cartelera = this.recuperar(id);
		GenericDTO dto = new GenericDTO();
		if(cartelera==null){
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("El id de cartelera ingresado no existe");
		}else{
			if(this.baja(id))
				dto.setMensaje("La cartelera fue borrada correctamente");
		}
		return dto;	
	}
	
	private Cartelera agregarPermisos(CarteleraVO carteleraVO, Cartelera cartelera) {
		for (Long idPermiso : carteleraVO.getPermisosCarteleras()) {
			PermisoCartelera perm = this.getPermisoCarteleraService().recuperar(idPermiso);
			cartelera.getPermisosPublicadores().add(perm);
		}
		return cartelera;
	}
	
	public PublicacionService getPublicacionService() {
		return publicacionService;
	}


	public void setPublicacionService(PublicacionService publicacionService) {
		this.publicacionService = publicacionService;
	}


	public PermisoCarteleraService getPermisoCarteleraService() {
		return permisoCarteleraService;
	}


	public void setPermisoCarteleraService(PermisoCarteleraService permisoCarteleraService) {
		this.permisoCarteleraService = permisoCarteleraService;
	}


	@Override
	public GenericDTO recuperarNombreCarteleraVO(String nombreCartelera) {
		GenericDTO dto = new GenericDTO();
		boolean cartelera = this.getDao().existeConNombre(nombreCartelera);  
		dto.setObjeto(cartelera);
		if(cartelera){
			dto.setCodigo(HttpStatus.NO_CONTENT.value());
			dto.setMensaje("Ya existe la cartelera con nombre "+nombreCartelera);
		}else{
			dto.setCodigo(HttpStatus.OK.value());
			dto.setMensaje("no existe "+nombreCartelera+" se puede agregar con seguridad");
		}
		return dto;
	}


	public GenericDTO recuperarConPermisos(Set<PermisoCartelera> permisosCarteleras) {
		GenericDTO dto = new GenericDTO();
		List<Cartelera> carteleras = this.recuperarTodos();
		List<CarteleraVO> cartelerasVO = new ArrayList<>();
		if (!carteleras.isEmpty()) {
			Set<Cartelera> cartelerasConPermiso = new HashSet<Cartelera>();
			for (PermisoCartelera permisoCartelera : permisosCarteleras) {
				cartelerasConPermiso.addAll(permisoCartelera.getCartelerasConPermiso());
			}
			cartelerasConPermiso.forEach((c)->cartelerasVO.add(new CarteleraVO(c)));
			dto.setObjeto(cartelerasVO);
		} else {
			dto.setCodigo(HttpStatus.NO_CONTENT.value());
			dto.setMensaje("No se encontraron carteleras");
		}
		return dto;
	}
	
	

		

	
	


}
