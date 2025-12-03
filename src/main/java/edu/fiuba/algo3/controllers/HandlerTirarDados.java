package edu.fiuba.algo3.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.JuegoObservable;

public class HandlerTirarDados implements EventHandler<ActionEvent> {

    private JuegoObservable modelo;

    public HandlerTirarDados(JuegoObservable modelo) {
        this.modelo = modelo;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            modelo.realizarTirada();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}