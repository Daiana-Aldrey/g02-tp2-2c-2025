package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Vertice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerticeTest {
    @Test
    public void verticeDeniegaNombre() {
        Vertice vertice = new Vertice(1);
        assertFalse(vertice.tieneNombre(2));
    }

    @Test
    public void verticeDevuelveSusVerticesAdyacentes() {
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        assertTrue(vertice2.hayVerticeAdyacente(vertice1));

    }

    @Test
    public void verticeNoDisponiblePorEstarOcupadoPorUnaPieza() {
        Vertice vertice = new Vertice(1);

        vertice.tienePieza();

        assertFalse(vertice.estaDisponible());
    }

    @Test
    public void verticeNoDisponiblePorTenerPiezaAdyacente() {
        Vertice vertice1 = new Vertice(1);
        Vertice vertice2 = new Vertice(2);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        vertice1.tienePieza();

        assertFalse(vertice2.estaDisponible());
    }

}
