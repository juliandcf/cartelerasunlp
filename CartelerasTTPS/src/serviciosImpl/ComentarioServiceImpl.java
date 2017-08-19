package serviciosImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ComentarioDAO;
import dto.ComentarioVO;
import dto.GenericDTO;
import modelo.Comentario;
import modelo.Publicacion;
import modelo.Usuario;
import serviciosInt.AlumnoService;
import serviciosInt.CarteleraService;
import serviciosInt.ComentarioService;
import serviciosInt.PublicacionService;
import serviciosInt.UsuarioPublicadorService;

@Transactional
@Service
public class ComentarioServiceImpl extends GenericServiceImpl<Comentario,ComentarioDAO>  implements ComentarioService{

	@Autowired
	private UsuarioPublicadorService usuarioPublicadorService;
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private CarteleraService carteleraService;
	
	@Autowired
	private AlumnoService alumnoService;

	
	@Override
	public GenericDTO altaVO(Long idCartelera, Long idPublicacion, ComentarioVO comentarioVO) {
		GenericDTO dto = new GenericDTO();
		Usuario usuario = this.recuperarUsuario(comentarioVO.getIdUsuario());
		if(usuario != null) {
			Publicacion publicacion = this.getPublicacionService().recuperar(idPublicacion);
			Comentario comentario = comentarioVO.toEntidad(usuario);
			comentario.setPublicacion(publicacion);
			Comentario comentarioCreado = this.getDao().persistir(comentario);
			if(comentarioCreado != null) {
				ComentarioVO cVO = new ComentarioVO(comentarioCreado);
				dto.setObjeto(cVO);
			}else {
				dto.setMensaje("Hubo un error en la creacion del comentario");
				dto.setCodigo(HttpStatus.CONFLICT.value());
			}
		}else {
			dto.setMensaje("El usuario ingresado no existe");
			dto.setCodigo(HttpStatus.CONFLICT.value());
		}
		return dto;
	}

	@Override
	public GenericDTO modificarVO(Long idCartelera, Long idPublicacion, Long idComentario, ComentarioVO comentarioEditadoVO) {
		GenericDTO dto = new GenericDTO();
		Comentario comentario = this.getDao().recuperar(idComentario);
		if(comentario != null) {
			comentario.setTexto(comentarioEditadoVO.getTexto());
			comentario.setFechaEdicion(new Date());
			Comentario comentarioEditado = this.getDao().actualizar(comentario);
			if(comentarioEditado != null) {
				ComentarioVO cVO = new ComentarioVO(comentarioEditado);
				dto.setObjeto(cVO);
			}else {
				dto.setMensaje("Hubo un error en la edicion del comentario");
				dto.setCodigo(HttpStatus.CONFLICT.value());
			}
		}else {
			dto.setMensaje("El comentario con ese id no existe");
			dto.setCodigo(HttpStatus.CONFLICT.value());
		}
		return dto;
	}

	@Override
	public GenericDTO eliminarVO(Long idCartelera, Long idPublicacion, Long idComentario) {
		GenericDTO dto = new GenericDTO();
		Comentario comentario = this.getDao().recuperar(idComentario);
		if(comentario != null) {
			this.getDao().borrar(idComentario);
			dto.setMensaje("El comentario fue borrado correctamente");
		}else {
			dto.setMensaje("El comentario con ese id no existe");
			dto.setCodigo(HttpStatus.CONFLICT.value());
		}
		return dto;
	}
	
	private Usuario recuperarUsuario(Long idUsuario) {
		// Hay un problema con el DAO generico de Usuario, por eso decido hacerlo de esta forma
		Usuario usuario = null;
		 usuario = this.getUsuarioPublicadorService().recuperar(idUsuario);
		 if (usuario == null) {
			 usuario = this.getAlumnoService().recuperar(idUsuario);
		 }
		return usuario;
	}

	public PublicacionService getPublicacionService() {
		return publicacionService;
	}

	public void setPublicacionService(PublicacionService publicacionService) {
		this.publicacionService = publicacionService;
	}

	public CarteleraService getCarteleraService() {
		return carteleraService;
	}

	public void setCarteleraService(CarteleraService carteleraService) {
		this.carteleraService = carteleraService;
	}

	public AlumnoService getAlumnoService() {
		return alumnoService;
	}

	public void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	public UsuarioPublicadorService getUsuarioPublicadorService() {
		return usuarioPublicadorService;
	}

	public void setUsuarioPublicadorService(UsuarioPublicadorService usuarioPublicadorService) {
		this.usuarioPublicadorService = usuarioPublicadorService;
	}
	

}
