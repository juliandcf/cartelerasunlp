package restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.GenericDTO;
import dto.PublicacionVO;
import modelo.Cartelera;
import modelo.Publicacion;
import modelo.UsuarioPublicador;
import serviciosInt.CarteleraService;
import serviciosInt.PublicacionService;
import serviciosInt.UsuarioPublicadorServiceJerarquia;

@RestController
@RequestMapping("/cartelera")
public class PublicacionRestController {
	
	@Autowired
	private PublicacionService publicacionService;
	
	@RequestMapping(value="/{idCartelera}/publicacion",  method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(@PathVariable("idCartelera") Long idCartelera){
		return new ResponseEntity<GenericDTO>(this.getPublicacionService().recuperarTodosVO(idCartelera),HttpStatus.OK);
	}
	

	@RequestMapping(value="/{idCartelera}/publicacion", method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@PathVariable("idCartelera") Long idCartelera, @RequestBody PublicacionVO publicacionVO){
		return new ResponseEntity<GenericDTO>(this.getPublicacionService().altaVO(publicacionVO,idCartelera),HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("idPublicacion") Long idPublicacion,
													@RequestBody PublicacionVO publicacionVO){
		return new ResponseEntity<GenericDTO>(this.getPublicacionService().modificarVO(idPublicacion,publicacionVO),HttpStatus.OK);

	}
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("idPublicacion") Long idPublicacion){
		return new ResponseEntity<GenericDTO>(this.getPublicacionService().borrarVO(idPublicacion), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("idCartelera") Long idCartelera, @PathVariable("idPublicacion") Long idPublicacion){
		return new ResponseEntity<GenericDTO>(this.getPublicacionService().recuperarVO(idCartelera,idPublicacion), HttpStatus.OK);
	}	
	
	
	public PublicacionService getPublicacionService() {
		return publicacionService;
	}

	public void setPublicacionService(PublicacionService publicacionService) {
		this.publicacionService = publicacionService;
	}

}
