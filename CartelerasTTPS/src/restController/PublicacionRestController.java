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
import dto.PublicacionDTO;
import modelo.Cartelera;
import modelo.Publicacion;
import modelo.UsuarioPublicador;
import serviciosInt.CarteleraService;
import serviciosInt.GenericService;
import serviciosInt.PublicacionService;
import serviciosInt.UsuarioPublicadorService;

@RestController
@RequestMapping("/cartelera")
public class PublicacionRestController {
	
	@Autowired
	private CarteleraService carteleraService;
		
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private UsuarioPublicadorService usuarioPublicadorService;
	
	@RequestMapping(value="/{idCartelera}/publicacion",  method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<List<PublicacionDTO>> recuperarTodos(@PathVariable("idCartelera") Long idCartelera){
		ResponseEntity<List<PublicacionDTO>> response = new ResponseEntity<List<PublicacionDTO>>(HttpStatus.NO_CONTENT);
		Cartelera cartelera = this.getCarteleraService().recuperar(idCartelera);
		List<Publicacion> publicaciones = this.getPublicacionService().getPublicaciones(idCartelera);
		List<PublicacionDTO> publicacionesDTO = new ArrayList<PublicacionDTO>();
		if(!publicaciones.isEmpty()){
			for (Publicacion p : publicaciones) {
				publicacionesDTO.add(new PublicacionDTO(p));
			}
			response =  new ResponseEntity<List<PublicacionDTO>>(publicacionesDTO,HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/{idCartelera}/publicacion", method=RequestMethod.POST)
	public ResponseEntity<Void> alta(@PathVariable("idCartelera") Long idCartelera, @RequestBody PublicacionDTO publicacionDTO){
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		Cartelera cartelera = this.getCarteleraService().recuperar(idCartelera);
		UsuarioPublicador publicador = (UsuarioPublicador) this.getUsuarioPublicadorService().recuperar(publicacionDTO.getIdPublicador());
		Publicacion publicacion = publicacionDTO.toEntidad(cartelera, publicador);
		Publicacion publicacionAgregada= this.getPublicacionService().alta(publicacion);
		if (publicacionAgregada != null){
			response = new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return response;	
	}
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}", method=RequestMethod.PUT, produces={"application/json"})
	public ResponseEntity<PublicacionDTO> actualizar(@PathVariable("idPublicacion") Long idPublicacion,
													@RequestBody PublicacionDTO publicacionDTO){
		Publicacion publicacionRecuperada = getPublicacionService().recuperar(idPublicacion);
		if (publicacionRecuperada == null)
			return new ResponseEntity<PublicacionDTO>(HttpStatus.NOT_FOUND);
		Publicacion publicacionModificada = publicacionDTO.copiarAtributos(publicacionRecuperada);
		this.getPublicacionService().modificar(publicacionModificada);
		return new ResponseEntity<PublicacionDTO>(new PublicacionDTO(publicacionModificada),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}", method=RequestMethod.DELETE, produces={"application/json"})
	public ResponseEntity<PublicacionDTO> borrar(@PathVariable("idPublicacion") Long idPublicacion){
		ResponseEntity<PublicacionDTO> response = new ResponseEntity<PublicacionDTO>(HttpStatus.NOT_FOUND);
		Publicacion publicacionBorrar = this.getPublicacionService().recuperar(idPublicacion);
		if (publicacionBorrar != null){
			if (this.getPublicacionService().baja(idPublicacion))
				response = new ResponseEntity<PublicacionDTO>(HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/{idCartelera}/publicacion/{idPublicacion}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<PublicacionDTO> recuperar(@PathVariable("idPublicacion") Long idPublicacion){
		ResponseEntity<PublicacionDTO> response = new ResponseEntity<PublicacionDTO>(HttpStatus.NOT_FOUND);
		Publicacion publicacionRecuperar = this.getPublicacionService().recuperar(idPublicacion);
		if (publicacionRecuperar!= null){
			PublicacionDTO publicacionDTO = new PublicacionDTO(publicacionRecuperar);
			publicacionDTO.agregarMultimedia(publicacionRecuperar);
			//publicacionDTO.agregarComentarios(publicacionRecuperar);
			
			response = new ResponseEntity<PublicacionDTO>(publicacionDTO,HttpStatus.OK);
		}
		return response;
	}	
	
	
	
	public CarteleraService getCarteleraService() {
		return carteleraService;
	}	
	
	public void setCarteleraService(CarteleraService carteleraService) {
		this.carteleraService = carteleraService;
	}

	public PublicacionService getPublicacionService() {
		return publicacionService;
	}

	public void setPublicacionService(PublicacionService publicacionService) {
		this.publicacionService = publicacionService;
	}


	public UsuarioPublicadorService getUsuarioPublicadorService() {
		return usuarioPublicadorService;
	}


	public void setUsuarioPublicadorService(UsuarioPublicadorService usuarioPublicadorService) {
		this.usuarioPublicadorService = usuarioPublicadorService;
	}
	
	
	
	

}
