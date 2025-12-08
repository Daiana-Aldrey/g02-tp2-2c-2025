package edu.fiuba.algo3.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaInfo {

    public static void mostrar(String titulo, String contenido) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);

        Label lblTitulo = new Label(titulo);
        lblTitulo.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        Label lblContenido = new Label(contenido);
        lblContenido.setWrapText(true);
        lblContenido.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");

        VBox caja = new VBox(15, lblTitulo, lblContenido);
        caja.setPadding(new Insets(20));
        caja.setAlignment(Pos.TOP_LEFT);
        caja.setStyle(
                "-fx-background-color: rgba(30,30,30,0.92);" +
                        "-fx-background-radius: 18;"
        );

        caja.setMaxWidth(600);
        lblContenido.setMaxWidth(580);
        lblContenido.setWrapText(true);

        Pane root = new Pane();
        root.setStyle("-fx-background-color: transparent;");
        root.getChildren().add(caja);

        root.widthProperty().addListener((obs, oldV, newV) ->
                caja.setLayoutX((newV.doubleValue() - caja.getWidth()) / 2)
        );
        root.heightProperty().addListener((obs, oldV, newV) ->
                caja.setLayoutY((newV.doubleValue() - caja.getHeight()) / 2)
        );

        root.setOnMouseClicked(e -> {
            if (!caja.localToScene(caja.getBoundsInLocal()).contains(e.getSceneX(), e.getSceneY())) {
                stage.close();
            }
        });

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setMaximized(true);

        stage.show();
    }
}


