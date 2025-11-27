package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Ladron;
import edu.fiuba.algo3.modelo.Terreno.*;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneradorDeTablero {
    private int cantidadVertices;
    private List<List<Integer>> verticePorFIla;
    private List<Integer> aristasDiagonales;
    private List<Integer> inicioDiagonales;
    private List<Integer> fichasDeNumero;
    private List<Terreno> terrenos;
    private GeneradorNumerosAleatorios aleatorio;

    public GeneradorDeTablero(GeneradorNumerosAleatorios aleatorio) {
        cantidadVertices = 54;
        this.aleatorio = aleatorio;

        establecerVerticesporFila();
        establecerAristasDiagonales();
        establecerInicioDiagonales();
        establecerFichasDeNumero();
        establecerTerrenos();
    }

    public void generarEstructura(Grafo grafo) {
        generarVertices(grafo);
        generarAristasHorizontales(grafo);
        generarAristasVerticales(grafo);
    }

    public void generarTerrenos(Grafo grafo, Ladron ladron) {
        establecerUbicacionTerrenos(grafo, ladron);
        conectarTerrenos(grafo);
    }

    private void establecerFichasDeNumero() {
        fichasDeNumero = new ArrayList<>(Arrays.asList(5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11));
    }

    private void establecerTerrenos() {
        terrenos = new ArrayList<>();
        terrenos.add(new Colina());
        terrenos.add(new Colina());
        terrenos.add(new Colina());
        terrenos.add(new Bosque());
        terrenos.add(new Bosque());
        terrenos.add(new Bosque());
        terrenos.add(new Bosque());
        terrenos.add(new Campo());
        terrenos.add(new Campo());
        terrenos.add(new Campo());
        terrenos.add(new Campo());
        terrenos.add(new Montania());
        terrenos.add(new Montania());
        terrenos.add(new Montania());
        terrenos.add(new Pastizal());
        terrenos.add(new Pastizal());
        terrenos.add(new Pastizal());
        terrenos.add(new Pastizal());
    }


    private void establecerUbicacionTerrenos(Grafo grafo, Ladron ladron) {
        int numeroAleatorio;
        int otroNumeroAleatorio;
        int terrenosRestantes = 18;
        boolean desierto = false;
        VerticeTerreno vertice;

        for (char i = 'A'; i <= 'S'; i++) {
            numeroAleatorio = aleatorio.generarEnRangoDesdeCero(terrenosRestantes);
            otroNumeroAleatorio = aleatorio.generarEnRangoDesdeCero(terrenosRestantes);
            UbicacionVertice ubicacion = new UbicacionVertice(i);

            if ((otroNumeroAleatorio == 0) && (!desierto))  {
                vertice = new VerticeTerreno(ubicacion, new Desierto(), 0);
                vertice.colocarLadron(ladron);
                grafo.agregarVertice(vertice);
                desierto = true;
            } else {
                vertice = new VerticeTerreno(ubicacion, terrenos.get(numeroAleatorio), fichasDeNumero.get(otroNumeroAleatorio));
                grafo.agregarVertice(vertice);
                terrenos.remove(numeroAleatorio);
                fichasDeNumero.remove(otroNumeroAleatorio);
                terrenosRestantes--;
            }
        }
    }

    private void establecerVerticesporFila() {
        verticePorFIla = List.of(List.of(1,7), List.of(8,16), List.of(17,27), List.of(28,38), List.of(39,47), List.of(48,54));
    }

    private void establecerAristasDiagonales() {
        aristasDiagonales = List.of(8,10,11,10,8);
    }

    private void establecerInicioDiagonales() {
        inicioDiagonales = List.of(0,0,0,1,1);
    }


    private void generarVertices(Grafo grafo) {
        for (int i = 1; i <= cantidadVertices; i++) {
            UbicacionVertice ubicacion = new UbicacionVertice(i);
            VerticeEdificio vertice = new VerticeEdificio(ubicacion);
            grafo.agregarVertice(vertice);
        }
    }

    private void generarAristasHorizontales(Grafo grafo) {
        int vertice = 1;
        int ultimoVerticeFila;
        UbicacionVertice ubicacion1;
        UbicacionVertice ubicacion2;

        for (List<Integer> vertices : verticePorFIla) {
            ultimoVerticeFila = vertices.get(1);
            for (; vertice < ultimoVerticeFila; vertice++) {
                ubicacion1 = new UbicacionVertice(vertice);
                ubicacion2 = new UbicacionVertice(vertice + 1);
                grafo.agregarArista(ubicacion1, ubicacion2);
            }
            vertice++;
        }
    }

    private void generarAristasVerticales(Grafo grafo) {
        int vertice;
        int indice = 0;
        int primerVerticeFila;
        int ultimoVerticeFila;
        int verticeAEnlazar;

        for (int inicia: inicioDiagonales) {
            primerVerticeFila = verticePorFIla.get(indice).get(0) + inicia;
            ultimoVerticeFila = verticePorFIla.get(indice).get(1);
            for (vertice = primerVerticeFila; vertice <= ultimoVerticeFila ; vertice += 2) {
                verticeAEnlazar = vertice + aristasDiagonales.get(indice);
                UbicacionVertice ubicacion1 = new UbicacionVertice(vertice);
                UbicacionVertice ubicacion2 = new UbicacionVertice(verticeAEnlazar);
                grafo.agregarArista(ubicacion1, ubicacion2);
            }
            indice++;
        }
    }

    private void conectarTerrenos(Grafo grafo) {
        int vertice = 1;
        Integer ultimoVerticeFila;
        Integer primerVerticeFila;
        int verticeAEnlazar;
        int ultimoVerticeTerreno;
        int inicioDiagonales;
        int indice = 0;
        int tres = 2;

        for (char i = 'A'; i <= 'S'; i++) {
            primerVerticeFila = verticePorFIla.get(indice).get(0);
            inicioDiagonales = this.inicioDiagonales.get(indice);
            if (primerVerticeFila == vertice) {
                vertice += inicioDiagonales;
            }
            ultimoVerticeTerreno = vertice + tres;
            ultimoVerticeFila = verticePorFIla.get(indice).get(1);
            for (; vertice <= ultimoVerticeTerreno; vertice++ ) {
                verticeAEnlazar = vertice + aristasDiagonales.get(indice);
                UbicacionVertice ubicacionVerticeArriba = new UbicacionVertice(vertice);
                UbicacionVertice ubicacionVerticeAbajo = new UbicacionVertice(verticeAEnlazar);
                UbicacionVertice ubicacionTerreno = new UbicacionVertice(i);
                grafo.agregarArista(ubicacionVerticeArriba, ubicacionTerreno);
                grafo.agregarArista(ubicacionVerticeAbajo, ubicacionTerreno);
            }
            vertice--;
            if (ultimoVerticeTerreno + inicioDiagonales == ultimoVerticeFila) {
                vertice += inicioDiagonales + 1;
                indice++;
            }
        }
    }
}