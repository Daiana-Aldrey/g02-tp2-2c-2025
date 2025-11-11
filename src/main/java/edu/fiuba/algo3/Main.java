package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Grafo;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.TableroCatan;
import edu.fiuba.algo3.modelo.Vertice;

public class Main {
    public static void main(String[] args) {
    	Tablero tablero = Tablero.getInstance();
    	tablero.crearGrafo();
    	tablero.mostrarGrafo();
    }	

}
