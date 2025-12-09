package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.JuegoObservable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.scene.control.ChoiceDialog;
import java.util.Optional;
import java.util.Arrays;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import java.util.Arrays;
import java.util.Optional;
import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.Excepciones.ErrorNoUsoDeCartaInvalido;
import edu.fiuba.algo3.Excepciones.NoTieneCarta;
import java.util.List;
import java.util.Map;

public class VistaCartasDesarrollo {

    private final Stage ventana;
    private final JuegoObservable modelo;

    private FlowPane panelCartas;
    private VBox layout;

    private static final List<Map<String, String>> CATALOGO = List.of(
            Map.of(
                    "nombre", "Caballero",
                    "descripcion", "Permite mover al ladrón y robar un recurso de un jugador adyacente."
            ),
            Map.of(
                    "nombre", "Construccion de Carreteras",
                    "descripcion", "Construye dos carreteras gratis."
            ),
            Map.of(
                    "nombre", "Descubrimiento",
                    "descripcion", "Obtén dos recursos a elección."
            ),
            Map.of(
                    "nombre", "Monopolio",
                    "descripcion", "Elegí un recurso, todos los jugadores deben darte todo lo que tengan de ese tipo."
            ),
            Map.of(
                    "nombre", "Punto de victoria",
                    "descripcion", "Otorga 1 punto de victoria oculto."
            )
    );

    public VistaCartasDesarrollo(Stage ventana, JuegoObservable modelo) {
        this.ventana = ventana;
        this.modelo = modelo;
    }


