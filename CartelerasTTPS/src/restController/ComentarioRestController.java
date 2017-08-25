package restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.ComentarioVO;
import dto.GenericDTO;
import serviciosInt.ComentarioService;
@CrossOrigin
@RestController
@RequestMapping("/cartelera")
public class ComentarioRestController {

	@Autowired
	private ComentarioService comentarioService;

	public ComentarioService getComentarioService() {
		return comentarioService;
	}

	public void setComentarioService(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}/comentario", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<GenericDTO> alta( @PathVariable("idCartelera") Long idCartelera, @PathVariable("idPublicacion") Long idPublicacion,
													@RequestBody ComentarioVO comentarioVO){
		return new ResponseEntity<GenericDTO>(this.getComentarioService().altaVO(idCartelera,idPublicacion,comentarioVO),HttpStatus.OK);

	}
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}/comentario/{idComentario}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> modificar(@PathVariable("idPublicacion") Long idPublicacion, @PathVariable("idCartelera") Long idCartelera,
												@PathVariable("idComentario") Long idComentario,@RequestBody ComentarioVO comentarioVO){
		return new ResponseEntity<GenericDTO>(this.getComentarioService().modificarVO(idCartelera,idPublicacion,idComentario, comentarioVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}/comentario/{idComentario}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> baja(@PathVariable("idPublicacion") Long idPublicacion, @PathVariable("idCartelera") Long idCartelera,
												@PathVariable("idComentario") Long idComentario){
		return new ResponseEntity<GenericDTO>(this.getComentarioService().eliminarVO(idCartelera,idPublicacion,idComentario),HttpStatus.OK);

	}
}
