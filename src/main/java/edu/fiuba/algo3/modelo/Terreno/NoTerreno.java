package edu.fiuba.algo3.modelo.Terreno;

import edu.fiuba.algo3.Excepciones.ObjetoNoUsable;
import edu.fiuba.algo3.modelo.Pieza.Pieza;

import java.util.List;

public class NoTerreno extends Terreno {

    public NoTerreno() {
        super();
    }

    @Override
    public void repartirRecurso(List<Pieza> edificios) {
        throw new ObjetoNoUsable("Error: No se puede repartir recurso desde un Terreno Nulo.");
    }
}