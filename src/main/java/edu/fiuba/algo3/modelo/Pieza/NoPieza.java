package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.Recurso.*;
import java.util.List;

public class NoPieza extends Pieza {
    private Integer ubicacion;
    public NoPieza(Integer ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public List<Recurso> costoDeConstruccion() {
        throw new RuntimeException("No tiene costo de construccion");
    }

    @Override
    public void colocar(List<Integer> vertices) {
        throw new RuntimeException("Pieza inusable, no es debido colocar");
    }

    @Override
    public boolean tenesUbicacion(int ubicacion) {
        return this.ubicacion == ubicacion;
    }
}