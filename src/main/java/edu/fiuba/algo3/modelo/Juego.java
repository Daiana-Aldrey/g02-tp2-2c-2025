package edu.fiuba.algo3.modelo;
import java.util.*;

public class Juego {
	  private final Tablero tablero;
	  private final Banco banco;
	  private final List<Jugador> jugadores;
	  private Jugador jugadorTurno;
	  private final int maxTurno;
	  private int rondas;
	  private final GeneradorDeDados generador;

	  

	public Juego(int cantJugadores, List<String> nombres, GeneradorDeDados generador) {
		this.jugadores = new ArrayList<Jugador>();
		this.maxTurno = cantJugadores;
		this.tablero = Tablero.getInstance();
		this.banco = new Banco();
		this.rondas = 0;
		this.generador = generador;
		
		validarCantJugadores(cantJugadores);
		for(int i = 0; i < cantJugadores; i++)	{
			Jugador jugador = new Jugador(nombres.get(i));
			jugadores.add(jugador);
		}
		
		this.jugadorTurno = jugadores.get(0);
	}
	
	private void validarCantJugadores(int cantidad) {
		if (cantidad < 3 || cantidad > 4) {
			throw new IllegalArgumentException("La cantidad de jugadores debe estar entre 3 y 4");
	    }
	}

	public int tirarDado() {
		int n = generador.tirar();
		if (n < 2 || n > 12) {
			throw new IllegalStateException("Tirada fuera de rango: " + n);
		}
		return n;
	}
	
	public void inicializarPiezas(List<List<Integer>> verticesPoblados, List<List<Integer>> verticesCaminos) {
		for (int i = 0; i < maxTurno; i++) {
			Jugador jugador = jugadores.get(i);
			jugador.colocarPiezaInicial("poblado", verticesPoblados.get(i));
			
			jugador.colocarPiezaInicial("camino", verticesCaminos.get(i));
		}
	}
	
	public void siguienteRonda() {
		for(int i = 0; i < maxTurno; i++) {
			jugadorTurno = jugadores.get(i);
			int numDados = tirarDado();
			manejarTirada(numDados); 
			jugadores.get(i).turno();
		}
	}
	
	public void Jugar() {
		while(rondas <2) {
			siguienteRonda();
			rondas ++;
		}
	}
	
	public int cantidadJugadores(){
		return maxTurno;
	}

	public void manejarTirada(int n) {
		if (n == 7) {
			aplicarEventoSiete();
		} else {
			tablero.cosechar(n);
		}
	}


	private void aplicarEventoSiete() {
		for (Jugador j : jugadores) {
			j.descartarMitad();
		}
		char destino = 'B'; //esto se preguntara por controlador
		jugadorTurno.moverLadron(destino);
	}

	public List<Jugador> jugadores() {
		return jugadores;
	}

}

