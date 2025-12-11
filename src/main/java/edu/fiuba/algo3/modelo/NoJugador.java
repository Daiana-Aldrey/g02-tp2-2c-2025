package edu.fiuba.algo3.modelo;

public class NoJugador extends Jugador {

    public NoJugador() {
        super("Nulo");
    }

    @Override
    public boolean tengoMasCantidadDeUsosCartaCaballero(Jugador jugador) {
        return false;
    }
}
