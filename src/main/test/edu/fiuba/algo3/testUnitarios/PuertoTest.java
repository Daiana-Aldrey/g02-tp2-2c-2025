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
import edu.fiuba.algo3.Excepciones.ErrorNoUsoDeCartaInvalido;

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
	  
}
