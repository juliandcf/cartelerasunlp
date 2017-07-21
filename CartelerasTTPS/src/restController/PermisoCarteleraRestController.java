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
import dto.PermisoCarteleraVO;
import serviciosInt.PermisoCarteleraService;

@RestController
@RequestMapping("/permisoCartelera")
public class PermisoCarteleraRestController {
	
	@Autowired
	private PermisoCarteleraService permisoCarteleraService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(){
		return (new ResponseEntity<GenericDTO>(permisoCarteleraService.recuperarTodosVO(),HttpStatus.OK));
	}
	@RequestMapping(value="/permisoCarteleraSinDocente",method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarSinDocentes(){
		return (new ResponseEntity<GenericDTO>(permisoCarteleraService.recuperarSinDocentesVO(),HttpStatus.OK));
	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("id") Long id){
		return new ResponseEntity<GenericDTO>(permisoCarteleraService.recuperarVO(id),HttpStatus.OK);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@RequestBody PermisoCarteleraVO permisoCarteleraVO){
		return new ResponseEntity<GenericDTO>(permisoCarteleraService.altaVO(permisoCarteleraVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("id") Long id, @RequestBody PermisoCarteleraVO permisoCarteleraVO){
		return new ResponseEntity<GenericDTO>(permisoCarteleraService.modificarVO(id,permisoCarteleraVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("id") Long id){
		return new ResponseEntity<GenericDTO>(permisoCarteleraService.borrarVO(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/cargarPermisos", method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> cargarPermisosPorDefecto(){
		return new ResponseEntity<GenericDTO>(permisoCarteleraService.cargarPermisosPorDefecto(),HttpStatus.OK);
	}

	public PermisoCarteleraService getPermisoCarteleraService() {
		return permisoCarteleraService;
	}

	public void setPermisoCarteleraService(PermisoCarteleraService permisoCarteleraService) {
		this.permisoCarteleraService = permisoCarteleraService;
	}
}
