package org.alumno.alex.alex_proyecto_motogp.mvc;

import org.alumno.alex.alex_proyecto_motogp.model.ram.Corredor;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Moto;
import org.alumno.alex.alex_proyecto_motogp.srv.FileService;
import org.alumno.alex.alex_proyecto_motogp.srv.MotoService;
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
public class MotoController {
	@Autowired
	MotoService motoService;
	@Autowired
	FileService fileService;
	@GetMapping("list-motos")
	public String listarMotos(ModelMap model) {
		model.addAttribute("listaMotos", motoService.getMotos());
		return "list-motos";
	}
	
	
	
	@RequestMapping(value = "/imagenMotos/{id}", method = RequestMethod.GET)
	public ResponseEntity<FileSystemResource> getFile(@PathVariable("id") String id) {

		try {
			Moto motoEncontrar = motoService.encontrarMotoPorId(id);
			String nombreFicheroConImagen = "Desconocido.jpg";// valor por defecto
			if (motoEncontrar.getNombreFicheroImagen() != "Desconocido.jpg") { // Si no es el valor por defecto
				// La imagen siempre se almacenará como 'nickName.extensionImagen',
				// con la posibilidad de que extensionImagen sea JPG, PNG o GIF.
				// Para saber el formato consultaremos 'Usuario' para conseguir el nombre del
				// fichero
				Moto motoAMostrarFoto = motoService.encontrarMotoPorId(id);
				nombreFicheroConImagen = motoAMostrarFoto.getNombreFicheroImagen();
				System.out.println(motoAMostrarFoto.getNombreFicheroImagen());
			}
			// El servicio nos devolverá la imagen y nos abstrae de saber donde esta
			// guardada realmente

			FileSystemResource resource = fileService.getImagenMoto(nombreFicheroConImagen);
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
