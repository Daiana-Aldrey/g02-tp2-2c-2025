package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Pieza.Poblado;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.assertTrue;


public class TableroTest {
	@Test
    public void tableroColocaEdificioCorrectamente() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        UbicacionVertice ubicacion = new UbicacionVertice('5');
        Pieza pieza = new Poblado(new Jugador("Luis"));
        tablero.colocarEdificio(ubicacion, pieza);

        assertTrue(tablero.hayEdificio(ubicacion));


    }

    @Test
    public void tableroColocaCaminoCorrectamente() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Camino camino = new Camino(luis);

        UbicacionVertice ubicacion1 = new UbicacionVertice(10);
        UbicacionVertice ubicacion2 = new UbicacionVertice(11);

        tablero.colocarEdificio(ubicacion1, new Poblado(new Jugador("Luis")));
        tablero.colocarCamino(ubicacion1, ubicacion2, camino);

        assertTrue(tablero.hayCamino(ubicacion1, ubicacion2));
    }

    @Test
    public void tableroNoPermiteColocarDosPiezasEnElMismoVertice() throws Exception {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Pieza p1 = new Poblado(new Jugador("Luis"));
        tablero.colocarEdificio(new UbicacionVertice('5'), p1);
        Pieza p2 = new Poblado(new Jugador("Juan"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tablero.colocarEdificio(new UbicacionVertice('5'), p2);
        });
    }
}
