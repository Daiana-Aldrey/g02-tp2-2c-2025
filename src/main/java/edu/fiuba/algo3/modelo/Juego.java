package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.CartaDeBonificacion.BonificadorRutaMayor;
import edu.fiuba.algo3.modelo.CartaDeBonificacion.CartaGranCaballeria;
import edu.fiuba.algo3.modelo.CartaDeBonificacion.RutaMayor;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.Intercambio.Banco;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Ronda.*; 
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.Excepciones.*;
import java.util.*;

public class Juego {
    private final Tablero tablero;
    private final Banco banco;
    private List<Jugador> jugadores;
    private final CartaGranCaballeria cartaGranCaballeria;
    private final int cantJugadores;
    private OrganizadorDeTurnos organizador;
    private final Dados dados;
    private final BonificadorRutaMayor bonificadorRutaMayor;

    public Juego(List<Jugador> jugadores) {
        this.cantJugadores = jugadores.size();
        this.jugadores = jugadores;
        this.tablero = Tablero.getInstance();
        this.cartaGranCaballeria = CartaGranCaballeria.getInstance();
        this.bonificadorRutaMayor = new BonificadorRutaMayor();
        this.banco = new Banco();
        this.dados = new Dados(2);
        
        this.organizador = new OrganizadorDeTurnos(jugadores);

        validarCantJugadores(cantJugadores);
        setearRutasParaBonificador();
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

    public int cantidadJugadores(){
        return cantJugadores;
    }

    public void manejarTirada(int n) {
    	if (n == 7) {
            for (Jugador j : jugadores) {
                j.descartarMitad();
            }
        } else {
            tablero.cosechar(n);
        }
    }
    
    /*private void aplicarEventoSiete() {
        for (Jugador j : jugadores) {
            j.descartarMitad();
        }
        UbicacionVertice destino = new UbicacionVertice('B');
        Jugador victima = jugadores.get(1); 
        
        jugadorActual().moverLadron(destino, victima);
    }*/

    public List<Jugador> jugadores() {
        return jugadores;
    }

    public void comprarCartaDesarrollo() {
        banco.venderCartaDesarrollo(jugadorActual());
    }

    public void finalizarTurnoActual() {
        jugadorActual().prepararCartasDesarrolloParaNuevoTurno();
    }

    public boolean verificarVictoria(){
        for(Jugador jugador : jugadores){
            if(jugador.gano()){
                return true;
            }
        }
        return false;
    }

    public void jugarTurno(){
        int resultadoDados = dados.sumarTirada();
        manejarTirada(resultadoDados);

        jugadorActual().turno();
        finalizarTurnoActual();
    }

    public void setearRutasParaBonificador() {
        for (Jugador jugador : jugadores){
            jugador.incluirRuta(bonificadorRutaMayor);
        }
    }

    public void pasarAlSiguienteJugador() {
        organizador.siguienteTurno();
    }

    public void jugar() {
        while (!verificarVictoria()) {
            jugarTurno(); 
            pasarAlSiguienteJugador();
        }
    }
    
    public Jugador jugadorActual() {
        return organizador.jugadorActual();
    }

    public List<Recurso> recursosJugadorActual(){
        return jugadorActual().recursos();
    }
    
    public boolean esFaseInicial() {
        return organizador.esFaseInicial();
    }

	public CartaGranCaballeria obtenerCartaGranCaballeria(){
		return cartaGranCaballeria;
	}

    public BonificadorRutaMayor obtenerBonificadorRutaMayor(){
        return bonificadorRutaMayor;
    }

}

