package edu.fiuba.algo3.modelo.Terreno;

import edu.fiuba.algo3.modelo.Pieza.Pieza;

import java.util.List;

public abstract class Terreno {

    public boolean sosEsteTerreno(String terreno) {
        return this.getClass().getSimpleName().equals(terreno);
    }


    public abstract void repartirRecurso(List<Pieza> edificios);
    
}