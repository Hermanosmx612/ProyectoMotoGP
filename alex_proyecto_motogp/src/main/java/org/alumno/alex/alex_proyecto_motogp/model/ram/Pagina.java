package org.alumno.alex.alex_proyecto_motogp.model.ram;

public class Pagina {
	String titulo;
	String paginaActiva;
	String idioma;
	
	
	
	
	
	
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPaginaActiva() {
		return paginaActiva;
	}
	public void setPaginaActiva(String paginaActiva) {
		this.paginaActiva = paginaActiva;
	}
	public Pagina(String titulo, String paginaActiva) {
		super();
		this.titulo = titulo;
		this.paginaActiva = paginaActiva;
		this.idioma = "es";
	}
	public String getStrBootstrapActiva(String pagina) {
		if(paginaActiva.equals(pagina)) {
			return "active";
		}else {
			return "";
		}
	}
	
	public String loginLogout(String pagina) {
		if(pagina.equals("login.do")){
			return "/login.do";
		}else {
			return "/logout.do";
		}
	}
	public Pagina(String titulo, String paginaActiva, String idioma) {
		super();
		this.titulo = titulo;
		this.paginaActiva = paginaActiva;
		this.idioma = idioma;
	}
	
	
	
	
	
	
}
