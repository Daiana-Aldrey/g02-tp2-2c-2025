package edu.fiuba.algo3.modelo;

import java.util.*;

public abstract class Tablero {
    private int cantidadVertices;
    private Grafo grafo;
    private List<Pieza> piezas;

    public Tablero() {
        grafo = new Grafo();
        piezas = new ArrayList();
    }

    public void crearGrafo() {
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
    public void generarVertices() {
        int vertices = this.cantidadVertices;
        for (int i = 1; i <= vertices; i++) {
            grafo.agregarVertice(i);
        }
    }

    //sacar
    public void mostrarGrafo() {
        grafo.mostrarGrafo();
    }
    
    public boolean sinPiezas() {
    	return piezas.size() == 0 ;
    }	
    	
    public void colocarPieza (Integer num_vertice, Pieza pieza) {
        grafo.colocarPieza(num_vertice, pieza);
        piezas.add(pieza);
    }

    public void colocarPiezaCamino(List<Integer> ubicacion, Camino camino) {
        grafo.colocarCamino(ubicacion, camino);
        piezas.add(camino);
    }

    public void cosechar(int resultadoDado) {
        grafo.terrenosCompatibles(resultadoDado);
    }
    
    public void mostrarPiezas() {
    	for(int i = 0; i < piezas.size(); i++) {
    		 System.out.println("Pieza en vertice:" + piezas.get(i).ubicacion());
    	}
    }
    
    public int cantidadPiezas() {
    	return piezas.size();
    }
}


