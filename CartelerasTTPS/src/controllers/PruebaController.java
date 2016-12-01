package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class PruebaController {
	
	@RequestMapping(value="/prueba", method=RequestMethod.GET)
	public String prueba(ModelMap model){
		model.addAttribute("mensaje", "Hello Spring MVC");
		return "Hola prueba controlador spring";
	}

}
