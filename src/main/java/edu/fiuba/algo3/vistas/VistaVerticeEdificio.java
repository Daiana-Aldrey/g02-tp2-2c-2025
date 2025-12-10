package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorVertice;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.VerticeEdificio;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class VistaVerticeEdificio extends Button {

    private VerticeEdificio vertice;
    private ControladorVertice controlador;
    private boolean poblado;
    Circle circulo;
    DropShadow sombra;

    public VistaVerticeEdificio(VerticeEdificio vertice) {
        this.vertice = vertice;
        controlador = new ControladorVertice(this, vertice);
        poblado = false;

        double radius = 20;
        double diameter = radius * 2;

        this.circulo = new Circle(radius);

        sombra = new DropShadow();

        setPrefSize(diameter, diameter);
        setMinSize(diameter, diameter);
        setMaxSize(diameter, diameter);
        setOpacity(0.5);

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

    public void cambiarFormaYColorPoblado(Color color) {
        setOpacity(color.getOpacity());
        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        SVGPath techo = new SVGPath();
        techo.setContent("M2487 4590 c-27 -5 -72 -20 -100 -34 -31 -16 -520 -416 -1212 -994 "
                + "-1222 -1019 -1188 -988 -1172 -1054 4 -13 58 -85 122 -160 96 -114 121 -138 "
                + "149 -144 19 -3 44 -3 55 1 11 3 517 420 1123 925 607 506 1105 920 1108 920 3 "
                + "0 501 -414 1108 -920 606 -505 1112 -922 1123 -925 11 -4 36 -4 55 -1 28 6 53 "
                + "30 149 144 64 75 118 147 122 160 15 62 3 74 -367 382 l-355 296 -5 672 -5 "
                + "672 -28 27 -27 28 -323 3 c-210 2 -335 0 -358 -7 -68 -20 -68 -22 -71 -386 "
                + "l-3 -327 -408 340 c-437 365 -450 374 -572 385 -33 3 -82 2 -108 -3z");

        SVGPath paredes = new SVGPath();
        paredes.setContent("M1642 3018 l-912 -752 0 -780 c0 -537 4 -794 11 -821 15 -55 83 -119 "
                + "142 -133 32 -9 228 -12 656 -12 l611 0 0 610 0 610 410 0 410 0 0 -610 0 -610 "
                + "611 0 c407 0 625 4 653 11 56 14 114 62 137 113 18 39 19 84 19 832 l0 790 "
                + "-909 749 c-499 412 -912 750 -917 752 -5 2 -420 -335 -922 -749z");

        techo.setFill(color);
        paredes.setFill(color);

        Group casa = new Group(techo,paredes);
        casa.setRotate(180);
        casa.setScaleX(0.011);
        casa.setScaleY(0.011);

        this.setCursor(Cursor.DEFAULT);
        this.setGraphic(casa);
        this.setEffect(sombra);
        poblado = true;
    }

    public void resaltarPoblado(Jugador jugador) {
        if (jugador.tenesPiezaEnUbicacion(vertice.obtenerUbicacion())) {
            setCursor(Cursor.HAND);
            setStyle("-fx-effect: dropshadow(gaussian, white, 25, 0.7, 0, 0); ");
        }
    }

    public void pobladoNoClickeable() {
        if (poblado) {
            setCursor(Cursor.DEFAULT);
            setStyle("-fx-background-color: transparent;");
            setEffect(sombra);
        }
    }

    public void colocarPieza(String poblado) {
        controlador.colocarPiezaPrimerTurno(poblado);
    }

    public void colocarPrimeraPieza(String poblado) {
        controlador.colocarPiezaPrimerTurno(poblado);
    }
    public void setComportamientoInicial(JuegoObservable modelo) {
        controlador.setComportamientoInicial(modelo);
    }

}
