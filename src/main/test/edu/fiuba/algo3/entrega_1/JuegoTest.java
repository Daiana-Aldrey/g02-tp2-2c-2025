package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.controllers.Juego;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JuegoTest {

    @Test
    public void juegoInicializaJugadoresCorrectamente() {
        Tablero.getInstance().crearGrafo();

        List<String> nombres = List.of("Juli", "Valen", "Sofi");
        Juego juego = new Juego(3, nombres);

        Assertions.assertEquals(3, juego.cantidadJugadores());
    }

    @Test
    public void juegoLanzaExcepcionSiHayMenosDeTresJugadores() {
        List<String> nombres = List.of("A", "B");

        assertThrows(IllegalArgumentException.class, () -> {new Juego(2, nombres);});
    }

    @Test
    public void juegoLanzaExcepcionSiHayMasDeCuatroJugadores() {
        List<String> nombres = List.of("A", "B", "C", "D", "E");

        assertThrows(IllegalArgumentException.class, () -> {new Juego(5, nombres);});
    }

    @Test
    public void colocarPobladosIncialesEnTablero() {
        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo();

        List<String> nombres = List.of("Juli", "Valen", "Sofi");
        Juego juego = new Juego(3, nombres);

        juego.inicializarPiezas();
        Assertions.assertEquals(3, tablero.cantidadPiezas());
    }
    
    
  }


