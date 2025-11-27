package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.Excepciones.CaminoInvalido;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Ladron;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Vertice> vertices;
    private List<VerticeTerreno> verticesTerreno;
    private List<Arista> aristas;

    public Grafo() {
        vertices = new ArrayList<>();
        verticesTerreno = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarVertice(Vertice vertice) {
        vertices.add(vertice);
        if(vertice.contieneTerreno()) {
            verticesTerreno.add((VerticeTerreno) vertice);
        }
    }

    public void agregarArista(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2) {
        Vertice vertice1 = buscarVertice(ubicacion1);
        Vertice vertice2 = buscarVertice(ubicacion2);

        if (hayArista(vertice1, vertice2)) {
            throw new IllegalArgumentException("Ya existe arista");
        }

        Arista arista = new Arista(ubicacion1,ubicacion2);
        aristas.add(arista);

        vertice1.agregarVerticeAdyacente(vertice2);
        vertice2.agregarVerticeAdyacente(vertice1);
    }

    public void agregarArista(Arista arista) {
        aristas.add(arista);
    }

    public boolean contieneVertice(Vertice vertice) {
        int i = 0;
        boolean encontrado = false;
        while (i < vertices.size() && !encontrado) {
            if (vertices.get(i).equals(vertice)){
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public Vertice buscarVertice(UbicacionVertice ubicacion) {
        int i = 0;
        boolean encontrado = false;
        while (i < vertices.size() && !encontrado) {
            if (vertices.get(i).tieneUbicacion(ubicacion)) {
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

    public Arista buscarArista(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2) {
        int i = 0;
        boolean encontrado = false;

        while (i < aristas.size() && !encontrado) {
            if (aristas.get(i).tieneUbicacion(ubicacion1, ubicacion2)) {
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


    public boolean hayArista(Vertice vertice1, Vertice vertice2) {
        return vertice1.hayVerticeAdyacente(vertice2);
    }


    public void colocarCamino(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2, Camino pieza) {
        Arista aristaEncontrada = buscarArista(vertices);
        if ((buscarVertice(vertices.get(0))).perteneceA(pieza.obtenerJugador()) || (buscarVertice(vertices.get(1)).perteneceA(pieza.obtenerJugador()))) {
            aristaEncontrada.colocarCamino(pieza);
        } else {
            throw new CaminoInvalido("No se puede colocar camino sin una pieza del jugador adyacente");
        }
    }
    public void colocarPieza(UbicacionVertice ubicacion, Pieza pieza) {
        Vertice verticeEncontrado = buscarVertice(ubicacion);
        if (verticeEncontrado.contieneTerreno()) {
            throw new IllegalArgumentException("No se puede colocar una pieza en un vertice donde se alojan terrenos");
        }
        VerticeEdificio verticeEdificioEncontrado = (VerticeEdificio) verticeEncontrado;
        verticeEdificioEncontrado.colocarPieza(pieza);
    }

    public void terrenosCompatibles(int resultadoDado) {
        List<VerticeTerreno> terrenosConFichaDeNumero = buscarTerrenoCompatible(resultadoDado);
        for (VerticeTerreno verticeTerreno : terrenosConFichaDeNumero) {
            verticeTerreno.cosecharTerreno();
        }
    }

    private List<VerticeTerreno> buscarTerrenoCompatible(int resultadoDados) {
        List<VerticeTerreno> compatibles = new ArrayList<>();

        for (VerticeTerreno vertice : verticesTerreno) {
            if (vertice.tieneFichaDeNumero(resultadoDados)) {
                compatibles.add(vertice);
            }
        }
        return compatibles;
    }

    public void removerPieza(UbicacionVertice ubicacion) {
        Vertice vertice = buscarVertice(ubicacion);
        if (vertice.contieneTerreno()) {
            throw new IllegalArgumentException("Ubicacion erronea");
        }
        VerticeEdificio verticeEdificio = (VerticeEdificio) vertice;
        verticeEdificio.removerPieza();
    }

    public void colocarLadron(UbicacionVertice ubicacion, Ladron ladron, Jugador jugador) {
        Vertice verticeEncontrado = buscarVertice(ubicacion);
        VerticeTerreno verticeTerreno = (VerticeTerreno) verticeEncontrado;
        verticeTerreno.colocarLadron(ladron);
        verticeTerreno.robarPara(jugador);
    }

    public boolean verticeTenesPieza(UbicacionVertice ubicacion) {
        Vertice verticeEncontrado = buscarVertice(ubicacion);
        VerticeEdificio verticeEdificioEncontrado = (VerticeEdificio) verticeEncontrado;
        return verticeEdificioEncontrado.hayPieza();
    }

    public boolean aristaTenesCamino(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2) {
        Arista aristaEncontrada = buscarArista(ubicacion1, ubicacion2);
        return aristaEncontrada.hayCamino();
    }
}
