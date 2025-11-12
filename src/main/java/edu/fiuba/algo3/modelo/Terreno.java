package edu.fiuba.algo3.modelo;

import java.util.List;

public class Terreno {
    private int fichaDeNumero;

    public Terreno(int ficha){
        this.fichaDeNumero = ficha;
    }
    public boolean esMiNumero( int resultadoDados) {
        return resultadoDados == fichaDeNumero;
    }

    public void cosecharRecursos(List<Pieza> edificios) {
        for (Pieza edificio : edificios) {
            //if (edificio.esCasa())
            edificio.agregarRecursos("mineral",1);
            //else {
            //edificio.agregarRecursos("mineral",2); }
        }
    }
}
