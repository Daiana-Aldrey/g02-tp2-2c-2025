package edu.fiuba.algo3.entrega_1.testUnitarios;

import edu.fiuba.algo3.modelo.DadosAleatorios;
import edu.fiuba.algo3.controllers.Juego;
import edu.fiuba.algo3.modelo.GeneradorDeDados;

import java.util.List;
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
        edu.fiuba.algo3.controllers.Juego juego =
                new edu.fiuba.algo3.controllers.Juego(3, java.util.List.of("A","B","C"), dado);

        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void dadoConValorMayorA12DebeFallarEnJuego() {
        GeneradorDeDados dado =()-> 13;
        edu.fiuba.algo3.controllers.Juego juego =
                new edu.fiuba.algo3.controllers.Juego(3, java.util.List.of("A","B","C"), dado);

        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void cuandoDa2_esValidoYDevuelve2() {
        var juego = new Juego(3, List.of("A","B","C"), ()-> 2);
        int n = juego.tirarDado();
        assertEquals(2, n);
    }

    @Test
    void cuandoDa12_esValidoYDevuelve12() {
        var juego = new Juego(3, List.of("A","B","C"),()-> 12);
        int n = juego.tirarDado();
        assertEquals(12, n);
    }

    @Test
    void cuandoDa9_esValidoYDevuelve9() {
        var juego = new Juego(3, List.of("A","B","C"), ()-> 9);
        int n = juego.tirarDado();
        assertEquals(9, n);
    }

    @Test
    void cuandoDa1_fueraDeRangoLanzaExcepcion() {
        var juego = new Juego(3, List.of("A","B","C"),()-> 1);
        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void cuandoDa13_fueraDeRangoLanzaExcepcion() {
        var juego = new Juego(3, List.of("A","B","C"),()-> 13);
        assertThrows(IllegalStateException.class, juego::tirarDado);
    }


}
