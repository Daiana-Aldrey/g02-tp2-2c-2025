package edu.fiuba.algo3.modelo;

public class Recurso extends Carta {
	private String nombre;
	private int cantidad;
	
	public Recurso(String nombre) {
	    this.nombre = nombre.trim().toUpperCase();
        this.cantidad = 0;
	}
	
	public Recurso(String nombre, int cantidad) {
	    this.nombre = nombre.trim().toUpperCase();
        this.cantidad = cantidad;
	}
	
	
	public void usar(int cantidadUsada) {
		cantidad = cantidad - cantidadUsada;
	}

    public boolean sosTipo(String unTipo) {
        return this.nombre.equals(unTipo.trim().toUpperCase());
    }
    
    public void cobrarDe(Jugador jugador) {
        jugador.descontarRecurso(this.nombre, this.cantidad);
    }

    public void incrementar(int cantidad) {
        this.cantidad += cantidad;
    }

    public void decrementar(int cantidadPedida) {
    	if (cantidadPedida > cantidad) {
            throw new IllegalArgumentException("No posees cantidad suficiente de " + nombre);
	    }
    	
        this.cantidad -= cantidad;
    }
    
   public void usar() {
	   
   }

    public int cantidad() {
        return cantidad;
    }

    public String nombre(){
        return nombre;
    }
}
