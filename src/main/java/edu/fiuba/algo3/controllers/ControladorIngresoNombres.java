package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.vistas.App;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ControladorIngresoNombres {

    private final Stage stage;

    public ControladorIngresoNombres(Stage stage) {
        this.stage = stage;
    }

    public void crearJugadoresYIniciarJuego(List<String> nombres, List<Color> colores) {
        List<Jugador> jugadores = new ArrayList<>();
        int contador = 0;
        for (String nombre : nombres) {
            jugadores.add(new Jugador(nombre));
        }
        for(Jugador jugador : jugadores) {
            jugador.asignarColor(colores.get(contador));
            contador++;
        }
        contador = 0;
        new App().iniciarJuego(stage, jugadores);
    }
}
