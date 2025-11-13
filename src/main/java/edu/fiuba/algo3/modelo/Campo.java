package edu.fiuba.algo3.modelo;

import java.util.List;

public class Campo extends Terreno{
    public Campo(int ficha){
        super(ficha);
    }

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            //if (pieza.esCasa())
            pieza.agregarRecursos("GRANO",1);
        }
    }
}