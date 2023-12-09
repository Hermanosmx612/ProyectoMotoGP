package org.alumno.alex.alex_proyecto_motogp.srv;

import java.util.ArrayList;
import java.util.List;

import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorEdit;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Corredor;
import org.alumno.alex.alex_proyecto_motogp.srv.mapper.PilotoMapper;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class CorredorService {
	@Getter
	@Setter
	public static List<Corredor> corredores = new ArrayList<Corredor>();
	
	static {
		corredores.add(new Corredor(111111111, "Marc Marquez", 93,"Gresini Racing","España","111111111.jpeg"));
		corredores.add(new Corredor(111111112, "Jorge Lorenzo", 99,"Honda","España","111111112.jpeg"));
		corredores.add(new Corredor(111111113, "Dani Pedrosa", 26,"KTM","España","111111113.jpeg"));

	}
	
	public List<String> listarCampos() {
		List<String> campos = new ArrayList<String>();
		campos.removeAll(campos);
		campos.add("Numero Licencia");
		campos.add("Equipo");
		campos.add("Nacionalidad");
		return campos;
	}
	
	
	
	public Corredor encontrarPilotoLicencia(String numLicencia) {
		for(Corredor pil : corredores) {
			if(pil.getNumLicencia() == (Integer.parseInt(numLicencia))) {
				return pil;
			}
		}
		return null;
	}
	
	public List<String> listFisico() {
		List<String> listGeneros = new ArrayList<String>();
		listGeneros.removeAll(listGeneros);
		listGeneros.add("Fit");
		listGeneros.add("Unfit");
		return listGeneros;

	}
	
	
	public boolean comprobarSiExiste(int numLicencia) {
		for(Corredor pilot : corredores) {
			if(pilot.getNumLicencia() == numLicencia) {
				return true;
			}
		}
		return false;
	}
	
	
	public void addPiloto(CorredorEdit piloto) {
		corredores.add(PilotoMapper.INSTANCE.corredorEditToCorredor(piloto));
	}
	
	public void delPiloto(String licencia) {
		for(int i = 0; i < corredores.size(); i++) {
			if(corredores.get(i).getNumLicencia() == Integer.parseInt(licencia)) {
				corredores.remove(i);
				break;
			}
		}
	}
}
