package edu.fiuba.algo3.testUnitarios.TableroTest;

import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Pieza.Poblado;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.modelo.Intercambio.*;
import org.junit.jupiter.api.Test;
import java.util.*;

import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

        Assertions.assertThrows(ColocacionInvalida.class, () -> {
            tablero.colocarEdificio(new UbicacionVertice('5'), p2);
        });
    }
    
    @Test
    public void tableroInicializaPuertosCorrectamente() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        List<Puerto> puertos = tablero.getPuertos();
        assertEquals(9, puertos.size());

        UbicacionVertice[][] ubicacionesEsperadas = {
            { new UbicacionVertice(1), new UbicacionVertice(2) }, 
            { new UbicacionVertice(6), new UbicacionVertice(7) },  
            { new UbicacionVertice(48), new UbicacionVertice(49) },
            { new UbicacionVertice(53), new UbicacionVertice(54) },
            
            { new UbicacionVertice(8), new UbicacionVertice(17) },
            { new UbicacionVertice(16), new UbicacionVertice(27) },
            { new UbicacionVertice(27), new UbicacionVertice(38) },
            { new UbicacionVertice(28), new UbicacionVertice(39) },
            { new UbicacionVertice(47), new UbicacionVertice(48) } 
        };

        for (int i = 0; i < puertos.size(); i++) {
            Puerto p = puertos.get(i);
            UbicacionVertice u1 = p.getMuelle1().getUbicacion();
            UbicacionVertice u2 = p.getMuelle2().getUbicacion();

            assertEquals(ubicacionesEsperadas[i][0], u1);
            assertEquals(ubicacionesEsperadas[i][1], u2);
        }
    }
    
    @Test
    public void tableroTienePuertosEspecificos() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        List<Puerto> puertos = tablero.getPuertos();

        int cantidadEspecificos = 0;
        for (Puerto puerto : puertos) {
            if (puerto instanceof PuertoEspecifico) {
                cantidadEspecificos++;
            }
        }

        assertEquals(5, cantidadEspecificos);
    }

    @Test
    public void tableroTienePuertosGenericos() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        List<Puerto> puertos = tablero.getPuertos();

        int cantidadGenericos = 0;
        for (Puerto puerto : puertos) {
            if (puerto instanceof PuertoGenerico) {
                cantidadGenericos++;
            }
        }

        assertEquals("Debe haber 4 puertos genéricos", 4, cantidadGenericos);
    }


}
