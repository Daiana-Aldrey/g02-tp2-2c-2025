package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.controllers.Juego;
import edu.fiuba.algo3.modelo.*;
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
	    Tablero.getInstance().crearGrafo();
	    
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
        tablero.crearGrafo();

        // dado dummy
        GeneradorDeDados dadoPrueba = new GeneradorDeDados() {
            @Override
            public int tirar() {
                return 7;
            }
        };

        Juego juego = new Juego(3, List.of("Luis","Ana","Maria"), dadoPrueba);
        
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
        tablero.reset();
        tablero.crearGrafo();

        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis","Ana","Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);
        jugador.recibirRecurso(RecursoTipo.MADERA, 10);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 10);
        jugador.recibirRecurso(RecursoTipo.LANA, 10);
        jugador.recibirRecurso(RecursoTipo.GRANO, 10);

        Terreno montania = new Montania(8);
        tablero.registrarTerreno('A', montania, List.of(10));
        jugador.construirPieza("poblado", List.of(10));

        int tirada = juego.tirarDado();  
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(1, mineral.cantidad());
    }
    
    @Test
    public void jugadoresRecibenRecursosPorSusSegundosPoblados() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();
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

        Terreno montaniaA = new Montania(8);
        Terreno montaniaB = new Montania(8);

        tablero.registrarTerreno('A', montaniaA, List.of(10));
        tablero.registrarTerreno('B', montaniaB, List.of(40));

      
        j1.colocarPiezaInicial("poblado", List.of(1));
        j1.colocarPiezaInicial("camino",  List.of(1, 2));
        j1.construirPieza("poblado", List.of(10));  

        j2.colocarPiezaInicial("poblado", List.of(54));
        j2.colocarPiezaInicial("camino",  List.of(54, 53));
        j2.construirPieza("poblado", List.of(40));  

      
        j3.colocarPiezaInicial("poblado", List.of(6));
        j3.colocarPiezaInicial("camino",  List.of(6, 7));
        j3.construirPieza("poblado", List.of(36));
        
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


