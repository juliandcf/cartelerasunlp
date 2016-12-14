package restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serviciosInt.CarteleraService;
import serviciosInt.PublicacionService;

@RestController
@RequestMapping("/publicacion")
public class PublicacionRestController {
	
	@Autowired
	private CarteleraService carteleraService;
	
	@Autowired
	private PublicacionService publicacionService;

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
	
	

}
