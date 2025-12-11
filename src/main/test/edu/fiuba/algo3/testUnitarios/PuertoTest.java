package edu.fiuba.algo3.testUnitarios;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.Bosque;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.modelo.Intercambio.PuertoEspecifico;
import edu.fiuba.algo3.Excepciones.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PuertoTest {
	
	  @Test
	    public void muelleOtorgaAccesoSiHayPiezaSobreEl() {
	        Jugador jugador = new Jugador("Ana");
	        Muelle muelle = new Muelle(new UbicacionVertice(1));
	        Pieza poblado = new Poblado(jugador);

	        muelle.notificarConstruccion(new UbicacionVertice(1), poblado);
	        assertTrue(muelle.perteneceAJugador(jugador));
	    }
	  
	  @Test
	  public void muelleNoOtorgaAccesoSiHayPiezaSobreEl() { 
	        Jugador jugador = new Jugador("Ana");
	        Muelle muelle = new Muelle(new UbicacionVertice(1));
	        Pieza poblado = new Poblado(jugador);

	        muelle.notificarConstruccion(new UbicacionVertice(2), poblado);
	        assertFalse(muelle.perteneceAJugador(jugador)); 
	  }
	  
	  @Test
	  public void puertoEspecificoRechazaRecursoIncorrecto() {
		    Jugador jugador = new Jugador("Valen");
		    PuertoEspecifico puerto = new PuertoEspecifico(
		            new Madera(3),
		            new UbicacionVertice(3),
		            new UbicacionVertice(4)
		    );

		    Pieza poblado = new Poblado(jugador);
		    puerto.notificarConstruccion(new UbicacionVertice(3), poblado);

		    jugador.recibirRecurso(new Lana(), 2);

		    assertThrows(RecursoOfertaIncorrecto.class, () -> puerto.comercializar(jugador, new Lana(), new Grano(), 1));
		}
	  
	  @Test
	  public void puertoEspecificoPermiteComercioConRecursoCorrectoYAcceso() {
		    Jugador jugador = new Jugador("Juli");
		    PuertoEspecifico puerto = new PuertoEspecifico(
		            new Madera(1),
		            new UbicacionVertice(5),
		            new UbicacionVertice(6)
		    );

		    Pieza poblado = new Poblado(jugador);
		    puerto.notificarConstruccion(new UbicacionVertice(6), poblado);

		    jugador.recibirRecurso(new Madera(), 2);
		    puerto.comercializar(jugador, new Madera(), new Mineral(), 1);

		    assertEquals(0, jugador.buscarRecurso(new Madera()).cantidad());
		    assertEquals(1, jugador.buscarRecurso(new Mineral()).cantidad());
		}
	  
	  @Test
	  public void puertoGenericoNoPermiteComercioSinAcceso() {
	      Jugador jugador = new Jugador("Ana");
	      PuertoGenerico puerto = new PuertoGenerico(
	              new UbicacionVertice(1),
	              new UbicacionVertice(2)
	      );

	      assertThrows(PuertoNoAccesible.class, () -> puerto.comercializar(jugador, new Madera(), new Grano(), 1));
	  }
	  
	  @Test
	  public void puertoGenericoPermiteComercioConAcceso() {
	      Jugador jugador = new Jugador("Sofi");
	      PuertoGenerico puerto = new PuertoGenerico(
	              new UbicacionVertice(1),
	              new UbicacionVertice(2)
	      );

	      Pieza poblado = new Poblado(jugador);
	      puerto.notificarConstruccion(new UbicacionVertice(1), poblado);

	      jugador.recibirRecurso(new Madera(), 3);
	      puerto.comercializar(jugador, new Madera(), new Lana(), 1);

	      assertEquals(0, jugador.buscarRecurso(new Madera()).cantidad());
	      assertEquals(1, jugador.buscarRecurso(new Lana()).cantidad());
	  }
}

