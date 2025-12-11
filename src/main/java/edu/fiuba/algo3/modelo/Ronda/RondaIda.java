package edu.fiuba.algo3.modelo.Ronda;

public class RondaIda implements Ronda {
    @Override
    public void pasarTurno(OrganizadorDeTurnos organizador) {
        if (organizador.esElUltimoJugador()) {
            organizador.setRonda(new RondaVuelta());
        } else {
            organizador.avanzarIndice();
        }
    }

    @Override
    public boolean esFaseInicial() {
        return true;
    }
}
