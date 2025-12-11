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

    public void cambiarFormaACiudad(Color color) {
        setStyle("-fx-background-color: transparent;");
        SVGPath edificioSVG = new SVGPath();
        edificioSVG.setContent(
                "M1658 4686 c-626 -238 -1149 -441 -1163 -450 -14 -10 -35 -31 -45 -49 -20 -31 -20 -66 -20 -1893 l0 -1862 -138 -4 c-128 -3 -140 -5 -184 -31 -84 -49 -124 -150 -98 -245 15 -56 80 -123 136 -140 60 -18 4768 -18 4828 0 57 17 119 82 135 141 35 123 -44 247 -171 272 l-28 5 0 1336 c0 1466 3 1386 -60 1419 -111 57 -1527 655 -1552 655 -21 0 -40 -10 -64 -34 l-34 -34 0 -1671 0 -1671 -105 0 -105 0 -2 2288 -3 2287 -21 35 c-25 43 -87 80 -134 80 -22 0 -463 -164 -1172 -434z m-359 -866 c22 -11 50 -36 62 -57 23 -37 24 -45 24 -243 0 -198 -1 -206 -24 -243 -31 -51 -91 -81 -149 -74 -50 5 -87 29 -119 77 -22 32 -23 42 -23 237 0 217 3 235 52 280 49 46 116 55 177 23z m960 0 c22 -11 50 -36 62 -57 23 -37 24 -45 24 -243 0 -198 -1 -206 -24 -243 -31 -51 -91 -81 -149 -74 -50 5 -87 29 -119 77 -22 32 -23 42 -23 237 0 217 3 235 52 280 49 46 116 55 177 23z m-960 -1280 c22 -11 50 -36 62 -57 23 -37 24 -45 24 -243 0 -198 -1 -206 -24 -243 -31 -51 -91 -81 -149 -74 -50 5 -87 29 -119 77 -22 32 -23 42 -23 237 0 217 3 235 52 280 49 46 116 55 177 23z m960 0 c22 -11 50 -36 62 -57 23 -37 24 -45 24 -243 0 -198 -1 -206 -24 -243 -31 -51 -91 -81 -149 -74 -50 5 -87 29 -119 77 -22 32 -23 42 -23 237 0 217 3 235 52 280 49 46 116 55 177 23z m1509 -7 c14 -10 35 -32 46 -47 20 -26 21 -41 21 -246 0 -241 -1 -243 -69 -294 -39 -29 -133 -29 -172 0 -67 49 -69 57 -72 267 -2 106 -1 206 3 224 7 40 49 91 90 109 40 19 120 12 153 -13z m731 7 c22 -11 50 -36 62 -57 23 -37 24 -45 24 -243 0 -198 -1 -206 -24 -243 -31 -51 -91 -81 -149 -74 -50 5 -87 29 -119 77 -22 32 -23 42 -23 237 0 217 3 235 52 280 49 46 116 55 177 23z m-3200 -1280 c22 -11 50 -36 62 -57 23 -37 24 -45 24 -243 0 -198 -1 -206 -24 -243 -31 -51 -91 -81 -149 -74 -50 5 -87 29 -119 77 -22 32 -23 42 -23 237 0 217 3 235 52 280 49 46 116 55 177 23z m960 0 c22 -11 50 -36 62 -57 23 -37 24 -45 24 -243 0 -198 -1 -206 -24 -243 -31 -51 -91 -81 -149 -74 -50 5 -87 29 -119 77 -22 32 -23 42 -23 237 0 217 3 235 52 280 49 46 116 55 177 23z m1509 -7 c14 -10 35 -32 46 -47 20 -26 21 -41 21 -246 0 -241 -1 -243 -69 -294 -39 -29 -133 -29 -172 0 -67 49 -69 57 -72 267 -2 106 -1 206 3 224 7 40 49 91 90 109 40 19 120 12 153 -13z m731 7 c22 -11 50 -36 62 -57 23 -37 24 -45 24 -243 0 -198 -1 -206 -24 -243 -31 -51 -91 -81 -149 -74 -50 5 -87 29 -119 77 -22 32 -23 42 -23 237 0 217 3 235 52 280 49 46 116 55 177 23z"
        );

        edificioSVG.setFill(color);

        Group edificio = new Group(edificioSVG);
        edificio.setRotate(180);
        edificio.setScaleY(0.011);
        edificio.setScaleX(0.011);

        setEffect(sombra);
        setCursor(Cursor.DEFAULT);
        this.setGraphic(edificio);
        poblado = false;

    }

    public void resaltarPoblado(Jugador jugador) {
        if (jugador.tenesPobladoEnUbicacion(vertice.obtenerUbicacion())) {
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

    public void setComportamientoInicial(JuegoObservable modelo) {
        controlador.setComportamientoInicial(modelo);
    }
    public void habilitarConstruccion(String pieza, VistaRecursos vistaRecursos) {
        controlador.colocarPieza(pieza, vistaRecursos);
    }

}
