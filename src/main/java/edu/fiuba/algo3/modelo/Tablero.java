package edu.fiuba.algo3.modelo;

import java.util.*;

public abstract class Tablero {
    protected int cantidadVertices;
    private Grafo grafo;
    private List<Pieza> piezas;
    private Ladron ladron;

    public Tablero() {
        grafo = new Grafo();
        piezas = new ArrayList();
    }

    public void crearGrafo() {
        generarVertices();
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
    protected void generarVertices() {
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
    	

    public void colocarEdificio(int num_vertice, Pieza pieza) {
        grafo.colocarPieza(num_vertice, pieza);
        piezas.add(pieza);
    }
    
    public void verificarAristaValida(VerticeEdificio vertice1, VerticeEdificio vertice2) {
        if (!grafo.hayArista(vertice1, vertice2)) {
            throw new IllegalArgumentException("Los vértices no son adyacentes; no se puede construir un camino ahí");
        }

        if (!vertice1.estaDisponible() || !vertice2.estaDisponible()) {
            throw new IllegalArgumentException("No se puede construir sobre un vértice ocupado o bloqueado");
        }	
    }
    
    public void colocarCamino(List<Integer> vertices, Camino camino) {
    	int v1 = vertices.get(0);
        int v2 = vertices.get(1);
        
        if (!grafo.contieneVertice(v1) || !grafo.contieneVertice(v2)) {
            throw new IllegalArgumentException("Alguno de los vértices no existe en el tablero");
        }

   	 	VerticeEdificio vertice1 = grafo.buscarVertice(v1);
        VerticeEdificio vertice2 = grafo.buscarVertice(v2);
        
        verificarAristaValida(vertice1, vertice2);

        vertice1.colocarPieza(camino);
        vertice2.colocarPieza(camino);
        
        piezas.add(camino);
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


