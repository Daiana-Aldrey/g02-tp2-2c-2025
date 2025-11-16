package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PiezaTest {
    @Test
    public void piezaAfectadaPorLadronHaceQueSeRobeAlPropietario() {
        Jugador propietario = new Jugador("Propietario");
        Jugador ladron = new Jugador("Ladrón");

        propietario.recibirRecurso(RecursoTipo.LANA, 1);
        Pieza poblado = new Poblado(propietario);

        int antesProp = propietario.cantidadDeCartas();   
        int antesLadron = ladron.cantidadDeCartas();      

        poblado.afectarPorLadron(ladron);

        assertEquals(antesProp - 1, propietario.cantidadDeCartas());
        assertEquals(antesLadron + 1, ladron.cantidadDeCartas());
    }
}
