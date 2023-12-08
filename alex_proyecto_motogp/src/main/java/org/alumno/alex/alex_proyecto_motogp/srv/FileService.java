package org.alumno.alex.alex_proyecto_motogp.srv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.alumno.alex.alex_proyecto_motogp.validaciones.ValidadorImagenes;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
		private static final String USER_HOME_TOMCAT = System.getProperty("user.home");
		private static final String SEPARATOR = File.separator;
		private static final String CARPETA_FICHEROS_DINAMICOS_WEBAPP = USER_HOME_TOMCAT + SEPARATOR
				+ "PilotosWebApp_DynamicFiles" + SEPARATOR;
		private static final String CARPETA_IMAGENES_USUARIOS = CARPETA_FICHEROS_DINAMICOS_WEBAPP + "ImagenPilotos";
		private static final String CARPETA_IMAGENES_MOTOS = CARPETA_FICHEROS_DINAMICOS_WEBAPP + "ImagenMotos";

		private static final String CARPETA_DOCUMENTACION_ALUMNOS = CARPETA_FICHEROS_DINAMICOS_WEBAPP
				+ "DocumentacionAlumnos";

		public FileSystemResource getImagenUsuario(String fichero) {
			return new FileSystemResource(new File(CARPETA_IMAGENES_USUARIOS, fichero));
		}
		public FileSystemResource getImagenMoto(String fichero) {
			return new FileSystemResource(new File(CARPETA_IMAGENES_MOTOS, fichero));
		}
		

		private String guardarFichero(String ruta, MultipartFile fichero) {
			try {
				// Obtener datos del fichero
				byte[] fileBytes = fichero.getBytes();
				// Obtener ruta
				Path path = Paths.get(ruta);
				// Guardar fichero
				// El fichero no tiene porque existir en la ruta, pero
				// si la carpeta destino no existe se producirá una excepción
				Files.write(path, fileBytes);
			} catch (NoSuchFileException e) {
				return "No existe la carpeta para poder guardar '" + e.getMessage() + "'";
			} catch (IOException e) {
				// Si hay error devolver mensaje de error
				return e.getMessage();
			}
			// Si no hay error devolver null
			return null;
		}

		/** Guardar imagenes de los usuarios **/
		// Guardar fichero en la carpeta de las imagenes de usuario con un nombre
		// determinado
		// Si falla devuelve una lista con los errores detectados
		public ArrayList<String> guardaImagenPiloto(MultipartFile fichero, String nickName) {
			String nombreFichero = getNombreImagenUsuario(fichero, nickName);
			// Comprobaciones de errores
			if (!ValidadorImagenes.imagenValida(fichero)) {
				return ValidadorImagenes.mensajesErrorImagen(fichero);
			}
			// Guardar fichero
			String errorAlGuardar = guardarFichero(CARPETA_IMAGENES_USUARIOS + SEPARATOR + nombreFichero, fichero);
			if (errorAlGuardar == null)
				return new ArrayList<String>();
			else
				return new ArrayList<String>(List.of(errorAlGuardar));

		}

		// Consulta el tipo de extensión del fichero y devuelve un String "nickname.ext"
		public String getNombreImagenUsuario(MultipartFile fichero, String nickName) {
			String extension = ValidadorImagenes.getExtension(fichero);
			String nombreFichero = nickName + "." + extension;
			return nombreFichero;
		}

		public String getExtensionMultipartfile(MultipartFile fichero) {
			System.out.println(fichero.getOriginalFilename());
			int ultimo = fichero.getOriginalFilename().lastIndexOf(".");
			//System.out.println(fichero.getOriginalFilename().substring(ultimo, fichero.getOriginalFilename().length()));
			return fichero.getOriginalFilename().substring(ultimo + 1, fichero.getOriginalFilename().length());
		}
		
//		public ArrayList<String> guardaDocumentacionAlumno(MultipartFile fichero, String nickName) {
//			String nombreFichero = getNombreImagenUsuario(fichero, nickName);
//			// Comprobaciones de errores
//			if (!ValidadorDocumentoAlumno.documentoValido(fichero)) {
//				return ValidadorDocumentoAlumno.mensajesErrorDocumento(fichero);
//			}
//			// Guardar fichero
//			//System.out.println("Ruta fichero: "+CARPETA_DOCUMENTACION_ALUMNOS + SEPARATOR + nickName);
//			String errorAlGuardar = guardarFichero(CARPETA_DOCUMENTACION_ALUMNOS + SEPARATOR + nickName, fichero);
//			if (errorAlGuardar == null)
//				return new ArrayList<String>();
//			else
//				return new ArrayList<String>(List.of(errorAlGuardar));
//
//		}
//
//		public FileSystemResource getDocumentoAlumno(String nombreFichero) {
//			Path filePath = Paths.get(CARPETA_DOCUMENTACION_ALUMNOS, nombreFichero);
//
//	        if (java.nio.file.Files.exists(filePath)) {
//	            return new FileSystemResource(filePath.toFile());
//	        } else {
//	            // Manejar el caso en el que el archivo no existe
//	            //throw new DocumentoNoEncontradoException("El documento no existe: " + nombreFichero);
//	        }
//	        return null;
//			
//		}
}
