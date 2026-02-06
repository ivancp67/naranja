package ceu.dam.javafx.ejemplo32.modelo;

public class Equipo {

	private String nombre;
	private Integer creacion;
	private String ciudad;
	
	public Equipo(String nombre, Integer creacion, String ciudad) {
		this.nombre = nombre;
		this.creacion = creacion;
		this.ciudad = ciudad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCreacion() {
		return creacion;
	}
	public void setCreacion(Integer creacion) {
		this.creacion = creacion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
