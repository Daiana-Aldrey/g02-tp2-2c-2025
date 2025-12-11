package edu.fiuba.algo3.modelo.Terreno;

import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Recurso.Lana;

import java.util.List;

public class Pastizal extends Terreno {

    @Override
    public void repartirRecurso(List<Pieza> edificios){
        for(Pieza pieza : edificios){
            int cant = pieza.produccion();
            pieza.agregarRecursos(new Lana(cant),cant);
        }
    }
}
