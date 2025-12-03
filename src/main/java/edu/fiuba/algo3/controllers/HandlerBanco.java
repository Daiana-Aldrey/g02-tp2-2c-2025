package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.vistas.VistaIntercambio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class HandlerBanco implements EventHandler<ActionEvent> {

    private final JuegoObservable modelo;

    public HandlerBanco(JuegoObservable modelo) {
        this.modelo = modelo;
    }

    @Override
    public void handle(ActionEvent event) {
        Stage ventanaBanco = new Stage();
        VistaIntercambio vistaBanco = new VistaIntercambio(ventanaBanco, modelo);
        
        ventanaBanco.setTitle("Banco - Intercambio 4:1");
        ventanaBanco.setScene(vistaBanco.getEscena());
        ventanaBanco.show();
    }
}