package edu.fiuba.algo3.modelo;

public class Recurso extends Carta {
	private final RecursoTipo tipo;
	private int cantidad;
	
	public Recurso(RecursoTipo tipo) {
	    this.tipo = tipo;
        this.cantidad = 0;
	}
	
	public Recurso(RecursoTipo tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
	}
	
	
	public void usar(int cantidadUsada) {
		cantidad = cantidad - cantidadUsada;
	}

    public boolean sosTipo(RecursoTipo unTipo) {
        return this.tipo.equals(unTipo);
    }
    
    public void cobrarDe(Jugador jugador) {
        jugador.descontarRecurso(this.tipo, this.cantidad);
    }

    public void incrementar(int cantidad) {
        this.cantidad += cantidad;
    }

    public void decrementar(int cantidadPedida) {
    	if (cantidadPedida > cantidad) {
            throw new IllegalArgumentException("No posees cantidad suficiente de " + tipo);
	    }
    	
        this.cantidad -= cantidad;
    }
    
   public void usar() {
	   
   }

    public int cantidad() {
        return cantidad;
    }

    public RecursoTipo tipo(){return tipo;}
}
