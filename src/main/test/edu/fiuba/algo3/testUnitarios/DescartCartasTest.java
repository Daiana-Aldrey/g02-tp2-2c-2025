package edu.fiuba.algo3.testUnitarios;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescartCartasTest {

    @Test
    void conDosDeCadaRecurso_alSalir7_descartaLaMitad() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(RecursoTipo.MADERA, 2);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 2);
        jugador.recibirRecurso(RecursoTipo.LANA, 2);
        jugador.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador.recibirRecurso(RecursoTipo.MINERAL, 3);

        jugador.descartarMitad();
        assertEquals(6, jugador.cantidadDeCartas());
    }
    @Test
    void conTresCartas_noDescartaAlSalir7() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(RecursoTipo.MADERA, 1);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 1);
        jugador.recibirRecurso(RecursoTipo.LANA, 1);

        jugador.descartarMitad();
        assertEquals(3, jugador.cantidadDeCartas());
    }
    @Test
    void totalMenorA7_noDescarta() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(RecursoTipo.MADERA, 3);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 3);
        jugador.descartarMitad();

        assertEquals(6, jugador.cantidadDeCartas());
    }

    @Test
    void totalNueve_descartaCuatro_yQuedanCinco() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(RecursoTipo.MADERA, 5);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 4);
        jugador.descartarMitad();

        assertEquals(5, jugador.cantidadDeCartas());
    }

    @Test
    void totalDiez_descartaCinco_yQuedanCinco() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(RecursoTipo.GRANO, 6);
        jugador.recibirRecurso(RecursoTipo.LANA, 4);
        jugador.descartarMitad();
        assertEquals(5, jugador.cantidadDeCartas());
    }
    
    @Test
    void conCincoDeCadaRecurso_descartaMitad_yQuedanTrece() {

        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(RecursoTipo.MADERA, 5);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 5);
        jugador.recibirRecurso(RecursoTipo.LANA, 5);
        jugador.recibirRecurso(RecursoTipo.GRANO, 5);
        jugador.recibirRecurso(RecursoTipo.MINERAL, 5);

        jugador.descartarMitad();
        assertEquals(13, jugador.cantidadDeCartas());
    }
    @Test
    void jugadorCon9CartasDescarta4() {
        Jugador jugador = new Jugador("jole");

        jugador.recibirRecurso(RecursoTipo.MADERA, 5);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 4);

        jugador.descartarMitad();

        assertEquals(5, jugador.cantidadDeCartas());
    }
    @Test
    void totaligualA7_NoDescarta() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(RecursoTipo.MADERA, 3);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 4);
        jugador.descartarMitad();

        assertEquals(7, jugador.cantidadDeCartas());

    }
    @Test
    void tieneOchoCartas_alSalir7_descartaLaMitad() {
        Jugador jugador = new Jugador("mike");

        jugador.recibirRecurso(RecursoTipo.MADERA, 2);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 2);
        jugador.recibirRecurso(RecursoTipo.LANA, 2);
        jugador.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador.descartarMitad();
        assertEquals(4, jugador.cantidadDeCartas());
    }
}
