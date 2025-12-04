package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.vistas.VistaCartas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;
import java.util.Map;


public class HandlerVerCartas implements EventHandler<ActionEvent> {
    private JuegoObservable modelo;
    private Map<String, Integer> cartas;

    public HandlerVerCartas(JuegoObservable modelo) {
        this.modelo = modelo;
        this.cartas = new HashMap<>();
        this.cartas = Map.of("Carta Puntos De Victoria", 0,
                "Carta Caballero", 0,
                "Carta Construccion Carreteras", 0,
                "Carta Descubrimiento", 0,
                "Carta Monopolio", 0
        );
    }

    public void handle(ActionEvent event) {
        VistaCartas vista = new VistaCartas(modelo, cartas);
        vista.mostrar();
    }
}
