package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.Excepciones.AccionNoPermitida;
public class CartaPuntoVictoria extends Carta {
    static final int PUNTOSGANADOS = 1;

    @Override
    public void usar(Jugador jugador) {
        jugador.sumarPuntosDeVictoria(PUNTOSGANADOS);
    }

    /* como hacerlo todo depende del gestor de turno
    si tiene 8pv y 2cartas pv = terminar el juego o
    tiene 9pv y 1cartas pv = terminar el juego
    esta es una opcion pero tengo otra opcion que puede ser quiza la mejor si no tenemos que mostrar los de c/u
    usar :
    OPCION2PV
    @Override
    public void usar(Jugador jugador) {
        // No hace nada: solo me suma un pv
        // Su efecto es aportar 1 PV oculto cuando me las compro .
    }

    @Override
    public int puntosDeVictoriaOcultos() {
        return 1;
    }
    * */
}
