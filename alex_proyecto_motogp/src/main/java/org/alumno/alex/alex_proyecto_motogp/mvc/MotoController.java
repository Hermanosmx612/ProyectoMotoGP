package org.alumno.alex.alex_proyecto_motogp.mvc;

import java.util.ArrayList;
import java.util.List;

import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorList;
import org.alumno.alex.alex_proyecto_motogp.model.dto.PilotoMoto;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Corredor;
import org.alumno.alex.alex_proyecto_motogp.model.ram.FiltroMoto;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Moto;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Pagina;
import org.alumno.alex.alex_proyecto_motogp.srv.CorredorService;
import org.alumno.alex.alex_proyecto_motogp.srv.FileService;
import org.alumno.alex.alex_proyecto_motogp.srv.I18nService;
import org.alumno.alex.alex_proyecto_motogp.srv.MotoService;
import org.alumno.alex.alex_proyecto_motogp.srv.mapper.MotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@Autowired
	I18nService i18nService;
	@Autowired
	CorredorService servicioCorredor;
	
	Pagina pagina = new Pagina("Motos", "list-motos");
	@GetMapping("list-motos")
	public String listarMotos(ModelMap model, @RequestParam(name = "orden", required = false) String orden) {
		if(orden == null) {
			model.addAttribute("listaMotos", motoService.getMotos());
		}else 
		//{
		//pagina.setIdioma(i18nService.getIdioma());
		model.addAttribute("listaMotos", motoService.ordenarPorCriterio(orden));
		model.addAttribute("pagina", new Pagina("Motos", "list-motos", i18nService.getIdioma()));

		
		model.addAttribute("filtroMoto", new FiltroMoto());
		return "list-motos";
	}
	
	@ModelAttribute("campoLista")
	public Object[] listarCampos() {
		return motoService.listarCampos().toArray();
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
		model.addAttribute("pagina", pagina);
		model.addAttribute("moto", new Moto(motoService.saberUltimoIdMoto()+1));
		return "add-moto";
	}
	
	@PostMapping("add-moto")
		public String addMoto_post(ModelMap model,@Valid Moto moto,
				BindingResult validacion) {
		if(validacion.hasErrors()) {
			return "add-moto";
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
			return "add-moto";
		}
		}
	
	@GetMapping("del-moto")
	public String delMoto(@RequestParam("idMoto") String idMoto) {
		motoService.delMoto(Integer.parseInt(idMoto));
		return "redirect:list-motos";
	}
	
	@PostMapping("filtrar-moto")
	public String filtrarMoto(ModelMap model, FiltroMoto filtroMoto) {
		model.put("listaMotos", motoService.listaMotos(filtroMoto.getCampo(), filtroMoto.getValor()));
		model.addAttribute("filtroPiloto", filtroMoto);
		return "list-motos";
	}
	
	
	@GetMapping("info-moto")
	public String infoMotosPilotos(ModelMap model, @RequestParam(name = "idMoto", required = false) String idMoto) {
		List<CorredorList> actuales = servicioCorredor.encontrarMotosActuales(idMoto);
		List<CorredorList> noActuales = servicioCorredor.encontrarMotosNoActuales(idMoto);
		model.addAttribute("pilotoMoto", new PilotoMoto(Integer.parseInt(idMoto)));
		model.addAttribute("moto",MotoMapper.INSTANCE.motoToMotoInfo(motoService.encontrarMotoPorId(idMoto)));
		model.addAttribute("pilotosActuales", actuales);
		model.addAttribute("pilotosNoActuales", motoService.listarPilotosNoActuales(noActuales));

		return "listMotoPilotos";
	}
	
	
	@GetMapping("del-pilotoMoto")
	public String borrarPiloto(ModelMap model, @RequestParam("idMoto") String idMoto, @RequestParam("numLicencia") String numLicencia) {
		System.out.println(idMoto);
		System.out.println(numLicencia);
		servicioCorredor.deletePilotTeam(idMoto, numLicencia);
		return "redirect:info-moto?idMoto="+idMoto;
	}
	
	@PostMapping("addPilto-moto")
	public String addPilotoMoto(ModelMap model, PilotoMoto p) {
		ArrayList<Integer> matriculadoEn = new ArrayList<Integer>();
		Corredor p2 = servicioCorredor.encontrarPilotoLicencia(p.getNumLicencia()+"");
		matriculadoEn = p2.getMotoCorre();
		matriculadoEn.add(p.getIdMoto());
		p2.setMotoCorre(matriculadoEn);
		return "redirect:info-moto?idMoto="+p.getIdMoto();
	}
	}
