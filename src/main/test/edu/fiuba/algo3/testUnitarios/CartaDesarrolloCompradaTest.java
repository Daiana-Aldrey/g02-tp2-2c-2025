package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.CartaDeDesarrollo.Carta;
import edu.fiuba.algo3.modelo.Intercambio.Banco;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class CartaDesarrolloCompradaTest {

    final int UNO = 1;
    @Test
    void comprarCartaDesarrollo_descuentaRecursos_yAgregaCartaAlMazoOculto() {

        Jugador jugador = new Jugador("joo");
        Banco banco = new Banco();

        jugador.recibirRecurso(new Lana(), 3);
        jugador.recibirRecurso(new Grano(), 2);
        jugador.recibirRecurso(new Mineral(), 1);

        int lanaAntes = jugador.buscarRecurso(new Lana()).cantidad();
        int granoAntes = jugador.buscarRecurso(new Grano()).cantidad();
        int mineralAntes = jugador.buscarRecurso(new Mineral()).cantidad();
        banco.venderCartaDesarrollo(jugador);

        assertEquals(lanaAntes - UNO, jugador.buscarRecurso(new Lana()).cantidad(),"quedan 2 lanas");
        assertEquals(granoAntes - UNO, jugador.buscarRecurso(new Grano()).cantidad(), "quedan 1 granos");
        assertEquals(mineralAntes - UNO, jugador.buscarRecurso(new Mineral()).cantidad(), "quedan 0 mineral");

        assertEquals(1, jugador.obtenerCartasDesarrollo().size(), "El jugador debería tener 1 carta nueva.");
    }
    @Test
    public void JugadorSinMineralNoPuedeComprarYConservaRecursos() {
        Banco banco = new Banco();
        Jugador jugador = new Jugador("Jp");

        jugador.recibirRecurso(new Lana(), 4);
        jugador.recibirRecurso(new Grano(), 6);

        assertThrows(IllegalArgumentException.class, () -> {banco.venderCartaDesarrollo(jugador);}, "Debe fallar por que no tiene Minerales");

        assertEquals(4, jugador.buscarRecurso(new Lana()).cantidad(), "No se desconto nada de  Lana");
        assertEquals(6, jugador.buscarRecurso(new Grano()).cantidad(), "No se desconto nada de Grano");
        assertEquals(0, jugador.buscarRecurso(new Mineral()).cantidad(), "mineral sigue en 0.");

        assertTrue(jugador.obtenerCartasDesarrollo().isEmpty(), "No se agrega ninguna carta de desarrollo.");

    }


    @Test
    void cartaCompradaEnEsteTurnoNoPuedeUsarseInmediatamente() {
        Jugador jugador = new Jugador("Jm");
        Banco banco = new Banco();

        Carta cartaMock = mock(Carta.class);
        jugador.recibirCartaDesarrollo(cartaMock);

        assertThrows(IllegalStateException.class, () -> {jugador.jugarCartaDesarrollo(cartaMock);}, "debería dar error al usar carta el mismo turno");

        verify(cartaMock, never()).usar(any());

    }
    @Test
    void cartaCompradaEnEsteTurno_noPuedeJugarseInmediatamente() {
        Jugador jugador = new Jugador("Lu");
        Banco banco = new Banco();

        jugador.recibirRecurso(new Lana(), 1);
        jugador.recibirRecurso(new Grano(), 1);
        jugador.recibirRecurso(new Mineral(), 1);

        Carta carta = banco.venderCartaDesarrollo(jugador);

        assertThrows(IllegalStateException.class, () -> jugador.jugarCartaDesarrollo(carta), "debería dar error al usar carta el mismo turno");
    }

    @Test
    public void test04JugadorEsperaElTurnoYPuedeUsarLaCarta() {
        Jugador jugador = new Jugador("Jugador Paciente");
        Carta cartaMock = mock(Carta.class);
        jugador.recibirCartaDesarrollo(cartaMock);

        jugador.prepararCartasDesarrolloParaNuevoTurno();

        assertDoesNotThrow(() -> {jugador.jugarCartaDesarrollo(cartaMock);});

        verify(cartaMock, times(1)).usar(jugador);

        assertEquals(0, jugador.obtenerCartasDesarrollo().size());
    }
    @Test
    void cartaComprada_sePuedeJugarEnTurnoSiguiente() {
        Jugador jugador = new Jugador("Lu");
        Banco banco = new Banco();

        jugador.recibirRecurso(new Lana(), 1);
        jugador.recibirRecurso(new Grano(), 1);
        jugador.recibirRecurso(new Mineral(), 1);

        Carta carta = banco.venderCartaDesarrollo(jugador);

        jugador.prepararCartasDesarrolloParaNuevoTurno();
        assertDoesNotThrow(() -> jugador.jugarCartaDesarrollo(carta));
        assertEquals(0, jugador.obtenerCartasDesarrollo().size(), "Después de usarla carta,no debe quedar en el mazo de desarrollo.");
    }



}
