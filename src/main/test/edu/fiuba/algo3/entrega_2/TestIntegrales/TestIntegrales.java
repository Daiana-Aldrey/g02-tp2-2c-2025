package edu.fiuba.algo3.entrega_2.TestIntegrales;
import edu.fiuba.algo3.Excepciones.ErrorNoUsoDeCartaInvalido;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.CartaDeDesarrollo.*;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.*;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestIntegrales {

    @Test
    public void validacionDelConsumoDeRecursosYLaCorrectaColocacionDeUnaCarretera() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador marcelo = new Jugador("Marcelo");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(1))));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(1), new UbicacionVertice(2))));

        marcelo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(18))));
        marcelo.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(18), new UbicacionVertice(17))));

        luis.recibirRecurso(RecursoTipo.MADERA, 10);
        luis.recibirRecurso(RecursoTipo.LADRILLO, 10);
        luis.recibirRecurso(RecursoTipo.LANA, 10);
        luis.recibirRecurso(RecursoTipo.GRANO, 10);

        marcelo.recibirRecurso(RecursoTipo.MADERA, 2);
        marcelo.recibirRecurso(RecursoTipo.LADRILLO, 2);
        marcelo.recibirRecurso(RecursoTipo.LANA, 2);
        marcelo.recibirRecurso(RecursoTipo.GRANO, 2);

        luis.construirPieza("camino", new ArrayList<>(List.of(new UbicacionVertice(1), new UbicacionVertice(9))));
        marcelo.construirPieza("camino", new ArrayList<>(List.of(new UbicacionVertice(18), new UbicacionVertice(19))));

        Assertions.assertTrue(tablero.hayCamino(new UbicacionVertice(1), new UbicacionVertice(9)));
        Assertions.assertTrue(tablero.hayCamino(new UbicacionVertice(18), new UbicacionVertice(19)));

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

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(2))));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(2), new UbicacionVertice(3))));

        marcelo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(17))));
        marcelo.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(18), new UbicacionVertice(17))));

        luis.recibirRecurso(RecursoTipo.MADERA, 11);
        luis.recibirRecurso(RecursoTipo.LADRILLO, 11);
        luis.recibirRecurso(RecursoTipo.LANA, 10);
        luis.recibirRecurso(RecursoTipo.GRANO, 10);

        marcelo.recibirRecurso(RecursoTipo.MADERA, 3);
        marcelo.recibirRecurso(RecursoTipo.LADRILLO, 3);
        marcelo.recibirRecurso(RecursoTipo.LANA, 2);
        marcelo.recibirRecurso(RecursoTipo.GRANO, 2);

        luis.construirPieza("camino", new ArrayList<>(List.of(new UbicacionVertice(3), new UbicacionVertice(4))));
        luis.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(4))));
        marcelo.construirPieza("camino", new ArrayList<>(List.of(new UbicacionVertice(18), new UbicacionVertice(19))));
        marcelo.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(19))));

        Assertions.assertTrue(tablero.hayEdificio(new UbicacionVertice(4)));
        Assertions.assertTrue(tablero.hayEdificio(new UbicacionVertice(19)));

        assertEquals(9, luis.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.GRANO).cantidad());

        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.GRANO).cantidad());

        assertThrows(IllegalArgumentException.class, () -> {
            luis.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(19))));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            marcelo.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(5))));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            luis.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(1))));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            marcelo.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(3))));;
        });
    }

    @Test
    public void mejorarPobladoACiudadConsumeRecursosYCambiaPV(){
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacionA = new UbicacionVertice('A');
        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        Vertice verticeTerreno = new VerticeTerreno(ubicacionA, new Bosque(), 8);
        grafo.agregarVertice(verticeTerreno);
        grafo.agregarArista(ubicacion10,ubicacionA);
        tablero.setearGrafo(grafo);
        GeneradorDeDados dado = () -> 8;

        Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");
 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
 	    Juego juego = new Juego(jugadores, dado);

        assertEquals(0, jugador1.puntosDeVictoria());

        jugador1.colocarPiezaInicial("poblado", List.of(ubicacion10));
        assertEquals(1, jugador1.puntosDeVictoria());

        jugador1.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador1.recibirRecurso(RecursoTipo.MINERAL, 3);

        jugador1.construirPieza("ciudad", List.of(ubicacion10));
        assertEquals(2, jugador1.puntosDeVictoria());

        Recurso mineral = jugador1.buscarRecurso(RecursoTipo.MINERAL);
        Recurso grano = jugador1.buscarRecurso(RecursoTipo.GRANO);
        assertEquals(0, mineral.cantidad());
        assertEquals(0, grano.cantidad());


        jugador1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(18)));
        assertEquals(3, jugador1.puntosDeVictoria());
        jugador1.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador1.recibirRecurso(RecursoTipo.MINERAL, 3);
        jugador1.construirPieza("ciudad", List.of(new UbicacionVertice(18)));
        assertEquals(4, jugador1.puntosDeVictoria());
    }

    @Test
    public void comercioMaritimoAplicaTasaEstandarCuatroAUno() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacionA = new UbicacionVertice('A');
        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        Vertice verticeTerreno = new VerticeTerreno(ubicacionA, new Bosque(), 8);
        grafo.agregarVertice(verticeTerreno);
        grafo.agregarArista(ubicacion10,ubicacionA);
        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");
 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
 	    Juego juego = new Juego(jugadores, dado);

        jugador2.recibirRecurso(RecursoTipo.MADERA, 4);

        Banco banco = new Banco();
        banco.comerciar(jugador2, RecursoTipo.MADERA, RecursoTipo.GRANO, 1);

        Recurso madera = jugador2.buscarRecurso(RecursoTipo.MADERA);
        assertEquals(0, madera.cantidad());

        Recurso grano = jugador2.buscarRecurso(RecursoTipo.GRANO);
        assertEquals(1, grano.cantidad());
    }

    @Test
    public void comercioMaritimoAplicaTasaTresAUnoConPuertoGenerico() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacionA = new UbicacionVertice('A');
        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        Vertice verticeTerreno = new VerticeTerreno(ubicacionA, new Bosque(), 8);
        grafo.agregarVertice(verticeTerreno);
        grafo.agregarArista(ubicacion10,ubicacionA);

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");
 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
 	    Juego juego = new Juego(jugadores, dado);

        jugador3.agregarPuerto(new PuertoGenerico());
        jugador3.recibirRecurso(RecursoTipo.MADERA, 3);

        Banco banco = new Banco();
        banco.comerciar(jugador3, RecursoTipo.MADERA, RecursoTipo.LANA, 1);

        assertEquals(0, jugador3.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(1, jugador3.buscarRecurso(RecursoTipo.LANA).cantidad());
    }

    @Test
    public void comercioMaritimoAplicaTasaDosAUnoConPuertoEspecifico(){
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacionA = new UbicacionVertice('A');
        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        Vertice verticeTerreno = new VerticeTerreno(ubicacionA, new Bosque(), 8);
        grafo.agregarVertice(verticeTerreno);
        grafo.agregarArista(ubicacion10,ubicacionA);

        tablero.setearGrafo(grafo);

        Jugador jugador1 = new Jugador("Juli");

        jugador1.agregarPuerto(new PuertoEspecifico(RecursoTipo.MADERA));
        jugador1.recibirRecurso(RecursoTipo.MADERA, 2);

        Banco banco = new Banco();
        banco.comerciar(jugador1,RecursoTipo.MADERA, RecursoTipo.MINERAL,1);

        assertEquals(0, jugador1.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(1, jugador1.buscarRecurso(RecursoTipo.MINERAL).cantidad());
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
    @Test
    void validacionComprarCartaDesarrolloDescuentaRecursosAgregaCartaAlJugador() {
        GeneradorDeDados dado = () -> 8;
        Jugador jugador1 = new Jugador("Juli");
  	    Jugador jugador2 = new Jugador("Valen");
  	    Jugador jugador3 = new Jugador("Sofi");
  	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
  	    Juego juego = new Juego(jugadores, dado);

        jugador1.recibirRecurso(RecursoTipo.LANA, 1);
        jugador1.recibirRecurso(RecursoTipo.GRANO, 1);
        jugador1.recibirRecurso(RecursoTipo.MINERAL, 1);

        int lanaAntes = jugador1.buscarRecurso(RecursoTipo.LANA).cantidad();
        int granoAntes = jugador1.buscarRecurso(RecursoTipo.GRANO).cantidad();
        int mineralAntes = jugador1.buscarRecurso(RecursoTipo.MINERAL).cantidad();

        juego.comprarCartaDesarrollo();

        assertEquals(lanaAntes - 1, jugador1.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(granoAntes - 1, jugador1.buscarRecurso(RecursoTipo.GRANO).cantidad());
        assertEquals(mineralAntes - 1, jugador1.buscarRecurso(RecursoTipo.MINERAL).cantidad());

        assertEquals(1, jugador1.obtenerCartasDesarrollo().size(), "El jugador debería tener una carta por que la compro");
    }

    @Test
    void CartaRecienCompradaNoPuedeUsarseEnTurnoActual_SiTrasFinalizarTurno() {
    	 GeneradorDeDados dado = () -> 8;
         Jugador jugador1 = new Jugador("Juli");
   	    Jugador jugador2 = new Jugador("Valen");
   	    Jugador jugador3 = new Jugador("Sofi");
   	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
   	    Juego juego = new Juego(jugadores, dado);


        jugador1.recibirRecurso(RecursoTipo.LANA, 1);
        jugador1.recibirRecurso(RecursoTipo.GRANO, 1);
        jugador1.recibirRecurso(RecursoTipo.MINERAL, 1);

        juego.comprarCartaDesarrollo();

        Carta carta = jugador1.obtenerCartasDesarrollo().get(0);

        assertThrows(ErrorNoUsoDeCartaInvalido.class, () -> jugador1.jugarCartaDesarrollo(carta), "No deberia usar la carta en el mismo turnoque la  compra.");

        juego.finalizarTurnoActual();

        assertDoesNotThrow(() -> jugador1.jugarCartaDesarrollo(carta), "finalizo el turno, podes usar la carta.");

        assertEquals(0, jugador1.obtenerCartasDesarrollo().size(), "usaste tu unica carta no tenes mas");
    }

}

