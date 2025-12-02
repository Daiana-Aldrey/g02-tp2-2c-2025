package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.*;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.Excepciones.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class JuegoTest {
	
	@Test
	public void juegoInicializaJugadoresCorrectamente() {
	    Tablero.getInstance().reset();

	    Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

	    Juego juego = new Juego(jugadores);

	    Assertions.assertEquals(3,juego.cantidadJugadores());
	}

	@Test
	public void juegoLanzaExcepcionSiHayMenosDeTresJugadores() {
	    Tablero.getInstance().reset();

	    Jugador jugador1 = new Jugador("Valen");
	    Jugador jugador2 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2);

	    assertThrows(CantJugadoresInvalida.class, () -> new Juego(jugadores));
	}

	@Test
	public void juegoLanzaExcepcionSiHayMasDeCuatroJugadores() {
	    Tablero.getInstance().reset();

	    Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    Jugador jugador4 = new Jugador("Mati");
	    Jugador jugador5 = new Jugador("Fran");

	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3, jugador4, jugador5);
	    assertThrows(CantJugadoresInvalida.class, () -> new Juego(jugadores));
	}
  
    @Test
    public void colocacionCorrectaDePobladosIniciales() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

	    Juego juego = new Juego(jugadores);
        List<List<Ubicacion>> verticesPoblados = List.of(
                List.of(new UbicacionVertice(10)),
                List.of(new UbicacionVertice(41)),
                List.of(new UbicacionVertice(25))
        );

        List<List<Ubicacion>> verticesCaminos = List.of(
                List.of(new UbicacionVertice(10),new UbicacionVertice(11)),
                List.of(new UbicacionVertice(41),new UbicacionVertice(42)),
                List.of(new UbicacionVertice(25),new UbicacionVertice(36))
        );

        juego.inicializarPiezas(verticesPoblados, verticesCaminos);

        Assertions.assertTrue(tablero.hayEdificio(new UbicacionVertice(10)));
        Assertions.assertTrue(tablero.hayEdificio(new UbicacionVertice(41)));
        Assertions.assertTrue(tablero.hayEdificio(new UbicacionVertice(25)));
    }

    @Test
    public void jugadorRecibeRecursoDelTerrenoAdyacenteAlSegundoPoblado() {
        Tablero tablero = Tablero.getInstance();

        GeneradorDeTablero generadorTablero= new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        Grafo grafo = new Grafo();
        generadorTablero.generarEstructura(grafo);
        UbicacionVertice ubicacion1 = new UbicacionVertice('A');
        Vertice vertice1 = new VerticeTerreno(ubicacion1, new Montania(), 8);
        grafo.agregarVertice(vertice1);

        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        grafo.agregarArista(ubicacion10, ubicacion1);

        tablero.setearGrafo(grafo);


        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

	    Juego juego = new Juego(jugadores);

        Jugador jugador = juego.jugadores().get(0);
        jugador.recibirRecurso(new Madera(), 10);
        jugador.recibirRecurso(new Ladrillo(), 10);
        jugador.recibirRecurso(new Lana(), 10);
        jugador.recibirRecurso(new Grano(), 10);

        jugador.colocarPiezaInicial("poblado", List.of(ubicacion10));

        Dados dados =  new Dados(2);
        int tirada = dados.tiradaFalsa(8);  
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(new Mineral());
        assertEquals(1, mineral.cantidad());
    }

    @Test
    public void jugadoresRecibenRecursosPorSusSegundosPoblados() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacion1 = new UbicacionVertice('A');
        Vertice vertice1 = new VerticeTerreno(ubicacion1, new Montania(), 8);
        grafo.agregarVertice(vertice1);

        UbicacionVertice ubicacion2 = new UbicacionVertice('B');
        Vertice vertice2 = new VerticeTerreno(ubicacion2, new Montania(), 8);
        grafo.agregarVertice(vertice2);

        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        UbicacionVertice ubicacion40 = new UbicacionVertice(40);
        grafo.agregarArista(ubicacion10, ubicacion1);
        grafo.agregarArista(ubicacion40,ubicacion2);

        tablero.setearGrafo(grafo);

        Jugador jugador1 = new Jugador("Juli");
        Jugador jugador2 = new Jugador("Valen");
        Jugador jugador3 = new Jugador("Sofi");
        List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

        Juego juego = new Juego(jugadores);

        for (Jugador j : juego.jugadores()) {
            j.recibirRecurso(new Madera(),   10);
            j.recibirRecurso(new Ladrillo(), 10);
            j.recibirRecurso(new Lana(),     10);
            j.recibirRecurso(new Grano(),    10);
        }

        jugador1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(1)));
        jugador1.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(1), new UbicacionVertice(2)));
        jugador1.colocarPiezaInicial("poblado", List.of(ubicacion10));

        jugador2.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(54)));
        jugador2.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(54), new UbicacionVertice(53)));
        jugador2.colocarPiezaInicial("poblado", List.of(ubicacion40));


        jugador3.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(6)));
        jugador3.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(6), new UbicacionVertice(7)));
        jugador3.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(36)));

        Dados dados =  new Dados(2);
        int tirada = dados.tiradaFalsa(8);
        juego.manejarTirada(tirada);

        Recurso mineralJ1 = jugador1.buscarRecurso(new Mineral());
        Recurso mineralJ2 = jugador2.buscarRecurso(new Mineral());

        assertEquals(1, mineralJ1.cantidad());
        assertEquals(1, mineralJ2.cantidad());

      
        Recurso mineralJ3 = jugador3.buscarRecurso(new Mineral());
        assertTrue(mineralJ3.cantidad() == 0);
    }

    @Test
    public void testColocacionInicialOrdenCorrecto() {
        Jugador j1 = mock(Jugador.class);
        Jugador j2 = mock(Jugador.class);
        Jugador j3 = mock(Jugador.class);

        List<Jugador> jugadores = List.of(j1, j2, j3);
        Juego juego = new Juego(jugadores);

        List<List<Ubicacion>> pobladosR1 = List.of(
                List.of(new UbicacionVertice(1)),
                List.of(new UbicacionVertice(2)),
                List.of(new UbicacionVertice(3))
        );

        List<List<Ubicacion>> caminosR1 = List.of(
                List.of(new UbicacionVertice(10)),
                List.of(new UbicacionVertice(20)),
                List.of(new UbicacionVertice(30))
        );

        List<List<Ubicacion>> pobladosR2 = List.of(
                List.of(new UbicacionVertice(4)),
                List.of(new UbicacionVertice(5)),
                List.of(new UbicacionVertice(6))
        );

        List<List<Ubicacion>> caminosR2 = List.of(
                List.of(new UbicacionVertice(40)),
                List.of(new UbicacionVertice(50)),
                List.of(new UbicacionVertice(60))
        );

        juego.colocacionInicial(pobladosR1, caminosR1, pobladosR2, caminosR2);
        InOrder orden = inOrder(j1, j2, j3);


        orden.verify(j1).colocarPiezaInicial("poblado", pobladosR1.get(0));
        orden.verify(j1).colocarPiezaInicial("camino", caminosR1.get(0));

        orden.verify(j2).colocarPiezaInicial("poblado", pobladosR1.get(1));
        orden.verify(j2).colocarPiezaInicial("camino", caminosR1.get(1));

        orden.verify(j3).colocarPiezaInicial("poblado", pobladosR1.get(2));
        orden.verify(j3).colocarPiezaInicial("camino", caminosR1.get(2));



        orden.verify(j3).colocarPiezaInicial("poblado", pobladosR2.get(2));
        orden.verify(j3).colocarPiezaInicial("camino", caminosR2.get(2));

        orden.verify(j2).colocarPiezaInicial("poblado", pobladosR2.get(1));
        orden.verify(j2).colocarPiezaInicial("camino", caminosR2.get(1));

        orden.verify(j1).colocarPiezaInicial("poblado", pobladosR2.get(0));
        orden.verify(j1).colocarPiezaInicial("camino", caminosR2.get(0));
    }

}


