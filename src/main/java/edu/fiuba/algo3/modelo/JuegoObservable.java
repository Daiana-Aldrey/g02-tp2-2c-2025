package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.observador.Observable;

public class JuegoObservable extends Observable {

    private final Juego juego;

    public JuegoObservable(Juego juego) {
        this.juego = juego;
    }

    public Juego juego() {
        return juego;
    }

    
    public int[] tirarDadosYRepartir() {
        int[] tirada = juego.tirarDados();
        int suma = tirada[0] + tirada[1];

        notificarObservadores("TIRADA:" + suma);
        return tirada;
    }
}
