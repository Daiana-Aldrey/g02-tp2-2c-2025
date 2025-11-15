package edu.fiuba.algo3.modelo;

import java.util.List;

public class Bosque extends Terreno{
    public Bosque(int ficha){
        super(ficha);
    }

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            pieza.agregarRecursos(RecursoTipo.MADERA,1);
        }
    }
}
