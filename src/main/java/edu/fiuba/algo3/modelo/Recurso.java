package edu.fiuba.algo3.modelo;

public class Recurso extends Carta {
	private String nombre;
	private int cantidad;
	
	public Recurso(String nombre) {
	    this.nombre = nombre;
        this.cantidad = 0;
	}
	
	public void usar(int cantidadUsada) {
		cantidad = cantidad - cantidadUsada;
	}
	
	private void verficarDisponibilidad(int cantidadPedida) {
		if (cantidadPedida < cantidad) {
			throw new IllegalArgumentException("No posees cantidad suficiente de " + nombre);
	    }
	}

    public boolean sosTipo(String unTipo) {
        return this.nombre.equals(unTipo);
    }

    public void incrementar(int cantidad) {
        this.cantidad += cantidad;
    }

    public void decrementar(int cantidad) {
        verficarDisponibilidad(cantidad);
        this.cantidad -= cantidad;
    }

    @Override
    public void usar() {
        System.out.println("Usando recurso " + nombre);
    }
}
