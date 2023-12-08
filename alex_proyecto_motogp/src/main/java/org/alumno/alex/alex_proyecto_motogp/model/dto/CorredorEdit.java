package org.alumno.alex.alex_proyecto_motogp.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorredorEdit {
	@NotNull(message = "No debe estar vacio")
	private int numLicencia;
	@Size(min = 1, max = 30, message = "Longitud incorrecta") // Ajusta los valores mínimo y máximo según tus necesidades
	private String nombre;
	
	private int dorsal;
	@Size(min = 1, max = 30, message = "Longitud incorrecta")
	private String equipo;
	@Size(min = 1, max = 30, message = "Longitud incorrecta")
	private String nacionalidad;
	private int edad;
	private String estadoFisico;
	@Size(min = 1, max = 20, message = "Longitud incorrecta")
	private String motoActual;
	private String nombreFicheroConImagen;
	
	
	public CorredorEdit() {
		this.nombreFicheroConImagen = "Desconocido.jpg";
	}
}
