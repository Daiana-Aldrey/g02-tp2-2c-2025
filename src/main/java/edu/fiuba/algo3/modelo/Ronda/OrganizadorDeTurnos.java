package edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.*;

import java.util.List;

public class OrganizadorDeTurnos {
    private List<Jugador> jugadores;
    private int indiceActual;
    private Ronda rondaActual;

    public OrganizadorDeTurnos(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.indiceActual = 0;
        this.rondaActual = new RondaIda();
    }

    public Jugador jugadorActual() {
        return jugadores.get(indiceActual);
    }

    public void siguienteTurno() {
        rondaActual.pasarTurno(this);
    }

    public boolean esFaseInicial() {
        return rondaActual.esFaseInicial();
    }

    
    public void setRonda(Ronda nuevaRonda) {
        this.rondaActual = nuevaRonda;
    }

    public boolean esElUltimoJugador() {
        return indiceActual == jugadores.size()-1;
    }

    public boolean esElPrimerJugador() {
        return indiceActual == 0;
    }

    public void avanzarIndice() {
        indiceActual++;
    }

    public void avanzarIndiceCircular() {
        indiceActual = (indiceActual + 1) % jugadores.size();
    }
    
    public void retrocederIndice() {
        if (indiceActual > 0) {
            indiceActual--;
        }
    }
    

}