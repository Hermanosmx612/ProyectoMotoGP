package org.alumno.alex.alex_proyecto_motogp.mvc;

import java.util.Locale;

import org.alumno.alex.alex_proyecto_motogp.model.ram.Pagina;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Usuario;
import org.alumno.alex.alex_proyecto_motogp.srv.I18nService;
import org.alumno.alex.alex_proyecto_motogp.srv.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@SessionAttributes({ "nickname"})
public class LoginController {
	@Autowired
	LoginService servicioLogin;
	@Autowired
	I18nService i18nService;
	
	//Pagina pagina = new Pagina("Login", "login");

	@RequestMapping(value= {"/","login"},method=RequestMethod.GET)
	public String mostrarLogin(ModelMap model, HttpServletRequest request, Locale locale, HttpServletResponse response) {
		//System.out.println("Accept-language: " + request.getHeader("Accept-Language"));
		//System.out.println(String.format("Peticion de recibida, Lenguaje: %s, País: %s %n", locale.getLanguage(), locale.getDisplayCountry()));
		i18nService.configuraIdiomaPeticion(request, response, locale);
		Pagina pagina = new Pagina("Login","login", i18nService.getIdioma());
		//model.addAttribute("pagina",pagina);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("pagina", pagina);
		//model.addAttribute("loginNickname","Desconocido");
		return "login";
	}
	
	@PostMapping("login")
	public String loginPost(Usuario usuario, BindingResult bindingResult, ModelMap model) {
		System.out.println("Pepa");
		if (bindingResult.hasErrors()) {
			System.out.println("Pepe");
			System.out.println(bindingResult);
            return "login";
        }
		if(servicioLogin.usuarioValido(usuario)) {
			model.put("nickname", usuario.getNickname());
			Usuario user = servicioLogin.encontrarUsuarioPorNickname(usuario.getNickname());
			model.put("loginName", user.getNombre());
			model.put("loginNickname", usuario.getNickname());
			model.put("usuario", usuario);
			return "redirect:list-corredores";
		}else {
			System.out.println("Pepe");
			model.put("errores", "El usuario introducido no existe o contraseña incorrecta");
			//servicioErrores.anadirLogError(new LogError(servicioErrores.saberUltimoId() + 1,"Login incorrecto" ,"Login incorrecto de: "+usuario.getNickname()));
			return "login";
		}
	}
}
