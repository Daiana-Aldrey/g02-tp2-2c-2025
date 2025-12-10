package edu.fiuba.algo3.controllers;


import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.vistas.VistaCrearOferta;
import edu.fiuba.algo3.vistas.VistaJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class HandlerIntercambio implements EventHandler<ActionEvent> {
    private JuegoObservable modelo;
    private final VistaJuego vistaJuego;

    public HandlerIntercambio(JuegoObservable modelo, VistaJuego vistaJuego) {
        this.modelo = modelo;
        this.vistaJuego = vistaJuego;
    }

    @Override
    public void handle(ActionEvent event) {
        Stage ventanaIntercambio = new Stage();
        VistaCrearOferta vista = new VistaCrearOferta(ventanaIntercambio, modelo);
        vista.mostrar();
        vistaJuego.registrarVentana(ventanaIntercambio);
    }
}