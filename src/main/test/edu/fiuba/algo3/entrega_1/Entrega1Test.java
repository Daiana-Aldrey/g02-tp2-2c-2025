package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class Entrega1Test {

    @Test
    public void seCreaTableroConJugadoresSeValidaLaReglaDeLaDistanciaEnCasilleroConPobladoYTiraExcepcion() {

        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo();

        Jugador luis = new Jugador("Luis");
        Jugador federico = new Jugador("Federico");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));

        assertThrows(IllegalArgumentException.class, () -> {
            federico.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));;
        });
    }

    @Test
    public void seCreaTableroConJugadoresSeValidaLaReglaDeLaDistanciaEnCasilleroAdyacenteAlPobladoYTiraExcepcion() {

        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo();

        Jugador luis = new Jugador("Luis");
        Jugador federico = new Jugador("Federico");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));

        assertThrows(IllegalArgumentException.class, () -> {
            federico.colocarPiezaInicial("poblado", new ArrayList<>(List.of(2)));;
        });
    }

    @Test
    public void seCreaTableroConJugadoresSeValidaLaReglaDeLaDistancia() {

        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo();

        Jugador luis = new Jugador("Luis");
        Jugador federico = new Jugador("Federico");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(1)));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(1, 2)));
        federico.colocarPiezaInicial("poblado", new ArrayList<>(List.of(15)));;
        federico.colocarPiezaInicial("camino", new ArrayList<>(List.of(15, 16)));
    }
}
