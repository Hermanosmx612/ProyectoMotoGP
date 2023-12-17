package org.alumno.alex.alex_proyecto_motogp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PilotoMoto {
	private int numLicencia;
	private int idMoto;
	
	
	public PilotoMoto(int idMoto) {
		super();
		this.idMoto = idMoto;
	}
	
	
}
