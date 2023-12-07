package org.alumno.alex.alex_proyecto_motogp.srv;

import java.util.ArrayList;
import java.util.List;

import org.alumno.alex.alex_proyecto_motogp.model.ram.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	private static List<Usuario> usuarios = new ArrayList<Usuario>();

	public boolean usuarioValido(Usuario user) {
		for(Usuario listaUsers : usuarios) {
	    	if(user.getNickname().equals(listaUsers.getNickname())) {
	    		if(user.getPassword().equals(listaUsers.getPassword())) {
	    			return true;
	    		}
	    	}
	    }
	    return false;
	}
	
	public Usuario encontrarUsuarioPorNickname(String nickname) {
		for(Usuario listaUsers : usuarios) {
	    	if(nickname.equals(listaUsers.getNickname())) {
	    			return listaUsers;
	    		
	    	}
	    }
		return null;
	}
	
	static {
		usuarios.add(new Usuario("alex","alexito","@Alex123","alexito.jpeg"));
	}
}
