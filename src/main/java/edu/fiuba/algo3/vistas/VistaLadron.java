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
    private DropShadow sombra;
    private ImageView viewLadron;
    

    public VistaLadron() {
        double radius = 20;
        double diameter = radius * 2;

        this.circulo = new Circle(radius);

        sombra = new DropShadow();

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
        viewLadron.setOpacity(0.7);
    }

    public void colocarLadron() {
        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        setGraphic(viewLadron);
    }
    
    public void setUbicacion(UbicacionVertice ubicacion) {
        this.ubicacion = ubicacion;
    }


    public void inicializarControlador(JuegoObservable modelo) {
        this.controlador = new ControladorMoverLadron(modelo, this.ubicacion);
        
        this.setOnMouseClicked(e -> {
            controlador.manejarClick();
        });
    }


}
