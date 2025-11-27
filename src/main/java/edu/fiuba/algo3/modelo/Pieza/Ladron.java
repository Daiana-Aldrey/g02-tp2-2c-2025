package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.Ubicacion.NoUbicacion;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

public class Ladron {

    private Ubicacion ubicacion;

    public Ladron() {
        this.ubicacion = new NoUbicacion();
    }

    public void moverA(UbicacionVertice destino) {
        this.ubicacion = destino;
    }

    public boolean tieneUbicacion(UbicacionVertice ubicacion) {
        return (ubicacion.equals(this.ubicacion));
    }
}
