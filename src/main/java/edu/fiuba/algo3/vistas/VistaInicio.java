package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorIngresoNombres;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VistaInicio {

    private final Stage stage;
    private final ControladorIngresoNombres controlador;

    public VistaInicio(Stage stage, ControladorIngresoNombres controlador) {
        this.stage = stage;
        this.controlador = controlador;
    }

    public void mostrar() {
        Text titulo = new Text("Catán");
        titulo.setFont(Font.font("System", FontWeight.BOLD, 50));
        titulo.setFill(Color.web("#5a3e1b"));
        VBox.setMargin(titulo, new Insets(20, 0, 0, 0));


        ImageView imagen = new ImageView(new Image("catan.png"));
        imagen.setPreserveRatio(true);
        imagen.setFitWidth(250);
        VBox.setMargin(imagen, new Insets(-20, 0, 0, 0));


        Text texto = new Text("Elegí la cantidad de jugadores");
        texto.setFont(Font.font(30));
        VBox.setMargin(texto, new Insets(30, 0, 20, 0));


        Button btn3 = new Button("3 jugadores");
        Button btn4 = new Button("4 jugadores");
        // Configuración de botones
        Button[] botonesArray = {btn3, btn4};
        for (Button btn : botonesArray) {
            btn.setPrefWidth(160);
            btn.setPrefHeight(50);
            btn.setCursor(Cursor.HAND);
            btn.setStyle(
                    "-fx-background-color: #d9a86c;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-size: 18px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 15;" +
                            "-fx-border-radius: 15;" +
                            "-fx-border-color: transparent;" +
                            "-fx-border-width: 2px;"
            );

            //Cambia borde a azul
            btn.setOnMouseEntered(e -> btn.setStyle(
                    "-fx-background-color: #d9a86c;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-size: 18px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 15;" +
                            "-fx-border-radius: 15;" +
                            "-fx-border-color: #5dade2;" +
                            "-fx-border-width: 2px;"
            ));
            btn.setOnMouseExited(e -> btn.setStyle(
                    "-fx-background-color: #d9a86c;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-size: 18px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 15;" +
                            "-fx-border-radius: 15;" +
                            "-fx-border-color: transparent;" +
                            "-fx-border-width: 2px;"
            ));
        }

        HBox botones = new HBox(20, btn3, btn4);
        botones.setAlignment(Pos.CENTER);
        VBox.setMargin(botones, new Insets(20, 0, 0, 0));


        VBox layout = new VBox();
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #e7d3a8;");
        layout.getChildren().addAll(titulo, imagen, texto, botones);

        Scene escena = new Scene(layout, 800, 600);
        stage.setScene(escena);
        stage.setTitle("Inicio - Catán");
        stage.setMaximized(true);
        stage.show();

        btn3.setOnAction(e -> abrirVentanaNombres(3));
        btn4.setOnAction(e -> abrirVentanaNombres(4));
    }

    private void abrirVentanaNombres(int cantidad) {
        new VistaIngresarNombres(stage, cantidad, controlador).mostrar();
    }

}
