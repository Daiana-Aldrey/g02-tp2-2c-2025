package edu.fiuba.algo3.modelo.RondaInicial;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import java.util.List;

public interface RondaColocacion {
    void ejecutarRonda(List<Jugador> jugadores, List<List<Ubicacion>> poblados, List<List<Ubicacion>> caminos);
}

