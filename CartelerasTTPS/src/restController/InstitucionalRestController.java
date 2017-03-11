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
import dto.InstitucionalVO;
import dto.PublicadorExternoVO;
import dto.UsuarioVO;
import modelo.Administrador;
import serviciosInt.AdministradorService;
import serviciosInt.InstitucionalService;
import serviciosInt.PublicadorExternoService;


@RestController
@RequestMapping("/usuario/institucional")
public class InstitucionalRestController {
	
	@Autowired
	private InstitucionalService institucionalService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(){
		return new ResponseEntity<>(this.getInstitucionalService().recuperarTodosVO(), HttpStatus.OK);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("id") Long id){
		return new ResponseEntity<>(this.getInstitucionalService().recuperarVO(id), HttpStatus.OK);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@RequestBody InstitucionalVO institucionalVO){
		return (new ResponseEntity<GenericDTO>(this.getInstitucionalService().altaVO(institucionalVO), HttpStatus.OK));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("id") Long id,@RequestBody InstitucionalVO institucionalVO){
		return new ResponseEntity<>(this.getInstitucionalService().modificarVO(id, institucionalVO),HttpStatus.OK);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("id") Long id){
		return (new ResponseEntity<>(this.getInstitucionalService().borrarVO(id),HttpStatus.OK)); 
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> login(@RequestBody UsuarioVO usuarioVO){
		return (new ResponseEntity<>(this.getInstitucionalService().login(usuarioVO),HttpStatus.OK));
	}
	
	
	public InstitucionalService getInstitucionalService() {
		return institucionalService;
	}
	
	public void setInstitucionalService(InstitucionalService institucionalService) {
		this.institucionalService = institucionalService;
	}
	

	
	

}
