package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.Excepciones.CaminoInvalido;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Terreno.Terreno;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<VerticeEdificio> vertices;
    private List<VerticeTerreno> verticesTerrenos;
    private List<Arista> aristas;

    public Grafo() {
        vertices = new ArrayList<>();
        verticesTerrenos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarVertice(Integer vertice) {
        VerticeEdificio verticeEdificioNuevo = new VerticeEdificio(vertice);
        vertices.add(verticeEdificioNuevo);
    }

    //metodo usado unicamente para test
    public void agregarVertice(VerticeEdificio verticeEdificio) {
        vertices.add(verticeEdificio);
    }

    public void agregarVertice(char vertice, Terreno terreno, int fichaDeNumero) {
        verticesTerrenos.add(new VerticeTerreno(vertice, terreno, fichaDeNumero));
    }

    //metodo usado unicamente para test
    public void agregarVertice(VerticeTerreno vertice) {
        verticesTerrenos.add(vertice);
    }

    //metodo usado unicamente para test
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

    public Arista buscarArista(List<Integer> vertices) {
        int i = 0;
        boolean encontrado = false;

        while (i < aristas.size() && !encontrado) {
            if (aristas.get(i).sonMisAdyacentes(vertices)) {
                encontrado = true;
            } else {
                i++;
            }
        }

        if (!encontrado) {
            throw new IllegalArgumentException("Arista no encontrada");
        }

        return aristas.get(i);
    }

    public void agregarArista(Integer v1, Integer v2) {
        VerticeEdificio vertice1 = buscarVertice(v1);
        VerticeEdificio vertice2 = buscarVertice(v2);

        if (hayArista(vertice1, vertice2)) {
            throw new IllegalArgumentException("Ya existe arista");
        }

        Arista arista = new Arista(v1,v2);
        aristas.add(arista);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);
    }

    public void agregarArista(Integer v1, char v2) {
        VerticeEdificio vertice = buscarVertice(v1);
        VerticeTerreno verticeTerreno = buscarVertice(v2);

        if (hayArista(vertice, verticeTerreno)) {
            throw new IllegalArgumentException("Ya existe arista");
        }

        vertice.agregarVerticeAdyacente(verticeTerreno);
        verticeTerreno.agregarVerticeAdyacente(vertice);

    }

    //metodo usado unicamente para test
    public void agregarArista(Arista arista) {
        aristas.add(arista);
    }

    public boolean hayArista(VerticeEdificio v1, VerticeTerreno v2) {
        return v1.hayTerrenoAdyacente(v2);
    }

    public boolean hayArista(VerticeEdificio v1, VerticeEdificio v2) {
        return v1.hayVerticeAdyacente(v2);
    }

    public void colocarPieza(int vertice, Pieza pieza) {
        VerticeEdificio verticeEncontrado = buscarVertice(vertice);
        verticeEncontrado.colocarPieza(pieza);
    }

    public void colocarCamino(List<Integer> vertices, Camino pieza) {
        Arista aristaEncontrada = buscarArista(vertices);
        if (!aristaEncontrada.estaDisponible()) {
            throw new CaminoInvalido("Ya hay un camino");
        }
        //aristaEncontrada.colocarCamino(pieza);

        if(((buscarVertice(vertices.get(0)).obtenerJugadorDePieza()) == pieza.obtenerJugador()) || ((buscarVertice(vertices.get(1)).obtenerJugadorDePieza()) == pieza.obtenerJugador()) ) {
            aristaEncontrada.colocarCamino(pieza);
        } /*else if((buscarVertice(vertices.get(1)).obtenerJugadorDePieza()) == pieza.obtenerJugador()) {
            aristaEncontrada.colocarCamino(pieza); */
         else {
            throw new CaminoInvalido("No se puede colocar camino");
        }

    }

    public void terrenosCompatibles(int resultadoDado) {
        List<VerticeTerreno> terrenosConFichaDeNumero = buscarTerrenoCompatible(resultadoDado);
        for (VerticeTerreno verticeTerreno : terrenosConFichaDeNumero) {
            verticeTerreno.cosecharTerreno();
        }
    }

    private List<VerticeTerreno> buscarTerrenoCompatible(int resultadoDados) {
        List<VerticeTerreno> compatibles = new ArrayList<>();

        for (VerticeTerreno vertice : verticesTerrenos) {
            if (vertice.tieneFichaDeNumero(resultadoDados)) {
                compatibles.add(vertice);
            }
        }
        return compatibles;
    }

    public void removePieza(int ubicacion) {
        VerticeEdificio vertice = buscarVertice(ubicacion);
        vertice.removerPieza();
    }
}
