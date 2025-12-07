package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.vistas.App;

import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ControladorIngresoNombres {

    private final Stage stage;

    public ControladorIngresoNombres(Stage stage) {
        this.stage = stage;
    }

    public void crearJugadoresYIniciarJuego(List<String> nombres) {
        List<Jugador> jugadores = new ArrayList<>();
        for (String nombre : nombres) {
            jugadores.add(new Jugador(nombre));
        }

        new App().iniciarJuego(stage, jugadores);
    }
}
