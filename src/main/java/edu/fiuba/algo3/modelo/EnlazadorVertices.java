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

    // Post: Verifica si tiene cierto vertice como adyacente
    public boolean hayVerticeAdyacente(VerticeEdificio vertice) {
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

}