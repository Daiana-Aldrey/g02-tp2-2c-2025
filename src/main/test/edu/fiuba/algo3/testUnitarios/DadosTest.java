package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Dados.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DadosTest {

    @Test
    public void tirarConDosDadosGeneraSumaEnRangoValido() {
    	int cantidad = 2;
        Dados dados = new Dados(cantidad);
        
        dados.tirar();
        int suma = dados.sumarTirada();

        assertTrue(suma >= 2 && suma <= 12);
    }

    @Test
    public void tirarConTresDadosGeneraSumaEnRangoValido() {
        int cantidad = 3;
        Dados dados = new Dados(cantidad);

        dados.tirar();
        int suma = dados.sumarTirada();

        assertTrue(suma >= 3 && suma <= 18);
    }

   
    @Test
    public void tiradaFalsaDevuelveSiempreElValorPasado() {
        Dados dados = new Dados(2);
        int valor = dados.tiradaFalsa(7);
        assertEquals(7, valor);
    }
}
