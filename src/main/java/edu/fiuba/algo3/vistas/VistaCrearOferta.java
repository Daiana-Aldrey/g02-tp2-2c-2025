package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Recurso.*;
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

public class VistaCrearOferta {

    private final Stage stage;
    private final JuegoObservable modelo;
    private List<Recurso> listaOferta;
    private List<Recurso> listaDemanda;

    private final String[] nombresImagenes = {"Madera", "Ladrillo", "Lana", "Grano", "Mineral"};
    
    private Label lblMensaje;

    public VistaCrearOferta(Stage stage, JuegoObservable modelo) {
        this.modelo = modelo;
        this.stage = stage;
        this.stage.setTitle("Crear Propuesta");
        this.stage.setResizable(false); 
        
        this.listaOferta = inicializarListaRecursos();
        this.listaDemanda = inicializarListaRecursos();

        VBox layoutPrincipal = new VBox(5); 
        layoutPrincipal.setPadding(new Insets(10, 15, 10, 15));
        layoutPrincipal.setAlignment(Pos.CENTER);
        layoutPrincipal.setStyle("-fx-background-color: #f0f0f0;");

        Label titulo = new Label("Configura tu Propuesta");
        titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #3b2145;");
        VBox.setMargin(titulo, new Insets(0, 0, 5, 0));

     
        Label lblOferta = new Label("Tus Recursos a Entregar:");
        lblOferta.setStyle("-fx-font-weight: bold; -fx-text-fill: #c0392b; -fx-font-size: 12px;");
        HBox panelOferta = crearPanelSelector(true);

        Label spacer = new Label(""); 
        spacer.setMinHeight(5);

        Label lblDemanda = new Label("Recursos que Pides:");
        lblDemanda.setStyle("-fx-font-weight: bold; -fx-text-fill: #27ae60; -fx-font-size: 12px;");
        VBox.setMargin(lblDemanda, new Insets(5, 0, 0, 0));
        HBox panelDemanda = crearPanelSelector(false); 

        // BOTONES
        Button btnPublicar = new Button("PUBLICAR");
        btnPublicar.setStyle("-fx-background-color: #3b2145; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-padding: 8 20; -fx-cursor: hand;");
        btnPublicar.setOnAction(e -> enviarPropuesta());
        
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 8 15; -fx-cursor: hand;");
        btnCancelar.setOnAction(e -> stage.close());

        HBox botonesAccion = new HBox(15, btnCancelar, btnPublicar);
        botonesAccion.setAlignment(Pos.CENTER);
        botonesAccion.setPadding(new Insets(10, 0, 5, 0));

        lblMensaje = new Label("");
        lblMensaje.setStyle("-fx-font-size: 11px;");

        layoutPrincipal.getChildren().addAll(titulo, lblOferta, panelOferta, spacer, lblDemanda, panelDemanda, botonesAccion, lblMensaje);

        this.stage.setScene(new Scene(layoutPrincipal));
    }

    public void mostrar() {
        stage.show();
        stage.centerOnScreen();
    }


    private List<Recurso> inicializarListaRecursos() {
        List<Recurso> lista = new ArrayList<>();
        lista.add(new Madera(0));   
        lista.add(new Ladrillo(0)); 
        lista.add(new Lana(0));     
        lista.add(new Grano(0));    
        lista.add(new Mineral(0));  
        return lista;
    }


