package org.alumno.alex.alex_proyecto_motogp.model.dto;

import org.alumno.alex.alex_proyecto_motogp.validaciones.ImagenValida;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class MotoInfo {
	private int id;
	private String nombre;
	private String nacionalidad;
	private int anoFundacion;
	private String nombreFicheroImagen;
	
	
	public MotoInfo(int id, String nombre, String nacionalidad, int anoFundacion, String nombreFicheroImagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.anoFundacion = anoFundacion;
		this.nombreFicheroImagen = nombreFicheroImagen;
	}
	
	
	
	
}