    public void mostrar() {
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

        btnComprar.setOnAction(e -> {
            try {
                modelo.comprarCartaDesarrollo();
                actualizarCartas();
                //mostrarMensaje("Compra exitosa", "Se agregó una nueva carta de desarrollo a tu mano.");
            } catch (Exception ex) {
                mostrarError("No se pudo comprar la carta: " + ex.getMessage());
            }
        });
        panelCartas = new FlowPane();
        panelCartas.setHgap(10);
        panelCartas.setVgap(10);
        panelCartas.setPadding(new Insets(10));
        panelCartas.setAlignment(Pos.CENTER);

//        List<Map<String, String>> cartas = modelo.obtenerCartasDesarrolloJugadorActual();
//        for (Map<String, String> datos : cartas) {
//            panelCartas.getChildren().add(crearTarjetaCarta(datos));
//        }

        ScrollPane scroll = new ScrollPane(panelCartas);
        scroll.setFitToWidth(true);
        scroll.setPannable(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        layout.getChildren().addAll(titulo, btnComprar, scroll);

        Scene escena = new Scene(layout, 600, 600);
        ventana.setScene(escena);
        actualizarCartas();
        ventana.show();
    }



    //Tarjeta de una carta vcon contador
    private VBox crearTarjetaCarta(Map<String, String> datos, int cantidad) {
        String nombreCarta = datos.get("nombre");
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

        Label lblCantidad = new Label("x" + cantidad);
        lblCantidad.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        //Titulo
        Text txtNombre = new Text(nombreCarta);
        txtNombre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextFlow flowNombre = new TextFlow(txtNombre);
        flowNombre.setTextAlignment(TextAlignment.CENTER);
        flowNombre.setMaxWidth(220);
        flowNombre.setPrefWidth(20);
        flowNombre.setLineSpacing(1);

        // Imagen
        ImageView imgView;
        try {
//            String archivoImagen = datos.get("imagen");
//            String ruta = "/Cartas/" + archivoImagen;

            String nombreArchivo = datos.get("nombre").replace(" ", "");
            String ruta = "/cartas/" + nombreArchivo + ".png";


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


        //Botones
        Button btnUsar = new Button();
        if ("Punto de victoria".equals(nombreCarta)) {
            btnUsar.setText("Revelar");
        } else {
            btnUsar.setText("Usar");
        }
        btnUsar.setStyle("-fx-background-color: #4b1f4f; -fx-text-fill: white; -fx-font-weight: bold;");

        if (cantidad == 0) {
            btnUsar.setDisable(true);
        }

        btnUsar.setOnAction(e -> {manejarUsarCarta(nombreCarta);
            actualizarCartas();
        });

        card.getChildren().addAll(flowNombre, imgView, flowDescripcion, btnUsar);
        return card;
    }

    private void manejarUsarCarta(String nombreCarta) {
        try {
            if ("Monopolio".equals(nombreCarta)) {
                String recursoElegido = mostrarDialogoMonopolio();
                modelo.configurarCartaMonopolio(recursoElegido);
                modelo.usarCartaDesarrollo(nombreCarta);
                mostrarMensaje("Carta usada", "Aplicaste Monopolio sobre " + recursoElegido + ".");
            }
            else if ("Descubrimiento".equals(nombreCarta)) {
                List<String> recursosElegidos = mostrarDialogoSeleccionRecursosDescubrimiento();

                String recurso1 = recursosElegidos.get(0);
                String recurso2 = recursosElegidos.get(1);
                modelo.configurarCartaDescubrimiento(recurso1, recurso2);
                modelo.usarCartaDesarrollo(nombreCarta);
                mostrarMensaje("Carta usada", "Recibiste 1 " + recurso1 + " y 1 " + recurso2 + ".");

            }
            else if ("Caballero".equals(nombreCarta)) {
                ConfiguracionCaballero config = mostrarDialogoCaballero();
                modelo.configurarCartaCaballero(config.destino, config.nombreVictima);
                modelo.usarCartaDesarrollo(nombreCarta);
                mostrarMensaje("Carta usada", "Moviste el ladrón y robaste a " + config.nombreVictima + ".");
            }

            else if ("Construccion de Carreteras".equals(nombreCarta)) {
                ConfiguracionCarreteras config = mostrarDialogoConstruccionCarreteras();
                modelo.configurarCartaConstruccionCarreteras(
                        config.camino1, config.camino2);
                modelo.usarCartaDesarrollo(nombreCarta);
                mostrarMensaje("Carta usada", "Construiste dos carreteras gratis.");
            }
            else {
                modelo.usarCartaDesarrollo(nombreCarta);
                mostrarMensaje("Carta usada", "Usaste la carta " + nombreCarta + ".");
            }
            actualizarCartas();


        } catch (ErrorNoUsoDeCartaInvalido e) {
            mostrarError("No podés usar una carta comprada en este turno.");
        } catch (NoTieneCarta e) {
            mostrarError("No tenés una carta de tipo " + nombreCarta + ".");
        } catch (Exception e) {
            mostrarError("Error al usar carta: " + e.getMessage());
        }
    }

    public void actualizarCartas() {
        panelCartas.getChildren().clear();
        Map<String, Integer> conteo = modelo.obtenerConteoCartasDesarrolloJugadorActual();
        for (Map<String, String> datos : CATALOGO) {
            String nombre = datos.get("nombre");
            int cantidad = conteo.getOrDefault(nombre, 0);
            panelCartas.getChildren().add(crearTarjetaCarta(datos, cantidad));
        }
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private String mostrarDialogoMonopolio() {
        java.util.List<String> opciones = Arrays.asList("Madera", "Ladrillo", "Lana", "Grano", "Mineral");

        ChoiceDialog<String> dialogo = new ChoiceDialog<>(opciones.get(0), opciones);
        dialogo.setTitle("Carta Monopolio");
        dialogo.setHeaderText("Elegí el recurso que querés monopolizar");
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
    private List<String> mostrarDialogoSeleccionRecursosDescubrimiento() {

        Dialog<ButtonType> dialogo = new Dialog<>();
        dialogo.setTitle("Carta Descubrimiento");
        dialogo.setHeaderText("Elegí dos recursos para obtener del banco");

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

    private ConfiguracionCarreteras mostrarDialogoConstruccionCarreteras() {
        TextInputDialog dialogoCamino1 = new TextInputDialog();
        dialogoCamino1.setTitle("Carta Construcción de carreteras");
        dialogoCamino1.setHeaderText("Primer camino");
        dialogoCamino1.setContentText("Ingresá dos vértices separados por guión (ej: A-B):");
        Optional<String> res1 = dialogoCamino1.showAndWait();
        List<Ubicacion> camino1;
        try {
            camino1 = parsearCamino(res1.get());
        } catch (Exception e) {
            mostrarError("Formato inválido para el primer camino (usá algo como A-B)");
            return null;
        }
        TextInputDialog dialogoCamino2 = new TextInputDialog();
        dialogoCamino2.setTitle("Carta Construcción de carreteras");
        dialogoCamino2.setHeaderText("Segundo camino");
        dialogoCamino2.setContentText("Ingresá dos vértices separados por guión (ej: C-D):");
        Optional<String> res2 = dialogoCamino1.showAndWait();
        List<Ubicacion> camino2;
        try {
            camino2 = parsearCamino(res2.get());
        } catch (Exception e) {
            mostrarError("Formato inválido para el segundo camino (usá C-D)");
            return null;
        }

        return new ConfiguracionCarreteras(camino1, camino2);
    }

    private ConfiguracionCaballero mostrarDialogoCaballero() {
        TextInputDialog dialogoVictima = new TextInputDialog();
        dialogoVictima.setTitle("Carta Caballero");
        dialogoVictima.setHeaderText("Elegí al jugador víctima");
        dialogoVictima.setContentText("Nombre del jugador a robar:");

        Optional<String> resultadoVictima = dialogoVictima.showAndWait();
        String nombreVictima = resultadoVictima.get().trim();

        TextInputDialog dialogoDestino = new TextInputDialog();
        dialogoDestino.setTitle("Carta Caballero");
        dialogoDestino.setHeaderText("Elegí la casilla donde mover al ladrón");
        dialogoDestino.setContentText("Identificador de vértice (ej: A, B, C...):");

        Optional<String> resultadoDestino = dialogoDestino.showAndWait();

        String textoDestino = resultadoDestino.get().trim().toUpperCase();
        char idVertice = textoDestino.charAt(0);
        UbicacionVertice destino = new UbicacionVertice(idVertice);
        return new ConfiguracionCaballero(destino, nombreVictima);
    }
    private static class ConfiguracionCaballero {
        private final UbicacionVertice destino;
        private final String nombreVictima;

        private ConfiguracionCaballero(UbicacionVertice destino, String nombreVictima) {
            this.destino = destino;
            this.nombreVictima = nombreVictima;
        }
    }

    private static class ConfiguracionCarreteras {
        private final List<Ubicacion> camino1;
        private final List<Ubicacion> camino2;

        private ConfiguracionCarreteras(List<Ubicacion> camino1, List<Ubicacion> camino2) {
            this.camino1 = camino1;
            this.camino2 = camino2;
        }
    }



}
