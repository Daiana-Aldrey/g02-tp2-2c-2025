package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.JuegoObservable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandlerPasarTurno implements EventHandler<ActionEvent> {

    private final JuegoObservable modelo;

    public HandlerPasarTurno(JuegoObservable modelo) {
        this.modelo = modelo;
    }

    @Override
    public void handle(ActionEvent event) {
        modelo.siguienteTurno();
    }
}