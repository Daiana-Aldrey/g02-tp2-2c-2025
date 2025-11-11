package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.*;
import java.util.*;

public class Juego {
	  private final Tablero tablero;
	  private final Banco banco;
	  private final List<Jugador> jugadores;
	  private final int turno;
	  private final int maxTurno;
	  private final Ronda ronda;

	
	public Juego(int cantJugadores, List<String> nombres) {
		this.jugadores = new ArrayList<Jugador>();
		this.turno = 0;
		this.maxTurno = cantJugadores;
		this.tablero = Tablero.getInstance();
		this.banco = new Banco();
		
		validarCantJugadores(cantJugadores);
		for(int i = 0; i < cantJugadores; i++)	{
			Jugador jugador = new Jugador(nombres.get(i));
			jugadores.add(jugador);
		}
	}
	
	private void validarCantJugadores(int cantidad) {
		if (cantidad < 3 || cantidad > 4) {
			throw new IllegalArgumentException("La cantidad de jugadores debe estar entre 3 y 4");
	    }
	}
	
	

}

