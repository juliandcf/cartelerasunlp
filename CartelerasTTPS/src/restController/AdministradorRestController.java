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

import dto.AdministradorDTO;
import dto.CarteleraDTO;
import dto.AdministradorDTO;
import modelo.Administrador;
import modelo.Cartelera;
import serviciosInt.AdministradorService;


@RestController
@RequestMapping("/usuario/administrador")
public class AdministradorRestController {
	
	@Autowired
	private AdministradorService administradorService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<List<AdministradorDTO>> recuperarTodos(){
		ResponseEntity<List<AdministradorDTO>> response = new ResponseEntity<List<AdministradorDTO>>(HttpStatus.NO_CONTENT);
		List<AdministradorDTO> administradoresDTO = new ArrayList<AdministradorDTO>();
		List<Administrador> administradores =this.getAdministradorService().recuperarTodos();
		if(!administradores.isEmpty()){
			for (Administrador a : administradores) {
				administradoresDTO.add(new AdministradorDTO(a));
			}
			response =  new ResponseEntity<List<AdministradorDTO>>(administradoresDTO,HttpStatus.OK);
		}
		return response;
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<AdministradorDTO> recuperar(@PathVariable("id") Long id){
		ResponseEntity<AdministradorDTO> response = new ResponseEntity<AdministradorDTO>(HttpStatus.NOT_FOUND);
		Administrador administradorRecuperar = this.getAdministradorService().recuperar(id);
		if (administradorRecuperar != null){
			AdministradorDTO administradorDTO = new AdministradorDTO(administradorRecuperar);
			
			response = new ResponseEntity<AdministradorDTO>(administradorDTO,HttpStatus.OK);
		}
		return response;
	}	

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> alta(@RequestBody AdministradorDTO administradorDTO){
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		Administrador administrador = administradorDTO.toEntidad();
		Administrador adminCreado = getAdministradorService().alta(administrador);
		if(adminCreado != null){
			response = new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return response;
	}

	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<AdministradorDTO> actualizar(@PathVariable("id") Long id,@RequestBody AdministradorDTO administradorDTO){
		Administrador adminRecuperar = getAdministradorService().recuperar(id);
		if (adminRecuperar == null){
			return new ResponseEntity<AdministradorDTO>(HttpStatus.NOT_FOUND);
		}
		Administrador adminRecibidoDTO = administradorDTO.toEntidad(); 
		if(!adminRecuperar.equals(adminRecibidoDTO)){
			if(getAdministradorService().existe(adminRecibidoDTO))
				return new ResponseEntity<AdministradorDTO>(HttpStatus.CONFLICT);
		}
		Administrador adminModificado = administradorDTO.copiarAtributos(adminRecuperar);
		this.getAdministradorService().modificar(adminModificado);
		return new ResponseEntity<AdministradorDTO>(new AdministradorDTO(adminModificado),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<AdministradorDTO> borrar(@PathVariable("id") Long id){
		ResponseEntity<AdministradorDTO> response = new ResponseEntity<AdministradorDTO>(HttpStatus.NOT_FOUND);
		Administrador adminBorrar = this.getAdministradorService().recuperar(id);
		if (adminBorrar != null){
			if (this.getAdministradorService().baja(id))
				response = new ResponseEntity<AdministradorDTO>(HttpStatus.OK);
		}
		return response;
	}
	
	
	public AdministradorService getAdministradorService() {
		return administradorService;
	}

	public void setAdministradorService(AdministradorService administradorService) {
		this.administradorService = administradorService;
	}
}
