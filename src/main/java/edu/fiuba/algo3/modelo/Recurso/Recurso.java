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

    public boolean esSuficiente(int cant){
        return cantidad >= cant;
    }

    //primer dispatch
    public abstract boolean podesIncrementar(Recurso recursoDelJugador, int cantidad);

    //segundo dispatch
    protected abstract boolean incrementarMadera(Recurso recursorecibido, int cant);
    protected abstract boolean incrementarMineral(Recurso recursorecibido, int cant);
    protected abstract boolean incrementarLadrillo(Recurso recursorecibido, int cant);
    protected abstract boolean incrementarLana(Recurso recursorecibido, int cant);
    protected abstract boolean incrementarGrano(Recurso recursorecibido, int cant);


    public abstract boolean podesDecrementar(Recurso recursoDelJugador, int cant);
    protected abstract boolean decrementarMadera(Recurso recursoADecrementar, int cant);
    protected abstract boolean decrementarLadrillo(Recurso recursoADecrementar, int cant);
    protected abstract boolean decrementarGrano(Recurso recursoADecrementar, int cant);
    protected abstract boolean decrementarLana(Recurso recursoADecrementar, int cant);
    protected abstract boolean decrementarMineral(Recurso recursoADecrementar, int cant);

    public abstract Recurso crearCon(int cant);

}
