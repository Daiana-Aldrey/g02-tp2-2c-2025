package edu.fiuba.algo3.modelo.Terreno;

import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;

import java.util.List;

public class Montania extends Terreno {

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            int cant = pieza.produccion();
            pieza.agregarRecursos(RecursoTipo.MINERAL,cant);
        }
    }
}
