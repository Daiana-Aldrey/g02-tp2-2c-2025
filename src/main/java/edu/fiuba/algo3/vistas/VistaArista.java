package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

public class VistaArista extends Line {
    private final Arista arista;

    public VistaArista(Arista arista, Runnable onClick) {
        this.arista = arista;

        /*
        setStartX(arista.getV1().getX());
        setStartY(arista.getV1().getY());
        setEndX(arista.getV2().getX());
        setEndY(arista.getV2().getY());
        */

        setStrokeWidth(8);
        setStroke(Color.GREY);

        setOnMouseClicked(e -> onClick.run());
    }

    public void actualizar() {
        if (arista.estaDisponible()) {
            setStroke(arista.obtenerPieza().obtenerJugador().obtenerColor());
        } else {
            setStroke(Color.GREY);
        }
    }
}