package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerticeTerrenoTest {
    @Test
    public void verticeConfirmaUbicación() {
        VerticeTerreno vertice = new VerticeTerreno('A', new Bosque(8));
        assertTrue(vertice.tieneUbicacion('A'));
    }

    @Test
    public void verticeConfirmaQueElTerrenoQueAlojaTieneLaFichaDeNumeroQueSalioEnLosDados () {
        VerticeTerreno vertice = new VerticeTerreno('A', new Bosque(8));
        assertTrue(vertice.tieneFichaDeNumero(8));
    }
}
