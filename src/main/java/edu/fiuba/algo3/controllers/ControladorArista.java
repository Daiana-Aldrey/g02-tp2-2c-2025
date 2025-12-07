package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.vistas.VistaArista;


public class ControladorArista {
    private VistaArista vista;
    private Arista modelo;

    public ControladorArista(VistaArista vista, Arista modelo) {
        this.vista = vista;
        this.modelo = modelo;

        colocarCamino(new Camino(new Jugador("jaime")));
    }

    public void colocarCamino(Camino pieza) {
        vista.setOnAction(e -> {
            modelo.colocarCamino(pieza);
            vista.actualizar();
        });
    }
}
