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

    public abstract boolean podesIncrementar(Recurso recursoDelJugador, int cantidad);
    protected abstract boolean incrementar(Madera recursorecibido, int cant);
    protected abstract boolean incrementar(Mineral recursorecibido, int cant);
    protected abstract boolean incrementar(Ladrillo recursorecibido, int cant);
    protected abstract boolean incrementar(Lana recursorecibido, int cant);
    protected abstract boolean incrementar(Grano recursorecibido, int cant);


    public abstract boolean podesDecrementar(Recurso recursoDelJugador, int cant);
    protected abstract boolean decrementar(Madera recursoADecrementar, int cant);
    protected abstract boolean decrementar(Ladrillo recursoADecrementar, int cant);
    protected abstract boolean decrementar(Grano recursoADecrementar, int cant);
    protected abstract boolean decrementar(Lana recursoADecrementar, int cant);
    protected abstract boolean decrementar(Mineral recursoADecrementar, int cant);

    public abstract Recurso crearCon(int cant);

}
