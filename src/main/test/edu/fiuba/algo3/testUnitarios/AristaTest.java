package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AristaTest {
    @Test
    public void AristaConfirmaSusVerticesAdyacentes() {
        Arista arista = new Arista(new UbicacionVertice(1),new UbicacionVertice(2));

        assertTrue(arista.tieneUbicacion(new UbicacionVertice(2),new UbicacionVertice(1)));
    }

    @Test
    public void SePoneCaminoEnAristaYCambiaDeEstadoANoDisponible() {
        Arista arista = new Arista(new UbicacionVertice(1),new UbicacionVertice(2));
        arista.colocarCamino(new Camino(new Jugador("Juan")));

        assertFalse(arista.estaDisponible());
    }

    @Test
    public void AristaNoDisponibleSeIntentaPonerCaminoYLanzaExcepcion() {
        Arista arista = new Arista(new UbicacionVertice(1),new UbicacionVertice(2));
        arista.colocarCamino(new Camino(new Jugador("Juan")));

        assertThrows( IllegalArgumentException.class, () -> {
            arista.colocarCamino(new Camino(new Jugador("Juana")));
        });
    }
}
