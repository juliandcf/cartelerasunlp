package restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.AdministradorVO;
import dto.GenericDTO;
import dto.PublicadorExternoVO;
import dto.UsuarioVO;
import modelo.Administrador;
import serviciosInt.AdministradorService;
import serviciosInt.PublicadorExternoService;


@RestController
@RequestMapping("/usuario/publicadorExterno")
public class PublicadorExternoRestController {
	
	@Autowired
	private PublicadorExternoService publicadorExternoService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(){
		return new ResponseEntity<>(this.getPublicadorExternoService().recuperarTodosVO(), HttpStatus.OK);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("id") Long id){
		return new ResponseEntity<>(this.getPublicadorExternoService().recuperarVO(id), HttpStatus.OK);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@RequestBody PublicadorExternoVO publicadorVO){
		return (new ResponseEntity<GenericDTO>(this.getPublicadorExternoService().altaVO(publicadorVO), HttpStatus.OK));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("id") Long id,@RequestBody PublicadorExternoVO publicadorVO){
		return new ResponseEntity<>(this.getPublicadorExternoService().modificarVO(id, publicadorVO),HttpStatus.OK);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("id") Long id){
		return (new ResponseEntity<>(this.getPublicadorExternoService().borrarVO(id),HttpStatus.OK)); 
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> login(@RequestBody UsuarioVO usuarioVO){
		return (new ResponseEntity<>(this.getPublicadorExternoService().login(usuarioVO),HttpStatus.OK));
	}
	
	public PublicadorExternoService getPublicadorExternoService() {
		return publicadorExternoService;
	}
	public void setPublicadorExternoService(PublicadorExternoService publicadorExternoService) {
		this.publicadorExternoService = publicadorExternoService;
	}
	
	

}
