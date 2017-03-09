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
	public ResponseEntity<List<AdministradorVO>> recuperarTodos(){
		ResponseEntity<List<AdministradorVO>> response = new ResponseEntity<List<AdministradorVO>>(HttpStatus.NO_CONTENT);
		List<AdministradorVO> administradoresDTO = new ArrayList<AdministradorVO>();
		List<Administrador> administradores =this.getAdministradorService().recuperarTodos();
		if(!administradores.isEmpty()){
			for (Administrador a : administradores) {
				administradoresDTO.add(new AdministradorVO(a));
			}
			response =  new ResponseEntity<List<AdministradorVO>>(administradoresDTO,HttpStatus.OK);
		}
		return response;
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<AdministradorVO> recuperar(@PathVariable("id") Long id){
		ResponseEntity<AdministradorVO> response = new ResponseEntity<AdministradorVO>(HttpStatus.NOT_FOUND);
		Administrador administradorRecuperar = this.getAdministradorService().recuperar(id);
		if (administradorRecuperar != null){
			AdministradorVO administradorDTO = new AdministradorVO(administradorRecuperar);
			
			response = new ResponseEntity<AdministradorVO>(administradorDTO,HttpStatus.OK);
		}
		return response;
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> alta(@RequestBody AdministradorVO administradorDTO){
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		Administrador administrador = administradorDTO.toEntidad();
		Administrador adminCreado = getAdministradorService().alta(administrador);
		if(adminCreado != null){
			response = new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return response;
	}

	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<AdministradorVO> actualizar(@PathVariable("id") Long id,@RequestBody AdministradorVO administradorDTO){
		Administrador adminRecuperar = getAdministradorService().recuperar(id);
		if (adminRecuperar == null){
			return new ResponseEntity<AdministradorVO>(HttpStatus.NOT_FOUND);
		}
		Administrador adminRecibidoDTO = administradorDTO.toEntidad(); 
		if(!adminRecuperar.equals(adminRecibidoDTO)){
			if(getAdministradorService().existe(adminRecibidoDTO))
				return new ResponseEntity<AdministradorVO>(HttpStatus.CONFLICT);
		}
		Administrador adminModificado = administradorDTO.copiarAtributos(adminRecuperar);
		this.getAdministradorService().modificar(adminModificado);
		return new ResponseEntity<AdministradorVO>(new AdministradorVO(adminModificado),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<AdministradorVO> borrar(@PathVariable("id") Long id){
		ResponseEntity<AdministradorVO> response = new ResponseEntity<AdministradorVO>(HttpStatus.NOT_FOUND);
		Administrador adminBorrar = this.getAdministradorService().recuperar(id);
		if (adminBorrar != null){
			if (this.getAdministradorService().baja(id))
				response = new ResponseEntity<AdministradorVO>(HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<GenericDTO> login(@RequestBody UsuarioVO usuarioVO){
		return (new ResponseEntity<>(this.getAdministradorService().login(usuarioVO) ,HttpStatus.OK));
	}
	
	
	public AdministradorService getAdministradorService() {
		return administradorService;
	}

	public void setAdministradorService(AdministradorService administradorService) {
		this.administradorService = administradorService;
	}
}
