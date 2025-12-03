package edu.fiuba.algo3.controllers;


import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.vistas.VistaCrearOferta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandlerIntercambio implements EventHandler<ActionEvent> {
    private JuegoObservable modelo;

    public HandlerIntercambio(JuegoObservable modelo) {
        this.modelo = modelo;
    }

    @Override
    public void handle(ActionEvent event) {
        VistaCrearOferta vista = new VistaCrearOferta(modelo);
        vista.mostrar();
    }
}