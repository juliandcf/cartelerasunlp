package serviciosImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PublicacionDAO;
import dto.CarteleraVO;
import dto.GenericDTO;
import dto.PublicacionVO;
import modelo.Cartelera;
import modelo.Publicacion;
import modelo.UsuarioPublicador;
import serviciosInt.CarteleraService;
import serviciosInt.MultimediaService;
import serviciosInt.PublicacionService;
import serviciosInt.UsuarioPublicadorService;

@Transactional
@Service
public class PublicacionServiceImpl extends GenericServiceImpl<Publicacion,PublicacionDAO>  implements PublicacionService {

	@Autowired
	private CarteleraService carteleraService;
	
	@Autowired
	private UsuarioPublicadorService usuarioPublicadorService;
	
	@Autowired
	private MultimediaService multimediaService;
	
	
	public CarteleraService getCarteleraService() {
		return carteleraService;
	}

	public void setCarteleraService(CarteleraService carteleraService) {
		this.carteleraService = carteleraService;
	}

	public UsuarioPublicadorService getUsuarioPublicadorService() {
		return usuarioPublicadorService;
	}

	public void setUsuarioPublicadorService(UsuarioPublicadorService usuarioPublicadorService) {
		this.usuarioPublicadorService = usuarioPublicadorService;
	}
	
	@Override
	public boolean existe(Publicacion entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Publicacion> getPublicaciones(Long idCartelera) {
		return this.getDao().recuperarPublicacionesDeCartelera(idCartelera);
		
	}

	@Override
	public GenericDTO altaVO(PublicacionVO publicacionVO, Serializable idCartelera) {
		GenericDTO dto = new GenericDTO();
		Cartelera cartelera = this.getCarteleraService().recuperar(idCartelera); 
		UsuarioPublicador usuario = (UsuarioPublicador) this.getUsuarioPublicadorService().recuperar(publicacionVO.getIdPublicador());
		if(cartelera!=null && usuario!=null){
			Publicacion publicacion = publicacionVO.toEntidad(cartelera, usuario);
			Publicacion publicacionAgregada= this.alta(publicacion);
			if (publicacionAgregada != null){
				dto.setCodigo(HttpStatus.CREATED.value());
			}else{
				dto.setCodigo(HttpStatus.CONFLICT.value());
			}
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			if(cartelera==null)
				dto.setMensaje("La cartelera ingresada no es correcta");
			else
				dto.setMensaje("El usuario publicador no es correcta");
		}
		return dto;
		
	}
	
	@Override
	public GenericDTO recuperarVO(Long idCartelera, Long idPublicacion) {
		GenericDTO dto = new GenericDTO();
		if(this.getCarteleraService().recuperar(idCartelera)!=null){
			Publicacion publicacionRecuperar = this.recuperar(idPublicacion);
			if (publicacionRecuperar!= null){
				PublicacionVO publicacionVO = new PublicacionVO(publicacionRecuperar);			
				dto.setObjeto(publicacionVO);
			}else{
				dto.setCodigo(HttpStatus.NOT_FOUND.value());
				dto.setMensaje("No existe la publicacion con id "+idPublicacion);
			}
		}else{
			dto.setCodigo(HttpStatus.NOT_FOUND.value());
			dto.setMensaje("No existe la cartelera con id "+idCartelera);
		}
		
		return dto;
	}


	@Override
	public GenericDTO modificarVO(Serializable idPublicacion, PublicacionVO publicacionVO) {
		GenericDTO dto = new GenericDTO();
		Publicacion publicacionRecuperada = this.recuperar(idPublicacion);
		if(publicacionRecuperada != null){
			Publicacion publicacionModificada = publicacionVO.copiarAtributos(publicacionRecuperada);
			this.modificar(publicacionModificada);
		}else{
			dto.setCodigo(HttpStatus.CONFLICT.value());
			dto.setMensaje("La publicacion que se desea modificar no existe");
		}
		return dto;
	}

	@Override
	public GenericDTO recuperarTodosVO(Long idCartelera) {
		GenericDTO dto = new GenericDTO();
		Cartelera cartelera = this.getCarteleraService().recuperar(idCartelera);
		if(cartelera!=null){
			List<Publicacion> publicaciones = this.getDao().recuperarPublicacionesDeCartelera(idCartelera);
			List<PublicacionVO> publicacionesVO = new ArrayList<PublicacionVO>();
			for (Publicacion p: publicaciones) {
				publicacionesVO.add(new PublicacionVO(p, this.getMultimediaService().recuperarTodos(p.getId())));
			}
			
			dto.setObjeto(publicacionesVO);
		}else{
			dto.setCodigo(HttpStatus.NO_CONTENT.value());
			dto.setMensaje("No se encontró la cartelera solicitada");
		}
		return dto;		
	}
	
	@Override
	public GenericDTO borrarVO(Long idPublicacion){
		GenericDTO dto = new GenericDTO();
		Publicacion publicacion = this.recuperar(idPublicacion);
		if(publicacion!=null){
			this.getDao().borrar(idPublicacion);	
		}else{
			dto.setCodigo(HttpStatus.NO_CONTENT.value());
			dto.setMensaje("No se encontró la publicacion solicitada");
		}
		return dto;
		
	} 

	public MultimediaService getMultimediaService() {
		return multimediaService;
	}

	public void setMultimediaService(MultimediaService multimediaService) {
		this.multimediaService = multimediaService;
	}
	
}
