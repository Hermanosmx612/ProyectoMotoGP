package org.alumno.alex.alex_proyecto_motogp.mvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorEdit;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Corredor;
import org.alumno.alex.alex_proyecto_motogp.model.ram.FiltroPiloto;
import org.alumno.alex.alex_proyecto_motogp.model.ram.ImagenPiloto;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Moto;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Pagina;
import org.alumno.alex.alex_proyecto_motogp.srv.CorredorService;
import org.alumno.alex.alex_proyecto_motogp.srv.FileService;
import org.alumno.alex.alex_proyecto_motogp.srv.I18nService;
import org.alumno.alex.alex_proyecto_motogp.srv.MotoService;
import org.alumno.alex.alex_proyecto_motogp.srv.excepciones.PilotoDuplicadoException;
import org.alumno.alex.alex_proyecto_motogp.srv.mapper.PilotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;


@Controller
@SessionAttributes({ "nickname"})
public class CorredorController {
	@Autowired
	CorredorService servicioCorredores;
	@Autowired
	FileService fileService;
	@Autowired
	I18nService i18nService;
	@Autowired
	MotoService servicioMotos;
	
	Pagina pagina = new Pagina("Pilotos", "list-corredores");
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping("list-corredores")
	
	public String listarCorredores(ModelMap model,  @RequestParam(name = "orden", required = false) String orden) {
		if(orden == null) {
			model.put("corredoresList", PilotoMapper.INSTANCE.corredorToCorredorList(servicioCorredores.getCorredores()));
		}else {
			model.put("corredoresList", servicioCorredores.ordenarPorCriterio(orden));
		}
		pagina.setIdioma(i18nService.getIdioma());
		model.addAttribute("pagina", pagina);
		
		model.addAttribute("filtroPiloto", new FiltroPiloto());
		return "lista-corredores";
	}
	
