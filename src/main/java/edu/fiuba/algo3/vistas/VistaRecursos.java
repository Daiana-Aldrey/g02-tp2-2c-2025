package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VistaRecursos extends VBox {

    private Label madera;
    private Label lana;
    private Label trigo;
    private Label arcilla;
    private Label mineral;

    private Jugador jugador;

    public VistaRecursos(Jugador jugador) {
        this.jugador = jugador;

        setSpacing(5);
        setStyle("-fx-font-size: 16px;");

        madera = new Label();
        lana   = new Label();
        trigo  = new Label();
        arcilla= new Label();
        mineral= new Label();

        getChildren().addAll(madera, lana, trigo, arcilla, mineral);

        actualizar();
    }

    public void cambiarJugador(Jugador nuevoJugador) {
        this.jugador = nuevoJugador;
        actualizar();
    }

    public void actualizar() {
        madera.setText("Madera: " + jugador.buscarRecurso(new Madera()).cantidad());
        lana.setText("Mineral: " + jugador.buscarRecurso(new Mineral()).cantidad());
        trigo.setText("Lana: " + jugador.buscarRecurso(new Lana()).cantidad());
        arcilla.setText("Grano: " + jugador.buscarRecurso(new Grano()).cantidad());
        mineral.setText("Ladrillo: " + jugador.buscarRecurso(new Ladrillo()).cantidad());
    }
}
