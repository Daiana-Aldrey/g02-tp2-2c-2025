package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.controllers.ControladorArista;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

public class VistaArista extends Button {
    private final Arista arista;
    private ControladorArista controlador;

    public VistaArista(Arista arista) {
        this.arista = arista;
        controlador = new ControladorArista(this, arista);

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


    public void actualizar() {
        if (arista.estaDisponible()) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    }
}