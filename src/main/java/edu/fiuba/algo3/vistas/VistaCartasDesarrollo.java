package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorCartasDesarrollo;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.*;

public class VistaCartasDesarrollo {

    private final Stage ventana;
    private final JuegoObservable modelo;
    private ControladorCartasDesarrollo controlador;
    private FlowPane panelCartas;
    private VBox layout;

    public VistaCartasDesarrollo(Stage ventana, JuegoObservable modelo) {
        this.ventana = ventana;
        this.modelo = modelo;
        this.controlador = new ControladorCartasDesarrollo(modelo, this);
    }

    public void mostrar() {
        List<Map<String, String>> cartas = modelo.obtenerCartasDesarrolloJugadorActual();

        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        Label titulo = new Label("Cartas de Desarrollo");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button btnComprar = new Button("Comprar carta de desarrollo");
        btnComprar.setStyle(
                "-fx-background-color: #2e7d32;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );

        btnComprar.setOnAction(e -> controlador.manejarComprarCarta());

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

        layout.getChildren().addAll(titulo, btnComprar, scroll);

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
        flowNombre.setMaxWidth(220);
        flowNombre.setPrefWidth(20);
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
        String nombreCarta = datos.get("nombre");
        btnUsar.setOnAction(e -> controlador.manejarUsarCarta(nombreCarta));
        btnUsar.setCursor(Cursor.HAND);

        card.getChildren().addAll(flowNombre, imgView, flowDescripcion, btnUsar);
        return card;
    }

    public void actualizarCartas() {
        panelCartas.getChildren().clear();

        List<Map<String, String>> cartas = modelo.obtenerCartasDesarrolloJugadorActual();

        for (Map<String, String> datos : cartas) {
            panelCartas.getChildren().add(crearTarjetaCarta(datos));
        }
    }

    public void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void mostrarMensaje(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public String mostrarDialogoMonopolio() {
        java.util.List<String> opciones = Arrays.asList("Madera", "Ladrillo", "Lana", "Grano", "Mineral");

        ChoiceDialog<String> dialogo = new ChoiceDialog<>(opciones.get(0), opciones);
        dialogo.setTitle("Carta Monopolio");
        dialogo.setHeaderText("Elegí el recurso que a monopolizar");
        dialogo.setContentText("Recurso:");

        Optional<String> resultado = dialogo.showAndWait();

        /*if (resultado.isPresent()) {
            String recursoElegido = resultado.get();
            try {
                modelo.configurarCartaMonopolio(recursoElegido);
                modelo.usarCartaDesarrollo("Monopolio");
                actualizarCartas();
            } catch (Exception e) {
                mostrarError("No se pudo usar Monopolio: " + e.getMessage());
            }
        }*/
        return resultado.orElse(null);
    }

    public List<String> mostrarDialogoSeleccionRecursosDescubrimiento() {

        Dialog<ButtonType> dialogo = new Dialog<>();
        dialogo.setTitle("Carta Descubrimiento");
        dialogo.setHeaderText("Elegí dos recursos para obtener de la banca");

        ButtonType botonAceptar = ButtonType.OK;
        ButtonType botonCancelar = ButtonType.CANCEL;
        dialogo.getDialogPane().getButtonTypes().addAll(botonAceptar, botonCancelar);

        ComboBox<String> comboRecurso1 = new ComboBox<>();
        ComboBox<String> comboRecurso2 = new ComboBox<>();

        comboRecurso1.getItems().addAll("madera", "ladrillo", "lana", "grano", "mineral");
        comboRecurso2.getItems().addAll("madera", "ladrillo", "lana", "grano", "mineral");

        comboRecurso1.setValue("madera");
        comboRecurso2.setValue("lana");

        VBox contenido = new VBox(10);
        contenido.setPadding(new Insets(10));
        contenido.setAlignment(Pos.CENTER_LEFT);
        contenido.getChildren().addAll(
                new Label("Recurso 1:"), comboRecurso1,
                new Label("Recurso 2:"), comboRecurso2
        );

        dialogo.getDialogPane().setContent(contenido);
        var resultado = dialogo.showAndWait();

        if (resultado.isPresent() && resultado.get() == botonAceptar) {
            String r1 = comboRecurso1.getValue();
            String r2 = comboRecurso2.getValue();
            return List.of(r1, r2);
        }
        return List.of();
    }

    /*public ConfiguracionCaballero mostrarDialogoCaballero() {
        TextInputDialog dialogoVictima = new TextInputDialog();
        dialogoVictima.setTitle("Carta Caballero");
        dialogoVictima.setHeaderText("Elegí al jugador víctima");
        dialogoVictima.setContentText("Nombre del jugador a robar:");

        Optional<String> resultadoVictima = dialogoVictima.showAndWait();
        String nombreVictima = resultadoVictima.get().trim();

        TextInputDialog dialogoDestino = new TextInputDialog();
        dialogoDestino.setTitle("Carta Caballero");
        //si no tengo un MODO LADRON lo hago
        //dialogoDestino.setHeaderText("Elegí la casilla donde mover al ladrón");
        //dialogoDestino.setContentText("en que vertice");

        Optional<String> resultadoDestino = dialogoDestino.showAndWait();

        String textoDestino = resultadoDestino.get().trim().toUpperCase();
        char idVertice = textoDestino.charAt(0);
        UbicacionVertice destino = new UbicacionVertice(idVertice);
        return new ConfiguracionCaballero(destino, nombreVictima);
    }*/

    public ConfiguracionCarreteras mostrarDialogoConstruccionCarreteras() {
        TextInputDialog dialogoCamino1 = new TextInputDialog();
        dialogoCamino1.setTitle("Carta Construcción de carreteras");
        dialogoCamino1.setHeaderText("Primer camino");
        dialogoCamino1.setContentText("Ingresá dos vértices :");
        Optional<String> res1 = dialogoCamino1.showAndWait();
        List<Ubicacion> camino1;
        try {
            camino1 = parsearCamino(res1.get());
        } catch (Exception e) {
            mostrarError("Formato inválido");
            return null;
        }
        TextInputDialog dialogoCamino2 = new TextInputDialog();
        dialogoCamino2.setTitle("Carta Construcción de carreteras");
        dialogoCamino2.setHeaderText("Segundo camino");
        dialogoCamino2.setContentText("Ingresá dos vértices ");
        Optional<String> res2 = dialogoCamino2.showAndWait();
        List<Ubicacion> camino2;
        try {
            camino2 = parsearCamino(res2.get());
        } catch (Exception e) {
            mostrarError("Formato inválido");
            return null;
        }

        return new ConfiguracionCarreteras(camino1, camino2);
    }

    private List<Ubicacion> parsearCamino(String texto) {
        String[] partes = texto.trim().toUpperCase().split("-");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Formato inválido. Usá algo como A-B");
        }

        java.util.List<Ubicacion> camino = new ArrayList<>();
        camino.add(new UbicacionVertice(partes[0].trim().charAt(0)));
        camino.add(new UbicacionVertice(partes[1].trim().charAt(0)));
        return camino;
    }

    public static class ConfiguracionCaballero {
        public final UbicacionVertice destino;
        public final String nombreVictima;

        private ConfiguracionCaballero(UbicacionVertice destino, String nombreVictima) {
            this.destino = destino;
            this.nombreVictima = nombreVictima;
        }
    }

    public static class ConfiguracionCarreteras {
        public final List<Ubicacion> camino1;
        public final List<Ubicacion> camino2;

        private ConfiguracionCarreteras(List<Ubicacion> camino1, List<Ubicacion> camino2) {
            this.camino1 = camino1;
            this.camino2 = camino2;
        }
    }
}