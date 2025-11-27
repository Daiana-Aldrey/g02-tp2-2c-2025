package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class Main {
    public static void main(String[] args) {
    	Tablero tablero = Tablero.getInstance();
        tablero.reset();
        /*
    	List<String> nombresJugadores = List.of("Juli", "Valen", "Sofi");
    	Juego juego = new Juego(3, nombresJugadores);
    	juego.inicializarPoblados();
    	tablero.mostrarPiezas();
         */
    }
}
