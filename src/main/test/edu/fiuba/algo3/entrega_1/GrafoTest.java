package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        VerticeTerreno buscado = new VerticeTerreno('A', new Bosque(9));

        grafo.agregarVertice(buscado);

        VerticeTerreno encontrado = grafo.buscarVertice('A');

        Assertions.assertEquals(buscado, encontrado);
    }

    @Test
    public void grafoEncuentraLaAristaBuscada() {
        Grafo grafo = new Grafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);

        Arista aristaBuscada = new Arista(1,2);

        grafo.agregarArista(aristaBuscada);

        List<Integer> vertices = new ArrayList<>();
        vertices.add(1);
        vertices.add(2);

        Arista aristaEncontrada = grafo.buscarArista(vertices);

        Assertions.assertEquals(aristaBuscada, aristaEncontrada);
    }

    @Test
    public void seIntentaPonerUnCaminoEnUnaAristaYaOcupadaYLanzaExcepcion() {
        Grafo grafo = new Grafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarArista(1,2);

        Jugador luis = new Jugador("Luis");

        grafo.buscarVertice(1).colocarPieza(new Poblado(luis));
        List<Integer> vertices = new ArrayList<>();
        vertices.add(1);
        vertices.add(2);

        grafo.colocarCamino(vertices,new Camino(luis));
        assertThrows(IllegalArgumentException.class, () -> {
            grafo.colocarCamino(vertices,new Camino(luis));
        });
    }

    @Test
    public void seIntentaPonerUnCaminodoDondeNoHayunaPiezaDelMismoJugadorYLanzaExcepcion() {
        Grafo grafo = new Grafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarArista(1,2);

        Jugador luis = new Jugador("Luis");

        grafo.buscarVertice(1).colocarPieza(new Poblado(luis));
        grafo.buscarVertice(2).colocarPieza(new Poblado(luis));
        List<Integer> vertices = new ArrayList<>();
        vertices.add(1);
        vertices.add(2);

        assertThrows(IllegalArgumentException.class, () -> {
            grafo.colocarCamino(vertices,new Camino(new Jugador("Federico")));
        });
    }
}
