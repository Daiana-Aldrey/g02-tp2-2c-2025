package edu.fiuba.algo3.modelo.RondaInicial;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;

import java.util.List;

public class RondaInversa implements RondaColocacion{
    @Override
    public void ejecutarRonda(List<Jugador> jugadores, List<List<Ubicacion>> poblados, List<List<Ubicacion>> caminos) {
        for (int i = jugadores.size() - 1; i >= 0; i--) {
            Jugador jugador = jugadores.get(i);
            jugador.colocarPiezaInicial("poblado", poblados.get(i));
            jugador.colocarPiezaInicial("camino", caminos.get(i));
        }
    }
}
