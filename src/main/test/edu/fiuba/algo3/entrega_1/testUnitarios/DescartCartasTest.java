package edu.fiuba.algo3.entrega_1.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescartCartasTest {

    @Test
    void conDosDeCadaRecurso_alSalir7_descartaLaMitad() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Juego juego = new Juego(3, List.of("Lu", "Bren", "Caro"), ()-> 7);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(RecursoTipo.MADERA, 2);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 2);
        jugador.recibirRecurso(RecursoTipo.LANA, 2);
        jugador.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador.recibirRecurso(RecursoTipo.MINERAL, 2);

        int n = juego.tirarDado();
        juego.manejarTirada(n);
        //10 descarta 5 quedan 5
        assertEquals(5, jugador.cantidadDeCartas());
    }
    @Test
    void conTresCartas_noDescartaAlSalir7() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Juego juego = new Juego(3, List.of("Lu", "Bren", "Caro"),()-> 7);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(RecursoTipo.MADERA, 1);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 1);
        jugador.recibirRecurso(RecursoTipo.LANA, 1);

        juego.manejarTirada(juego.tirarDado());

        //  3  NO descarta
        assertEquals(3, jugador.cantidadDeCartas());
    }
    @Test
    void totalMenorA7_noDescarta() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Juego juego = new Juego(3, List.of("Lu", "Bren", "Caro"),  ()-> 7);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(RecursoTipo.MADERA, 3);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 3);

        juego.manejarTirada(juego.tirarDado());

        assertEquals(6, jugador.cantidadDeCartas());
    }

    @Test
    void totalNueve_descartaCuatro_yQuedanCinco() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Juego juego = new Juego(3, List.of("Lu", "Bren", "Caro"),()-> 7);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(RecursoTipo.MADERA, 5);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 4);

        juego.manejarTirada(juego.tirarDado());
        //9 descarta 4
        assertEquals(5, jugador.cantidadDeCartas());
    }

    @Test
    void totalDiez_descartaCinco_yQuedanCinco() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Juego juego = new Juego(3, List.of("Lu", "Bren", "Caro"), ()-> 7);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(RecursoTipo.GRANO, 6);
        jugador.recibirRecurso(RecursoTipo.LANA, 4);

        juego.manejarTirada(juego.tirarDado());

        assertEquals(5, jugador.cantidadDeCartas());
    }
    @Test
    void conCincoDeCadaRecurso_descartaMitad_yQuedanTrece() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Juego juego = new Juego(3, List.of("Lu", "Bren", "Caro"),()-> 7);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(RecursoTipo.MADERA, 5);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 5);
        jugador.recibirRecurso(RecursoTipo.LANA, 5);
        jugador.recibirRecurso(RecursoTipo.GRANO, 5);
        jugador.recibirRecurso(RecursoTipo.MINERAL, 5);

        juego.manejarTirada(juego.tirarDado());

        // 25 descarta 12
        assertEquals(13, jugador.cantidadDeCartas());
    }

}
