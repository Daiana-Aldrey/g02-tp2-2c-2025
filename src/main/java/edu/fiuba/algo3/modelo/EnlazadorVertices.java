package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class EnlazadorVertices {
    protected List<VerticeEdificio> adyacentes;

    public EnlazadorVertices() {
        adyacentes = new ArrayList<>();
    }

    // Post: agrega un vértice adyacente a la lista de "adyacentes".
    public void agregarVerticeAdyacente(VerticeEdificio vertice) {
        adyacentes.add(vertice);
    }
}