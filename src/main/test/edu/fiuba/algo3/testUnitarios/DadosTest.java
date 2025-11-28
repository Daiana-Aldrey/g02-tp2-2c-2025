package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Dados.DadosAleatoriosConSemilla;
import edu.fiuba.algo3.modelo.Dados.GeneradorDeDados;

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
    void dadoPuedeDar2() {
        GeneradorDeDados dado = () -> 2;
        assertEquals(2, dado.tirar());
    }

    @Test
    void dadoPuedeDar12() {
        GeneradorDeDados dado = () -> 12;
        assertEquals(12, dado.tirar());
    }

    @Test
    void dadoPuedeDar10() {
        GeneradorDeDados dado = () -> 10;
        assertEquals(10, dado.tirar());
    }
    @Test
    void laSumaDeLosDadosNoPuedeSerMenorA2() {
        var dado = new DadosAleatoriosConSemilla(22L);
        int resultado = dado.tirar();
        assertEquals(6, resultado);
        assertTrue(resultado >= 2, "El resultado debe ser mayor o igual a 2");
    }

    @Test
    void laSumaDeLosDadosNoPuedeSerMayorA12() {
        var dado = new DadosAleatoriosConSemilla(47L);
        int resultado = dado.tirar();
        assertEquals(9, resultado);
        assertTrue(resultado <= 12, "El resultado debe ser menor o igual a 12");
    }

    @Test
    void elDadoGeneraUnNumeroValidoCualquiera() {
        var dado = new DadosAleatoriosConSemilla();
        int resultado = dado.tirar();
        assertTrue(resultado >= 2 && resultado <= 12, "La tirada debe estar entre 2 y 12. Salió: " + resultado);
    }



}
