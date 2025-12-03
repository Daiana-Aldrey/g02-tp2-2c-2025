package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Tablero.VerticeEdificio;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;


public class VistaVerticeEdificio extends Circle {

    private final VerticeEdificio vertice;

    public VistaVerticeEdificio(VerticeEdificio vertice, Runnable onClick) {
        this.vertice = vertice;

        /*
        setCenterX(vertice.getX());
        setCenterY(vertice.getY());
        setRadius(15);
         */

        setFill(Color.BEIGE);
        setStroke(Color.BLACK);

        setOnMouseClicked(e -> onClick.run());
    }

    public void actualizar() {
        if (vertice.estaDisponible()) {
            setFill(vertice.obtenerPieza().obtenerJugador().obtenerColor());
        } else {
            setFill(Color.BEIGE);
        }
    }
}
