package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.controllers.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Assertions;



public class JugadorTest {
	@Test
	public void jugadorInicializaCorrectamenteLosRecursos() {
	    Jugador jugador = new Jugador("Lu");

	    assertNotNull(jugador.buscarRecurso(RecursoTipo.MADERA));
	    assertNotNull(jugador.buscarRecurso(RecursoTipo.LADRILLO));
	    assertNotNull(jugador.buscarRecurso(RecursoTipo.LANA));
	    assertNotNull(jugador.buscarRecurso(RecursoTipo.GRANO));
	    assertNotNull(jugador.buscarRecurso(RecursoTipo.MINERAL));
	}

	@Test
	public void jugadorPuedeConstruirPobladoSiTieneRecursosSuficientes() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();        
        tablero.crearGrafo();
        GeneradorDeDados dadoPrueba = () -> 7;

        List<String> nombres = List.of("Luis", "Ana", "Maria");
        Juego juego = new Juego(3, nombres, dadoPrueba);
        Jugador jugador = juego.jugadores().get(0);
        Pieza pobladoDeReferencia = Pieza.crear("poblado", jugador);
        List<Recurso> precio = pobladoDeReferencia.costoDeConstruccion();

        for (Recurso costo : precio) {
            jugador.recibirRecurso(costo.tipo(), costo.cantidad());
        }
  
        List<Integer> ubicacion = List.of(10);
        jugador.construirPieza("poblado", ubicacion);

        Assertions.assertTrue(tablero.hayPieza(ubicacion));
        Assertions.assertEquals(0, jugador.cantidadDeCartas());
    }


	//SOLO PARA PROBAR 
    private static class TerrenoTest extends Terreno {
        private RecursoTipo tipoRecurso;

        public TerrenoTest(int ficha, RecursoTipo tipo) {
            super(ficha);
            this.tipoRecurso = tipo;
        }

        @Override
        public void repartirRecurso(List<Pieza> edificios) {
            for (Pieza p : edificios) {
                p.agregarRecursos(tipoRecurso, 1);
            }
        }
    }

    @Test
    public void jugadorRecibeRecursoDelTerreno() {
    	Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();
      
        Jugador jugador = new Jugador("Luis");
        Terreno montania = new Montania(8);
        VerticeTerreno vt = new VerticeTerreno('A', montania);
        Poblado p = new Poblado(jugador);
        
        vt.agregarEdificio(p);
        vt.cosecharTerreno();
        
        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(1, mineral.cantidad());
    }

    
    public void jugadorRecibeUnRecursoPorPobladoCuandoCorresponde() { 
    }
    
    public void jugadorRecibeDosRecursosPorCiudadCuandoCorresponde() {
    	
    }
    
    public void jugadorDescartaLaMitadDeCartasSiSale7yTieneMasDe7Cartas() {
    	
    }
    
    public void jugadorActivoMueveAlLadronYRobaCartaAJugadorAdyacenteANuevoTerreno() {
    	
    }
}
