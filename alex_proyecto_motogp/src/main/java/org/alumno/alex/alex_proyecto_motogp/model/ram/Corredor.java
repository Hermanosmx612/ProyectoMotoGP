package org.alumno.alex.alex_proyecto_motogp.model.ram;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Corredor {
	private int numLicencia;
	private MultipartFile imagenPiloto;
	private String nombre;
	private int dorsal;
	private String equipo;
	private String nacionalidad;
	private int edad;
	private String estadoFisico;
	private String motoActual;
	private String nombreFicheroConImagen;
	
	
	public Corredor( String nombre, int dorsal, String equipo, String nacionalidad,
			int edad, String estadoFisico, String motoActual) {
		super();
//		this.imagenPiloto = imagenPiloto;
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.equipo = equipo;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.estadoFisico = estadoFisico;
		this.motoActual = motoActual;
	}


	public Corredor(int numLicencia, String nombre, int dorsal, String equipo, String nacionalidad) {
		super();
		this.numLicencia = numLicencia;
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.equipo = equipo;
		this.nacionalidad = nacionalidad;
		nombreFicheroConImagen = "Desconocido.jpg";
	}
	
	public Corredor(int numLicencia, String nombre, int dorsal, String equipo, String nacionalidad, String nombrefichero) {
		super();
		this.numLicencia = numLicencia;
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.equipo = equipo;
		this.nacionalidad = nacionalidad;
		nombreFicheroConImagen = nombrefichero;
	}


	
	
	
	
	
	
}
