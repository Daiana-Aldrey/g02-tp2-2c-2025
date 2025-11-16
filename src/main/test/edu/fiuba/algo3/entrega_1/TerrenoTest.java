package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

public class TerrenoTest {

    @Test
    public void terrenoConLadronNoProduceRecursos() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();

        Terreno bosque = new Bosque(8);
        tablero.registrarTerreno('X', bosque, List.of(1,2,3,4,5,6));
        VerticeTerreno vt = tablero.buscarVerticeTerreno('X');

        Jugador jugador = new Jugador("Jugador");
        Pieza poblado = new Poblado(jugador);
        vt.agregarEdificio(poblado);

        tablero.moverLadronA('X', jugador);

        int antes = jugador.cantidadDeCartas();
        tablero.cosechar(6);
        int despues = jugador.cantidadDeCartas();

        assertEquals(antes, despues,
                "Un terreno con el Ladrón NO debe producir recursos");
    }
    
}


