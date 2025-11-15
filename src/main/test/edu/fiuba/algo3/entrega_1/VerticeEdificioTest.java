package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerticeEdificioTest {
    @Test
    public void verticeDeniegaUbicacion() {
        VerticeEdificio vertice = new VerticeEdificio(1);
        assertFalse(vertice.tieneUbicacion(2));
    }

    @Test
    public void verticeVerificaSusVerticesAdyacentes() {
        VerticeEdificio vertice1 = new VerticeEdificio(1);
        VerticeEdificio vertice2 = new VerticeEdificio(2);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        assertTrue(vertice2.hayVerticeAdyacente(vertice1));

    }

    @Test
    public void verticeNoDisponiblePorEstarOcupadoPorUnPoblado() {
        VerticeEdificio verticeEdificio = new VerticeEdificio(1);
        Pieza pieza = new Poblado(new Jugador("Luis"));
        verticeEdificio.colocarPieza(pieza);

        assertFalse(verticeEdificio.estaDisponible());
    }

    @Test
    public void verticeNoDisponiblePorTenerPiezaAdyacente() {
        VerticeEdificio vertice1 = new VerticeEdificio(1);
        VerticeEdificio vertice2 = new VerticeEdificio(2);
        Pieza pieza = new Poblado(new Jugador("Luis"));

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        vertice1.colocarPieza(pieza);

        assertFalse(vertice2.estaDisponible());
    }

    @Test
    public void verticeAgregaUnTerrenoAdyacenteCorrectamente() {
        VerticeEdificio vertice1 = new VerticeEdificio(1);
        VerticeTerreno vertice2 = new VerticeTerreno('A',new Bosque(8));

        vertice1.agregarVerticeAdyacente(vertice2);

        assertTrue(vertice1.hayTerrenoAdyacente(vertice2));
    }

}
