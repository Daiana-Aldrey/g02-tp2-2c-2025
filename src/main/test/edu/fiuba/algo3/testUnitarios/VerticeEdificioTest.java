package edu.fiuba.algo3.testUnitarios;
import edu.fiuba.algo3.modelo.*;

import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.Bosque;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerticeEdificioTest {

    @Mock
    private UbicacionVertice ubicacionMock;

    @Mock
    private Jugador jugadorMock;

    @Mock
    private Bosque bosqueMock;

    @Test
    public void verticeDeniegaUbicacion() {
        VerticeEdificio vertice = new VerticeEdificio(new UbicacionVertice(1));
        assertFalse(vertice.tieneUbicacion(new UbicacionVertice(2)));
    }

    @Test
    public void verticeVerificaSusVerticesPiezaAdyacentes() {
        VerticeEdificio vertice1 = new VerticeEdificio(ubicacionMock);
        VerticeEdificio vertice2 = new VerticeEdificio(ubicacionMock);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        assertTrue(vertice2.hayVerticeAdyacente(vertice1));

    }

    @Test
    public void verticeNoDisponiblePorEstarOcupadoPorUnPoblado() {
        VerticeEdificio verticeEdificio = new VerticeEdificio(ubicacionMock);
        Pieza pieza = new Poblado(jugadorMock);
        verticeEdificio.colocarPieza(pieza);

        assertFalse(verticeEdificio.estaDisponible());
    }

    @Test
    public void verticeNoDisponiblePorTenerPiezaAdyacente() {
        VerticeEdificio vertice1 = new VerticeEdificio(ubicacionMock);
        VerticeEdificio vertice2 = new VerticeEdificio(ubicacionMock);
        Pieza pieza = new Poblado(jugadorMock);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        vertice1.colocarPieza(pieza);

        assertFalse(vertice2.estaDisponible());
    }

    @Test
    public void verticeAgregaUnTerrenoAdyacenteCorrectamente() {
        VerticeEdificio vertice1 = new VerticeEdificio(ubicacionMock);
        VerticeTerreno vertice2 = new VerticeTerreno(ubicacionMock, bosqueMock,8);

        vertice1.agregarVerticeAdyacente(vertice2);

        assertTrue(vertice1.hayTerrenoAdyacente(vertice2));
    }

    @Test
    public void verticeRemueveCorrectamentePieza() {
        VerticeEdificio vertice1 = new VerticeEdificio(ubicacionMock);
        Pieza pieza = new Poblado(jugadorMock);
        vertice1.colocarPieza(pieza);

        vertice1.removerPieza();

        assertTrue(vertice1.estaDisponible());
    }

}
