package edu.fiuba.algo3.modelo;

public class Recurso extends Carta {
	private String nombre;
	private int cantidad;
	
	public Recurso(String nombre) {
	    this.nombre = nombre;
	}
	
	public void usar(int cantidadUsada) {
		cantidad = cantidad - cantidadUsada;
	}
	
	private void verficarDisponibilidad(int cantidadPedida) {
		if (cantidadPedida < cantidad) {
			throw new IllegalArgumentException("No posees cantidad suficiente de " + nombre);
	    }
	}
}
