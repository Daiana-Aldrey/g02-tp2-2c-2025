package edu.fiuba.algo3.modelo;

import java.util.List;

public class Campo extends Terreno{

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            int cant = pieza.produccion();
            pieza.agregarRecursos(RecursoTipo.GRANO,cant);
        }
    }
}