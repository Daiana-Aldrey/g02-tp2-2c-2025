package edu.fiuba.algo3.modelo;

import java.util.List;

public class Colina extends Terreno{
    public Colina (int ficha){
        super(ficha);
    }

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            pieza.agregarRecursos(RecursoTipo.LADRILLO,1);
        }
    }
}
