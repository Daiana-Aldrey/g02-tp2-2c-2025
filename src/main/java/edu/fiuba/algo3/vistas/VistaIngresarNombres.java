package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorIngresoNombres;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class VistaIngresarNombres {

    private final Stage stage;
    private final int cantidadJugadores;
    private final List<TextField> camposNombres = new ArrayList<>();
    private final ControladorIngresoNombres controlador;

    public VistaIngresarNombres(Stage stage, int cantidadJugadores, ControladorIngresoNombres controlador) {
        this.stage = stage;
        this.cantidadJugadores = cantidadJugadores;
        this.controlador = controlador;
    }

    public void mostrar() {
        VBox layoutPrincipal = new VBox(30);
        layoutPrincipal.setAlignment(Pos.TOP_CENTER);
        layoutPrincipal.setPadding(new Insets(30));
        layoutPrincipal.setStyle("-fx-background-color: #e7d3a8;");

        // Título
        Text titulo = new Text("Ingresá los nombres de los jugadores");
        titulo.setFont(Font.font("System", FontWeight.BOLD, 30));
        VBox.setMargin(titulo, new Insets(50, 0, 80, 0));
        layoutPrincipal.getChildren().add(titulo);

        // Fila de tarjetas
        HBox filaTarjetas = new HBox(60);
        filaTarjetas.setAlignment(Pos.CENTER);

        for (int i = 1; i <= cantidadJugadores; i++) {
            VBox tarjeta = new VBox(10);
            tarjeta.setAlignment(Pos.CENTER);
            tarjeta.setPadding(new Insets(20));
            tarjeta.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #cccccc;" +
                            "-fx-border-radius: 10;" +
                            "-fx-background-radius: 10;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 4, 0, 0, 2);"
            );
            tarjeta.setPrefWidth(200);

            // Imagen jugador.png
            ImageView imgJugador;
            try {
                Image img = new Image(getClass().getResourceAsStream("/jugador.png"));
                imgJugador = new ImageView(img);
                imgJugador.setFitHeight(70);
                imgJugador.setPreserveRatio(true);
            } catch (Exception ex) {
                imgJugador = new ImageView();
            }

            Text tituloJugador = new Text("Jugador " + i);
            tituloJugador.setFont(Font.font("System", FontWeight.BOLD, 20));

            TextField tf = new TextField();
            tf.setPromptText("Ingrese nombre");
            tf.setMaxWidth(200);
            camposNombres.add(tf);

            tarjeta.getChildren().addAll(imgJugador, tituloJugador, tf);
            filaTarjetas.getChildren().add(tarjeta);
        }

        layoutPrincipal.getChildren().add(filaTarjetas);

        Button continuar = new Button("Continuar");
        continuar.setFont(Font.font("System", FontWeight.BOLD, 18));
        VBox.setMargin(continuar, new Insets(20, 0, 0, 0));

        // Estilo inicial: bordes redondeados y fondo
        continuar.setStyle(
                "-fx-background-color: #d9a86c;" +   // color de fondo
                        "-fx-text-fill: black;" +            // color del texto
                        "-fx-background-radius: 15;" +      // bordes redondeados
                        "-fx-border-radius: 15;" +          // borde redondeado
                        "-fx-border-color: transparent;" +  // borde inicial transparente
                        "-fx-border-width: 2;"
        );

        // Efecto al pasar el cursor
        continuar.setOnMouseEntered(e -> continuar.setStyle(
                "-fx-background-color: #d9a86c;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: #3399ff;" +   // borde azul al hover
                        "-fx-border-width: 2;"
        ));

        continuar.setOnMouseExited(e -> continuar.setStyle(
                "-fx-background-color: #d9a86c;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-width: 2;"
        ));

        continuar.setOnAction(e -> {
            List<String> nombres = new ArrayList<>();
            int contador = 1;
            for (TextField tf : camposNombres) {
                String nombre = tf.getText().trim();
                if (nombre.isEmpty()) {
                    nombre = "Jugador " + contador;
                }
                nombres.add(nombre);
                contador++;
            }
            controlador.crearJugadoresYIniciarJuego(nombres);
        });

        layoutPrincipal.getChildren().add(continuar);

        Scene escena = new Scene(layoutPrincipal, 800, 600);
        stage.setScene(escena);
        stage.setTitle("Ingresar nombres - Catán");
        stage.setMaximized(true);
        stage.show();
    }
}
