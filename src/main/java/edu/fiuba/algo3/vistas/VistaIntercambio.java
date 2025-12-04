package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.JuegoObservable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VistaIntercambio {

    private final Scene escena;
    private final JuegoObservable modelo;
    private final Stage ventana;

    private String recursoOfertaSeleccionado = null;
    private String recursoPedidoSeleccionado = null;

    private List<Button> botonesOferta = new ArrayList<>();
    private List<Button> botonesPedido = new ArrayList<>();

    private Label lblMensaje;

    public VistaIntercambio(Stage ventana, JuegoObservable modelo) {
        this.ventana = ventana;
        this.modelo = modelo;

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        Label titulo = new Label("Intercambio con el Banco (Costo 4:1)");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label lblOferta = new Label("Selecciona el recurso a ENTREGAR (4):");
        HBox panelOferta = crearPanelDeFichas(true);

        Label lblPedido = new Label("Selecciona el recurso a RECIBIR (1):");
        HBox panelPedido = crearPanelDeFichas(false); 

        Button btnConfirmar = new Button("Realizar Intercambio");
        btnConfirmar.setStyle("-fx-background-color: #4b1f4f; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        lblMensaje = new Label("");
        btnConfirmar.setOnAction(e -> manejarIntercambio());
        layout.getChildren().addAll(titulo, lblOferta, panelOferta, lblPedido, panelPedido, btnConfirmar, lblMensaje);
        this.escena = new Scene(layout, 500, 500); 
    }


    private HBox crearPanelDeFichas(boolean esOferta) {
        HBox fila = new HBox(10);
        fila.setAlignment(Pos.CENTER);

        String[] recursos = {"Madera", "Ladrillo", "Lana", "Grano", "Mineral"};

        for (String nombreRecurso : recursos) {
            Button boton = crearBotonFicha(nombreRecurso, esOferta);
            fila.getChildren().add(boton);
            
            if (esOferta) {
                botonesOferta.add(boton);
            } else {
                botonesPedido.add(boton);
            }
        }
        return fila;
    }

    private Button crearBotonFicha(String nombre, boolean esOferta) {
        Button btn = new Button();
        btn.setUserData(nombre); 
        try {
            //las imagens tienen que llamrse "madera.png", "piedra.png"
            String ruta = "/recursos/" + nombre.toLowerCase() + ".png";
            ImageView imgView = new ImageView(new Image(getClass().getResourceAsStream(ruta)));
            imgView.setFitWidth(50);
            imgView.setFitHeight(70);
            imgView.setPreserveRatio(true);
            btn.setPrefSize(80, 100);
            btn.setMinSize(80, 100);
            btn.setMaxSize(80, 100);

            btn.setGraphic(imgView);
        } catch (Exception e) {
            //texto por si falla
            btn.setText(nombre);
            btn.setPrefSize(60, 80);
        }

        btn.setStyle("-fx-background-color: transparent; -fx-border-color: #cccccc; -fx-border-width: 2;");
        btn.setOnAction(e -> {
            if (esOferta) {
                recursoOfertaSeleccionado = nombre;
                actualizarEstiloSeleccion(botonesOferta, btn, "#e74c3c");
            } else {
                recursoPedidoSeleccionado = nombre;
                actualizarEstiloSeleccion(botonesPedido, btn, "#2ecc71"); 
            }
        });

        return btn;
    }

    private void actualizarEstiloSeleccion(List<Button> grupo, Button seleccionado, String colorBorde) {
        for (Button b : grupo) {
            b.setStyle("-fx-background-color: transparent; -fx-border-color: #cccccc; -fx-border-width: 2;");
        }
        seleccionado.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: " + colorBorde + "; -fx-border-width: 4;");
    }

    private void manejarIntercambio() {
        if (recursoOfertaSeleccionado == null || recursoPedidoSeleccionado == null) {
            lblMensaje.setText("¡Debes seleccionar ambos recursos!");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            modelo.realizarIntercambio(recursoOfertaSeleccionado, recursoPedidoSeleccionado);
            lblMensaje.setText("¡Intercambio realizado!");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } catch (Exception ex) {
            lblMensaje.setText("Error: No tienes suficientes recursos.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    public Scene getEscena() {
        return escena;
    }
}