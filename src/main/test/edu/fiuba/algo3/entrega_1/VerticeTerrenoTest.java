package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void verticeTerrenoVerificaSusVerticesAdyacentes() {
        VerticeTerreno vertice1 = new VerticeTerreno('A', new Terreno(8));
        VerticeEdificio vertice2 = new VerticeEdificio(2);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        assertTrue(vertice1.hayVerticeAdyacente(vertice2));

    }

    @Test
    public void verticeNoPermiteCosecharTerrenoPorNoTenerPiezasAdyacentes () {
        VerticeTerreno vertice = new VerticeTerreno('A', new Terreno(8));

        assertThrows(IllegalStateException.class, () -> {
            vertice.cosecharTerreno();
        });

    }
}
