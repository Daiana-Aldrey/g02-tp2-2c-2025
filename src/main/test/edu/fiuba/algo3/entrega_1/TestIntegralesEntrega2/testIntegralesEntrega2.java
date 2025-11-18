package edu.fiuba.algo3.entrega_1.TestIntegralesEntrega2;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class testIntegralesEntrega2 {

    @Test
    public void validacionDelConsumoDeRecursosYLaCorrectaColocacionDeUnaCarretera() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador marcelo = new Jugador("Marcelo");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));

        marcelo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(18)));
        marcelo.colocarPiezaInicial("camino", new ArrayList<>(List.of(18, 17)));

        luis.recibirRecurso(RecursoTipo.MADERA, 10);
        luis.recibirRecurso(RecursoTipo.LADRILLO, 10);
        luis.recibirRecurso(RecursoTipo.LANA, 10);
        luis.recibirRecurso(RecursoTipo.GRANO, 10);

        marcelo.recibirRecurso(RecursoTipo.MADERA, 2);
        marcelo.recibirRecurso(RecursoTipo.LADRILLO, 2);
        marcelo.recibirRecurso(RecursoTipo.LANA, 2);
        marcelo.recibirRecurso(RecursoTipo.GRANO, 2);

        luis.construirPieza("camino", new ArrayList<>(List.of(1, 9)));
        marcelo.construirPieza("camino", new ArrayList<>(List.of(18, 19)));

        Assertions.assertTrue(tablero.hayPieza(List.of(1, 9)));
        Assertions.assertTrue(tablero.hayPieza(List.of(18, 19)));

        assertEquals(9, luis.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(10, luis.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(10, luis.buscarRecurso(RecursoTipo.GRANO).cantidad());

        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(2, marcelo.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(2, marcelo.buscarRecurso(RecursoTipo.GRANO).cantidad());

    }

    @Test
    public void validacionDelConsumoDeRecursosYValidacionDeLaReglaDeLaDistanciaAlConstruirPoblado() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador marcelo = new Jugador("Marcelo");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));

        marcelo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(18)));
        marcelo.colocarPiezaInicial("camino", new ArrayList<>(List.of(18, 17)));

        luis.recibirRecurso(RecursoTipo.MADERA, 10);
        luis.recibirRecurso(RecursoTipo.LADRILLO, 10);
        luis.recibirRecurso(RecursoTipo.LANA, 10);
        luis.recibirRecurso(RecursoTipo.GRANO, 10);

        marcelo.recibirRecurso(RecursoTipo.MADERA, 2);
        marcelo.recibirRecurso(RecursoTipo.LADRILLO, 2);
        marcelo.recibirRecurso(RecursoTipo.LANA, 2);
        marcelo.recibirRecurso(RecursoTipo.GRANO, 2);

        luis.construirPieza("poblado", new ArrayList<>(List.of(4)));
        marcelo.construirPieza("poblado", new ArrayList<>(List.of(20)));

        Assertions.assertTrue(tablero.hayPieza(List.of(4)));
        Assertions.assertTrue(tablero.hayPieza(List.of(20)));

        assertEquals(9, luis.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(9, luis.buscarRecurso(RecursoTipo.GRANO).cantidad());

        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(1, marcelo.buscarRecurso(RecursoTipo.GRANO).cantidad());

        assertThrows(IllegalArgumentException.class, () -> {
            luis.construirPieza("poblado", new ArrayList<>(List.of(21)));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            marcelo.construirPieza("poblado", new ArrayList<>(List.of(5)));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            luis.construirPieza("poblado", new ArrayList<>(List.of(1)));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            marcelo.construirPieza("poblado", new ArrayList<>(List.of(3)));;
        });
    }
}

