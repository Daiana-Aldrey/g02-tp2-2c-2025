package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.controllers.ControladorArista;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class VistaArista extends Button {
    private final Arista arista;
    private ControladorArista controlador;
    private Circle circulo;
    private Line linea;

    public VistaArista(Arista arista) {
        this.arista = arista;
        controlador = new ControladorArista(this, arista);

        double radius = 20;
        double diameter = radius * 2;

        circulo = new Circle(radius);

        // --- LÍNEA PARA CUANDO HAY CAMINO ---
        linea = new Line(30, 30, 40, 40);
        linea.setStrokeWidth(8);

        setPrefSize(diameter, diameter);
        setMinSize(diameter, diameter);
        setMaxSize(diameter, diameter);

        setShape(circulo);
        setVisible(false);
    }

    public void mostrarAristaDisponible() {
        if (arista.estaDisponible()) {
            setVisible(true);
        }
    }

    public void invisibilizarVerticeDisponible() {
        if (arista.estaDisponible()) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public void setJugador(Jugador jugador) {
        controlador.setJugador(jugador);
    }

    public void cambiarColor(Color color) {
        this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void colocarPiezar(String camino) {
        controlador.colocarPiezaPrimerTurno(camino);
    }
}