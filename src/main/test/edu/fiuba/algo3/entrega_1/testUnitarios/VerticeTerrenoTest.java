package edu.fiuba.algo3.entrega_1.testUnitarios;
import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class VerticeTerrenoTest {
    @Test
    public void verticeConfirmaUbicacion() {
        VerticeTerreno vertice = new VerticeTerreno('A', new Bosque(),8);
        assertTrue(vertice.tieneUbicacion('A'));
    }

    @Test
    public void verticeConfirmaQueElTerrenoQueAlojaTieneLaFichaDeNumeroQueSalioEnLosDados () {
        VerticeTerreno vertice = new VerticeTerreno('A', new Bosque(),8);
        assertTrue(vertice.tieneFichaDeNumero(8));
    }

    @Test
    public void verticeTerrenoVerificaSusVerticesAdyacentes() {
        VerticeTerreno vertice1 = new VerticeTerreno('A', new Bosque(),8);
        VerticeEdificio vertice2 = new VerticeEdificio(2);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        assertTrue(vertice1.hayVerticeAdyacente(vertice2));

    }

    @Test
    public void verticeNoPermiteCosecharTerrenoPorNoTenerPiezasAdyacentes () {
        VerticeTerreno vertice = new VerticeTerreno('A', new Bosque(),8);

        assertThrows(IllegalStateException.class, () -> {
            vertice.cosecharTerreno();
        });

    }
    
    @Test
    public void verticeTerrenoConUnSoloEdificioLlamaAfectarPorLadronEnEsaPieza() {
        Terreno terrenoMock = mock(Terreno.class);

        VerticeTerreno vt = new VerticeTerreno('X', terrenoMock,8);

        Jugador jugadorQueMueve = mock(Jugador.class);
        Pieza piezaVictima = mock(Pieza.class);

        vt.agregarEdificio(piezaVictima);

        vt.recibirLadron(jugadorQueMueve);

        verify(piezaVictima, times(1)).afectarPorLadron(jugadorQueMueve);
        verifyNoMoreInteractions(piezaVictima);
    }

    @Test
    public void seIntentaColocarElLadronEnUnTerrenoDondeYEstabaSituadoYSeLanzaUnaExcepcion () {
        VerticeTerreno vertice = new VerticeTerreno('B', new Bosque(),8);

        Ladron ladron = new Ladron('A');
        vertice.colocarLadron(ladron);

        assertThrows(IllegalStateException.class, () -> {
            vertice.colocarLadron(ladron);
        });
    }
}
