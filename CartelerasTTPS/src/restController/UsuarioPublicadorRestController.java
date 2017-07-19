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
import dto.UsuarioPublicadorVO;
import dto.UsuarioVO;
import serviciosInt.UsuarioPublicadorService;


@RestController
@RequestMapping("/usuario/publicador")
public class UsuarioPublicadorRestController {
	
	@Autowired
	private UsuarioPublicadorService usuarioPublicadorService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(){
		return new ResponseEntity<>(this.getUsuarioPublicadorService().recuperarTodosVO(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("id") Long id){
		return new ResponseEntity<>(this.getUsuarioPublicadorService().recuperarVO(id), HttpStatus.OK);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@RequestBody UsuarioPublicadorVO usuarioPublicadorVO){
		return (new ResponseEntity<GenericDTO>(this.getUsuarioPublicadorService().altaVO(usuarioPublicadorVO), HttpStatus.OK));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("id") Long id,@RequestBody UsuarioPublicadorVO usuarioPublicadorVO){
		return new ResponseEntity<>(this.getUsuarioPublicadorService().modificarVO(id, usuarioPublicadorVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("id") Long id){
		return (new ResponseEntity<>(this.getUsuarioPublicadorService().borrarVO(id),HttpStatus.OK)); 
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> login(@RequestBody UsuarioVO usuarioVO){
		return (new ResponseEntity<>(this.getUsuarioPublicadorService().login(usuarioVO),HttpStatus.OK));
	}
	
	@RequestMapping(value="/existeUsuario", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@RequestBody UsuarioPublicadorVO usuarioPublicadorVO){
		return new ResponseEntity<>(this.getUsuarioPublicadorService().recuperarNombreUsuarioVO(usuarioPublicadorVO.getUsuario()), HttpStatus.OK);
	}	
	
	@RequestMapping(value="/{id}/carteleras", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarCarteleras(@PathVariable("id") Long id){
		return new ResponseEntity<>(this.getUsuarioPublicadorService().recuperarCartelerasParaUsuarioVO(id), HttpStatus.OK);
	}	
	@RequestMapping(value="admin/{id}/usuariosPublicadores", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarUsuariosPublicadores(@PathVariable("id") Long id){
		return new ResponseEntity<>(this.getUsuarioPublicadorService().recuperarPublicadoresVO(id), HttpStatus.OK);
	}	
	
	
	

	public UsuarioPublicadorService getUsuarioPublicadorService() {
		return usuarioPublicadorService;
	}
	public void setUsuarioPublicadorService(UsuarioPublicadorService usuarioPublicadorService) {
		this.usuarioPublicadorService = usuarioPublicadorService;
	}
}
