package edu.fiuba.algo3.modelo.Terreno;

import edu.fiuba.algo3.modelo.Pieza.Pieza;

import java.util.List;

public class Desierto extends Terreno {
	
	@Override
	public void repartirRecurso(List<Pieza> edificios) {
	    System.out.print("El desierto no reparte recursos");
	}

}
