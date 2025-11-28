package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Poblado;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PobladoTest {
    @Test
    public void LuegoDeLasPrimerasRondasSeIntetaPonerUnPobladoEnUnaUbicacionSinCaminoAntecesorYSeLanzaExcepcion() {
        Jugador jugador = new Jugador("Jugador");
        jugador.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(1)));
        jugador.colocarPiezaInicial("camino", List.of(new UbicacionVertice(1), new UbicacionVertice(9)));

        Camino caminoAnterior = new Camino(jugador);
        caminoAnterior.setearUbicacion(new UbicacionVertice(9));
        caminoAnterior.setearSegundaUbicacion(new UbicacionVertice(10));

        jugador.incorporarCamino(caminoAnterior);

        Poblado poblado = new Poblado(jugador);

        assertThrows(IllegalArgumentException.class, () -> {
            poblado.colocar(List.of(new UbicacionVertice(20)));
        });
    }
}