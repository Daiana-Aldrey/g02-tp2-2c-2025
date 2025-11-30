package edu.fiuba.algo3.modelo.CartaDeBonificacion;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.*;

public class CartaGranCaballeria {
    private int cantidadDeUsos = 0;
    private List<Jugador> jugadores ;
    private static final CartaGranCaballeria INSTANCE = new CartaGranCaballeria();

    public CartaGranCaballeria() {;
        jugadores = new ArrayList<>();
    }

    public static CartaGranCaballeria getInstance() {
        return INSTANCE;
    }

    public void setearJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void verificarBonificacion() {

    }

    public void aumentarPuntosDeVictoria(int puntosDeVictoria, Jugador jugador) {
        jugador.otorgarPuntos(puntosDeVictoria);
    }

    public void DisminuirPuntosDeVictoria(int puntosDeVictoria, Jugador jugador) {
        jugador.sacarPuntos(puntosDeVictoria);
    }
}
