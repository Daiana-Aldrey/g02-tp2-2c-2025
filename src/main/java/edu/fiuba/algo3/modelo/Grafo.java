package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Vertice> vertices;

    public Grafo() {
        vertices = new ArrayList<>();
    }

    public void agregarVertice(Integer vertice) {
        Vertice vertice_nuevo = new Vertice(vertice);
        vertices.add(vertice_nuevo);
    }

    public void agregarVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public boolean contieneVertice(Integer vertice) {
        int i = 0;
        boolean encontrado = false;
        while (i < vertices.size() && !encontrado) {
            if (vertices.get(i).tieneNombre(vertice)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public Vertice buscarVertice(Integer vertice) {
        int i = 0;
        boolean encontrado = false;
        while (i < vertices.size() && !encontrado) {
            if (vertices.get(i).tieneNombre(vertice)) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            throw new RuntimeException("Vertice no encontrado");
        }
        return vertices.get(i);
    }

    public void agregarArista(int v1, int v2) {
        if (hayArista(v1, v2)) {
            throw new RuntimeException("Ya existe arista");
        }
        Vertice vertice1 = buscarVertice(v1);
        Vertice vertice2 = buscarVertice(v2);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);
    }

    public boolean hayArista(int v1, int v2) {
        Vertice vertice1 = buscarVertice(v1);
        Vertice vertice2 = buscarVertice(v2);

        return vertice1.hayVerticeAdyacente(vertice2);
    }

    public boolean verticeDisponible(int vertice) {
        Vertice verticeEncontrado = buscarVertice(vertice);

        return verticeEncontrado.estaDisponible();
    }

    public void ponerPieza(int vertice) {
        Vertice verticeEncontrado = buscarVertice(vertice);

        verticeEncontrado.tienePieza();
    }
    //sacar
    public void mostrarGrafo() {
        for(Vertice vertice: vertices) {
            System.out.println("vertice:" + vertice.nombre());
            for (Vertice vertice2 : vertice.adyacentes()) {
                System.out.println("adyacente: " + vertice2.nombre());
            }
        }
    }
}
