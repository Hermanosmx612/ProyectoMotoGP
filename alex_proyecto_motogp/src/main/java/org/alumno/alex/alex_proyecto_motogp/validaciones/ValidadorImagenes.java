package org.alumno.alex.alex_proyecto_motogp.validaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import org.alumno.alexGarcia.alex_primer_app_spring_boot.srv.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidadorImagenes implements ConstraintValidator<ImagenValida, MultipartFile>{
	@Autowired
	//I18nService i18nService;
	
	
	public static final List<String> tiposDeImagenes = Arrays.asList("image/png", "image/jpg", "image/jpeg",
			"image/gif");
	public static final long MAX_BYTES = 524288;

//public void intialize(ImagenValida constraitAnnotation) {
//	
//}
	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		// Por defecto resultado de la comprobación válido hasta que encontremos un
		// error
		boolean result = true;
		// comprobar lista de errores
		
		ArrayList<String> listaErrores = (ArrayList<String>) mensajesErrorImagen(multipartFile); // i18nService.getTraduccion(mensajesErrorImagen(multipartFile));
		// Si hay errores añadirlos al contexto
		if (!listaErrores.isEmpty()) {
			context.disableDefaultConstraintViolation();
			// iteramos por la lista de errores para añadirlos al contexto
			for (String textoError : listaErrores) {
				context.buildConstraintViolationWithTemplate(textoError).addConstraintViolation();
			}
			// Comprobación incorrecta (resultado no valido)
			result = false;
		}
		// Devolvemos resultado de la comprobación
		return result;
	}

	

	// comprueba si el tipo de imagen es uno de los predefinidos en "tipos de
	// imagen"
	private static boolean tipoDeImagenValido(String contenType) {
		return tiposDeImagenes.contains(contenType);

	}

	public static boolean imagenValida(MultipartFile fichero) {
		ArrayList<String> Listaerrores = mensajesErrorImagen(fichero);
		return Listaerrores.isEmpty() ? true : false;

	}

	public static ArrayList<String> mensajesErrorImagen(MultipartFile fichero) {
		//VALIDAR SI ESTA VACIO
		ArrayList<String> errores = new ArrayList<String>();
		if (fichero.isEmpty()) {
			errores.add("imagenValida.vacia");
		}
		
		//VALIDAR FICHERO
		String contentType = fichero.getContentType();
		if (!tipoDeImagenValido(contentType)) {
			errores.add("imagenValida.tipoIncorrecto");
		}
		//VALIDAAR EL TAMAÑO MAXIMO
		if (fichero.getSize()>MAX_BYTES) {
			errores.add("Tamaño incorrecto, has superado el limite#"+MAX_BYTES);
		}
		return errores;
	}

	public static String getExtension(MultipartFile fichero) {
		String contentType = fichero.getContentType();
		if (tiposDeImagenes.contains(contentType)) {
			System.out.println(contentType);
			String[] parts = contentType.split("\\/");
			return parts[1];

		}
		return "";
	
	}
}
