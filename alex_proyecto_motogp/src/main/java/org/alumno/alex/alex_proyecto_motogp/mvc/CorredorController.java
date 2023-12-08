package org.alumno.alex.alex_proyecto_motogp.mvc;

import org.alumno.alex.alex_proyecto_motogp.model.ram.Corredor;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Usuario;
import org.alumno.alex.alex_proyecto_motogp.srv.CorredorService;
import org.alumno.alex.alex_proyecto_motogp.srv.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class CorredorController {
	@Autowired
	CorredorService servicioCorredores;
	@Autowired
	FileService fileService;
	
	@GetMapping("list-corredores")
	public String listarCorredores(ModelMap model) {
		model.put("corredoresList", servicioCorredores.getCorredores());
		return "lista-corredores";
	}
	
	@RequestMapping(value = "/imagenPilotos/{numLicencia}", method = RequestMethod.GET)
	public ResponseEntity<FileSystemResource> getFile(@PathVariable("numLicencia") String numLicencia) {

		try {
			Corredor pilotoEncontar = servicioCorredores.encontrarPilotoLicencia(numLicencia);
			String nombreFicheroConImagen = "Desconocido.jpg";// valor por defecto
			if (pilotoEncontar.getNombreFicheroConImagen() != "Desconocido.jpg") { // Si no es el valor por defecto
				// La imagen siempre se almacenará como 'nickName.extensionImagen',
				// con la posibilidad de que extensionImagen sea JPG, PNG o GIF.
				// Para saber el formato consultaremos 'Usuario' para conseguir el nombre del
				// fichero
				Corredor pilotoAMostrarFoto = servicioCorredores.encontrarPilotoLicencia(numLicencia);
				nombreFicheroConImagen = pilotoAMostrarFoto.getNombreFicheroConImagen();
			}
			// El servicio nos devolverá la imagen y nos abstrae de saber donde esta
			// guardada realmente

			FileSystemResource resource = fileService.getImagenUsuario(nombreFicheroConImagen);
			if (!resource.exists()) {
				throw new Exception("La imagen no existe");
			}
			ResponseEntity<FileSystemResource> responseEntity = new ResponseEntity<>(resource, HttpStatus.OK);
			return responseEntity;
		} catch (Exception e) {// Ante cualquier error
			// Devolver error 404-recurso no encontrado
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
