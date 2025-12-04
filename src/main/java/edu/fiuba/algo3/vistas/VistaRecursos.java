package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Recurso.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class VistaRecursos extends HBox {

    private HBox madera;
    private HBox lana;
    private HBox grano;
    private HBox ladrillo;
    private HBox mineral;

    public VistaRecursos() {
        setSpacing(20);
        this.setAlignment(Pos.CENTER);
        madera = crearVistaDeRecurso("madera.png");
        lana   = crearVistaDeRecurso("lana.png");
        grano  = crearVistaDeRecurso("grano.png");
        ladrillo= crearVistaDeRecurso("ladrillo.png");
        mineral= crearVistaDeRecurso("mineral.png");

        getChildren().addAll(madera, lana, grano, ladrillo, mineral);

    }

    private HBox crearVistaDeRecurso(String imgNombre) {
        Image icono = new Image("file:src/main/resources/" + imgNombre);
        ImageView vista = new ImageView(icono);
        vista.setFitWidth(50);
        vista.setFitHeight(50);

        Label cantidad = new Label("0");

        HBox fila = new HBox(5, vista, cantidad);
        fila.setAlignment(Pos.CENTER_LEFT);

        fila.setMinHeight(50);
        fila.setMaxHeight(50);

        fila.setStyle(
                "-fx-background-color: #e0e0e0;" +
                        "-fx-padding: 0 15 0 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-font-size: 18px;"
        );

        return fila;
    }
}