	@ModelAttribute("campoLista")
	public Object[] listarCampos() {
		return servicioCorredores.listarCampos().toArray();
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
	
	@GetMapping("/update-imagenPiloto")
	public String updateImagenUsuario(ModelMap model, @RequestParam String numLicencia) {
		//System.out.println("El geng");
		model.addAttribute("imagenPiloto", new ImagenPiloto(numLicencia));
		model.addAttribute("pagina", pagina);
		return "update-imagenPiloto";
	}
	
	@PostMapping("/guardar-imagen-piloto")
	public String guardarImagenUsuario(ModelMap model, @Valid ImagenPiloto imagenUsuario,
			BindingResult validacion) {
		if(validacion.hasErrors()) {
			return "update-imagenPiloto";
		}
		
		String numLicencia = imagenUsuario.getNumLicencia();
		MultipartFile fichero = imagenUsuario.getImagen();
		String usuarioActivo =  (String) model.getAttribute("nickname");
		try {
//			if(usuarioActivo == null || usuarioActivo == "") {
//				throw new Exception("Debes estar logueado para poder borrar");
//			}
			
			ArrayList<String> listaErroresAlGuardar = fileService.guardaImagenPiloto(fichero, numLicencia);
			if(!listaErroresAlGuardar.isEmpty()) {
				String mensajeCompleto = "";
				for(String mensaje : listaErroresAlGuardar) {
					mensajeCompleto+=mensaje + "<br>";
				}
				throw new Exception(mensajeCompleto);
			}
			
			//String quienModifica = model.getAttribute("nickname").toString();
			Corredor piloto = servicioCorredores.encontrarPilotoLicencia(numLicencia);
			piloto.setNombreFicheroConImagen(fileService.getNombreImagenUsuario(fichero, numLicencia));
			model.clear();
			model.addAttribute(servicioCorredores.encontrarPilotoLicencia(numLicencia));
			model.addAttribute("imagenPiloto", new ImagenPiloto(numLicencia));
			model.addAttribute("pagina", pagina);
			return "update-imagenPiloto";

		}catch(Exception e) {
			model.addAttribute(servicioCorredores.encontrarPilotoLicencia(numLicencia));
			model.addAttribute("imagenPiloto", new ImagenPiloto(numLicencia));
			
			
			model.addAttribute("errores", e.getMessage());
			return "update-imagenPiloto";
		}
		
		
	}
	
	
	@GetMapping("add-piloto")
	public String addPiloto(ModelMap model) {
		model.addAttribute("corredorEdit", new CorredorEdit());
		model.addAttribute("pagina", pagina);
		return "add-piloto";
	}
	
	@ModelAttribute("motoLista")
	public List<Moto> getMotos() {
		return servicioMotos.getMotos();
	}
	
	@ModelAttribute("listaFisico")
	public List<String> getEstadoFisico() {
//		List<String> i18nlista = i18nService.getTraduccion(servicioAlumno.listGeneros());
//		return i18nlista;
		
		List<String> listaFisico = servicioCorredores.listFisico();
		return listaFisico;
	}
	
	@PostMapping("add-piloto")
	public String addPilot(ModelMap model, @Valid CorredorEdit pilotoEdit, BindingResult validaciones) {
		System.out.println("Pepe");
		if(validaciones.hasErrors()) {
			//model.addAttribute("corredorEdit", new CorredorEdit());
			return "add-piloto";
		}
		try {
			if(servicioCorredores.comprobarSiExiste(pilotoEdit.getNumLicencia())) {
				Corredor existente = servicioCorredores.encontrarPilotoLicencia(pilotoEdit.getNumLicencia() + "");
				throw new PilotoDuplicadoException(existente, pilotoEdit);
			}else {
				servicioCorredores.addPiloto(pilotoEdit);
				return "redirect:list-corredores";
			}
		}catch(PilotoDuplicadoException e) {
			System.out.println("Error: " + e.toString());
			model.addAttribute("error", e.toString());
			return "add-piloto";
		}
		
		
	
	}
	
	
	@GetMapping("del-piloto")
	public String delPiloto(@RequestParam("piloto") String licencia) {
		servicioCorredores.delPiloto(licencia);
		return "redirect:list-corredores";
	}
	
	@GetMapping("update-piloto")
	public String updatePiloto(@RequestParam("piloto") String numLicencia,ModelMap model) {
		Corredor piloto = servicioCorredores.encontrarPilotoLicencia(numLicencia);
		System.out.println("Id motos: " + piloto.getMotoCorre());
		model.addAttribute("corredorEdit", PilotoMapper.INSTANCE.corredorToCorredorEdit(piloto));
		model.addAttribute("pagina", pagina);
		return "update-piloto";
	}
	
	@PostMapping("update-piloto")
	public String updatePilotoPost(@Valid CorredorEdit piloto,BindingResult validacion,ModelMap model) {
//		if(validacion.hasErrors()) {
//			return "update-piloto";
//		}
//		Corredor pilotoExistente = servicioCorredores.encontrarPilotoLicencia(piloto.getNumLicencia()+"");
//		String nombreArchivoExistente = pilotoExistente.getNombreFicheroConImagen();
//		servicioCorredores.delPiloto(pilotoExistente.getNumLicencia()+"");
//		piloto.setNombreFicheroConImagen(nombreArchivoExistente);
//		//servicioCorredores.addPiloto(PilotoMapper.INSTANCE.corredorToCorredorEdit(piloto));
//		model.addAttribute("piloto", piloto);
//		return "redirect:list-corredores";
		
		model.addAttribute("piloto", piloto);
		String nombreUsuario = (String) model.getAttribute("nickname");

		if (validacion.hasErrors()) {
			return "update-piloto";
		} else {
			try {
				servicioCorredores.modificarPiloto(piloto, nombreUsuario);
			} catch (Exception e) {
				model.put("error2", e.getMessage());
				Corredor a = servicioCorredores.encontrarPilotoLicencia(piloto.getNumLicencia()+"");
				PilotoMapper.INSTANCE.corredorToCorredorEdit(a);
				model.addAttribute("corredorEdit", a);
				return "update-piloto";
			}
			return "redirect:list-corredores";
		}
	}
	
	@PostMapping("filtrar-piloto")
	public String filtrarPiloto(ModelMap model, FiltroPiloto filtroPiloto) {
		model.put("corredoresList", servicioCorredores.listaPilotos(filtroPiloto.getCampo(), filtroPiloto.getValor()));
		model.addAttribute("filtroPiloto", filtroPiloto);
		return "lista-corredores";
	}
}
