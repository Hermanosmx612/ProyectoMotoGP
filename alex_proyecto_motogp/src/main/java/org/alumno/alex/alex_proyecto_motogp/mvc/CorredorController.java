package org.alumno.alex.alex_proyecto_motogp.mvc;

import org.alumno.alex.alex_proyecto_motogp.srv.CorredorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CorredorController {
	@Autowired
	CorredorService servicioCorredores;
	
	@GetMapping("list-corredores")
	public String listarCorredores(ModelMap model) {
		model.put("corredoresList", servicioCorredores.getCorredores());
		return "lista-corredores";
	}
}
