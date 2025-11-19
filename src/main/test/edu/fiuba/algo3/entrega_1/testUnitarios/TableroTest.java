package edu.fiuba.algo3.entrega_1.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TableroTest {
	@Test
    public void tableroColocaEdificioCorrectamente() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        
        Pieza pieza = new Poblado(new Jugador("Luis"));
        int vertice = 10;
        tablero.colocarEdificio(vertice, pieza);
        
        List<Integer> ubicacion = new ArrayList<>();
        ubicacion.add(vertice);
        boolean valorEsperado = true;
        boolean valorObtenido = tablero.hayPieza(ubicacion);
        
        Assertions.assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void tableroColocaCaminoCorrectamente() {
        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo();

        Jugador luis = new Jugador("Luis");

        Poblado poblado = new Poblado(luis);
        Camino camino = new Camino(luis);

        Integer ubicacionPoblado = 10;
        List<Integer> ubicacionCamino = List.of(10, 11);

        tablero.colocarEdificio(ubicacionPoblado, poblado);
        tablero.colocarCamino(ubicacionCamino, camino);
        boolean valorEsperado = true;
        boolean valorObtenido = tablero.hayPieza(ubicacionCamino);
        Assertions.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void tableroNoPermiteColocarDosPiezasEnElMismoVertice() throws Exception {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Pieza p1 = new Poblado(new Jugador("Luis"));
        tablero.colocarEdificio(5, p1);
        Pieza p2 = new Poblado(new Jugador("Juan"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tablero.colocarEdificio(5, p2);
        });
    }
}
