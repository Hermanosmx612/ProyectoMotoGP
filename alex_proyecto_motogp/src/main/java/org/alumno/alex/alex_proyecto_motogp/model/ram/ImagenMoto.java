package org.alumno.alex.alex_proyecto_motogp.model.ram;

import java.io.Serializable;

import org.alumno.alex.alex_proyecto_motogp.validaciones.ImagenValida;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ImagenMoto implements Serializable{
	
	private static final long serialVersionUID = 1L;
		private int id;
	@NotNull(message = "Fichero no puede estar vacio")
	@ImagenValida
	private MultipartFile imagen;
	public ImagenMoto(int id, @NotNull(message = "Fichero no puede estar vacio") MultipartFile imagen) {
		super();
		this.id = id;
		this.imagen = imagen;
	}
	public ImagenMoto(int id) {
		super();
		this.id = id;
	}
	public ImagenMoto() {
		super();
	}
	
	
	
	
	
	
}
