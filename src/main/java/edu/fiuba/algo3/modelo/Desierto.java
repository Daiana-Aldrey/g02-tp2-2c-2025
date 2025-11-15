package edu.fiuba.algo3.modelo;

import java.util.List;

public class Desierto extends Terreno { 
	public Desierto() { 
		super(0); 
	}
	
	@Override
	public void repartirRecurso(List<Pieza> edificios) {
	    System.out.print("El desierto no reparte recursos");
	}

}
