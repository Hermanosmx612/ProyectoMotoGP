package org.alumno.alex.alex_proyecto_motogp.srv;

import java.util.ArrayList;
import java.util.List;

import org.alumno.alex.alex_proyecto_motogp.model.ram.Corredor;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class CorredorService {
	@Getter
	@Setter
	public static List<Corredor> corredores = new ArrayList<Corredor>();
	
	static {
		corredores.add(new Corredor(111111111, "Marc Marquez", 93,"Gresini Racing","España"));
		corredores.add(new Corredor(111111112, "Bin Laden", 911,"Honda","New York"));
	}
	
	public Corredor encontrarPilotoLicencia(String numLicencia) {
		for(Corredor pil : corredores) {
			if(pil.getNumLicencia() == (Integer.parseInt(numLicencia))) {
				return pil;
			}
		}
		return null;
	}
}
