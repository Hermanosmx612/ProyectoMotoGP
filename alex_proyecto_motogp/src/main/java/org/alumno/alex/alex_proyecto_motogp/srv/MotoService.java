package org.alumno.alex.alex_proyecto_motogp.srv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.alumno.alex.alex_proyecto_motogp.model.dto.MotoInfo;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Moto;
import org.alumno.alex.alex_proyecto_motogp.srv.mapper.MotoMapper;
import org.alumno.alex.alex_proyecto_motogp.srv.mapper.PilotoMapper;
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
	
	public void addMoto(Moto moto) {
		motos.add(MotoMapper.INSTANCE.motoInfoToMoto(new MotoInfo(moto.getId(),moto.getNombre(),moto.getNacionalidad(),moto.getAnoFundacion(),moto.getNombreFicheroImagen())));
	}
	
	public int saberUltimoIdMoto() {
		return motos.stream().map(p -> p.getId()).sorted(Comparator.reverseOrder()).findFirst().orElse(0);
	}
	
	public void delMoto(int id) {
		System.out.println(motos.toString());
		motos.remove(id - 1);
	}
	
	static {
		motos.add(MotoMapper.INSTANCE.motoInfoToMoto(new MotoInfo(1,"Honda","Japon",1970,"1.jpeg")));
		motos.add(MotoMapper.INSTANCE.motoInfoToMoto(new MotoInfo(2,"Yamaha","Japon",1970,"2.jpeg")));
		motos.add(MotoMapper.INSTANCE.motoInfoToMoto(new MotoInfo(3,"Honda","Italia",1970,"3.jpeg")));
	}
}
