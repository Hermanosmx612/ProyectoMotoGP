package org.alumno.alex.alex_proyecto_motogp.model.ram;

import java.util.Objects;

import org.alumno.alex.alex_proyecto_motogp.validaciones.ImagenValida;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Moto {
	@NotNull
	@ImagenValida
	private MultipartFile imagenMoto;
	private int id;
	private String nombre;
	private String nacionalidad;
	private int anoFundacion;
	private String nombreFicheroImagen;
	
	
	
	public Moto(int id, String nombre, String nacionalidad, int anoFundacion, String nombreFicheroImagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.anoFundacion = anoFundacion;
		this.nombreFicheroImagen = nombreFicheroImagen;
	}



	public Moto(int id) {
		super();
		this.id = id;
	}



	public Moto() {
		super();
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moto other = (Moto) obj;
		return id == other.id;
	}
	
	
	
	
	
	
	
	
	
	
}
