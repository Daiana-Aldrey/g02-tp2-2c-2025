package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<VerticeEdificio> vertices;
    private List<VerticeTerreno> verticesTerrenos;
    private List<Aristas> aristas;

    public Grafo() {
        vertices = new ArrayList<>();
        verticesTerrenos = new ArrayList<>();
    }

    public void agregarVertice(Integer vertice) {
        VerticeEdificio vertice_Edificio_nuevo = new VerticeEdificio(vertice);
        vertices.add(vertice_Edificio_nuevo);
    }

    public void agregarVertice(VerticeEdificio verticeEdificio) {
        vertices.add(verticeEdificio);
    }

    public void agregarVertice(char vertice, Terreno terreno) {
        verticesTerrenos.add(new VerticeTerreno(vertice, terreno));
    }

    public void agregarVertice(VerticeTerreno vertice) {
        verticesTerrenos.add(vertice);
    }

    public boolean contieneVertice(Integer vertice) {
        int i = 0;
        boolean encontrado = false;
        while (i < vertices.size() && !encontrado) {
            if (vertices.get(i).tieneUbicacion(vertice)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public VerticeEdificio buscarVertice(Integer vertice) {
        int i = 0;
        boolean encontrado = false;
        while (i < vertices.size() && !encontrado) {
            if (vertices.get(i).tieneUbicacion(vertice)) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            throw new IllegalArgumentException("Vertice no encontrado");
        }
        return vertices.get(i);
    }

    public VerticeTerreno buscarVertice(char vertice) {
        int i = 0;
        boolean encontrado = false;
        while (i < verticesTerrenos.size() && !encontrado) {
            if (verticesTerrenos.get(i).tieneUbicacion(vertice)) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (!encontrado) {
            throw new IllegalArgumentException("Vertice no encontrado");
        }
        return verticesTerrenos.get(i);
    }

    public void agregarArista(int v1, int v2) {
        VerticeEdificio vertice1 = buscarVertice(v1);
        VerticeEdificio vertice2 = buscarVertice(v2);

        if (hayArista(vertice1, vertice2)) {
            throw new IllegalArgumentException("Ya existe arista");
        }

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);
    }

    public boolean hayArista(VerticeEdificio v1, VerticeEdificio v2) {
        return v1.hayVerticeAdyacente(v2);
    }

    public boolean verticeDisponible(int vertice) {
        VerticeEdificio verticeEncontrado = buscarVertice(vertice);

        return verticeEncontrado.estaDisponible();
    }

    public void colocarPieza(int vertice, Pieza pieza) {
        VerticeEdificio verticeEncontrado = buscarVertice(vertice);
        if (!verticeEncontrado.estaDisponible()) {
            throw new IllegalArgumentException("No se puede poner la pieza en el vertice");
        }
        verticeEncontrado.colocarPieza(pieza);
    } 
   

    public void cosechaCompatibles(int resultadoDado) {
        List<VerticeTerreno> terrenosConFichaDeNumero = algunTerrenoCompatible(resultadoDado);
        for (VerticeTerreno verticeTerreno : terrenosConFichaDeNumero) {
            verticeTerreno.cosecharPara();
        }
    }

    public List<VerticeTerreno> algunTerrenoCompatible(int resultadoDados) {
        List<VerticeTerreno> compatibles = new ArrayList<>();

        for (VerticeTerreno vertice : verticesTerrenos) {
            if (vertice.tieneFichaDeNumero(resultadoDados)) {
                compatibles.add(vertice);
            }
        }
        return compatibles;
    }


    //sacar
    public void mostrarGrafo() {
        for(VerticeEdificio verticeEdificio : vertices) {
            System.out.println("vertice:" + verticeEdificio.nombre());
            for (VerticeEdificio verticeEdificio2 : verticeEdificio.adyacentes()) {
                System.out.println("adyacente: " + verticeEdificio2.nombre());
            }
        }
    }

}
