package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.Excepciones.CantidadUbicacionesInvalida;
import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CaminoTest {

    @Test
    public void SeEnviaUnaSolaUbicacionParaColocarYSeLanzaExcepcion() {
        List<Ubicacion> ubicaciones = new ArrayList<>();
        ubicaciones.add(new UbicacionVertice(1));

        Camino camino = new Camino(mock(Jugador.class));

        assertThrows(CantidadUbicacionesInvalida.class, () ->  {
            camino.colocar(ubicaciones);
        });
    }

    @Test
    public void SeIntentaPonerPrimerCaminoNoAlLadoDeUnPobladoYSeLanzaUnaExcepcion() {
        Jugador jugador1 = new Jugador("Luis");
        Camino camino = new Camino(jugador1);

        jugador1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(1)));

        assertThrows(ColocacionInvalida.class, () -> {
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

        assertThrows(ColocacionInvalida.class, () -> {
            camino3.colocar(List.of(new UbicacionVertice(9),  new UbicacionVertice(10)));
        });

    }
}
