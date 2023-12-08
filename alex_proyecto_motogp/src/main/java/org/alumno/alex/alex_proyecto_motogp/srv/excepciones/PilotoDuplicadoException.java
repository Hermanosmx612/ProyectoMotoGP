package org.alumno.alex.alex_proyecto_motogp.srv.excepciones;

import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorEdit;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Corredor;

public class PilotoDuplicadoException extends Exception{
	Corredor pilotoExistente;
	CorredorEdit pilotoNuevo;
	
	
	public String toString() {
		return "ERROR insertando Piloto:<br>"+
				"Piloto existente:<br>"+
				"dni:"+ pilotoExistente.getNumLicencia()+ "<br>"+
				"nombre:"+pilotoExistente.getNombre()+ "<br>"+
				"Alumno nuevo:<br>"+
				"dni:"+ pilotoNuevo.getNumLicencia()+ "<br>"+
				"nombre:"+pilotoNuevo.getNombre()+ "<br>";
				
	}


	public PilotoDuplicadoException(Corredor pilotoExistente, CorredorEdit pilotoNuevo) {
		super();
		this.pilotoExistente = pilotoExistente;
		this.pilotoNuevo = pilotoNuevo;
	}
	
	
}
