package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.DosDados;
import edu.fiuba.algo3.controllers.Juego;
import edu.fiuba.algo3.modelo.GeneradorDeDados;
import edu.fiuba.algo3.modelo.DadosS7;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests del lanzamiento de dados (para la entrega 1)
 * Se verifica que los resultados estén entre 2 y 12,
 * tira error si da  fuera del rango.
 */
public class DadosTest {


    @Test
    void dadoS7DentroDelRangoNoLanzaExcepcion() {
        GeneradorDeDados dado = new DadosS7(10);
        int n = dado.tirar();
        assertTrue(n >= 2 && n <= 12, "Tirada dentro del rango");
    }

    @Test
    void dadoValFijoDentroDelRangoNoLanzaExcepcion() {
        GeneradorDeDados dado = new DadosS7(2);
        int n = dado.tirar();
        assertTrue(n >= 2 && n <= 12, "Tirada dentro del rango");
    }

    @Test
    void dadoDentroDelRangoNoLanzaExcepcion() {
        GeneradorDeDados dado = new DadosS7(12);
        int n = dado.tirar();
        assertTrue(n >= 2 && n <= 12, "Tirada dentro del rango");
    }

    @Test
    void dadoRealSiempreDevuelveValoresEntre2y12() {
        GeneradorDeDados dado = new DosDados();
        for (int i = 0; i < 10000; i++) {
            int n = dado.tirar();
            assertTrue(n >= 2 && n <= 12, "Tirada fuera de rango: " + n);
        }
    }

    @Test
    void dadoConValorMenorA2DebeFallarEnJuego() {
        GeneradorDeDados dado = new DadosS7(1);
        edu.fiuba.algo3.controllers.Juego juego =
                new edu.fiuba.algo3.controllers.Juego(3, java.util.List.of("A","B","C"), dado);

        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void dadoConValorMayorA12DebeFallarEnJuego() {
        GeneradorDeDados dado = new DadosS7(13);
        edu.fiuba.algo3.controllers.Juego juego =
                new edu.fiuba.algo3.controllers.Juego(3, java.util.List.of("A","B","C"), dado);

        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void cuandoDa2_esValidoYDevuelve2() {
        var juego = new Juego(3, List.of("A","B","C"), new DadosS7(2));
        int n = juego.tirarDado();
        assertEquals(2, n);
    }

    @Test
    void cuandoDa12_esValidoYDevuelve12() {
        var juego = new Juego(3, List.of("A","B","C"), new DadosS7(12));
        int n = juego.tirarDado();
        assertEquals(12, n);
    }

    @Test
    void cuandoDa9_esValidoYDevuelve9() {
        var juego = new Juego(3, List.of("A","B","C"), new DadosS7(9));
        int n = juego.tirarDado();
        assertEquals(9, n);
    }

    @Test
    void cuandoDa1_fueraDeRangoLanzaExcepcion() {
        var juego = new Juego(3, List.of("A","B","C"), new DadosS7(1));
        assertThrows(IllegalStateException.class, juego::tirarDado);
    }

    @Test
    void cuandoDa13_fueraDeRangoLanzaExcepcion() {
        var juego = new Juego(3, List.of("A","B","C"), new DadosS7(13));
        assertThrows(IllegalStateException.class, juego::tirarDado);
    }


}
