package edu.fiuba.algo3.modelo.CartaDeDesarrollo;
import edu.fiuba.algo3.Excepciones.ErrorNoUsoDeCartaInvalido;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;


public class CartaCaballero extends Carta {
    static final int NOGANAPUNTOS = 0;
    private UbicacionVertice destino;
    private Jugador victima;

    public CartaCaballero() {
        nombre = "Caballero";
        descripcion = "Permite mover el ladrón y robar un recurso de un jugador adyacente.";
    }
    @Override
    public void configurarLadron(UbicacionVertice destino, Jugador victima) {
        this.destino = destino;
        this.victima = victima;
    }

    @Override
    public void usar(Jugador jugadorQueJuegaLaCarta) {

        jugadorQueJuegaLaCarta.moverLadron(destino, victima);
        jugadorQueJuegaLaCarta.registrarCaballeroJugado();
    }

    public int puntosDeVictoriaOcultos() {
        return NOGANAPUNTOS;
    }

}