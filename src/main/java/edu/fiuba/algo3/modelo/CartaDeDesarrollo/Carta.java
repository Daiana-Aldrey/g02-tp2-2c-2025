package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.modelo.Jugador;

public abstract class Carta {
    protected String nombre;
    protected String descripcion;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }


	public abstract void usar(Jugador jugador);

	public int puntosDeVictoriaOcultos() {
		return 0;
	}

}
