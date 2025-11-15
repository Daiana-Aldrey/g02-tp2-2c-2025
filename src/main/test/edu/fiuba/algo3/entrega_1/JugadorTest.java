package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;



public class JugadorTest {

	public void jugadorInicializaCorrectamenteLosRecursos() {
	    Jugador jugador = new Jugador("Lu");

	    assertNotNull(jugador.buscarRecurso("MADERA"));
	    assertNotNull(jugador.buscarRecurso("LADRILLO"));
	    assertNotNull(jugador.buscarRecurso("LANA"));
	    assertNotNull(jugador.buscarRecurso("GRANO"));
	    assertNotNull(jugador.buscarRecurso("MINERAL"));
	}

	@Test
	public void jugadorPuedeConstruirPobladoSiTieneLosRecursosNecesarios() {
	    Jugador jugador = new Jugador("Lautaro");

	    jugador.recibirRecurso("MADERA", 1);
	    jugador.recibirRecurso("LADRILLO", 1);
	    jugador.recibirRecurso("LANA", 1);
	    jugador.recibirRecurso("GRANO", 1);

	    Pieza poblado = Pieza.crear("poblado", jugador);

	    assertDoesNotThrow(() -> jugador.construirPiezaDePrueba(poblado));
	}


	//SOLO PARA PROBAR 
    private static class TerrenoTest extends Terreno {
        private String tipoRecurso;

        public TerrenoTest(int ficha, String tipo) {
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
    public void jugadorRecibeRecursoDelTerrenoAdyacenteAlSegundoPoblado() {
       
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
