package org.alumno.alex.alex_proyecto_motogp.model.dto;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorredorList {
	private int numLicencia;
	private MultipartFile imagenPiloto;
	private String nombre;
	private int dorsal;
	private String equipo;
	private String nacionalidad;
	
	public CorredorList() {
		
	}
}
