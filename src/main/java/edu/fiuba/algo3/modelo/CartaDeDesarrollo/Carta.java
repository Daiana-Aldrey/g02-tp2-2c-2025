package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.modelo.Jugador;

public abstract class Carta {
/*private String nombre;

	protected void setNombre(String nombre) {
	        this.nombre = nombre;
	 } */

	public abstract void usar(Jugador jugador);

	public int puntosDeVictoriaOcultos() {
		return 0;
	}




}
