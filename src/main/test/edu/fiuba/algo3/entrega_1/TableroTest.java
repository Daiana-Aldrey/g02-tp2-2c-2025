package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Field;
import java.util.*;

public class TableroTest {

    @Test
    public void tableroColocaPiezaCorrectamente() throws Exception {
        // Dado un tablero limpio
        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo(); 
        
        Pieza pieza = new Poblado(new Jugador("Luis"));
        int vertice = 10;
        tablero.colocarPieza(vertice, pieza);
        
        Assertions.assertEquals(vertice, pieza.ubicacion());
    }

    @Test
    public void tableroNoPermiteColocarDosPiezasEnElMismoVertice() throws Exception {
        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo();

        Pieza p1 = new Poblado(new Jugador("Luis"));
        tablero.colocarPieza(5, p1);
        Pieza p2 = new Poblado(new Jugador("Juan"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tablero.colocarPieza(5, p2);
        });
    }
}
