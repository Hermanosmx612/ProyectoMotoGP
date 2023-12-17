package org.alumno.alex.alex_proyecto_motogp.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.alumno.alex.alex_proyecto_motogp.model.interfaces.Modificable;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Ts;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorredorEdit implements Modificable<CorredorEdit>, Serializable{
	@NotNull(message = "No debe estar vacio")
	private int numLicencia;
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
	private ArrayList<Integer> motoCorre;
	private Date ts;
	private String user;
	
	
	public CorredorEdit() {
		this.nombreFicheroConImagen = "Desconocido.jpg";
	}
	
	


	@Override
	public boolean sePuedeModificarUtilizando(CorredorEdit itemModificado) {
		if (this.getUser() != null && this.getTs()!= null) {
			//Existe un usuario y una fecha Inicial y tenemos que comprobar
			String usuarioActual = this.getUser();
			String usuarioModificado = itemModificado.getUser();
			//formateamos fechas gracias a la clase Ts que formatea fechas
			Date fechaActual = Ts.parseIso(Ts.formatIso(this.getTs()));
			Date fechaModificada = Ts.parseIso(Ts.formatIso(itemModificado.getTs()));
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
		CorredorEdit other = (CorredorEdit) obj;
		return numLicencia == other.numLicencia;
	}
}
