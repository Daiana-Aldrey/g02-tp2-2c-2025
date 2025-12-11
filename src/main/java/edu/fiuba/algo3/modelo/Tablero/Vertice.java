package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public abstract class Vertice {
    protected Ubicacion ubicacion;
    protected List<VerticeEdificio> adyacentes;

    public Vertice() {
        adyacentes = new ArrayList<>();
    }

    protected abstract void agregarVerticeAdyacente(Vertice vertice);

    public boolean tieneUbicacion(Ubicacion ubicacion) {
        return this.ubicacion.equals(ubicacion);
    }

    public boolean hayVerticeAdyacente(Vertice vertice) {
        int i = 0;
        boolean encontrado = false;
        while (i < adyacentes.size() && !encontrado) {
            if (adyacentes.get(i).equals(vertice)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    protected abstract boolean contieneTerreno();

}