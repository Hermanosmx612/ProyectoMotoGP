package org.alumno.alex.alex_proyecto_motogp.srv;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorEdit;
import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorList;
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
		corredores.add(new Corredor(111111111, "Marc Marquez", 93, "Gresini Racing", "España", "111111111.jpeg"));
		corredores.add(new Corredor(111111112, "Jorge Lorenzo", 99, "Honda", "España", "111111112.jpeg"));
		corredores.add(new Corredor(111111113, "Dani Pedrosa", 26, "KTM", "España", "111111113.jpeg"));

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
		for (Corredor pil : corredores) {
			if (pil.getNumLicencia() == (Integer.parseInt(numLicencia))) {
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
		for (Corredor pilot : corredores) {
			if (pilot.getNumLicencia() == numLicencia) {
				return true;
			}
		}
		return false;
	}

	public void addPiloto(CorredorEdit piloto) {
		corredores.add(PilotoMapper.INSTANCE.corredorEditToCorredor(piloto));
	}

	public void delPiloto(String licencia) {
		for (int i = 0; i < corredores.size(); i++) {
			if (corredores.get(i).getNumLicencia() == Integer.parseInt(licencia)) {
				corredores.remove(i);
				break;
			}
		}
	}

	public List<CorredorList> listaPilotos(String criterioOrdenacion, String buscar) {
		List<Corredor> pilotosFiltrados = new ArrayList<Corredor>();

		switch (criterioOrdenacion) {
		case "Numero Licencia":
			pilotosFiltrados = (List<Corredor>) corredores.stream()
					.filter(a -> (a.getNumLicencia() + "").equals(buscar)).collect(Collectors.toList());
			break;
		case "Equipo":
			pilotosFiltrados = (List<Corredor>) corredores.stream().filter(a -> (a.getEquipo() + "").equals(buscar))
					.collect(Collectors.toList());
			break;
		case "Nacionalidad":
			pilotosFiltrados = (List<Corredor>) corredores.stream()
					.filter(a -> (a.getNacionalidad() + "").equals(buscar)).collect(Collectors.toList());
			break;

		}
		return PilotoMapper.INSTANCE.corredorToCorredorList(pilotosFiltrados);

	}

	public List<CorredorList> ordenarPorCriterio(String criterioOrdenacion) {
		List<Corredor> pilotosOrdenados = new ArrayList<Corredor>();

		switch (criterioOrdenacion) {
		case "nombre":
			pilotosOrdenados = (List<Corredor>) corredores.stream()
					.sorted((a1, a2) -> a1.getNombre().compareTo(a2.getNombre())).collect(Collectors.toList());
			break;
		case "numLicencia":
			pilotosOrdenados = (List<Corredor>) corredores.stream()
					.sorted((a1, a2) -> Integer.compare(a1.getNumLicencia(), a2.getNumLicencia()))
					.collect(Collectors.toList());
			break;

		case "dorsal":

			pilotosOrdenados = (List<Corredor>) corredores.stream()
					.sorted((a1, a2) -> Integer.compare(a1.getDorsal(), a2.getDorsal())).collect(Collectors.toList());
			break;

		case "equipo":
			pilotosOrdenados = (List<Corredor>) corredores.stream()
					.sorted((a1, a2) -> a1.getEquipo().compareTo(a2.getEquipo())).collect(Collectors.toList());
			break;

		case "nacionalidad":
			pilotosOrdenados = (List<Corredor>) corredores.stream()
					.sorted((a1, a2) -> a1.getNacionalidad().compareTo(a2.getNacionalidad()))
					.collect(Collectors.toList());
			break;

		}
		return PilotoMapper.INSTANCE.corredorToCorredorList(pilotosOrdenados);
	}

	public void modificarPiloto(CorredorEdit corredorEditModifido, String usuarioModificacion) throws Exception {
		if (usuarioModificacion == null || corredorEditModifido == null) {
			throw new Exception("Algo mal va por aqui");
		} else {
			Corredor corredorActual = encontrarPilotoLicencia(corredorEditModifido.getNumLicencia() + "");
			CorredorEdit corredorEditActual = PilotoMapper.INSTANCE.corredorToCorredorEdit(corredorActual);
			if (corredorEditActual.sePuedeModificarUtilizando(corredorEditModifido)) {
				// List<DocAlumno> docsAlumnos = alumnoModificado.getDocsAlumno();
				// System.out.println(alumnoModificado.getDocsAlumno());
				corredores.remove(corredorActual);
				corredorEditModifido.setTs(new Date());
				corredorEditModifido.setUser(usuarioModificacion);
//				alumnoModificado.setDocsAlumno(docsAlumnos);
				// alumnos.add(alumnoEditModificado);
				PilotoMapper.INSTANCE.updateCorredorFromCorredorEdit(corredorEditModifido, corredorActual);
				corredores.add(corredorActual);
			} else {
				throw new Exception(corredorActual.mensajeNoSePuedeModificar());
			}
		}

	}

	public List<CorredorList> encontrarMotosActuales(String id) {
		return PilotoMapper.INSTANCE.corredorToCorredorList(
				corredores.stream().filter(a -> a.getMotoCorre().contains(Integer.parseInt(id))).toList());
	}
	
	public List<CorredorList> encontrarMotosNoActuales(String id) {
		return PilotoMapper.INSTANCE.corredorToCorredorList(
				corredores.stream().filter(a -> !a.getMotoCorre().contains(Integer.parseInt(id))).toList());
	}

	public void deletePilotTeam(String idMoto, String numLicencia) {
		ArrayList<Integer> idMotos = new ArrayList<Integer>();
		for(Corredor c : corredores) {
			if((c.getNumLicencia()+"").equals(numLicencia)) {
				for(int i : c.getMotoCorre()) {
					if(i != Integer.parseInt(idMoto)) {
						idMotos.add(i);
					}
				}
				c.setMotoCorre(idMotos);
			}
		}
	}
}
