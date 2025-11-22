package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.NoPieza;
import edu.fiuba.algo3.modelo.Pieza.Pieza;

import java.util.ArrayList;
import java.util.List;

public class VerticeEdificio extends EnlazadorVertices {
    private Integer ubicacion;
    private List<VerticeTerreno> terrenos;
    private boolean disponible;
    private Pieza pieza;

    public VerticeEdificio(Integer ubicacion) {
        terrenos = new ArrayList<>();
        this.ubicacion = ubicacion;
        disponible = true;
        pieza = new NoPieza(ubicacion);
    }

    public boolean tieneUbicacion(Integer ubicacion) {
        return ubicacion == this.ubicacion;
    }

    public boolean estaDisponible() {
        return this.disponible;
    }

    // Post: marca al vértice como no disponible y también a sus vertices adyacentes
    public void colocarPieza(Pieza edificio) {
        if (disponible) {
            this.pieza = edificio;
            noDisponible();
            agregarEdificioATerrenos(edificio);

            for (VerticeEdificio vertice : adyacentes) {
                vertice.noDisponible();
            }
        } else {
            throw new IllegalArgumentException("No se puede poner la pieza en el vertice");
        }
    }

    private void noDisponible() {
        disponible = false;
    }

    public void agregarVerticeAdyacente (VerticeTerreno vertice){
        terrenos.add(vertice);
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

    public Jugador obtenerJugadorDePieza() {
        return this.pieza.obtenerJugador();
    }

    public void removerPieza() {
        for (VerticeTerreno terreno : terrenos) {
            terreno.removerEdificio(pieza);
        }
        pieza = new NoPieza(ubicacion);
        disponible = true;
    }
}

