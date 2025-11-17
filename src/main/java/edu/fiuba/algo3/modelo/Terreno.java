package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class Terreno {
    //metodo simplemente para corroborar test integral
    public boolean sosEsteTerreno(String terreno) {
        return this.getClass().getSimpleName().equals(terreno);
    }


    public abstract void repartirRecurso(List<Pieza> edificios);
    
}