package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorVertice;
import edu.fiuba.algo3.modelo.Tablero.VerticeEdificio;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class VistaVerticeEdificio extends Button {

    private VerticeEdificio vertice;
    private ControladorVertice controlador;

    public VistaVerticeEdificio(VerticeEdificio vertice) {
        this.vertice = vertice;
        controlador = new ControladorVertice(this, vertice);

        double radius = 20;
        double diameter = radius * 2;

        Circle circulo = new Circle(radius);
        circulo.setFill(Color.BEIGE);
        circulo.setStroke(Color.BLACK);

        setPrefSize(diameter, diameter);
        setMinSize(diameter, diameter);
        setMaxSize(diameter, diameter);

        setShape(circulo);
        setVisible(false);

    }

    public void agregarAdyacente(VistaVerticeEdificio vista) {
        controlador.agregarAdyacente(vista);
    }

    public void actualizar() {
        if (vertice.estaDisponible()) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    }
}
