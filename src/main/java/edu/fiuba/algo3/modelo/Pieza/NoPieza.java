package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.List;

public class NoPieza extends Pieza {
    private UbicacionVertice ubicacion;
    public NoPieza(UbicacionVertice ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public List<Recurso> costoDeConstruccion() {
        throw new RuntimeException("No tiene costo de construccion");
    }

    @Override
    public void colocarPrimera(List<UbicacionVertice> ubicaciones) {
        throw  new RuntimeException("Pieza inusable, no es debido colocar");
    }

    @Override
    public void colocar(List<UbicacionVertice> vertices) {
        throw new RuntimeException("Pieza inusable, no es debido colocar");
    }

    @Override
    public boolean tenesUbicacion(UbicacionVertice ubicacion) {
        return this.ubicacion == ubicacion;
    }

    @Override
    public boolean usable() {
        boolean usable = false;
        return usable;
    }

    @Override
    public void setearUbicacion(UbicacionVertice ubicacion) {
        throw new RuntimeException("Pieza inusable, no se puede cambiar ubicacion");
    }
}