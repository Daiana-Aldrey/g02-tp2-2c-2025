package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Ladron;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.*;
public final class Tablero {
    private Grafo grafo;
    private List<Pieza> piezas;
    private List<Terreno> terrenos;
    private Ladron ladron;

    private static final Tablero INSTANCE = new Tablero();

    private Tablero() {
        this.grafo = new Grafo();
        this.terrenos = new ArrayList<>();
        this.piezas = new ArrayList<>();
        this.ladron = new Ladron();

        crearGrafo();
    }

    public static Tablero getInstance() {
        return INSTANCE;
    }

    public void setearGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    private void crearGrafo() {
        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        generador.generarTerrenos(grafo, ladron);
    }

    public void colocarEdificio(UbicacionVertice ubicacion, Pieza pieza) {
        grafo.colocarPieza(ubicacion, pieza);
        piezas.add(pieza);
    }

    public void colocarCamino(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2, Camino camino) {
        grafo.colocarCamino(ubicacion1, ubicacion2, camino);
        piezas.add(camino);
    }

    public void cosechar(int numeroDado) {
        grafo.terrenosCompatibles(numeroDado);
    }

    public void moverLadronA(UbicacionVertice ubicacion, Jugador jugadorQueMueve) {
        grafo.colocarLadron(ubicacion, ladron, jugadorQueMueve);
    }

    public void reset() {
        this.grafo = new Grafo();
        this.piezas = new ArrayList<>();
        this.terrenos = new ArrayList<>();

        crearGrafo();
    }

    public void removerPoblado(UbicacionVertice ubicacion) {
        grafo.removerPieza(ubicacion);
    }

    public boolean hayEdificio(UbicacionVertice ubicacion) {
        return grafo.verticeTenesPieza(ubicacion);
    }

    public boolean hayCamino(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2) {
        return grafo.aristaTenesCamino(ubicacion1, ubicacion2);
    }
}


