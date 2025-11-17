package edu.fiuba.algo3.modelo;

import java.util.List;

public class Bosque extends Terreno{

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            int cant = pieza.produccion();
            pieza.agregarRecursos(RecursoTipo.MADERA,cant);
        }
    }
}
