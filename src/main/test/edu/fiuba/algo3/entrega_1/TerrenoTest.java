package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerrenoTest {
  
	
    private static class TerrenoPrueba extends Terreno {
        public TerrenoPrueba() {
            super(0); 
        }
        
        @Override
        public void repartirRecurso(java.util.List<Pieza> edificios) {
        }
    }
    

    @Test
    public void verticeTerrenoConUnSoloEdificioRobaAlDuenoDeEsaPieza() {
        TerrenoPrueba terreno = new TerrenoPrueba();
        VerticeTerreno vt = new VerticeTerreno('X', terreno);

        Jugador victima = new Jugador("Víctima");
        Jugador ladron = new Jugador("Ladrón");

        victima.recibirRecurso(RecursoTipo.GRANO, 1);
        Pieza pobladoVictima = new Poblado(victima);
        vt.agregarEdificio(pobladoVictima);

        int antesVictima = victima.cantidadDeCartas();  
        int antesLadron = ladron.cantidadDeCartas();    

        vt.recibirLadron(ladron);

        assertEquals(antesVictima - 1, victima.cantidadDeCartas(),
                "La víctima debería perder una carta al recibir el ladrón");
        assertEquals(antesLadron + 1, ladron.cantidadDeCartas(),
                "El jugador que mueve el ladrón debería ganar una carta");
    }
}


