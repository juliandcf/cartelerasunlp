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

import dto.CarteleraDTO;
import modelo.Cartelera;
import modelo.Multimedia;
import serviciosInt.CarteleraService;

@RestController
@RequestMapping("/cartelera")
public class CarteleraRestController {
	
	@Autowired
	private CarteleraService carteleraService;
	
	@RequestMapping(method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<List<CarteleraDTO>> recuperarTodos(){
		List<CarteleraDTO> cartelerasDTO = new ArrayList<CarteleraDTO>();
		List<Cartelera> carteleras = carteleraService.recuperarTodos();
		carteleras.forEach((c)->cartelerasDTO.add(new CarteleraDTO(c)));
		if(cartelerasDTO.isEmpty()){
			return new ResponseEntity<List<CarteleraDTO>>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<List<CarteleraDTO>>(cartelerasDTO,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<CarteleraDTO> recuperar(@PathVariable("id") Long id){
		ResponseEntity<CarteleraDTO> response = new ResponseEntity<CarteleraDTO>(HttpStatus.NOT_FOUND);
		Cartelera carteleraRecuperar = carteleraService.recuperar(id);
		if (carteleraRecuperar!= null){
			response = new ResponseEntity<CarteleraDTO>(new CarteleraDTO(carteleraRecuperar),HttpStatus.OK);
		}
		return response;
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> alta(@RequestBody CarteleraDTO carteleraDTO){
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		Cartelera cartelera = carteleraDTO.toEntidad();
		Cartelera carteleraAgregada = carteleraService.alta(cartelera);
		if (carteleraAgregada != null){
			response = new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return response;	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<CarteleraDTO> pruebaJsonReciboJson(@PathVariable("id") Long id, @RequestBody CarteleraDTO carteleraDTO){
		Cartelera carteleraActualizar = carteleraService.recuperar(id);
		if (carteleraActualizar == null){
			return new ResponseEntity<CarteleraDTO>(HttpStatus.NOT_FOUND);
		}
		if(carteleraService.existe(carteleraDTO.toEntidad())){
			return new ResponseEntity<CarteleraDTO>(HttpStatus.CONFLICT);
		}
		Cartelera carteleraModificada = carteleraDTO.copiarAtributos(carteleraActualizar);
		carteleraService.modificar(carteleraModificada);
		return new ResponseEntity<CarteleraDTO>(new CarteleraDTO(carteleraModificada),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<CarteleraDTO> borrar(@PathVariable("id") Long id){
		ResponseEntity<CarteleraDTO> response = new ResponseEntity<CarteleraDTO>(HttpStatus.NOT_FOUND);
		Cartelera carteleraBorrar = carteleraService.recuperar(id);
		if (carteleraBorrar != null){
			if (carteleraService.baja(carteleraBorrar))
				response = new ResponseEntity<CarteleraDTO>(HttpStatus.OK);
		}
		return response;
	}
}
