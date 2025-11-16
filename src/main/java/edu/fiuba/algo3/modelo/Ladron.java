package edu.fiuba.algo3.modelo;

public class Ladron {

    private VerticeTerreno posicion;

    public Ladron(VerticeTerreno posicionInicial) {
        this.posicion = posicionInicial;
    }

    public void moverA(VerticeTerreno destino) {
        this.posicion = destino;
    }

    public Boolean posicion(VerticeTerreno vertice) {
        return (posicion == vertice);
    }
}
