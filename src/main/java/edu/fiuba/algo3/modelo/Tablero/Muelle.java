package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Ubicacion.*;
import edu.fiuba.algo3.modelo.*;

public class Muelle {
    private final UbicacionVertice ubicacion;
    private Pieza pieza; 
    
    public Muelle(UbicacionVertice ubicacion) {
        this.ubicacion = ubicacion;
        this.pieza = new NoPieza(ubicacion);
    }

    public void notificarConstruccion(UbicacionVertice ubicacion, Pieza pieza) {
        if (this.ubicacion.equals(ubicacion)) {
            this.pieza = pieza;
        }
    }

    public boolean perteneceAJugador(Jugador jugador) {
        return pieza.esDe(jugador);
    }
    
    public UbicacionVertice getUbicacion() {
        return ubicacion;
    }

}
