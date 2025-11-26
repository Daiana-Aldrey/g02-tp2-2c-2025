package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Dados.GeneradorNumerosAleatorios;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Ladron;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Terreno.Terreno;

import java.util.*;
public final class Tablero {
    private Grafo grafo;
    private List<Pieza> piezas;
    private List<Terreno> terrenos;
    private List<VerticeTerreno> verticesTerreno;
    private Ladron ladron;

    private static final Tablero INSTANCE = new Tablero();

    private Tablero() {
        this.grafo = new Grafo();
        this.terrenos = new ArrayList<>();
        this.piezas = new ArrayList<>();
        this.verticesTerreno = new ArrayList<>();
        this.ladron = new Ladron('X');

    }

    public static Tablero getInstance() {
        return INSTANCE;
    }
    //metodo para test
    public void setearGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public void crearGrafo() {
        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        generador.generarTerrenos(grafo);
    }

    public void colocarEdificio(int num_vertice, Pieza pieza) {
        grafo.colocarPieza(num_vertice, pieza);
        piezas.add(pieza);
    }
    
    public void colocarCamino(List<Integer> vertices, Camino camino) {
        grafo.colocarCamino(vertices, camino);
        piezas.add(camino);
    }

    public void cosechar(int numeroDado) {
            grafo.terrenosCompatibles(numeroDado);
    }


    public boolean hayPieza(List<Integer> vertices) {
        if(vertices.size() == 1 ) {
            VerticeEdificio ubicacion = grafo.buscarVertice(vertices.get(0));
            return !ubicacion.estaDisponible(); 
        }

        if(vertices.size() == 2) {
            Arista arista = grafo.buscarArista(vertices);
            return !arista.estaDisponible();
        }

        throw new IllegalArgumentException("Cantidad invalida de vertices");
    }

    public void moverLadronA(char idTerreno, Jugador jugadorQueMueve) {
        VerticeTerreno destino = grafo.buscarVertice(idTerreno);
        destino.colocarLadron(ladron);
        destino.recibirLadron(jugadorQueMueve);  
    }
    
    public void reset() {
        this.grafo = new Grafo();
        this.piezas = new ArrayList<>();
        this.terrenos = new ArrayList<>();
        this.verticesTerreno = new ArrayList<>();

        crearGrafo();
    }

    public void removerPoblado(int ubicacion) {
        grafo.removePieza(ubicacion);
    }
}


