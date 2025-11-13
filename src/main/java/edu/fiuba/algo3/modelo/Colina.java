package edu.fiuba.algo3.modelo;

import java.util.List;

public class Colina extends Terreno{
    public Colina (int ficha){
        super(ficha);
    }

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            //if (pieza.esCasa())
            pieza.agregarRecursos("ladrillo",1);
        }
    }
}
