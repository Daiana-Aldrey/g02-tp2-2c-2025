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
        piezas = new ArrayList();
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

        grafo.colocarCamino(vertices, camino);
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

    public void reset() {
        this.grafo = new Grafo();
        this.piezas = new ArrayList<>();
    }
    
    public void registrarTerreno(char id, Terreno terreno, List<Integer> verticesEdificio) {
        VerticeTerreno vt = new VerticeTerreno(id, terreno);
        grafo.agregarVertice(vt);

        for (Integer v : verticesEdificio) {
            grafo.agregarArista(v, id);
        }
    }


}


