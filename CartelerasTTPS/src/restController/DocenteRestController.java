package restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.DocenteVO;
import dto.GenericDTO;
import serviciosInt.DocenteService;


@RestController
@RequestMapping("/usuario/profesores")
public class DocenteRestController {
	
	@Autowired
	private DocenteService docenteService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(){
		return new ResponseEntity<>(this.getDocenteService().recuperarTodosVO(), HttpStatus.OK);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("id") Long id){
		return new ResponseEntity<>(this.getDocenteService().recuperarVO(id), HttpStatus.OK);
	}	
	
	@RequestMapping(value="/existeUsuario", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@RequestBody DocenteVO docenteVO){
		return new ResponseEntity<>(this.getDocenteService().recuperarNombreUsuarioVO(docenteVO.getUsuario()), HttpStatus.OK);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@RequestBody DocenteVO docenteVO){
		return (new ResponseEntity<GenericDTO>(this.getDocenteService().altaVO(docenteVO), HttpStatus.OK));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("id") Long id,@RequestBody DocenteVO docenteVO){
		return new ResponseEntity<>(this.getDocenteService().modificarVO(id, docenteVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("id") Long id){
		return (new ResponseEntity<>(this.getDocenteService().borrarVO(id),HttpStatus.OK)); 
	}
	
	
	
	public DocenteService getDocenteService() {
		return docenteService;
	}
	
	public void setDocenteService(DocenteService docenteService) {
		this.docenteService = docenteService;
	}
}