    private HBox crearPanelSelector(boolean esOferta) {
        HBox panel = new HBox(5);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(8));
        panel.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 0);");

        List<Recurso> listaObjetivo = esOferta ? listaOferta : listaDemanda;

        for (int i = 0; i < listaObjetivo.size(); i++) {
            Recurso recursoObj = listaObjetivo.get(i);
            String nombreImg = nombresImagenes[i]; 
            
            VBox carta = crearCartaRecurso(recursoObj, nombreImg, esOferta);
            panel.getChildren().add(carta);
        }
        return panel;
    }

    private VBox crearCartaRecurso(Recurso recursoObj, String nombreImagen, boolean esOferta) {
        VBox carta = new VBox(4);
        carta.setAlignment(Pos.CENTER);
        carta.setPadding(new Insets(5));
        carta.setStyle("-fx-background-color: #ffffff; -fx-border-color: #bdc3c7; -fx-border-radius: 6; -fx-border-width: 1;");
        carta.setPrefWidth(80); 


        ImageView imgView = new ImageView();
        try {
            String ruta = "/recursos/" + nombreImagen.toLowerCase() + ".png";
            imgView.setImage(new Image(getClass().getResourceAsStream(ruta)));
            imgView.setFitWidth(70); imgView.setFitHeight(75); imgView.setPreserveRatio(true);
        } catch (Exception e) {
            carta.getChildren().add(new Label(nombreImagen.substring(0, 3)));
        }
        if (imgView.getImage() != null) carta.getChildren().add(imgView);


        Label lblCantidad = new Label("0");
        lblCantidad.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-min-width: 16px; -fx-alignment: center;");

        String estiloNegro = "-fx-background-color: #000000; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px; -fx-padding: 2 6; -fx-background-radius: 3; -fx-cursor: hand;";
        
        Button btnMenos = new Button("-");
        btnMenos.setStyle(estiloNegro);
        
        Button btnMas = new Button("+");
        btnMas.setStyle(estiloNegro);

        
        btnMas.setOnAction(e -> {
            if (esOferta) {
                String nombreClase = recursoObj.getClass().getSimpleName();

                if (!modelo.jugadorTieneRecurso(nombreClase, recursoObj.cantidad() + 1)) {
                    carta.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e74c3c; -fx-border-radius: 6; -fx-border-width: 2;");
                    lblMensaje.setText("No tienes suficiente " + nombreClase);
                    lblMensaje.setStyle("-fx-text-fill: red;");
                    return; 
                } else {
                    carta.setStyle("-fx-background-color: #ffffff; -fx-border-color: #bdc3c7; -fx-border-radius: 6; -fx-border-width: 1;");
                    lblMensaje.setText("");
                }
            }
            
            recursoObj.incrementar(1);
            actualizarLabel(lblCantidad, recursoObj.cantidad(), esOferta);
        });

        btnMenos.setOnAction(e -> {
            if (recursoObj.cantidad() > 0) {
                recursoObj.decrementar(1);
                actualizarLabel(lblCantidad, recursoObj.cantidad(), esOferta);
                carta.setStyle("-fx-background-color: #ffffff; -fx-border-color: #bdc3c7; -fx-border-radius: 6; -fx-border-width: 1;");
                lblMensaje.setText("");
            }
        });

        HBox controles = new HBox(3, btnMenos, lblCantidad, btnMas);
        controles.setAlignment(Pos.CENTER);
        carta.getChildren().add(controles);

        return carta;
    }

    private void actualizarLabel(Label label, int cantidad, boolean esOferta) {
        label.setText(String.valueOf(cantidad));
        if (cantidad > 0) {
            label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-min-width: 16px; -fx-alignment: center; -fx-text-fill: " + (esOferta ? "#c0392b" : "#27ae60") + ";");
        } else {
            label.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-min-width: 16px; -fx-alignment: center; -fx-text-fill: black;");
        }
    }

    private void enviarPropuesta() {
        List<Recurso> ofertaFinal = filtrarVacios(listaOferta);
        List<Recurso> demandaFinal = filtrarVacios(listaDemanda);

        if (ofertaFinal.isEmpty() || demandaFinal.isEmpty()) {
            lblMensaje.setText("Selecciona recursos!");
            lblMensaje.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }

        try {
            modelo.crearPropuesta(ofertaFinal, demandaFinal);
            stage.close();
        } catch (Exception ex) {
            lblMensaje.setText("Error: " + ex.getMessage());
        }
    }

    private List<Recurso> filtrarVacios(List<Recurso> listaOriginal) {
        List<Recurso> filtrada = new ArrayList<>();
        for (Recurso r : listaOriginal) {
            if (r.cantidad() > 0) {
                filtrada.add(r);
            }
        }
        return filtrada;
    }
}