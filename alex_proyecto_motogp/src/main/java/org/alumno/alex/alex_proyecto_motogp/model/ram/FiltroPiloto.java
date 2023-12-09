package org.alumno.alex.alex_proyecto_motogp.model.ram;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroPiloto {
	String valor = "";
	String campo = "";
	public FiltroPiloto() {
		super();
	}
	public FiltroPiloto(String valor, String campo) {
		super();
		this.valor = valor;
		this.campo = campo;
	}
	
	
}
