package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.CartaDeDesarrollo.Carta;
import edu.fiuba.algo3.modelo.JuegoObservable;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class VistaCartas {
    private final Stage stage;
    private final JuegoObservable modelo;
    private List<Carta> cartas;

    public VistaCartas(JuegoObservable modelo, Map<String, Integer> cartas) {
        this.modelo = modelo;
        this.stage = new Stage();
        HBox contenedor = new HBox(20);
        contenedor.setAlignment(Pos.CENTER);

        for (Map.Entry<String, Integer> entrada : cartas.entrySet()) {
            String tipo = entrada.getKey();
            int cantidad = entrada.getValue();

            VBox carta = crearCarta(tipo, cantidad);
            contenedor.getChildren().add(carta);
        }

        Scene scene = new Scene(contenedor, 600, 300);
        stage.setTitle("Cartas del Jugador");
        stage.setScene(scene);
        stage.show();
    }

    private VBox crearCarta(String tipo, int cantidad) {
        VBox carta = new VBox(5);
        carta.setAlignment(Pos.CENTER);

        // Cantidad arriba
        Label lblCantidad = new Label("x" + cantidad);
        lblCantidad.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Imagen
        Image imagen = new Image("file:src/main/resources/cartas/" + tipo + ".png");
        ImageView imagenView = new ImageView(imagen);
        imagenView.setFitWidth(100);
        imagenView.setFitHeight(100);
        imagenView.setPreserveRatio(true);

        carta.getChildren().addAll(lblCantidad, imagenView);

        // Borde opcional
        carta.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10;");

        return carta;
    }

    public void mostrar() {
        stage.show();
        stage.centerOnScreen();
    }
}
