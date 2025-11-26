package edu.fiuba.algo3.testUnitarios;

import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.*;


public class TestUtilidades {
	/**
    * @param cantidad El número de jugadores a crear.
    * @return Una lista de objetos Jugador.
    */
   public static List<Jugador> generarJugadores(int cantidad) {
       List<Jugador> jugadores = new ArrayList<>();
       for (int i = 1; i <= cantidad; i++) {
           jugadores.add(new Jugador("Jugador" + i));
       }
       return jugadores;
   }
}
