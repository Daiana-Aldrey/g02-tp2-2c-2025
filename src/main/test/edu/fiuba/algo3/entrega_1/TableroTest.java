package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;

public class TableroTest {

    @Test
    public void tableroColocaEdificioCorrectamente() throws Exception {
        // Dado un tablero limpio
        Tablero tablero = TableroCatan.getInstance();
        tablero.crearGrafo(); 
        
        Pieza pieza = new Poblado(new Jugador("Luis"));
        int vertice = 10;
        tablero.colocarEdificio(vertice, pieza);
        
        Assertions.assertEquals(vertice, pieza.ubicacion());
    }

    @Test
    public void tableroColocaCaminoCorrectamente() {
        Tablero tablero = TableroCatan.getInstance();
        tablero.crearGrafo();

        Camino camino = new Camino(new Jugador("Luis"));

        List<Integer> vertices = List.of(10, 11);
        tablero.colocarCamino(vertices, camino);

        Assertions.assertEquals(10, camino.ubicacion());
    }
    
    @Test
    public void tableroNoPermiteColocarDosPiezasEnElMismoVertice() throws Exception {
        Tablero tablero = TableroCatan.getInstance();
        tablero.crearGrafo();

        Pieza p1 = new Poblado(new Jugador("Luis"));
        tablero.colocarEdificio(5, p1);
        Pieza p2 = new Poblado(new Jugador("Juan"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tablero.colocarEdificio(5, p2);
        });
    }
}
