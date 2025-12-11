package edu.fiuba.algo3.modelo.Ronda;

public class RondaVuelta implements Ronda {
    @Override
    public void pasarTurno(OrganizadorDeTurnos organizador) {
        if (organizador.esElPrimerJugador()) {
            organizador.setRonda(new RondaNormal());
        } else {
            organizador.retrocederIndice();
        }
    }
    
    @Override
    public boolean esFaseInicial() {
        return true;
    }
}
