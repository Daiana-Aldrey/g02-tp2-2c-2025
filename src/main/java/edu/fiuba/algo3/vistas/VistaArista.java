package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.controllers.ControladorArista;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class VistaArista extends Group {
    private final int VISTAVERTICE1 = 0;
    private final int VISTAVERTICE2 = 1;

    private final Arista arista;
    private List<VistaVerticeEdificio> verticesAdyacentes;
    private ControladorArista controlador;

    private Button boton;
    private Circle circulo;

    public VistaArista(Arista arista) {
        this.arista = arista;
        controlador = new ControladorArista(this, arista);
        verticesAdyacentes = new ArrayList<>();

        double radius = 20;
        double diameter = radius * 2;

        circulo = new Circle(radius);

        boton = new Button();
        boton.setPrefSize(diameter, diameter);
        boton.setMinSize(diameter, diameter);
        boton.setMaxSize(diameter, diameter);

        boton.setShape(circulo);
        boton.setVisible(false);
        getChildren().add(boton);

        controlador.setBoton(boton);
    }

    public void mostrarAristaDisponible() {
        if (arista.estaDisponible()) {
            boton.setVisible(true);
        }
    }

    public void invisibilizarVerticeDisponible() {
        if (arista.estaDisponible()) {
            boton.setVisible(false);
        }
    }

    public void setJugador(Jugador jugador) {
        controlador.setJugador(jugador);
    }

    public void agregarVistaVerticeAdyacente(VistaVerticeEdificio adyacente) {
        verticesAdyacentes.add(adyacente);
    }

    public void colocarPieza(String camino) {
        controlador.colocarPiezaPrimerTurno(camino);
    }

    public void cambiarFormaYColor(Color color) {
        boton.setVisible(false);
        VistaVerticeEdificio vertice1 = verticesAdyacentes.get(VISTAVERTICE1);
        VistaVerticeEdificio vertice2 = verticesAdyacentes.get(VISTAVERTICE2);

        double coordenadaX1 = vertice1.getTranslateX();
        double coordenadaY1 = vertice1.getTranslateY();

        double coordenadaX2 = vertice2.getTranslateX();
        double coordenadaY2 = vertice2.getTranslateY();

        double largo = 0;
        double grosor = 25;
        double anguloGrados = 0;

        Rectangle camino = new Rectangle();

        if (coordenadaX2 > coordenadaX1) {
            largo = coordenadaX2 - coordenadaX1 + 10;
            camino.setWidth(grosor);
            camino.setHeight(largo);
            camino.setTranslateX(10);
            camino.setTranslateY(-15);
            if (coordenadaY1 > coordenadaY2) {
                anguloGrados = 65;
            } else {
                anguloGrados = 295;
            }
        } else {
            largo = coordenadaY2 - coordenadaY1;
            camino.setWidth(grosor);
            camino.setHeight(largo);
            camino.setTranslateY(-20);
            camino.setTranslateX(8);

            anguloGrados = 0;
        }

        camino.setFill(color);
        camino.setStrokeWidth(4);
        camino.setArcHeight(10);
        camino.setArcWidth(10);
        camino.setStroke(Color.BLACK);

        camino.setRotate(anguloGrados);
        vertice1.toFront();
        vertice2.toFront();
        getChildren().add(camino);

    }
}
