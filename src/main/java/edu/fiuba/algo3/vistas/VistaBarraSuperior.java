package edu.fiuba.algo3.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class VistaBarraSuperior extends HBox {

    private final Button btnFullScreen;

    public VistaBarraSuperior(Label estadoLabel) {
        this.btnFullScreen = crearBotonFullscreen();

        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        this.getChildren().addAll(estadoLabel, espacio, btnFullScreen);
        this.setPadding(new Insets(10, 15, 0, 15));
        this.setAlignment(Pos.CENTER_LEFT);
    }

    private Button crearBotonFullscreen() {
        ImageView icono = new ImageView(
                new Image(getClass().getResourceAsStream("/fullScreen.png"))
        );
        icono.setFitWidth(35);
        icono.setFitHeight(35);

        Button btn = new Button();
        btn.setGraphic(icono);
        btn.setCursor(Cursor.HAND);
        btn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-padding: 5;");

        btn.setOnAction(e -> {
            Stage stage = (Stage) this.getScene().getWindow();
            if (stage == null) return;

            boolean nuevo = !stage.isFullScreen();
            stage.setFullScreen(nuevo);

            if (nuevo) {
                btn.setStyle(
                        "-fx-background-color: rgba(0, 0, 0, 0.10);" +
                        " -fx-background-radius: 6;" +
                        " -fx-padding: 5;");
            } else {
                btn.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-padding: 5;");
            }
        });

        return btn;
    }
}

