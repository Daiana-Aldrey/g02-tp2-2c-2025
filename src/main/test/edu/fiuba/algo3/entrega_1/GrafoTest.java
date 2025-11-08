package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Grafo;
import edu.fiuba.algo3.modelo.Vertice;
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
        Vertice verticeBuscado = new Vertice(2);

        grafo.agregarVertice(1);
        grafo.agregarVertice(verticeBuscado);

        Vertice encontrado = grafo.buscarVertice(2);
        Assertions.assertEquals(verticeBuscado, encontrado);
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

        assertTrue(grafo.hayArista(1,2));
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
}
