package org.alumno.alex.alex_proyecto_motogp.srv;

import java.util.ArrayList;
import java.util.List;

import org.alumno.alex.alex_proyecto_motogp.model.ram.Moto;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;


@Service
public class MotoService {
	@Getter
	@Setter
	public static List<Moto> motos = new ArrayList<Moto>();
	
	
	
	public Moto encontrarMotoPorId(String id) {
		for(Moto moto : motos) {
			if(moto.getId() == Integer.parseInt(id)) {
				return moto;
			}
		}
		return null;
	}
	
	
	static {
		motos.add(new Moto(1,"Honda","Japon",1970,"1.jpeg"));
		motos.add(new Moto(2,"Yamaha","Japon",1970,"2.jpeg"));
		motos.add(new Moto(3,"Honda","Italia",1970,"3.jpeg"));
	}
}
