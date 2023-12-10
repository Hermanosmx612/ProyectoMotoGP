package org.alumno.alex.alex_proyecto_motogp.model.ram;

import java.util.Date;
import java.util.Objects;

import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorEdit;
import org.alumno.alex.alex_proyecto_motogp.model.interfaces.Modificable;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Corredor implements Modificable{
	
	@NotNull(message = "No debe estar vacio")
	private int numLicencia;
	private MultipartFile imagenPiloto;
	@Size(min = 1, max = 30, message = "Longitud incorrecta") // Ajusta los valores mínimo y máximo según tus necesidades
	private String nombre;
	private int dorsal;
	@Size(min = 1, max = 30, message = "Longitud incorrecta")
	private String equipo;
	@Size(min = 1, max = 30, message = "Longitud incorrecta")
	private String nacionalidad;
	private int edad;
	private String estadoFisico;
	@Size(min = 1, max = 20, message = "Longitud incorrecta")
	private String motoActual;
	private String nombreFicheroConImagen;
	private Date ts;
	private String user; 


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

	
	public Corredor(int numLicencia, MultipartFile imagenPiloto, String nombre, int dorsal, String equipo,
			String nacionalidad, int edad, String estadoFisico, String motoActual, String nombreFicheroConImagen) {
		super();
		this.numLicencia = numLicencia;
		this.imagenPiloto = imagenPiloto;
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.equipo = equipo;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.estadoFisico = estadoFisico;
		this.motoActual = motoActual;
		this.nombreFicheroConImagen = nombreFicheroConImagen;
		this.ts = new Date();
		this.user = "";
	}
	
	public Corredor() {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(numLicencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corredor other = (Corredor) obj;
		return numLicencia == other.numLicencia;
	}


	@Override
	public boolean sePuedeModificarUtilizando(Object itemModificado) {
		if (this.getUser() != null && this.getTs()!= null) {
			//Existe un usuario y una fecha Inicial y tenemos que comprobar
			String usuarioActual = this.getUser();
			String usuarioModificado = ((CorredorEdit) itemModificado).getUser();
			//formateamos fechas gracias a la clase Ts que formatea fechas
			Date fechaActual = Ts.parseIso(Ts.formatIso(this.getTs()));
			Date fechaModificada = Ts.parseIso(Ts.formatIso(((Corredor) itemModificado).getTs()));
			if (!usuarioActual.equals(usuarioModificado) || !fechaActual.equals(fechaModificada))
				//El usuario no es el mismo o la fecha cambia
				return false;
		}
		//No tenemos fecha o usuario-> 1º modificación, por lo que se puede modificar
		return true;
	}


	@Override
	public String mensajeNoSePuedeModificar() {
		String msg="\r\n\t[ERROR]\r\n<br/>" +
				"\t'$item' ha sido modificado por otro usuario.\r\n<br/>" +
				"\tPara evitar la pérdida de información se impide guardar '$item'.\r\n<br/>" +
				"\tÚltima modificación realizada por [" + this.getUser() + "] el [" + 
				Ts.ts(this.getTs()) + "]\r\n<br/>";
		//Para concretar el tipo de registro modificado sustituimos $item por Alumno
		return msg.replace("$item", "Piloto");
	}
	
	


	
	
	
	
	
	
}
