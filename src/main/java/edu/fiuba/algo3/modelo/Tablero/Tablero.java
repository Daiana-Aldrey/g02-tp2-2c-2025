package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Ladron;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.modelo.Intercambio.*;

import java.util.*;
public final class Tablero {
    private Grafo grafo;
    private List<Pieza> piezas;
    private List<Terreno> terrenos;
    private Ladron ladron;
    private List<Puerto> puertos;
    
    private static final Tablero INSTANCE = new Tablero();

    private Tablero() {
        this.grafo = new Grafo();
        this.terrenos = new ArrayList<>();
        this.piezas = new ArrayList<>();
        this.ladron = new Ladron();
        this.puertos =  new ArrayList<>();

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
        this.puertos = generador.generarPuertos();
    }
    
    private void notificarPuertosConstruccion(UbicacionVertice ubicacion, Pieza pieza) {
        for (Puerto puerto : puertos) {
            puerto.notificarConstruccion(ubicacion, pieza);
        }
    }

    public void colocarEdificio(UbicacionVertice ubicacion, Pieza pieza) {
        grafo.colocarPieza(ubicacion, pieza);
        piezas.add(pieza);
        notificarPuertosConstruccion((UbicacionVertice) ubicacion, pieza);
        
    }

    public void colocarCamino(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2, Camino camino) {
        grafo.colocarCamino(ubicacion1, ubicacion2, camino);
        piezas.add(camino);
    }

    public void cosechar(int numeroDado) {
        grafo.terrenosCompatibles(numeroDado);
    }

    public void moverLadronA(UbicacionVertice ubicacion, Jugador jugadorQueMueve,Jugador victima) {
        grafo.colocarLadron(ubicacion, ladron, jugadorQueMueve);
        ladron.robar(jugadorQueMueve, victima);
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

    public boolean hayEdificio(Ubicacion ubicacion) {
        return grafo.verticeTenesPieza(ubicacion);
    }

    public boolean hayCamino(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        return grafo.aristaTenesCamino(ubicacion1, ubicacion2);
    }
    
    public List<Puerto> getPuertos() {
        return puertos;
    }

    public Vertice getTerreno(UbicacionVertice ubicacion) {
        return grafo.buscarVertice(ubicacion);
    }

    public List<Vertice> obtenerVertices() {
        return grafo.obtenerVertices();
    }

    public List<Arista> obtenerAristas() {
        return grafo.obtenerAristas();
    }
}


