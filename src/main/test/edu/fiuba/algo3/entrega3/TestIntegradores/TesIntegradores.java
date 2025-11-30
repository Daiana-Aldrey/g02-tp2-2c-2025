package edu.fiuba.algo3.entrega3.TestIntegradores;

import edu.fiuba.algo3.modelo.Bonificacion.BonificadorRutaMayor;
import edu.fiuba.algo3.modelo.Bonificacion.RutaMayor;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesIntegradores {
    @Test
    public void cuandoJugadorSuperaRutaMasLarga_seBonificaYDescuentaAlAnterior() {
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
        assertEquals(0,jugador1.puntosDeVictoria());
        assertEquals(0,jugador2.puntosDeVictoria());

        rutaMayor1.setearRutaMayor(6);
        rutaMayor2.setearRutaMayor(8);
        rutaMayor3.setearRutaMayor(7);

        bonificador.bonificarPorRutaMayor();

        assertEquals(0,jugador1.puntosDeVictoria());
        assertEquals(2,jugador2.puntosDeVictoria());
        assertEquals(0,jugador3.puntosDeVictoria());
    }
}
