package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.CartaDeBonificacion.CartaGranCaballeria;
import edu.fiuba.algo3.modelo.Dados.GeneradorDeDados;
import edu.fiuba.algo3.modelo.Intercambio.Banco;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.Excepciones.*;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.modelo.CartaDeBonificacion.CartaGranCaballeria;
import java.util.*;

public class Juego {
	  private final Tablero tablero;
	  private final Banco banco;
	  private List<Jugador> jugadores;
      private final CartaGranCaballeria cartaGranCaballeria;
	  private Jugador jugadorTurno;
	  private final int cantJugadores;
	  private int rondas;
	  private final GeneradorDeDados generador;

	  

	public Juego(List<Jugador> jugadores, GeneradorDeDados generador) {
		this.jugadores = new ArrayList<Jugador>();
		this.cantJugadores = jugadores.size();
		this.jugadores = jugadores;
		this.tablero = Tablero.getInstance();
        this.cartaGranCaballeria = CartaGranCaballeria.getInstance();
        this.cartaGranCaballeria.setearJugadores(jugadores);
		this.banco = new Banco();
		this.rondas = 0;
		this.generador = generador;
		
		validarCantJugadores(cantJugadores);
		this.jugadorTurno = jugadores.get(0);
	}
	
	private void validarCantJugadores(int cantidad) {
		if (cantidad < 3 || cantidad > 4) {
			throw new CantJugadoresInvalida("La cantidad de jugadores debe estar entre 3 y 4");
	    }
	}

	public int tirarDado() {
		return generador.tirar();
	}

	public void inicializarPiezas(List<List<Ubicacion>> verticesPoblados, List<List<Ubicacion>> verticesCaminos) {
		for (int i = 0; i < cantJugadores; i++) {
			Jugador jugador = jugadores.get(i);
			jugador.colocarPiezaInicial("poblado", verticesPoblados.get(i));
			
			jugador.colocarPiezaInicial("camino", verticesCaminos.get(i));
		}
	}
	
	public void siguienteRonda() {
		for(int i = 0; i < cantJugadores; i++) {
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
		return cantJugadores;
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
		UbicacionVertice destino = new UbicacionVertice('B');
		Jugador victima = jugadores.get(1);
		jugadorTurno.moverLadron(destino,victima);
	}

	public List<Jugador> jugadores() {
		return jugadores;
	}

	public void comprarCartaDesarrollo() {
		banco.venderCartaDesarrollo(jugadorTurno);
	}

	public void finalizarTurnoActual() {
		jugadorTurno.prepararCartasDesarrolloParaNuevoTurno();
		// nos va a servir mas adelante
	}

}

