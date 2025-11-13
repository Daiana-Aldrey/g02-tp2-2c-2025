package edu.fiuba.algo3.modelo;

public class Recurso extends Carta {
	private String nombre;
	private int cantidad;
	
	public Recurso(String nombre) {
	    this.nombre = nombre;
        this.cantidad = 0;
	}
	
	public Recurso(String nombre, int cantidad) {
	    this.nombre = nombre;
        this.cantidad = cantidad;
	}
	
	
	public void usar(int cantidadUsada) {
		cantidad = cantidad - cantidadUsada;
	}
	
	private void verficarDisponibilidad(int cantidadPedida) {
		if (cantidadPedida > cantidad) {
            // cambio el < por que si cantidadPedida < cantidad es true
            //no deberia tirar una exception , creo que para que tire una excepcion debe ser alreves

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
    
    public boolean puedeCubrir(Recurso costo) {
    	//NO SE COMO HACER ESTO SIN VIOLAR TELL DON'T ASK (PREGUNTAR)
        return this.sosTipo(costo.nombre()) && this.cantidad >= costo.cantidad(); 
    }

    public int cantidad() {
        return cantidad;
    }

    public String nombre(){
        return nombre;
    }
}
