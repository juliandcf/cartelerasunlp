package restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.AlumnoVO;
import dto.DocenteVO;
import dto.GenericDTO;
import serviciosInt.AlumnoService;


@RestController
@RequestMapping("/usuario/alumnos")
public class AlumnoRestController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(){
		return new ResponseEntity<>(this.getAlumnoService().recuperarTodosVO(), HttpStatus.OK);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("id") Long id){
		return new ResponseEntity<>(this.getAlumnoService().recuperarVO(id), HttpStatus.OK);
	}	
	
	@RequestMapping(value="/existeUsuario", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@RequestBody AlumnoVO alumnoVO){
		return new ResponseEntity<>(this.getAlumnoService().recuperarNombreUsuarioVO(alumnoVO.getUsuario()), HttpStatus.OK);
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@RequestBody AlumnoVO alumnoVO){
		return (new ResponseEntity<GenericDTO>(this.getAlumnoService().altaVO(alumnoVO), HttpStatus.OK));
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("id") Long id,@RequestBody AlumnoVO alumnoVO){
		return new ResponseEntity<>(this.getAlumnoService().modificarVO(id, alumnoVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("id") Long id){
		return (new ResponseEntity<>(this.getAlumnoService().borrarVO(id),HttpStatus.OK)); 
	}
	
	
	public AlumnoService getAlumnoService() {
		return alumnoService;
	}
	public void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}
}
