package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.*;
import edu.fiuba.algo3.Excepciones.*;
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
	    
	    Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
	    
	    Juego juego = new Juego(jugadores, dadoPrueba);

	    Assertions.assertEquals(3,juego.cantidadJugadores());
	}
	
	@Test
	public void juegoLanzaExcepcionSiHayMenosDeTresJugadores() {
	    Tablero.getInstance().reset();

	    GeneradorDeDados dadoPrueba = () -> 7;

	    Jugador jugador1 = new Jugador("Valen");
	    Jugador jugador2 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2);
	    
	    assertThrows(CantJugadoresInvalida.class, () -> new Juego(jugadores, dadoPrueba));
	}

	@Test
	public void juegoLanzaExcepcionSiHayMasDeCuatroJugadores() {
	    Tablero.getInstance().reset();
	    GeneradorDeDados dadoPrueba = () -> 7;
	    
	    Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    Jugador jugador4 = new Jugador("Mati");
	    Jugador jugador5 = new Jugador("Fran");
	    
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3, jugador4, jugador5);
	    assertThrows(CantJugadoresInvalida.class, () -> new Juego(jugadores, dadoPrueba));
	}
  
    @Test
    public void colocacionCorrectaDePobladosIniciales() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        
        GeneradorDeDados dadoPrueba = () -> 7;

        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
	    
	    Juego juego = new Juego(jugadores, dadoPrueba);
	    
        List<List<Integer>> verticesPoblados = List.of(
                List.of(10),   
                List.of(41),
                List.of(25)
        );

        List<List<Integer>> verticesCaminos = List.of(
                List.of(10,11),
                List.of(41,42),
                List.of(25,36)
        );

        juego.inicializarPiezas(verticesPoblados, verticesCaminos);

        Assertions.assertTrue(tablero.hayPieza(List.of(10)));
        Assertions.assertTrue(tablero.hayPieza(List.of(41)));
        Assertions.assertTrue(tablero.hayPieza(List.of(25)));
    }

    @Test
    public void jugadorRecibeRecursoDelTerrenoAdyacenteAlSegundoPoblado() {
        Tablero tablero = Tablero.getInstance();

        GeneradorDeTablero generadorTablero= new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        Grafo grafo = new Grafo();
        grafo.agregarVertice('A', new Montania(),8);
        generadorTablero.generarEstructura(grafo);
        grafo.agregarArista(10,'A');

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
	    
	    Juego juego = new Juego(jugadores, dado);
	    
        Jugador jugador = juego.jugadores().get(0);
        jugador.recibirRecurso(RecursoTipo.MADERA, 10);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 10);
        jugador.recibirRecurso(RecursoTipo.LANA, 10);
        jugador.recibirRecurso(RecursoTipo.GRANO, 10);

        jugador.construirPieza("poblado", List.of(10));

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
        grafo.agregarVertice('A', new Montania(), 8);
        grafo.agregarVertice('B', new Montania(), 8);
        grafo.agregarArista(10,'A');
        grafo.agregarArista(40,'B');

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;
        
        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
	    
	    Juego juego = new Juego(jugadores, dado);
	   

        for (Jugador j : juego.jugadores()) {
            j.recibirRecurso(RecursoTipo.MADERA,   10);
            j.recibirRecurso(RecursoTipo.LADRILLO, 10);
            j.recibirRecurso(RecursoTipo.LANA,     10);
            j.recibirRecurso(RecursoTipo.GRANO,    10);
        }

        jugador1.colocarPiezaInicial("poblado", List.of(1));
        jugador1.colocarPiezaInicial("camino",  List.of(1, 2));
        jugador1.construirPieza("poblado", List.of(10));  

        jugador2.colocarPiezaInicial("poblado", List.of(54));
        jugador2.colocarPiezaInicial("camino",  List.of(54, 53));
        jugador2.construirPieza("poblado", List.of(40));  

      
        jugador3.colocarPiezaInicial("poblado", List.of(6));
        jugador3.colocarPiezaInicial("camino",  List.of(6, 7));
        jugador3.construirPieza("poblado", List.of(36));
        
        int tirada = juego.tirarDado(); 
        juego.manejarTirada(tirada);

        Recurso mineralJ1 = jugador1.buscarRecurso(RecursoTipo.MINERAL);
        Recurso mineralJ2 = jugador2.buscarRecurso(RecursoTipo.MINERAL);

        assertEquals(1, mineralJ1.cantidad());
        assertEquals(1, mineralJ2.cantidad());

      
        Recurso mineralJ3 = jugador3.buscarRecurso(RecursoTipo.MINERAL);
        assertTrue(mineralJ3.cantidad() == 0);
    }
    
    
    
}


