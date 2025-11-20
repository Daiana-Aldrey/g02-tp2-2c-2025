package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AristaTest {
    @Test
    public void AristaConfirmaSusVerticesAdyacentes() {
        Arista arista = new Arista(1,2);

        List<Integer> vertices = new ArrayList<>();
        vertices.add(2);
        vertices.add(1);

        assertTrue(arista.sonMisAdyacentes(vertices));
    }

    @Test
    public void SePoneCaminoEnAristaYCambiaDeEstadoANoDisponible() {
        Arista arista = new Arista(1,2);
        arista.colocarCamino(new Camino(new Jugador("Juan")));

        assertFalse(arista.estaDisponible());
    }
}
