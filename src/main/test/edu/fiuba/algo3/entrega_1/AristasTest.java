package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AristasTest {
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
        arista.colocarCamino(new Camino());

        assertFalse(arista.estaDisponible());
    }
}
