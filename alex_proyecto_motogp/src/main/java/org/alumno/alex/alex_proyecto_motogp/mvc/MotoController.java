package org.alumno.alex.alex_proyecto_motogp.mvc;

import java.util.ArrayList;

import org.alumno.alex.alex_proyecto_motogp.model.ram.Moto;
import org.alumno.alex.alex_proyecto_motogp.srv.FileService;
import org.alumno.alex.alex_proyecto_motogp.srv.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

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
	
	@GetMapping("add-moto")
	public String addMoto(ModelMap model) {
		model.addAttribute("moto", new Moto(motoService.saberUltimoIdMoto()+1));
		return "add-moto";
	}
	
	@PostMapping("add-moto")
		public String addMoto_post(ModelMap model,@Valid Moto moto,
				BindingResult validacion) {
		if(validacion.hasErrors()) {
			return "update-imagenPiloto";
		}
		
		int id = moto.getId();
		System.out.println(moto.getId());
		MultipartFile fichero = moto.getImagenMoto();
		//String usuarioActivo =  (String) model.getAttribute("nickname");
		try {
//			if(usuarioActivo == null || usuarioActivo == "") {
//				throw new Exception("Debes estar logueado para poder borrar");
//			}
			
			ArrayList<String> listaErroresAlGuardar = fileService.guardaImagenMoto(fichero, id+"");
			if(!listaErroresAlGuardar.isEmpty()) {
				String mensajeCompleto = "";
				for(String mensaje : listaErroresAlGuardar) {
					mensajeCompleto+=mensaje + "<br>";
				}
				throw new Exception(mensajeCompleto);
			}
			
			//String quienModifica = model.getAttribute("nickname").toString();
			//Moto moto1 = motoService.encontrarMotoPorId(id+"");
			moto.setNombreFicheroImagen(fileService.getNombreImagenUsuario(fichero, id+""));
			model.clear();
			motoService.addMoto(moto);
			//model.addAttribute(servicioCorredores.encontrarPilotoLicencia(numLicencia));
			//model.addAttribute("imagenPiloto", new ImagenPiloto(id));
			return "redirect:list-motos";

		}catch(Exception e) {
			model.addAttribute(motoService.encontrarMotoPorId(id+""));
			//model.addAttribute("imagenPiloto", new ImagenPiloto(numLicencia));
			
			
			model.addAttribute("errores", e.getMessage());
			return "update-imagenPiloto";
		}
		}
	
	@GetMapping("del-moto")
	public String delMoto(@RequestParam("idMoto") String idMoto) {
		motoService.delMoto(Integer.parseInt(idMoto));
		return "redirect:list-motos";
	}
	}
