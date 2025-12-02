package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.Excepciones.SinRecursos;
import edu.fiuba.algo3.modelo.Jugador;

public abstract class Recurso {
	private int cantidad;

	public Recurso() {
        this.cantidad = 0;
	}

	public Recurso(int cantidad) {
        this.cantidad = cantidad;
	}

	public void usar() {
		System.out.print("Usar recurso");
	}

    public void cobrarDe(Jugador jugador) {
        jugador.descontarRecurso(this, cantidad);
    }

    public void incrementar(int cantidad) {
        this.cantidad += cantidad;
    }

    public void decrementar(int cantidadPedida) {
    	if (cantidadPedida > cantidad) {
            throw new SinRecursos("No posees cantidad suficiente" );
	    }

        this.cantidad -= cantidadPedida;
    }

    public void transferirA(Jugador destino, int cantidad) {
        this.decrementar(cantidad);
        destino.recibirRecurso(this, cantidad);
    }

    public void cobrarDe(Jugador pagador, Jugador receptor) {
        pagador.descontarRecurso(this, cantidad);

        receptor.recibirRecurso(this, cantidad);
    }

    public int cantidad() {
        return cantidad;
    }

    public abstract void podesIncrementar(Recurso recursoDelJugador, int cantidad);
    protected abstract void incrementar(Madera recursorecibido, int cant);
    protected abstract void incrementar(Mineral recursorecibido, int cant);
    protected abstract void incrementar(Ladrillo recursorecibido, int cant);
    protected abstract void incrementar(Lana recursorecibido, int cant);
    protected abstract void incrementar(Grano recursorecibido, int cant);


    public abstract void podesDecrementar(Recurso recursoDelJugador, int cant);
    protected abstract void decrementar(Madera recursoADecrementar, int cant);
    protected abstract void decrementar(Ladrillo recursoADecrementar, int cant);
    protected abstract void decrementar(Grano recursoADecrementar, int cant);
    protected abstract void decrementar(Lana recursoADecrementar, int cant);
    protected abstract void decrementar(Mineral recursoADecrementar, int cant);

    public abstract Recurso crearCon(int cant);

    public abstract String recurso();

}
