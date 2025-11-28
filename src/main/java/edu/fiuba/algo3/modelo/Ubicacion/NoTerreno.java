package edu.fiuba.algo3.modelo.Ubicacion;

import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Terreno.*;

import java.util.List;

public class NoTerreno extends Terreno {

    public NoTerreno() {
        super();
    }

    @Override
    public void repartirRecurso(List<Pieza> edificios) {
        throw new UnsupportedOperationException("Error: No se puede repartir recurso desde un Terreno Nulo.");
    }
}