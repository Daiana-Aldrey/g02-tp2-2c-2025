package edu.fiuba.algo3.modelo;

public class Tablero {
    private Grafo grafo;

    public void crearGrafo(){
        grafo = new Grafo();
        //creacion de vertices
        for (Integer i = 1; i < 55; i++) {
            grafo.agregarVertice(i);
        }
        //aristas de primera fila
        for (Integer i = 1; i < 7; i++) {
            grafo.agregarArista(i, i + 1);
        }
        for(Integer i = 1; i < 8; i += 2){
            grafo.agregarArista(i, i + 8);
        }
        //aristas segunda fila
        for(Integer i = 8; i < 16; i++){
            grafo.agregarArista(i, i + 1);
        }
        for(Integer i = 8; i < 17; i += 2){
            grafo.agregarArista(i, i + 10);
        }
        //aristas tercera fila
        for(Integer i = 17; i < 27; i++){
            grafo.agregarArista(i, i + 1);
        }
        for(Integer i = 17; i < 28; i += 2){
            grafo.agregarArista(i, i + 11);
        }
        //aristas cuarta fila
        for(Integer i = 28; i < 38; i++){
            grafo.agregarArista(i, i + 1);
        }
        for(Integer i = 29; i < 38; i += 2){
            grafo.agregarArista(i, i + 10);
        }
        //aristas quinta fila
        for(Integer i = 39; i < 47; i++){
            grafo.agregarArista(i, i + 1);
        }
        for(Integer i = 40; i < 47; i += 2){
            grafo.agregarArista(i, i + 8);
        }
        //aristas sexta fila
        for(Integer i = 48; i < 54; i++){
            grafo.agregarArista(i, i + 1);
        }
    }

    public void mostrarGrafo(){
        grafo.mostrarGrafo();
    }
}

