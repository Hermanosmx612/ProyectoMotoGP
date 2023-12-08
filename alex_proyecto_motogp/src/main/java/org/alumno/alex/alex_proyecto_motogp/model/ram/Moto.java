package org.alumno.alex.alex_proyecto_motogp.model.ram;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Moto {
	
	private int id;
	private String nombre;
	private String nacionalidad;
	private int anoFundacion;
	private String nombreFicheroImagen;
	
	
	
	public Moto(int id, String nombre, String nacionalidad, int anoFundacion, String nombreFicheroImagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.anoFundacion = anoFundacion;
		this.nombreFicheroImagen = nombreFicheroImagen;
	}
	
	
	
	
}
