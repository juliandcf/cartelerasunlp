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
import dto.UsuarioVO;
import modelo.Administrador;
import serviciosInt.AdministradorService;


@RestController
@RequestMapping("/usuario/administrador")
public class AdministradorRestController {
	
	@Autowired
	private AdministradorService administradorService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperarTodos(){
		return new ResponseEntity<>(this.getAdministradorService().recuperarTodosVO(), HttpStatus.OK);
		
//		ResponseEntity<List<AdministradorVO>> response = new ResponseEntity<List<AdministradorVO>>(HttpStatus.NO_CONTENT);
//		List<AdministradorVO> administradoresDTO = new ArrayList<AdministradorVO>();
//		List<Administrador> administradores =this.getAdministradorService().recuperarTodos();
//		if(!administradores.isEmpty()){
//			for (Administrador a : administradores) {
//				administradoresDTO.add(new AdministradorVO(a));
//			}
//			response =  new ResponseEntity<List<AdministradorVO>>(administradoresDTO,HttpStatus.OK);
//		}
//		return response;
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<GenericDTO> recuperar(@PathVariable("id") Long id){
		return new ResponseEntity<>(this.getAdministradorService().recuperarVO(id), HttpStatus.OK);
		
//		ResponseEntity<AdministradorVO> response = new ResponseEntity<AdministradorVO>(HttpStatus.NOT_FOUND);
//		Administrador administradorRecuperar = this.getAdministradorService().recuperar(id);
//		if (administradorRecuperar != null){
//			AdministradorVO administradorDTO = new AdministradorVO(administradorRecuperar);
//			
//			response = new ResponseEntity<AdministradorVO>(administradorDTO,HttpStatus.OK);
//		}
//		return response;
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> alta(@RequestBody AdministradorVO administradorVO){
		return (new ResponseEntity<GenericDTO>(this.getAdministradorService().altaVO(administradorVO), HttpStatus.OK));
	}

	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<GenericDTO> actualizar(@PathVariable("id") Long id,@RequestBody AdministradorVO administradorVO){
		return new ResponseEntity<>(this.getAdministradorService().modificarVO(id, administradorVO),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<GenericDTO> borrar(@PathVariable("id") Long id){
		return (new ResponseEntity<>(this.getAdministradorService().borrarVO(id),HttpStatus.OK)); 
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> login(@RequestBody UsuarioVO usuarioVO){
		return (new ResponseEntity<>(this.getAdministradorService().login(usuarioVO),HttpStatus.OK));
	}
	
	
	public AdministradorService getAdministradorService() {
		return administradorService;
	}

	public void setAdministradorService(AdministradorService administradorService) {
		this.administradorService = administradorService;
	}
}
