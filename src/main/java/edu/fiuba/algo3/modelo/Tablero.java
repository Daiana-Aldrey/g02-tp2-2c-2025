package edu.fiuba.algo3.modelo;

import java.util.*;

public final class Tablero {

    private Grafo grafo;
    private List<Pieza> piezas;

    //PATRON DE DISEÑO SINGLETON
  
    // unica instancia creada al cargar la clase
    private static final Tablero INSTANCE = new Tablero();


    // nadie puede hacer "new Tablero()"
    private Tablero() {
        grafo = new Grafo();
    }

    // metodo de acceso global
    public static Tablero getInstance() {
        return INSTANCE;
    }

    public void crearGrafo() {
        grafo = new Grafo();
        // creación de vértices
        for (Integer i = 1; i < 55; i++) {
            grafo.agregarVertice(i);
        }
        // aristas de primera fila
        for (Integer i = 1; i < 7; i++) {
            grafo.agregarArista(i, i + 1);
        }
        for (Integer i = 1; i < 8; i += 2) {
            grafo.agregarArista(i, i + 8);
        }
        // aristas segunda fila
        for (Integer i = 8; i < 16; i++) {
            grafo.agregarArista(i, i + 1);
        }
        for (Integer i = 8; i < 17; i += 2) {
            grafo.agregarArista(i, i + 10);
        }
        // aristas tercera fila
        for (Integer i = 17; i < 27; i++) {
            grafo.agregarArista(i, i + 1);
        }
        for (Integer i = 17; i < 28; i += 2) {
            grafo.agregarArista(i, i + 11);
        }
        // aristas cuarta fila
        for (Integer i = 28; i < 38; i++) {
            grafo.agregarArista(i, i + 1);
        }
        for (Integer i = 29; i < 38; i += 2) {
            grafo.agregarArista(i, i + 10);
        }
        // aristas quinta fila
        for (Integer i = 39; i < 47; i++) {
            grafo.agregarArista(i, i + 1);
        }
        for (Integer i = 40; i < 47; i += 2) {
            grafo.agregarArista(i, i + 8);
        }
        // aristas sexta fila
        for (Integer i = 48; i < 54; i++) {
            grafo.agregarArista(i, i + 1);
        }
    }

    public void mostrarGrafo() {
        grafo.mostrarGrafo();
    }
    
    public boolean sinPiezas() {
    	return piezas.size() == 0 ;
    }	
    	
    public void colocarPieza (int num_vertice, Pieza pieza) {
        grafo.colocarPieza(num_vertice, pieza);
    }

    public void cosechar(int resultadoDado) {
        grafo.cosechaCompatibles(resultadoDado);
    }
}


