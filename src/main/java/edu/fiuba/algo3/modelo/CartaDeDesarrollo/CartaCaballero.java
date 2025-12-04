package edu.fiuba.algo3.modelo.CartaDeDesarrollo;
import edu.fiuba.algo3.Excepciones.ErrorNoUsoDeCartaInvalido;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;


public class CartaCaballero extends Carta {
    static final int NOGANAPUNTOS = 0;
    private UbicacionVertice destino;
    private Jugador victima;
    private boolean configurada = false;

    public void configurar(UbicacionVertice destino, Jugador victima) {
        // Asumimos que la UI valida que no sean nulos / inválidos.
        this.destino = destino;
        this.victima = victima;
        this.configurada = true;
    }

    @Override
    public void usar(Jugador jugadorQueJuegaLaCarta) {
        if (!configurada) {
            throw new ErrorNoUsoDeCartaInvalido("configurar Caballero antes de usarlo.");
        }
        jugadorQueJuegaLaCarta.moverLadron(destino, victima);
        jugadorQueJuegaLaCarta.registrarCaballeroJugado();
    }

    public int puntosDeVictoriaOcultos() {
        return NOGANAPUNTOS;
    }

}