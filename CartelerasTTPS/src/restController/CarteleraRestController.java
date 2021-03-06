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

import dto.CarteleraVO;
import dto.GenericDTO;
import serviciosInt.CarteleraService;

@CrossOrigin
@RestController
@RequestMapping("/cartelera")
public class CarteleraRestController {
	
	@Autowired
	private CarteleraService carteleraService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(){
		return (new ResponseEntity<GenericDTO>(carteleraService.recuperarTodosVO(),HttpStatus.OK));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("id") Long id){
		return new ResponseEntity<GenericDTO>(carteleraService.recuperarVO(id),HttpStatus.OK);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@RequestBody CarteleraVO carteleraVO){
		return new ResponseEntity<GenericDTO>(carteleraService.altaVO(carteleraVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("id") Long id, @RequestBody CarteleraVO carteleraVO){
		return new ResponseEntity<GenericDTO>(carteleraService.modificarVO(id,carteleraVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("id") Long id){
		return new ResponseEntity<GenericDTO>(carteleraService.borrarVO(id),HttpStatus.OK);
	}
	@RequestMapping(value="/existeCartelera/{nombre}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("nombre") String nombre ){
		return new ResponseEntity<GenericDTO>(carteleraService.recuperarNombreCarteleraVO(nombre), HttpStatus.OK);
	}
	
	@RequestMapping(value="/paraPublicador/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarCarteleras(@PathVariable("id") Long id){
		return new ResponseEntity<GenericDTO>(carteleraService.recuperarCartelerasParaUsuarioVO(id), HttpStatus.OK);
	}	
	
	@RequestMapping(value="/conPublicaciones", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarCartelerasConPublicaciones(){
		return new ResponseEntity<GenericDTO>(carteleraService.recuperarCartelerasConPublicacionesVO(), HttpStatus.OK);
	}
	
//	@RequestMapping(value="/interesados", method=RequestMethod.GET, produces={"application/json"})
//	public ResponseEntity<GenericDTO> recuperarCartelerasConInteresados(){
//		return new ResponseEntity<GenericDTO>(carteleraService.recuperarCartelerasConInteresadosVO(), HttpStatus.OK);
//	}
//	
}
