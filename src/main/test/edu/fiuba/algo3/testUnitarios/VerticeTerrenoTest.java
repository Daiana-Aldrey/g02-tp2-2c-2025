package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.*;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class VerticeTerrenoTest {
    @Test
    public void verticeConfirmaUbicacion() {
        VerticeTerreno vertice = new VerticeTerreno(new UbicacionVertice('A'), new Bosque(),8);
        assertTrue(vertice.tieneUbicacion(new UbicacionVertice('A')));
    }

    @Test
    public void verticeConfirmaQueElTerrenoQueAlojaTieneLaFichaDeNumeroQueSalioEnLosDados () {
        VerticeTerreno vertice = new VerticeTerreno(new UbicacionVertice('A'), new Bosque(),8);
        assertTrue(vertice.tieneFichaDeNumero(8));
    }

    @Test
    public void verticeTerrenoVerificaSuVerticeAdyacentes() {
        VerticeTerreno vertice1 = new VerticeTerreno(new UbicacionVertice('A'), new Bosque(),8);
        VerticeEdificio vertice2 = new VerticeEdificio(new UbicacionVertice('2'));

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);

        assertTrue(vertice1.hayVerticeAdyacente(vertice2));

    }

    @Test
    public void verticeNoPermiteCosecharTerrenoPorNoTenerPiezasAdyacentes () {
        VerticeTerreno vertice = new VerticeTerreno(new UbicacionVertice('A'), new Bosque(),8);

        assertThrows(IllegalStateException.class, () -> {
            vertice.cosecharTerreno();
        });

    }
    

    @Test
    public void seIntentaColocarElLadronEnUnTerrenoDondeYEstabaSituadoYSeLanzaUnaExcepcion () {
        VerticeTerreno vertice = new VerticeTerreno(new UbicacionVertice('B'), new Bosque(),8);

        Ladron ladron = new Ladron();
        vertice.colocarLadron(ladron);

        assertThrows(IllegalStateException.class, () -> {
            vertice.colocarLadron(ladron);
        });
    }
}
