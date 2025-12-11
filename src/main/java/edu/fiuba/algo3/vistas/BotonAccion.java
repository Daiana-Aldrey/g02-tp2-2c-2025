package edu.fiuba.algo3.vistas;

import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class BotonAccion extends Button {

    public BotonAccion(String texto, EventHandler<ActionEvent> handler) {
        super(texto);
        this.setOnAction(handler);
        this.setStyle(
                "-fx-background-color: #e0e0e0;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 8 15 8 15;" +
                "-fx-background-radius: 10;"
        );
    }
}
