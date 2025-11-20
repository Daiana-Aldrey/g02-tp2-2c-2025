package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.Recurso.*;
import java.util.List;

public class NoPieza extends Pieza {
    public NoPieza() {}

    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of();
    }

    @Override
    public void colocar(List<Integer> vertices) {

    }

    @Override
    public boolean tenesUbicacion(int ubicacion) {
        return false;
    }
}