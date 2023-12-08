package org.alumno.alex.alex_proyecto_motogp.model.ram;

import java.io.Serializable;

import org.alumno.alex.alex_proyecto_motogp.validaciones.ImagenValida;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ImagenPiloto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Size(min=5,message="El usuario debe tener un tamaño minimo de 5 caracteres")
	private String numLicencia;
	@NotNull(message = "Fichero no puede estar vacio")
	@ImagenValida
	private MultipartFile imagen;
	
	public ImagenPiloto(
			@Size(min = 5, message = "El usuario debe tener un tamaño minimo de 5 caracteres") String numLicencia,
			MultipartFile imagen) {
		super();
		this.numLicencia = numLicencia;
		this.imagen = imagen;
	}
	public ImagenPiloto() {
		super();
	}
	public ImagenPiloto(
			@Size(min = 5, message = "El usuario debe tener un tamaño minimo de 5 caracteres") String numLicencia) {
		super();
		this.numLicencia = numLicencia;
	}
}
