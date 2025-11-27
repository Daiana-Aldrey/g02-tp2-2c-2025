package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Pieza.NoPieza;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.ArrayList;
import java.util.List;

public class VerticeEdificio extends Vertice {
    private List<VerticeTerreno> terrenos;
    private boolean disponible;
    private Pieza pieza;

    public VerticeEdificio(UbicacionVertice ubicacion) {
        terrenos = new ArrayList<>();
        this.ubicacion = ubicacion;
        disponible = true;
        pieza = new NoPieza(ubicacion);
    }

    public boolean estaDisponible() {
        return this.disponible;
    }

    public void colocarPieza(Pieza edificio) {
        if (!disponible) {
            throw new IllegalArgumentException("No se puede poner la pieza en el vertice");
        }

        this.pieza = edificio;
        pieza.setearUbicacion(this.ubicacion);

        noDisponible();
        agregarEdificioATerrenos(edificio);

        for (VerticeEdificio vertice : adyacentes) {
            vertice.noDisponible();
        }
    }

    private void noDisponible() {
        disponible = false;
    }

    public boolean hayTerrenoAdyacente(VerticeTerreno vertice) {
        int i = 0;
        boolean encontrado = false;
        while (i < terrenos.size() && !encontrado) {
            if (terrenos.get(i).equals(vertice)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public void agregarEdificioATerrenos (Pieza pieza) {
        for (VerticeTerreno terreno : terrenos) {
            terreno.agregarEdificio(pieza);
        }
    }

    public void removerPieza() {
        for (VerticeTerreno terreno : terrenos) {
            terreno.removerPieza(pieza);
        }
        pieza = new NoPieza(ubicacion);
        disponible = true;
    }

    @Override
    public void agregarVerticeAdyacente(Vertice vertice) {
        if (vertice.contieneTerreno()) {
            terrenos.add((VerticeTerreno) vertice);
        } else {
            adyacentes.add((VerticeEdificio) vertice);
        }
    }

    @Override
    protected boolean contieneTerreno() {
        boolean contiene = false;
        return contiene;
    }

    public boolean hayPieza() {
        boolean hayPieza = false;
        if (pieza.usable()) {
            hayPieza = true;
        }
        return hayPieza;
    }
}

