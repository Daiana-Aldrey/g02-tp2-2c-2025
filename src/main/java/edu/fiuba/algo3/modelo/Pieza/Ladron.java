package edu.fiuba.algo3.modelo.Pieza;

public class Ladron {

    private char posicion;

    public Ladron(char posicionInicial) {
        this.posicion = posicionInicial;
    }

    public void moverA(char destino) {
        this.posicion = destino;
    }

    public Boolean posicion(char vertice) {
        return (posicion == vertice);
    }
}
