package edu.fiuba.algo3.testUnitarios;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescartCartasTest {

    @Test
    void conDosDeCadaRecurso_alSalir7_descartaLaMitad() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(new Madera(), 2);
        jugador.recibirRecurso(new Ladrillo(), 2);
        jugador.recibirRecurso(new Lana(), 2);
        jugador.recibirRecurso(new Grano(), 2);
        jugador.recibirRecurso(new Mineral(), 2);

        jugador.descartarMitad();
        assertEquals(5, jugador.cantidadDeCartas());
    }
    @Test
    void conTresCartas_noDescartaAlSalir7() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(new Madera(), 1);
        jugador.recibirRecurso(new Ladrillo(), 1);
        jugador.recibirRecurso(new Lana(), 1);

        jugador.descartarMitad();
        assertEquals(3, jugador.cantidadDeCartas());
    }
    @Test
    void totalMenorA7_noDescarta() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(new Madera(), 3);
        jugador.recibirRecurso(new Ladrillo(), 3);
        jugador.descartarMitad();

        assertEquals(6, jugador.cantidadDeCartas());
    }

    @Test
    void totalNueve_descartaCuatro_yQuedanCinco() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(new Madera(), 5);
        jugador.recibirRecurso(new Ladrillo(), 4);
        jugador.descartarMitad();

        assertEquals(5, jugador.cantidadDeCartas());
    }

    @Test
    void totalDiez_descartaCinco_yQuedanCinco() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(new Grano(), 6);
        jugador.recibirRecurso(new Lana(), 4);
        jugador.descartarMitad();
        assertEquals(5, jugador.cantidadDeCartas());
    }
    
    @Test
    void conCincoDeCadaRecurso_descartaMitad_yQuedanTrece() {

        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(new Madera(), 5);
        jugador.recibirRecurso(new Ladrillo(), 5);
        jugador.recibirRecurso(new Lana(), 5);
        jugador.recibirRecurso(new Grano(), 5);
        jugador.recibirRecurso(new Mineral(), 5);

        jugador.descartarMitad();
        assertEquals(13, jugador.cantidadDeCartas());
    }
    @Test
    void jugadorCon9CartasDescarta4() {
        Jugador jugador = new Jugador("jole");

        jugador.recibirRecurso(new Madera(), 5);
        jugador.recibirRecurso(new Ladrillo(), 4);

        jugador.descartarMitad();

        assertEquals(5, jugador.cantidadDeCartas());
    }
    @Test
    void totaligualA7_NoDescarta() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(new Madera(), 3);
        jugador.recibirRecurso(new Ladrillo(), 4);
        jugador.descartarMitad();

        assertEquals(7, jugador.cantidadDeCartas());

    }
    @Test
    void tieneOchoCartas_alSalir7_descartaLaMitad() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(new Madera(), 2);
        jugador.recibirRecurso(new Ladrillo(), 2);
        jugador.recibirRecurso(new Lana(), 2);
        jugador.recibirRecurso(new Grano(), 2);
        jugador.descartarMitad();
        assertEquals(4, jugador.cantidadDeCartas());
    }
}
