package edu.fiuba.algo3.modelo.Ronda;

public class RondaNormal implements Ronda {
    @Override
    public void pasarTurno(OrganizadorDeTurnos organizador) {
        organizador.avanzarIndiceCircular();
    }

    @Override
    public boolean esFaseInicial() {
        return false;
    }
}