package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Tablero;
;

public class Main {
    public static void main(String[] args) {
    	Tablero tablero = Tablero.getInstance();
    	tablero.crearGrafo();
    	tablero.mostrarGrafo();
    }	

}
