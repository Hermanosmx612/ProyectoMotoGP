package org.alumno.alex.alex_proyecto_motogp.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorredorEdit {
	private int numLicencia;
	private String nombre;
//	private int dorsal;
//	private String equipo;
//	private String nacionalidad;
//	private int edad;
//	private String estadoFisico;
//	private String motoActual;
	private String nombreFicheroConImagen;
	
	
	public CorredorEdit() {
		this.nombreFicheroConImagen = "Desconocido.jpg";
	}
}
