package org.alumno.alex.alex_proyecto_motogp.mvc;

import java.util.Locale;

import org.alumno.alex.alex_proyecto_motogp.model.ram.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
	
	@RequestMapping(value= {"/","login"},method=RequestMethod.GET)
	public String mostrarLogin(ModelMap model, HttpServletRequest request, Locale locale, HttpServletResponse response) {
		//System.out.println("Accept-language: " + request.getHeader("Accept-Language"));
		//System.out.println(String.format("Peticion de recibida, Lenguaje: %s, País: %s %n", locale.getLanguage(), locale.getDisplayCountry()));
		//i18Service.configuraIdiomaPeticion(request, response, locale);
		//Pagina pagina = new Pagina("Login","login", i18Service.getIdioma());
		//model.addAttribute("pagina",pagina);
		model.addAttribute("usuario", new Usuario());
		//model.addAttribute("loginNickname","Desconocido");
		return "login";
	}
}
