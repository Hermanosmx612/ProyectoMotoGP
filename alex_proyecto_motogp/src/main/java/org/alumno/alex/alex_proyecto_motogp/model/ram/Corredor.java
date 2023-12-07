package org.alumno.alex.alex_proyecto_motogp.model.ram;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

public class Corredor {
	
	@Getter
	@Setter
	
	
	private int id;
	private MultipartFile imagenPiloto;
	private String nombre;
	private int dorsal;
	private String equipo;
	private String nacionalidad;
	private int edad;
	private String estadoFisico;
	private String motoActual;
	
	
	public Corredor(int id, MultipartFile imagenPiloto, String nombre, int dorsal, String equipo, String nacionalidad,
			int edad, String estadoFisico, String motoActual) {
		super();
		this.id = id;
		this.imagenPiloto = imagenPiloto;
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.equipo = equipo;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.estadoFisico = estadoFisico;
		this.motoActual = motoActual;
	}
	
	
	
}
