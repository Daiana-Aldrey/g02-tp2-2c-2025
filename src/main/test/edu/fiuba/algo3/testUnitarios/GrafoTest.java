package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.*;

import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class GrafoTest {
    @Mock
    private UbicacionVertice ubicacion;

    @Test
    public void seAgregaVerticeCorrectamente() {
        Grafo grafo = new Grafo();
        Vertice vertice = new VerticeEdificio(ubicacion);

        grafo.agregarVertice(vertice);

        assertTrue(grafo.contieneVertice(vertice));
    }

    @Test
    public void grafoEncuentraElVerticeBuscado() {
        Grafo grafo = new Grafo();
        VerticeEdificio verticeEdificioBuscado = new VerticeEdificio(new  UbicacionVertice('1'));

        grafo.agregarVertice(verticeEdificioBuscado);

        Vertice encontrado = grafo.buscarVertice(new UbicacionVertice('1'));
        Assertions.assertEquals(verticeEdificioBuscado, encontrado);
    }

    @Test
    public void grafoNoEncuentraElVerticeYLanzaUnaExcepcion() {
        Grafo grafo = new Grafo();
        Vertice vertice1 = new VerticeEdificio(new UbicacionVertice('1'));

        grafo.agregarVertice(vertice1);

        assertThrows(RuntimeException.class, () -> {
            grafo.buscarVertice(new UbicacionVertice('3'));
        });
    }

    @Test
    public void seAgregaAristaCorrectamente() {
        Grafo grafo = new Grafo();
        UbicacionVertice ubicacion1 = new UbicacionVertice('1');
        UbicacionVertice ubicacion2 = new UbicacionVertice('2');
        Vertice vertice1 = new VerticeEdificio(ubicacion1);
        Vertice vertice2 = new VerticeEdificio(ubicacion2);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);
        grafo.agregarArista(ubicacion1,ubicacion2);

        assertTrue(grafo.hayArista(vertice1, vertice2));
    }

    @Test
    public void seIntentaAgregarAristaRepetidaYLanzaExcepcion() {
        Grafo grafo = new Grafo();
        UbicacionVertice ubicacion1 = new UbicacionVertice('1');
        UbicacionVertice ubicacion2 = new UbicacionVertice('2');
        Vertice vertice1 = new VerticeEdificio(ubicacion1);
        Vertice vertice2 = new VerticeEdificio(ubicacion2);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);

        grafo.agregarArista(ubicacion1, ubicacion2);

        assertThrows(RuntimeException.class, () -> {
            grafo.agregarArista(ubicacion2, ubicacion1);
        });
    }

    @Test
    public void seIntentaAgregarPiezaEnUnVerticeNoDisponibleYLanzaExcepcion() {
        Grafo grafo = new Grafo();
        Pieza pieza = new Poblado(mock(Jugador.class));

        grafo.agregarVertice(new VerticeEdificio(new UbicacionVertice('1')));

        grafo.colocarPieza(new UbicacionVertice('1'),pieza);

        assertThrows(IllegalArgumentException.class, () -> {
            grafo.colocarPieza(new UbicacionVertice('1'),pieza);
        });
    }

    @Test
    public void grafoEncuentraElVerticeTerrenoBuscado() {
        Grafo grafo = new Grafo();
        Vertice buscado = new VerticeTerreno(new UbicacionVertice('A'), new Bosque(),9);

        grafo.agregarVertice(buscado);

        Vertice encontrado = grafo.buscarVertice(new UbicacionVertice('A'));

        Assertions.assertEquals(buscado, encontrado);
    }

    @Test
    public void grafoEncuentraLaAristaBuscada() {
        Grafo grafo = new Grafo();
        UbicacionVertice ubicacion1 = new UbicacionVertice('1');
        UbicacionVertice ubicacion2 = new UbicacionVertice('2');
        Vertice vertice1 = new VerticeEdificio(ubicacion1);
        Vertice vertice2 = new VerticeEdificio(ubicacion2);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);


        Arista aristaBuscada = new Arista(ubicacion1, ubicacion2);
        grafo.agregarArista(aristaBuscada);

        Arista aristaEncontrada = grafo.buscarArista(ubicacion2, ubicacion1);

        Assertions.assertEquals(aristaBuscada, aristaEncontrada);
    }

    @Test
    public void seIntentaPonerUnCaminoEnUnaAristaYaOcupadaYLanzaExcepcion() {
        Grafo grafo = new Grafo();
        UbicacionVertice ubicacion1 = new UbicacionVertice('1');
        UbicacionVertice ubicacion2 = new UbicacionVertice('2');
        Vertice vertice1 = new VerticeEdificio(ubicacion1);
        Vertice vertice2 = new VerticeEdificio(ubicacion2);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);
        grafo.agregarArista(ubicacion1, ubicacion2);

        Jugador luis = new Jugador("Luis");

        grafo.colocarCamino(ubicacion1, ubicacion2, new Camino(luis));

        assertThrows(IllegalArgumentException.class, () -> {
            grafo.colocarCamino(ubicacion2, ubicacion1,new Camino(luis));
        });
    }

    @Test
    public void seIntentaPonerUnCaminodoDondeNoHayunaPiezaDelMismoJugadorYLanzaExcepcion() {
        Grafo grafo = new Grafo();

        UbicacionVertice ubicacion1 = new UbicacionVertice('1');
        UbicacionVertice ubicacion2 = new UbicacionVertice('2');
        UbicacionVertice ubicacion3 = new UbicacionVertice('3');
        Vertice vertice1 = new VerticeEdificio(ubicacion1);
        Vertice vertice2 = new VerticeEdificio(ubicacion2);
        Vertice vertice3 = new VerticeEdificio(ubicacion3);

        grafo.agregarVertice(vertice1);
        grafo.agregarVertice(vertice2);
        grafo.agregarVertice(vertice3);
        grafo.agregarArista(ubicacion1, ubicacion2);
        grafo.agregarArista(ubicacion2, ubicacion3);

        Jugador luis = new Jugador("Luis");

        grafo.colocarPieza(ubicacion1, new Poblado(luis));
        grafo.colocarPieza(ubicacion3, new Poblado(luis));

        assertThrows(IllegalArgumentException.class, () -> {
            grafo.colocarCamino(ubicacion1, ubicacion2, new Camino(new Jugador("Federico")));
        });
    }
}
