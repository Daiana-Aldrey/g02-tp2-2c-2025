package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class VistaPropuesta extends VBox {
    private final JuegoObservable modelo;
    private final VBox panelIconosOferta;
    private final VBox panelIconosDemanda;
    private final Label lblTitulo;
    private final Label lblError;

    public VistaPropuesta(JuegoObservable modelo) {
        this.modelo = modelo;
        this.setStyle("-fx-background-color: rgba(236, 240, 241, 0.95); -fx-background-radius: 15; -fx-padding: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 0);");
        this.setMaxSize(340, 240); 
        this.setAlignment(Pos.CENTER);
        this.setVisible(false); 

        lblTitulo = new Label("Oferta Entrante");
        lblTitulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2c3e50;");
        
        panelIconosOferta = new VBox();
        panelIconosDemanda = new VBox();
        
        HBox zonaRecursos = new HBox(15);
        zonaRecursos.setAlignment(Pos.CENTER);
        zonaRecursos.getChildren().addAll(
            crearCajita("Piden:", panelIconosDemanda),
            crearCajita("Te dan:", panelIconosOferta)
        );

        lblError = new Label("");
        lblError.setStyle("-fx-text-fill: #c0392b; -fx-font-weight: bold; -fx-font-size: 11px;");

       
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-min-width: 100px;");
        btnAceptar.setOnAction(e -> {
            try {
                modelo.aceptarPropuesta(); 
                lblError.setText("");
            } catch (Exception ex) { 
                lblError.setText("¡No tienes recursos!");
            }
        });

        Button btnRechazar = new Button("Rechazar");
        btnRechazar.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-min-width: 100px;");
        btnRechazar.setOnAction(e -> {
            this.setVisible(false);
            lblError.setText("");
        });

        HBox cajaBotones = new HBox(15, btnAceptar, btnRechazar);
        cajaBotones.setAlignment(Pos.CENTER);

        this.setSpacing(12);
        this.getChildren().addAll(lblTitulo, zonaRecursos, lblError, cajaBotones);
    }

    private VBox crearCajita(String titulo, VBox contenido) {
        VBox caja = new VBox(5);
        caja.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-padding: 8; -fx-border-color: #bdc3c7; -fx-border-radius: 8;");
        caja.setMinWidth(100);
        caja.setAlignment(Pos.TOP_CENTER);
        Label lbl = new Label(titulo);
        lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        caja.getChildren().addAll(lbl, contenido);
        return caja;
    }

    public void actualizarPropuesta() {
        if (!modelo.hayPropuesta()) {
            this.setVisible(false);
            return;
        }
        
        String quienPropone = modelo.getNombreJugadorProponente();
        String quienJuegaAhora = modelo.getNombreJugadorActual();
         
      
        if (quienPropone.equals(quienJuegaAhora)) {
           this.setVisible(false);
           return; 
        } 
        lblError.setText("");
        this.setVisible(true);
        lblTitulo.setText("Oferta de: " + quienPropone);
        
        llenarIconos(panelIconosOferta, modelo.getOferta());
        llenarIconos(panelIconosDemanda, modelo.getDemanda());
    }

    private void llenarIconos(VBox panel, List<Recurso> recursos) {
        panel.getChildren().clear();
        for (Recurso r : recursos) {
            if (r.cantidad() > 0) {
                HBox item = new HBox(8);
                item.setAlignment(Pos.CENTER_LEFT);
                String nombreClase = r.getClass().getSimpleName();
                if (nombreClase.equals("Grano")) nombreClase = "Trigo";
                if (nombreClase.equals("Mineral")) nombreClase = "Piedra";
                try {
                    ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/recursos/" + nombreClase.toLowerCase() + ".png")));
                    img.setFitWidth(20); img.setFitHeight(28); img.setPreserveRatio(true);
                    item.getChildren().add(img);
                } catch (Exception e) {}
                Label lbl = new Label("x" + r.cantidad());
                lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #2c3e50;");
                item.getChildren().add(lbl);
                panel.getChildren().add(item);
            }
        }
    }
}