package edu.fiuba.algo3.modelo;

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

        crearGrafo();
        inicializarTerrenos(); 
    }
 
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
    	
    private void inicializarTerrenos() {
       Desierto desierto = new Desierto();
       registrarTerreno('Z', desierto, List.of(19, 20, 21, 22, 23, 24));
       VerticeTerreno vtDesierto = buscarVerticeTerreno('Z');
       
        Terreno bosqueA = new Bosque(4);     
        registrarTerreno('A', bosqueA, List.of(1, 2, 3, 4, 5, 6));

        Terreno campoB = new Campo(5);      
        registrarTerreno('B', campoB, List.of(4, 5, 6, 7, 8, 9));

        Terreno colinaC = new Colina(6);    
        registrarTerreno('C', colinaC, List.of(7, 8, 9, 10, 11, 12));

        Terreno pastizalD = new Pastizal(8); 
        registrarTerreno('D', pastizalD, List.of(10, 11, 12, 13, 14, 15));

        Terreno montaniaE = new Montania(3); 
        registrarTerreno('E', montaniaE, List.of(13, 14, 15, 16, 17, 18));

   
        this.ladron = new Ladron(vtDesierto);
    }
    
    public VerticeTerreno buscarVerticeTerreno(char id) {
        for (VerticeTerreno vt : verticesTerreno) {
            if (vt.tieneUbicacion(id)) {
                return vt;
            }
        }
        throw new IllegalArgumentException("No existe un terreno con id " + id);
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



    public void cosechar(int numeroDado) {
        List<VerticeTerreno> terrenos = grafo.buscarTerrenoCompatible(numeroDado);
        for (VerticeTerreno vt : terrenos) {
            if (ladron.posicion(vt)) {
                continue; 
            }

            vt.cosecharTerreno(); 
        }
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

    
    public void registrarTerreno(char id, Terreno terreno, List<Integer> verticesEdificio) {
        VerticeTerreno vt = new VerticeTerreno(id, terreno);
        grafo.agregarVertice(vt);

        verticesTerreno.add(vt);
        terrenos.add(terreno); 

        for (Integer v : verticesEdificio) {
            grafo.agregarArista(v, id);
        }
    }

    
    public void moverLadronA(char idTerreno, Jugador jugadorQueMueve) {
        VerticeTerreno destino = buscarVerticeTerreno(idTerreno);
        ladron.moverA(destino);
        destino.recibirLadron(jugadorQueMueve);  
    }
    
    public void reset() {
        this.grafo = new Grafo();
        this.piezas = new ArrayList<>();
        this.terrenos = new ArrayList<>();
        this.verticesTerreno = new ArrayList<>();

        crearGrafo();
        inicializarTerrenos(); 
    }


}


