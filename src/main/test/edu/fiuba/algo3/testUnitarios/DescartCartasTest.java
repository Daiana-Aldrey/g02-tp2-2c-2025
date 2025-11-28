package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Dados.GeneradorDeDados;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescartCartasTest {

    @Test
    void conDosDeCadaRecurso_alSalir7_descartaLaMitad() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        GeneradorDeDados dado =()-> 7;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(new Madera(), 2);
        jugador.recibirRecurso(new Ladrillo(), 2);
        jugador.recibirRecurso(new Lana(), 2);
        jugador.recibirRecurso(new Grano(), 2);
        jugador.recibirRecurso(new Mineral(), 2);

        int n = juego.tirarDado();
        juego.manejarTirada(n);
        //10 descarta 5 quedan 5
        assertEquals(5, jugador.cantidadDeCartas());
    }
    @Test
    void conTresCartas_noDescartaAlSalir7() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        GeneradorDeDados dado =()-> 7;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);

        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(new Madera(), 1);
        jugador.recibirRecurso(new Ladrillo(), 1);
        jugador.recibirRecurso(new Lana(), 1);

        juego.manejarTirada(juego.tirarDado());

        assertEquals(3, jugador.cantidadDeCartas());
    }
    @Test
    void totalMenorA7_noDescarta() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        GeneradorDeDados dado =()-> 7;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);

        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(new Madera(), 3);
        jugador.recibirRecurso(new Ladrillo(), 3);

        juego.manejarTirada(juego.tirarDado());

        assertEquals(6, jugador.cantidadDeCartas());
    }

    @Test
    void totalNueve_descartaCuatro_yQuedanCinco() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        
        GeneradorDeDados dado =()-> 7;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(new Madera(), 5);
        jugador.recibirRecurso(new Ladrillo(), 4);

        juego.manejarTirada(juego.tirarDado());
        //9 descarta 4
        assertEquals(5, jugador.cantidadDeCartas());
    }

    @Test
    void totalDiez_descartaCinco_yQuedanCinco() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        GeneradorDeDados dado =()-> 7;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(new Grano(), 6);
        jugador.recibirRecurso(new Lana(), 4);

        juego.manejarTirada(juego.tirarDado());

        assertEquals(5, jugador.cantidadDeCartas());
    }
    
    @Test
    void conCincoDeCadaRecurso_descartaMitad_yQuedanTrece() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        GeneradorDeDados dado =()-> 7;
    	List<Jugador> jugadores = TestUtilidades.generarJugadores(3);
        Juego juego = new Juego(jugadores, dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(new Madera(), 5);
        jugador.recibirRecurso(new Ladrillo(), 5);
        jugador.recibirRecurso(new Lana(), 5);
        jugador.recibirRecurso(new Grano(), 5);
        jugador.recibirRecurso(new Mineral(), 5);

        juego.manejarTirada(juego.tirarDado());

        assertEquals(13, jugador.cantidadDeCartas());
    }

}
