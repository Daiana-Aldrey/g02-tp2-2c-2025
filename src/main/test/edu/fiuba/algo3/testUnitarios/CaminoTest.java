package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CaminoTest {
    @Test
    public void SeIntentaPonerPrimerCaminoNoAlLadoDeUnPobladoYSeLanzaUnaExcepcion() {
        Jugador jugador1 = new Jugador("Luis");
        Camino camino = new Camino(jugador1);

        jugador1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(1)));

        assertThrows(IllegalArgumentException.class, () -> {
            camino.colocarPrimera(List.of(new UbicacionVertice(3),  new UbicacionVertice(2)));
        });

    }

    @Test
    public void SeIntetaPonerUnCaminoAisladoYLanzaExcepcion() {
        Jugador jugador1 = new Jugador("Luis");
        Camino camino1 = new Camino(jugador1);
        camino1.setearSegundaUbicacion(new UbicacionVertice(1));
        camino1.setearUbicacion(new UbicacionVertice(2));

        jugador1.incorporarCamino(camino1);

        Camino camino2 = new Camino(jugador1);
        camino2.setearUbicacion(new UbicacionVertice(2));
        camino2.setearSegundaUbicacion(new UbicacionVertice(3));

        jugador1.incorporarCamino(camino2);

        Camino camino3 = new Camino(jugador1);

        assertThrows(IllegalArgumentException.class, () -> {
            camino3.colocar(List.of(new UbicacionVertice(9),  new UbicacionVertice(10)));
        });

    }
}
