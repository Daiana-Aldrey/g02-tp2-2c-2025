package edu.fiuba.algo3.modelo.CartaDeBonificacion;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.NoJugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.*;

public class CartaGranCaballeria {
    private Jugador jugadorBonificado = new NoJugador();

    private static final CartaGranCaballeria INSTANCE = new CartaGranCaballeria();

    public static CartaGranCaballeria getInstance() {
        return INSTANCE;
    }

    public void verificarBonificacion(Jugador jugador) {
        Jugador jugadorBonificadoAnteriormente;
        if (jugador.tengoMasCantidadDeUsosCartaCaballero(this.jugadorBonificado))
            if (jugador.tengoMasDe2UsosCartaCaballero()) {
                jugadorBonificadoAnteriormente = this.jugadorBonificado;
                this.jugadorBonificado = jugador;
                this.disminuirPuntosDeVictoria(2, jugadorBonificadoAnteriormente);
                this.aumentarPuntosDeVictoria(2, this.jugadorBonificado);
            }
    }

    public void aumentarPuntosDeVictoria(int puntosDeVictoria, Jugador jugador) {
        jugador.otorgarPuntos(puntosDeVictoria);
    }

    public void disminuirPuntosDeVictoria(int puntosDeVictoria, Jugador jugador) {
        jugador.sacarPuntos(puntosDeVictoria);
    }

    public Jugador obtenerBonificado() {
        return this.jugadorBonificado;
    }
}
