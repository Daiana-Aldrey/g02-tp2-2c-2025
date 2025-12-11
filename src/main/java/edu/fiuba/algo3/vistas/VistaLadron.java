package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.*;
import edu.fiuba.algo3.modelo.Ubicacion.*;
import edu.fiuba.algo3.modelo.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VistaLadron extends Button {
    private Circle circulo;
    private ControladorMoverLadron controlador;
    private UbicacionVertice ubicacion;
    private ImageView viewLadron;
    private boolean hayLadron;
    

    public VistaLadron() {
        hayLadron = false;
        double radius = 20;
        double diameter = radius * 2;

        this.circulo = new Circle(radius);

        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        setPrefSize(diameter, diameter);
        setMinSize(diameter, diameter);
        setMaxSize(diameter, diameter);
        setCursor(Cursor.HAND);
        setOpacity(0.5);

        setShape(circulo);
        Image imgLadron = new Image("ladron.png");
        viewLadron = new ImageView(imgLadron);
        viewLadron.setFitHeight(50);
        viewLadron.setFitWidth(50);
        viewLadron.setOpacity(1.0);

        setVisible(false);
    }

    public void colocarLadron() {
        setOpacity(1.0);
        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        setGraphic(viewLadron);
        hayLadron = true;
        setVisible(true);
    }

    public void sacarLadron() {
        if (hayLadron) {
            setGraphic(null);
            setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            double radius = 20;
            double diameter = radius * 2;
            setPrefSize(diameter, diameter);
            setMinSize(diameter, diameter);
            setMaxSize(diameter, diameter);
            setOpacity(0.5);
            setShape(circulo);
            hayLadron = false;
        }
    }

    public void verDisponible() {
        if (!hayLadron) {
            setVisible(true);
        }
    }

    public void sacarDisponibles() {
        if (!hayLadron) {
            setVisible(false);
        }
    }
    
    public void setUbicacion(UbicacionVertice ubicacion) {
        this.ubicacion = ubicacion;
    }


    public void inicializarControlador(JuegoObservable modelo) {
        this.controlador = new ControladorMoverLadron(modelo, this.ubicacion, this);
        
        this.setOnMouseClicked(e -> {
            controlador.manejarClick();
        });
    }


}
