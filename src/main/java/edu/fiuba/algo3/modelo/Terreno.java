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

    public abstract String tipoRecurso();

    public void cosecharRecursos(List<Pieza> edificios) {
        for (Pieza edificio : edificios) {
            //if (edificio.esCasa())
            edificio.agregarRecursos(this.tipoRecurso(),1);
            //else {
            //edificio.agregarRecursos(this.tipoRecurso(),2); }
        }
    }
}