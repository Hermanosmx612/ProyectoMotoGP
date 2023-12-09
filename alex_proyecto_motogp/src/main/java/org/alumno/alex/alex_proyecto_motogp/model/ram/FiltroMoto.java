package org.alumno.alex.alex_proyecto_motogp.model.ram;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroMoto {
	String valor = "";
	String campo = "";
	public FiltroMoto() {
		super();
	}
	public FiltroMoto(String valor, String campo) {
		super();
		this.valor = valor;
		this.campo = campo;
	}
}
