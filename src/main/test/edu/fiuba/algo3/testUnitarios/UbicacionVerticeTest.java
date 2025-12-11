package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class UbicacionVerticeTest {

    @Test
    public void SeComparaConOtroObjetoCorrectamente() {
        UbicacionVertice ubicacion1 = new UbicacionVertice('1');
        UbicacionVertice ubicacion2 = new UbicacionVertice('1');

        assertTrue(ubicacion1.equals(ubicacion2));
    }
}
