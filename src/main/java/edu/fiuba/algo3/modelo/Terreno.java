package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class Terreno {
    private int fichaDeNumero;

    public Terreno(int ficha){
        this.fichaDeNumero = ficha;
    }
    public boolean esMiNumero( int resultadoDados) {
        return resultadoDados == fichaDeNumero;
    }

    public abstract void repartirRecurso(List<Pieza> edificios);
    
}