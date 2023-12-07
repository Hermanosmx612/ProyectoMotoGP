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
	List<Corredor> corredores = new ArrayList<Corredor>();
	
	
}
