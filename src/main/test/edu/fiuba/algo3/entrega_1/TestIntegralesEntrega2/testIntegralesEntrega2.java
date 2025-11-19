package edu.fiuba.algo3.entrega_1.TestIntegralesEntrega2;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class testIntegralesEntrega2 {

    @Test
    public void validacionDelConsumoDeRecursosYLaCorrectaColocacionDeUnaCarretera() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador marcelo = new Jugador("Marcelo");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));

        marcelo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(18)));
        marcelo.colocarPiezaInicial("camino", new ArrayList<>(List.of(18, 17)));

        luis.recibirRecurso(RecursoTipo.MADERA, 10);
        luis.recibirRecurso(RecursoTipo.LADRILLO, 10);
        luis.recibirRecurso(RecursoTipo.LANA, 10);
        luis.recibirRecurso(RecursoTipo.GRANO, 10);

        marcelo.recibirRecurso(RecursoTipo.MADERA, 2);
        marcelo.recibirRecurso(RecursoTipo.LADRILLO, 2);
        marcelo.recibirRecurso(RecursoTipo.LANA, 2);
        marcelo.recibirRecurso(RecursoTipo.GRANO, 2);

        luis.construirPieza("camino", new ArrayList<>(List.of(1, 9)));
        marcelo.construirPieza("camino", new ArrayList<>(List.of(18, 19)));

        Assertions.assertTrue(tablero.hayPieza(List.of(1, 9)));
        Assertions.assertTrue(tablero.hayPieza(List.of(18, 19)));

        assertEquals(9, luis.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(10, luis.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(10, luis.buscarRecurso(RecursoTipo.GRANO).cantidad());

        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(2, marcelo.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(2, marcelo.buscarRecurso(RecursoTipo.GRANO).cantidad());

    }

    @Test
    public void validacionDelConsumoDeRecursosYValidacionDeLaReglaDeLaDistanciaAlConstruirPoblado() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador marcelo = new Jugador("Marcelo");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));

        marcelo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(18)));
        marcelo.colocarPiezaInicial("camino", new ArrayList<>(List.of(18, 17)));

        luis.recibirRecurso(RecursoTipo.MADERA, 10);
        luis.recibirRecurso(RecursoTipo.LADRILLO, 10);
        luis.recibirRecurso(RecursoTipo.LANA, 10);
        luis.recibirRecurso(RecursoTipo.GRANO, 10);

        marcelo.recibirRecurso(RecursoTipo.MADERA, 2);
        marcelo.recibirRecurso(RecursoTipo.LADRILLO, 2);
        marcelo.recibirRecurso(RecursoTipo.LANA, 2);
        marcelo.recibirRecurso(RecursoTipo.GRANO, 2);

        luis.construirPieza("poblado", new ArrayList<>(List.of(4)));
        marcelo.construirPieza("poblado", new ArrayList<>(List.of(20)));

        Assertions.assertTrue(tablero.hayPieza(List.of(4)));
        Assertions.assertTrue(tablero.hayPieza(List.of(20)));

        assertEquals(9, luis.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.GRANO).cantidad());

        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.GRANO).cantidad());

        assertThrows(IllegalArgumentException.class, () -> {
            luis.construirPieza("poblado", new ArrayList<>(List.of(21)));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            marcelo.construirPieza("poblado", new ArrayList<>(List.of(5)));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            luis.construirPieza("poblado", new ArrayList<>(List.of(1)));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            marcelo.construirPieza("poblado", new ArrayList<>(List.of(3)));;
        });
    }

    @Test
    public void mejorarPobladoACiudadConsumeRecursosYCambiaPV(){
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        grafo.agregarVertice('A', new Bosque(), 8);
        grafo.agregarArista(10,'A');
        tablero.setearGrafo(grafo);
        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);

        assertEquals(0, jugador.puntosDeVictoria());

        jugador.colocarPiezaInicial("poblado", List.of(10));
        assertEquals(1, jugador.puntosDeVictoria());

        jugador.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador.recibirRecurso(RecursoTipo.MINERAL, 3);

        jugador.construirPieza("ciudad", List.of(10));
        assertEquals(2, jugador.puntosDeVictoria());

        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        Recurso grano = jugador.buscarRecurso(RecursoTipo.GRANO);
        assertEquals(0, mineral.cantidad());
        assertEquals(0, grano.cantidad());


        jugador.colocarPiezaInicial("poblado", List.of(18));
        assertEquals(3, jugador.puntosDeVictoria());
        jugador.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador.recibirRecurso(RecursoTipo.MINERAL, 3);
        jugador.construirPieza("ciudad", List.of(18));
        assertEquals(4, jugador.puntosDeVictoria());
    }
    
    @Test
    public void intercambioEntreJugadoresTransfiereRecursosCorrectamente() {
    	Jugador ofertante = new Jugador("Ofertante");
        Jugador receptor  = new Jugador("Receptor");

        ofertante.recibirRecurso(RecursoTipo.MADERA, 5);
        ofertante.recibirRecurso(RecursoTipo.LANA, 3);
        receptor.recibirRecurso(RecursoTipo.LADRILLO, 4);
        receptor.recibirRecurso(RecursoTipo.MINERAL, 2);

        List<Recurso> pedidos = List.of(
                new Recurso(RecursoTipo.LADRILLO, 1),
                new Recurso(RecursoTipo.MINERAL, 1)
        );

        List<Recurso> ofertas = List.of(
                new Recurso(RecursoTipo.MADERA, 2),
                new Recurso(RecursoTipo.LANA, 1)
        );

        int maderaOfAntes   = ofertante.buscarRecurso(RecursoTipo.MADERA).cantidad();
        int lanaOfAntes     = ofertante.buscarRecurso(RecursoTipo.LANA).cantidad();
        int ladrilloOfAntes = ofertante.buscarRecurso(RecursoTipo.LADRILLO).cantidad();
        int mineralOfAntes  = ofertante.buscarRecurso(RecursoTipo.MINERAL).cantidad();

        int maderaRecAntes   = receptor.buscarRecurso(RecursoTipo.MADERA).cantidad();
        int lanaRecAntes     = receptor.buscarRecurso(RecursoTipo.LANA).cantidad();
        int ladrilloRecAntes = receptor.buscarRecurso(RecursoTipo.LADRILLO).cantidad();
        int mineralRecAntes  = receptor.buscarRecurso(RecursoTipo.MINERAL).cantidad();

        receptor.intercambiar(pedidos, ofertas, ofertante);

        assertEquals(ladrilloRecAntes - 1, receptor.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(mineralRecAntes - 1,receptor.buscarRecurso(RecursoTipo.MINERAL).cantidad());
        assertEquals(ladrilloOfAntes + 1, ofertante.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(mineralOfAntes + 1, ofertante.buscarRecurso(RecursoTipo.MINERAL).cantidad());

        assertEquals(maderaOfAntes - 2, ofertante.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(lanaOfAntes - 1, ofertante.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(maderaRecAntes + 2, receptor.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(lanaRecAntes + 1, receptor.buscarRecurso(RecursoTipo.LANA).cantidad());
    }

}

