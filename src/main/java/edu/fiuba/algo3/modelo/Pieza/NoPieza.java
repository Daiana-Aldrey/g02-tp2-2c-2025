package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.Excepciones.ObjetoNoUsable;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;

import java.util.List;

public class NoPieza extends Pieza {
    private Ubicacion ubicacion;
    public NoPieza(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public List<Recurso> costoDeConstruccion() {
        throw new ObjetoNoUsable("No tiene costo de construccion");
    }

    @Override
    public void colocarPrimera(List<Ubicacion> ubicaciones) {
        throw  new ColocacionInvalida("Pieza inusable, no es debido colocar");
    }

    @Override
    public void colocar(List<Ubicacion> vertices) {
        throw new ColocacionInvalida("Pieza inusable, no es debido colocar");
    }

    @Override
    public boolean tenesUbicacion(Ubicacion ubicacion) {
        return this.ubicacion.equals(ubicacion);
    }

    @Override
    public boolean usable() {
        boolean usable = false;
        return usable;
    }

    @Override
    public void setearUbicacion(Ubicacion ubicacion) {
        throw new ObjetoNoUsable("Pieza inusable, no se puede cambiar ubicacion");
    }
}