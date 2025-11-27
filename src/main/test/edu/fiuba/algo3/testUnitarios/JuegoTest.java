package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.*;

import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;



public class JuegoTest {
	
	@Test
	public void juegoInicializaJugadoresCorrectamente() {
	    Tablero.getInstance().reset();
	    
	    GeneradorDeDados dadoPrueba = () -> 7;

	    List<String> nombres = List.of("Juli", "Valen", "Sofi");
	    Juego juego = new Juego(3, nombres, dadoPrueba);

	    Assertions.assertEquals(3, juego.cantidadJugadores());
	}

	@Test
	public void juegoLanzaExcepcionSiHayMenosDeTresJugadores() {
	    Tablero.getInstance().reset();

	    List<String> nombres = List.of("A", "B");
	    GeneradorDeDados dadoPrueba = () -> 7;

	    assertThrows(IllegalArgumentException.class, 
	        () -> new Juego(2, nombres, dadoPrueba)
	    );
	}

	@Test
	public void juegoLanzaExcepcionSiHayMasDeCuatroJugadores() {
	    Tablero.getInstance().reset();

	    List<String> nombres = List.of("A", "B", "C", "D", "E");

	    assertThrows(IllegalArgumentException.class, 
	        () -> new Juego(5, nombres, null)
	    );
	}
  
    @Test
    public void colocacionCorrectaDePobladosIniciales() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        GeneradorDeDados dadoPrueba = new GeneradorDeDados() {
            @Override
            public int tirar() {
                return 7;
            }
        };

        Juego juego = new Juego(3, List.of("Luis","Ana","Maria"), dadoPrueba);
        
        List<List<UbicacionVertice>> verticesPoblados = List.of(
                List.of(new UbicacionVertice(10)),
                List.of(new UbicacionVertice(41)),
                List.of(new UbicacionVertice(25))
        );

        List<List<UbicacionVertice>> verticesCaminos = List.of(
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

        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis","Ana","Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);
        jugador.recibirRecurso(RecursoTipo.MADERA, 10);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 10);
        jugador.recibirRecurso(RecursoTipo.LANA, 10);
        jugador.recibirRecurso(RecursoTipo.GRANO, 10);

        jugador.construirPieza("poblado", List.of(ubicacion10));

        int tirada = juego.tirarDado();  
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
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

        GeneradorDeDados dado = () -> 8;
        
        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador j1 = juego.jugadores().get(0);
        Jugador j2 = juego.jugadores().get(1);
        Jugador j3 = juego.jugadores().get(2);

        for (Jugador j : juego.jugadores()) {
            j.recibirRecurso(RecursoTipo.MADERA,   10);
            j.recibirRecurso(RecursoTipo.LADRILLO, 10);
            j.recibirRecurso(RecursoTipo.LANA,     10);
            j.recibirRecurso(RecursoTipo.GRANO,    10);
        }

        j1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(1)));
        j1.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(1), new UbicacionVertice(2)));
        j1.construirPieza("poblado", List.of(ubicacion10));

        j2.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(54)));
        j2.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(54), new UbicacionVertice(53)));
        j2.construirPieza("poblado", List.of(ubicacion40));


        j3.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(6)));
        j3.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(6), new UbicacionVertice(7)));
        j3.construirPieza("poblado", List.of(new UbicacionVertice(36)));

        int tirada = juego.tirarDado(); 
        juego.manejarTirada(tirada);

        Recurso mineralJ1 = j1.buscarRecurso(RecursoTipo.MINERAL);
        Recurso mineralJ2 = j2.buscarRecurso(RecursoTipo.MINERAL);

        assertEquals(1, mineralJ1.cantidad());
        assertEquals(1, mineralJ2.cantidad());

      
        Recurso mineralJ3 = j3.buscarRecurso(RecursoTipo.MINERAL);
        assertTrue(mineralJ3.cantidad() == 0);
    }
    
    
    
}


