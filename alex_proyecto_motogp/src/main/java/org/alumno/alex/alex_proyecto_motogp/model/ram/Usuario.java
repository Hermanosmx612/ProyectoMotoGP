package org.alumno.alex.alex_proyecto_motogp.model.ram;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Usuario {
	@NotBlank(message = "El nickname no puede estar en blanco")
	@Size(min = 5, message = "El nickname debe tener al menos 5 caracteres")
	private String nickname;
	private String nombre;
	@NotBlank(message = "La contraseña no puede estar en blanco")
	@Size(min = 8, max = 16, message = "La contraseña debe tener entre 8 y 16 caracteres")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,16}$", message = "La contraseña debe contener al menos un dígito, una minúscula, una mayúscula y un carácter no alfanumérico")
	private String password;
	private String nombreFicheroConImagen;
	
	private String user;

	@Override
	public int hashCode() {
		return Objects.hash(nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nickname, other.nickname);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreFicheroConImagen() {
		return nombreFicheroConImagen;
	}

	public void setNombreFicheroConImagen(String nombreFicheroConImagen) {
		this.nombreFicheroConImagen = nombreFicheroConImagen;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Usuario(String nombre, String nickname, String password) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.password = password;
		this.nombreFicheroConImagen = "Desconocido.jpg";
	}


	public Usuario(String nombre, String nickname, String password, String nombreFicheroConImagen) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.password = password;
		this.nombreFicheroConImagen = nombreFicheroConImagen;
	}
	
	public Usuario() {
		super();
	}
}
