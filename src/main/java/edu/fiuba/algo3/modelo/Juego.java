package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.CartaDeBonificacion.CartaGranCaballeria;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.Intercambio.Banco;
import edu.fiuba.algo3.modelo.RondaInicial.*;
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
	  private final Dados dados;


	public Juego(List<Jugador> jugadores) {

		this.cantJugadores = jugadores.size();
		this.jugadores = jugadores;
		this.tablero = Tablero.getInstance();
        this.cartaGranCaballeria = CartaGranCaballeria.getInstance();
		this.banco = new Banco();
		this.rondas = 0;
		this.dados = new Dados(2);

		validarCantJugadores(cantJugadores);
		this.jugadorTurno = jugadores.get(0);
	}
	
	private void validarCantJugadores(int cantidad) {
		if (cantidad < 3 || cantidad > 4) {
			throw new CantJugadoresInvalida("La cantidad de jugadores debe estar entre 3 y 4");
	    }
	}

	public int[] tirarDados() {
        return dados.tirar();
    }
	
	public int sumarTirada() {
		return dados.sumarTirada();
	}

	public void inicializarPiezas(List<List<Ubicacion>> verticesPoblados, List<List<Ubicacion>> verticesCaminos) {
		for (int i = 0; i < cantJugadores; i++) {
			Jugador jugador = jugadores.get(i);
			jugador.colocarPiezaInicial("poblado", verticesPoblados.get(i));
			
			jugador.colocarPiezaInicial("camino", verticesCaminos.get(i));
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

    public void colocacionInicial(
            List<List<Ubicacion>> pobladosR1,
            List<List<Ubicacion>> caminosR1,
            List<List<Ubicacion>> pobladosR2,
            List<List<Ubicacion>> caminosR2) {

        RondaColocacion ronda1 = new RondaOrdenada();
        RondaColocacion ronda2 = new RondaInversa();

        ronda1.ejecutarRonda(jugadores, pobladosR1, caminosR1);
        ronda2.ejecutarRonda(jugadores, pobladosR2, caminosR2);
    }

    private boolean verificarVictoria(){
        for(Jugador jugador : jugadores){
            if(jugador.gano()){
                return true;
            }
        }
        return false;
    }

    public void jugarTurno(Jugador jugadorActual){
        this.jugadorTurno = jugadorActual;

        int resultadoDados = dados.sumarTirada();
        manejarTirada(resultadoDados);

        jugadorTurno.turno();
        finalizarTurnoActual();
    }

    private void pasarAlSiguienteJugador() {
        int indiceActual = jugadores.indexOf(jugadorTurno);
        int siguiente = (indiceActual + 1) % cantJugadores;
        jugadorTurno = jugadores.get(siguiente);
    }

    public void jugar() {
        while (!verificarVictoria()) {
            jugarTurno(jugadorTurno);
            pasarAlSiguienteJugador();
        }
    }
    
    
    public Jugador jugadorActual() {
        return jugadorTurno;
    }
}

