package edu.fiuba.algo3.modelo;

import java.util.List;

public class Montania extends Terreno{
    public Montania(int ficha){
        super(ficha);
    }

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            //if (pieza.esCasa())
            pieza.agregarRecursos("mineral",1);
        }
    }
}
