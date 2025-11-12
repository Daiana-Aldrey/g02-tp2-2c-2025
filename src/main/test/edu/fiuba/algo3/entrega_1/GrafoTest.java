package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrafoTest {
    @Test
    public void seAgregaVerticeCorrectamente() {
        Grafo grafo = new Grafo();

        grafo.agregarVertice(1);

        assertTrue(grafo.contieneVertice(1));
    }

    @Test
    public void grafoEncuentraElVerticeBuscado() {
        Grafo grafo = new Grafo();
        VerticeEdificio verticeEdificioBuscado = new VerticeEdificio(2);

        grafo.agregarVertice(verticeEdificioBuscado);

        VerticeEdificio encontrado = grafo.buscarVertice(2);
        Assertions.assertEquals(verticeEdificioBuscado, encontrado);
    }

    @Test
    public void grafoNoEncuentraElVerticeYLanzaUnaExcepcion() {
        Grafo grafo = new Grafo();

        grafo.agregarVertice(1);
        grafo.agregarVertice(2);

        assertThrows(RuntimeException.class, () -> {
            grafo.buscarVertice(3);
        });
    }

    @Test
    public void seAgregaAristaCorrectamente() {
        Grafo grafo = new Grafo();

        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarArista(1,2);

        VerticeEdificio vertice1 = grafo.buscarVertice(1);
        VerticeEdificio vertice2 = grafo.buscarVertice(2);

        assertTrue(grafo.hayArista(vertice1, vertice2));
    }

    @Test
    public void seIntentaAgregarAristaRepetidaYLanzaExcepcion() {
        Grafo grafo = new Grafo();

        grafo.agregarVertice(1);
        grafo.agregarVertice(2);

        grafo.agregarArista(1,2);

        assertThrows(RuntimeException.class, () -> {
            grafo.agregarArista(2,1);
        });
    }

    @Test
    public void seIntentaAgregarPiezaEnUnVerticeNoDisponibleYLanzaExcepcion() {
        Grafo grafo = new Grafo();
        Pieza pieza = new Poblado(new Jugador("Luis"));
        grafo.agregarVertice(1);

        grafo.colocarPieza(1,pieza);

        assertThrows(IllegalArgumentException.class, () -> {
            grafo.colocarPieza(1,pieza);
        });
    }

    @Test
    public void grafoEncuentraElVerticeTerrenoBuscado() {
        Grafo grafo = new Grafo();
        VerticeTerreno buscado = new VerticeTerreno('A', new Terreno(9));

        grafo.agregarVertice(buscado);

        VerticeTerreno encontrado = grafo.buscarVertice('A');

        Assertions.assertEquals(buscado, encontrado);
    }
}
