package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Dados.DadosAleatorios;
import edu.fiuba.algo3.modelo.Dados.GeneradorDeDados;

import java.util.List;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DadosTest {


    @Test
    void dadoS7DentroDelRangoNoLanzaExcepcion() {
        GeneradorDeDados dado = ()-> 10;
        int n = dado.tirar();
        assertTrue(n >= 2 && n <= 12, "Tirada dentro del rango");
    }

    @Test
    void dadoValFijoDentroDelRangoNoLanzaExcepcion() {
        GeneradorDeDados dado = ()-> 2;
        int n = dado.tirar();
        assertTrue(n >= 2 && n <= 12, "Tirada dentro del rango");
    }

    @Test
    void dadoDentroDelRangoNoLanzaExcepcion() {
        GeneradorDeDados dado = ()-> 12;
        int n = dado.tirar();
        assertTrue(n >= 2 && n <= 12, "Tirada dentro del rango");
    }
    @Test
    void dadoRealSiempreDevuelveValoresEntre2y12() {
        GeneradorDeDados dado = new DadosAleatorios();
        for (int i = 0; i < 10000; i++) {
            int n = dado.tirar();
            assertTrue(n >= 2 && n <= 12, "Tirada fuera de rango: " + n);
        }
    }

    @Test
    void dadoConValorMenorA2DebeFallarEnJuego() {
        GeneradorDeDados dado =  ()-> 1;
        List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);

        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void dadoConValorMayorA12DebeFallarEnJuego() {
        GeneradorDeDados dado =()-> 13;
        List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);


        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void cuandoDa2_esValidoYDevuelve2() {
    	GeneradorDeDados dado =()-> 2;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);

        int n = juego.tirarDado();
        assertEquals(2, n);
    }

    @Test
    void cuandoDa12_esValidoYDevuelve12() {
    	GeneradorDeDados dado =()-> 12;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);

        int n = juego.tirarDado();
        assertEquals(12, n);
    }

    @Test
    void cuandoDa9_esValidoYDevuelve9() {
    	GeneradorDeDados dado =()-> 9;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);

        int n = juego.tirarDado();
        assertEquals(9, n);
    }

    @Test
    void cuandoDa1_fueraDeRangoLanzaExcepcion() {
    	GeneradorDeDados dado =()-> 1;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);

        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void cuandoDa13_fueraDeRangoLanzaExcepcion() {
    	GeneradorDeDados dado =()-> 2;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);

        assertThrows(IllegalStateException.class, juego::tirarDado);
    }


}
