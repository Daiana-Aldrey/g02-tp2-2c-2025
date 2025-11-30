package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Bonificacion.BonificadorRutaMayor;
import edu.fiuba.algo3.modelo.Bonificacion.RutaMayor;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BonificadorRutaMayorTest {
    @Test
    public void EntreTresRutasYDevuelveLaMasLarga() {
        BonificadorRutaMayor bonificador = new BonificadorRutaMayor();
        RutaMayor rutaMayor1 = new RutaMayor(mock(Jugador.class), new ArrayList<>());
        RutaMayor rutaMayor2 = new RutaMayor(mock(Jugador.class), new ArrayList<>());
        RutaMayor rutaMayor3 = new RutaMayor(mock(Jugador.class), new ArrayList<>());

        rutaMayor1.setearRutaMayor(4);
        rutaMayor2.setearRutaMayor(5);
        rutaMayor3.setearRutaMayor(1);

        bonificador.agregarRuta(rutaMayor1);
        bonificador.agregarRuta(rutaMayor2);
        bonificador.agregarRuta(rutaMayor3);

        bonificador.bonificarPorRutaMayor();

        RutaMayor esperado = rutaMayor2;
        RutaMayor obtenido = bonificador.getRutaMayor();

        assertEquals(esperado, obtenido);
    }

    @Test
    public void BonificadorDaPuntosDeVictoriaCorrectamente() {
        BonificadorRutaMayor bonificador = new BonificadorRutaMayor();
        Jugador jugador1 = new Jugador("Perrie");
        Jugador jugador2 = new Jugador("Marnie");
        Jugador jugador3 = new Jugador("Willy");
        RutaMayor rutaMayor1 = new RutaMayor(jugador1, new ArrayList<>());
        RutaMayor rutaMayor2 = new RutaMayor(jugador2, new ArrayList<>());
        RutaMayor rutaMayor3 = new RutaMayor(jugador3, new ArrayList<>());

        rutaMayor1.setearRutaMayor(5);
        rutaMayor2.setearRutaMayor(6);
        rutaMayor3.setearRutaMayor(7);

        bonificador.agregarRuta(rutaMayor1);
        bonificador.agregarRuta(rutaMayor2);
        bonificador.agregarRuta(rutaMayor3);

        bonificador.bonificarPorRutaMayor();

        int esperado = 2;
        int obtenido = jugador3.puntosDeVictoria();
        assertEquals(esperado, obtenido);
    }
}
