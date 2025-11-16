package edu.fiuba.algo3.modelo;

import java.util.List;

public class Pastizal extends Terreno{
    public Pastizal(int ficha){
        super(ficha);
    }

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            int cant = pieza.produccion();
            pieza.agregarRecursos(RecursoTipo.LANA,cant);
        }
    }
}
