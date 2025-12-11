package edu.fiuba.algo3.testUnitarios.PiezaTest;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Pieza.Poblado;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class PiezaTest {

    @Test
    public void piezaAfectadaPorLadronHaceQueSeRobeAlPropietario() {
        Jugador propietario = mock(Jugador.class);
        Jugador ladron = mock(Jugador.class);

        Pieza poblado = new Poblado(propietario);
        poblado.afectarPorLadron(ladron);

        verify(ladron, times(1)).robarCartaAleatoriaA(propietario);
        verifyNoMoreInteractions(ladron);
    }
}
