package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorIngresoNombres;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VistaIngresarNombres {

    private final Stage stage;
    private final int cantidadJugadores;
    private final List<TextField> camposNombres = new ArrayList<>();
    private final List<ColorPicker> coloresJugadores = new ArrayList<>();
    private final Set<Color> coloresUsados = new HashSet<>();
    private final ControladorIngresoNombres controlador;
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int)(color.getRed()*255),
                (int)(color.getGreen()*255),
                (int)(color.getBlue()*255)
        );
    }

    private String colorToName(Color c) {
        if (c.equals(Color.RED)) return "Rojo";
        if (c.equals(Color.BLUE)) return "Azul";
        if (c.equals(Color.GREEN)) return "Verde";
        if (c.equals(Color.YELLOW)) return "Amarillo";
        if (c.equals(Color.ORANGE)) return "Naranja";
        if (c.equals(Color.PURPLE)) return "Violeta";
        return "Color";
    }

    private Color nameToColor(String name) {
        switch (name) {
            case "Rojo":
                return Color.RED;
            case "Azul":
                return Color.BLUE;
            case "Verde":
                return Color.GREEN;
            case "Amarillo":
                return Color.YELLOW;
            case "Naranja":
                return Color.ORANGE;
            case "Violeta":
                return Color.PURPLE;
            default:
                return Color.BLACK;
        }
    }

    public VistaIngresarNombres(Stage stage, int cantidadJugadores, ControladorIngresoNombres controlador) {
        this.stage = stage;
        this.cantidadJugadores = cantidadJugadores;
        this.controlador = controlador;
    }

    public void mostrar() {
        // Layout principal como BorderPane
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setStyle("-fx-background-color: #e7d3a8;");

        // Botón Volver con imagen y texto
        Image imgVolver = new Image(getClass().getResourceAsStream("/volver.png"));
        ImageView ivVolver = new ImageView(imgVolver);
        ivVolver.setFitHeight(25);
        ivVolver.setPreserveRatio(true);

        Text textoVolver = new Text("Volver");
        textoVolver.setFont(Font.font("System", FontWeight.BOLD, 20));

        HBox contenidoBoton = new HBox(5, ivVolver, textoVolver);
        contenidoBoton.setAlignment(Pos.CENTER_LEFT);

        Button volver = new Button();
        volver.setGraphic(contenidoBoton);
        volver.setStyle("-fx-background-color: transparent;");
        volver.setCursor(Cursor.HAND);
        volver.setOnAction(e -> new VistaInicio(stage, controlador).mostrar());

        volver.setStyle(
                        "-fx-background-color: #d9a86c;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-width: 1;"
        );

        //Hover
        volver.setOnMouseEntered(e -> volver.setStyle(
                        "-fx-background-color: #d9a86c;" +
                        "-fx-border-color: #3399ff;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-width: 1;"
        ));

        volver.setOnMouseExited(e -> volver.setStyle(
                        "-fx-background-color: #d9a86c;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-width: 1;"
        ));

        HBox topBar = new HBox(volver);
        topBar.setPadding(new Insets(15));
        topBar.setAlignment(Pos.TOP_LEFT);

        contenedorPrincipal.setTop(topBar);


        VBox layoutPrincipal = new VBox(30);
        layoutPrincipal.setAlignment(Pos.TOP_CENTER);
        layoutPrincipal.setPadding(new Insets(30));

        // Paleta de colores permitidos
        List<Color> coloresPermitidos = List.of(
                Color.RED,
                Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.ORANGE,
                Color.PURPLE
        );

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


            // ------------------ Texto "Ingrese color" -------------------
            Text textoColor = new Text("Ingrese color:");
            textoColor.setFont(Font.font("System", 14));


            // ===================== COLOR PICKER PERSONALIZADO =====================
            ColorPicker colorPicker = new ColorPicker();
            colorPicker.setPrefWidth(150);

            // BLOQUEAR el menú nativo del ColorPicker
            colorPicker.addEventFilter(MouseEvent.MOUSE_RELEASED, Event::consume);
            //colorPicker.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
            //colorPicker.addEventFilter(ActionEvent.ACTION, Event::consume);

            // Menú personalizado
            ContextMenu menuColores = new ContextMenu();

            for (Color c : coloresPermitidos) {
                MenuItem item = new MenuItem(colorToName(c));
                item.setStyle("-fx-background-color: " + toHexString(c) + ";");

                item.setOnAction(e -> {
                    // Liberar color anterior
                    Color oldColor = colorPicker.getValue();
                    coloresUsados.remove(oldColor);

                    // Asignar color nuevo
                    colorPicker.setValue(c);
                    coloresUsados.add(c);
                });

                menuColores.getItems().add(item);
            }

            // Mostrar menú cuando clickean el ColorPicker
            colorPicker.setOnMouseClicked(e -> {
                for (MenuItem item : menuColores.getItems()) {
                    Color c = nameToColor(item.getText());
                    item.setDisable(coloresUsados.contains(c));
                }
                menuColores.show(colorPicker, Side.BOTTOM, 0, 0);
            });

            coloresJugadores.add(colorPicker);
            tarjeta.getChildren().addAll(
                    imgJugador,
                    tituloJugador,
                    tf,          // ⬅️ primero el campo de nombre
                    textoColor,  // ⬅️ luego el texto "Ingrese color"
                    colorPicker  // ⬅️ y finalmente el selector de color
            );
            filaTarjetas.getChildren().add(tarjeta);
        }

        layoutPrincipal.getChildren().add(filaTarjetas);

        // Botón continuar
        Button continuar = new Button("Continuar");
        continuar.setFont(Font.font("System", FontWeight.BOLD, 18));
        continuar.setCursor(Cursor.HAND);
        VBox.setMargin(continuar, new Insets(20, 0, 0, 0));

        // Estilo inicial
        continuar.setStyle(
                "-fx-background-color: #d9a86c;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-width: 2;"
        );

        // Hover efecto
        continuar.setOnMouseEntered(e -> continuar.setStyle(
                "-fx-background-color: #d9a86c;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: #3399ff;" +
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
            List<Color> coloresIndividuales = new ArrayList<>();
            int contador = 1;
            for (TextField tf : camposNombres) {
                String nombre = tf.getText().trim();
                if (nombre.isEmpty()) {
                    nombre = "Jugador " + contador;
                }
                nombres.add(nombre);
                contador++;
            }
            for (ColorPicker cp : coloresJugadores) {
                coloresIndividuales.add(cp.getValue());
            }

            // Validación → todos los colores deben estar elegidos
            if (coloresIndividuales.contains(Color.WHITE)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Falta seleccionar colores");
                alerta.setContentText("Cada jugador debe elegir un color antes de continuar.");
                alerta.showAndWait();
                return;
            }
            controlador.crearJugadoresYIniciarJuego(nombres, coloresIndividuales);
        });

        layoutPrincipal.getChildren().add(continuar);

        contenedorPrincipal.setCenter(layoutPrincipal);

        Scene escena = new Scene(contenedorPrincipal, 800, 600);
        stage.setScene(escena);
        stage.setTitle("Ingresar nombres y color - Catán");
        stage.setMaximized(true);
        stage.show();
    }
}
