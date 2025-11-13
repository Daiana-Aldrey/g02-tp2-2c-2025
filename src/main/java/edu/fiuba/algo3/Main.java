package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.controllers.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
    	Tablero tablero = TableroCatan.getInstance();
    	tablero.crearGrafo();
        tablero.mostrarGrafo();
        /*
    	List<String> nombresJugadores = List.of("Juli", "Valen", "Sofi");
    	Juego juego = new Juego(3, nombresJugadores);
    	juego.inicializarPoblados();
    	tablero.mostrarPiezas();
         */
    }
}
