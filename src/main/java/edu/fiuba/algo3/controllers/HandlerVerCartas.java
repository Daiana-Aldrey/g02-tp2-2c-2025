package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.vistas.VistaCartasDesarrollo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class HandlerVerCartas implements EventHandler<ActionEvent> {

    private final JuegoObservable modelo;

    public HandlerVerCartas(JuegoObservable modelo) {
        this.modelo = modelo;
    }

    @Override
    public void handle(ActionEvent event) {

        Stage ventanaCartas = new Stage();
        ventanaCartas.setTitle("Cartas de Desarrollo");

        ventanaCartas.setWidth(900);
        ventanaCartas.setHeight(700);


        ventanaCartas.setMinWidth(800);
        ventanaCartas.setMinHeight(600);

        VistaCartasDesarrollo vista =
                new VistaCartasDesarrollo(ventanaCartas, modelo);

        vista.mostrar();
    }

}


