package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.*;
import java.util.*;

public class Juego {
	  private final Tablero tablero;
	  private final Banco banco;
	  private final List<Jugador> jugadores;
	  private final int turno;
	  private final int maxTurno;
	  private int rondas;
	  private final GeneradorDeDados generador;

	public Juego(int cantJugadores, List<String> nombres) {
		this(cantJugadores, nombres, new DosDados());
	}

	public Juego(int cantJugadores, List<String> nombres, GeneradorDeDados generador) {
		this.jugadores = new ArrayList<Jugador>();
		this.turno = 0;
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
	}
	
	private void validarCantJugadores(int cantidad) {
		if (cantidad < 3 || cantidad > 4) {
			throw new IllegalArgumentException("La cantidad de jugadores debe estar entre 3 y 4");
	    }
	}

	/*public int tirarDado() {
		int n = generador.tirar();
		if (n < 2 || n > 12) {
			throw new IllegalStateException("Tirada fuera de rango: " + n);
		}
		return n;
	}*/
		
	//OPCION TIRAR DADOS RANDOMS, NUNCA VA A HABER TIRADA FUERA DE RANGO
	public int tirarDados() {
		Random random = new Random();
		int numero = random.nextInt(11) + 2;
		return numero;
	}

	public void inicializarPoblados() {
		for(int i = 0; i < maxTurno; i++)	{
			jugadores.get(i).elegirColocazionPieza("poblado");
		}
	}
	
	public void siguienteRonda() {
		if(rondas == 0) {
			inicializarPoblados();
		}
		int numDados = tirarDados();
		tablero.cosechar(numDados);
		
		for(int i = 0; i < maxTurno; i++) {
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

}

