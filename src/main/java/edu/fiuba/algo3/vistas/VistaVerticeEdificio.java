package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorVertice;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.VerticeEdificio;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class VistaVerticeEdificio extends Button {

    private VerticeEdificio vertice;
    private ControladorVertice controlador;
    Circle circulo;

    public VistaVerticeEdificio(VerticeEdificio vertice) {
        this.vertice = vertice;
        controlador = new ControladorVertice(this, vertice);

        double radius = 20;
        double diameter = radius * 2;

        this.circulo = new Circle(radius);
        circulo.setFill(Color.BLACK);
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

    public void mostrarVerticeDisponible() {
        if (vertice.estaDisponible()) {
            setVisible(true);
        } else if (vertice.hayPieza() && !vertice.estaDisponible()) {
            setVisible(true);
        } else if (!vertice.hayPieza() && !vertice.estaDisponible()) {
            setVisible(false);
        }
    }

    public void invisibilizarVerticeDisponible() {
        if (vertice.hayPieza()){
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

    public void setJugador(Jugador jugador) {
        controlador.setJugadorActual(jugador);
    }

    public void cambiarColor(Color color) {
        this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void colocarPiezar(String poblado) {
        controlador.colocarPiezaPrimerTurno(poblado);
    }
}
