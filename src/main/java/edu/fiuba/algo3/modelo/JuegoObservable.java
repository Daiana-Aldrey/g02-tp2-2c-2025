package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.observador.Observable;

public class JuegoObservable extends Observable {

    private final Juego juego;
    private int[] ultimaTirada; 

    public JuegoObservable(Juego juego) {
        this.juego = juego;
    }

    public Juego juego() {
        return juego;
    }


    public void realizarTirada() {
        this.ultimaTirada = juego.tirarDados();
        notificarObservadores("DADOS"); 
    }

    public int getDado1() {
        if (ultimaTirada == null) return 1; 
        return ultimaTirada[0];
    }

    public int getDado2() {
        if (ultimaTirada == null) return 1;
        return ultimaTirada[1];
    }

    public int getSuma() {
        return juego.sumarTirada();
    }
}