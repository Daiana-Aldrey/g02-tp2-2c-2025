package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.JuegoObservable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VistaCartasDesarrollo {

    private final Stage ventana;
    private final JuegoObservable modelo;

    private FlowPane panelCartas;
    private VBox layout;

    public VistaCartasDesarrollo(Stage ventana, JuegoObservable modelo) {
        this.ventana = ventana;
        this.modelo = modelo;
    }

    public void mostrar() {
        //List<Map<String, String>> cartas = modelo.obtenerCartasDesarrolloJugadorActual();


        //-------Prueba para chequear como queda en la vista-------
        List<Map<String, String>> cartas = new ArrayList<>();
        cartas.add(Map.of(
                "nombre", "Caballero",
                "descripcion", "Permite mover al ladrón y robar un recurso de un jugador adyacente."
        ));

        cartas.add(Map.of(
                "nombre", "Construccion de Carreteras",
                "descripcion", "Construye dos carreteras gratis."
        ));

        cartas.add(Map.of(
                "nombre", "Descubrimiento",
                "descripcion", "Obtén dos recursos a elección."
        ));

        cartas.add(Map.of(
                "nombre", "Monopolio",
                "descripcion", "Elegí un recurso, todos los jugadores deben darte todo lo que tengan de ese tipo."
        ));

        cartas.add(Map.of(
                "nombre", "Punto De Victoria",
                "descripcion", "Otorga 1 punto de victoria oculto."
        ));


        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        Label titulo = new Label("Cartas de Desarrollo");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        panelCartas = new FlowPane();
        panelCartas.setHgap(10);
        panelCartas.setVgap(10);
        panelCartas.setPadding(new Insets(10));
        panelCartas.setAlignment(Pos.CENTER);

        for (Map<String, String> datos : cartas) {
            panelCartas.getChildren().add(crearTarjetaCarta(datos));
        }

        ScrollPane scroll = new ScrollPane(panelCartas);
        scroll.setFitToWidth(true);
        scroll.setPannable(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        layout.getChildren().addAll(titulo, scroll);

        Scene escena = new Scene(layout, 600, 600);
        ventana.setScene(escena);
        ventana.show();
    }


    //Tarjeta de una carta
    private VBox crearTarjetaCarta(Map<String, String> datos) {

        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.setAlignment(Pos.CENTER);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 2);"
        );
        card.setPrefWidth(250);

        //Titulo
        Text txtNombre = new Text(datos.get("nombre"));
        txtNombre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextFlow flowNombre = new TextFlow(txtNombre);
        flowNombre.setTextAlignment(TextAlignment.CENTER);
        flowNombre.setMaxWidth(220);   // límite para forzar wrap
        flowNombre.setPrefWidth(20);  // mantiene tamaño fijo
        flowNombre.setLineSpacing(1);

        // Imagen
        ImageView imgView;
        try {
            String nombre = datos.get("nombre").replace(" ", "");
            String ruta = "/Cartas/" + nombre + ".png";

            Image img = new Image(getClass().getResourceAsStream(ruta));
            imgView = new ImageView(img);
            imgView.setFitHeight(120);
            imgView.setPreserveRatio(true);
        } catch (Exception e) {
            imgView = new ImageView();
        }

        //Descripcion
        Text txtDescripcion = new Text(datos.get("descripcion"));
        txtDescripcion.setStyle("-fx-font-size: 14px;");

        TextFlow flowDescripcion = new TextFlow(txtDescripcion);
        flowDescripcion.setTextAlignment(TextAlignment.CENTER);
        flowDescripcion.setMaxWidth(220);
        flowDescripcion.setPrefWidth(200);
        flowDescripcion.setLineSpacing(1);

        // Botón Usar
        Button btnUsar = new Button("Usar");
        btnUsar.setStyle("-fx-background-color: #4b1f4f; -fx-text-fill: white; -fx-font-weight: bold;");
        btnUsar.setOnAction(e -> {
            manejarUsarCarta(datos.get("nombre"));
            actualizarCartas();
        });


        card.getChildren().addAll(flowNombre, imgView, flowDescripcion, btnUsar);
        return card;
    }

    private void manejarUsarCarta(String nombreCarta) {
        try {
            modelo.usarCarta(nombreCarta);
        } catch (Exception e) {
            System.out.println("Error al usar carta: " + e.getMessage());
        }
    }

    public void actualizarCartas() {
        panelCartas.getChildren().clear();

        List<Map<String, String>> cartas = modelo.obtenerCartasDesarrolloJugadorActual();

        for (Map<String, String> datos : cartas) {
            panelCartas.getChildren().add(crearTarjetaCarta(datos));
        }
    }

}
