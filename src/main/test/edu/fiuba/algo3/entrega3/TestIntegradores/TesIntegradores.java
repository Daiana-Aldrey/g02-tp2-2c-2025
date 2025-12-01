package edu.fiuba.algo3.entrega3.TestIntegradores;

import edu.fiuba.algo3.modelo.Bonificacion.BonificadorRutaMayor;
import edu.fiuba.algo3.modelo.Bonificacion.RutaMayor;
import edu.fiuba.algo3.modelo.CartaDeDesarrollo.Carta;
import edu.fiuba.algo3.modelo.CartaDeDesarrollo.CartaConstruccionCarreteras;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void SeUsaCartaDeConstruccionDeCarreterasCorrectamente() {
        CartaConstruccionCarreteras carta = new CartaConstruccionCarreteras();
        Jugador jugador1 = new Jugador("Jaz");

        jugador1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(42)));
        jugador1.colocarPiezaInicial("camino", List.of(new UbicacionVertice(42),new UbicacionVertice(43)));

        carta.setearUbicacionesPrimerCamino(new UbicacionVertice(43), new UbicacionVertice(44));
        carta.setearUbicacionesSegundaCamino(new UbicacionVertice(44), new UbicacionVertice(45));

        jugador1.recibirCartaDesarrollo(carta);
        jugador1.prepararCartasDesarrolloParaNuevoTurno();

        List<Carta> esperado1 = List.of(carta);
        List<Carta> obtenido1 = jugador1.obtenerCartasDesarrollo();

        assertEquals(esperado1, obtenido1);

        jugador1.jugarCartaDesarrollo(carta);

        int esperado2 = 3;
        int obtenido2 = jugador1.getCaminos().size();

        assertEquals(esperado2, obtenido2);
    }
}
