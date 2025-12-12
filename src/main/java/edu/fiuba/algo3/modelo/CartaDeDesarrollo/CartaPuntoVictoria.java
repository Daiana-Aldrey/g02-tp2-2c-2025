package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.Excepciones.AccionNoPermitida;
public class CartaPuntoVictoria extends Carta {
    public CartaPuntoVictoria(){
        nombre = "Punto de victoria";
        descripcion = "Ganas un PV";
    }
    static final int PUNTOSGANADOS = 1;

    @Override
    public void usar(Jugador jugador) {
        jugador.otorgarPuntos(PUNTOSGANADOS);
    }
    @Override
    public int puntosDeVictoriaOcultos() {
        return 1;
    }
}
