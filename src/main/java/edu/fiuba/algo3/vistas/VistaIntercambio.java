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
    private Label lblOferta; 

    public VistaIntercambio(Stage ventana, JuegoObservable modelo) {
        this.ventana = ventana;
        this.modelo = modelo;

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f0f0f0;");

        Label titulo = new Label("Intercambio con el Banco");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        lblOferta = new Label("Selecciona el recurso a ENTREGAR:");
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
            String ruta = "/recursos/" + nombre.toLowerCase() + ".png";
            ImageView imgView = new ImageView(new Image(getClass().getResourceAsStream(ruta)));
            imgView.setFitWidth(70);
            imgView.setFitHeight(70);
            imgView.setPreserveRatio(true);
            btn.setPrefSize(80, 100);
            btn.setGraphic(imgView);
        } catch (Exception e) {
            btn.setText(nombre);
            btn.setPrefSize(80, 100);
        }

        btn.setStyle("-fx-background-color: transparent; -fx-border-color: #cccccc; -fx-border-width: 2;");
        
        btn.setOnAction(e -> {
            if (esOferta) {
                recursoOfertaSeleccionado = nombre;
                actualizarEstiloSeleccion(botonesOferta, btn, "#e74c3c");
                actualizarCostoVisual(nombre);
                
            } else {
                recursoPedidoSeleccionado = nombre;
                actualizarEstiloSeleccion(botonesPedido, btn, "#2ecc71"); 
            }
        });

        return btn;
    }

    private void actualizarCostoVisual(String recurso) {
        try {
            int costo = modelo.consultarCostoIntercambio(recurso);
            lblOferta.setText("Selecciona el recurso a ENTREGAR (" + costo + "):");
           
            if (costo < 4) {
                lblOferta.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            } else {
                lblOferta.setStyle("-fx-text-fill: black;");
            }
        } catch (Exception e) {
            lblOferta.setText("Selecciona el recurso a ENTREGAR:");
        }
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
            int costoFinal = modelo.consultarCostoIntercambio(recursoOfertaSeleccionado);
            lblMensaje.setText("¡Intercambio realizado! (Tasa " + costoFinal + ":1)");
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