package edu.fiuba.algo3.vistas;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VistaDados extends HBox {

    private final Pane dado1;
    private final Pane dado2;

    public VistaDados() {
        this.setSpacing(20);

        dado1 = crearDado();
        dado2 = crearDado();

        this.getChildren().addAll(dado1, dado2);
    }

    private Pane crearDado() {
        Pane pane = new Pane();
        pane.setMinSize(70, 70);

        Rectangle borde = new Rectangle(70, 70);
        borde.setFill(Color.WHITE);
        borde.setStroke(Color.BLACK);

        borde.setArcHeight(30);
        borde.setArcWidth(20);

        pane.getChildren().add(borde);
        return pane;
    }

    private Circle punto(double x, double y) {
        Circle c = new Circle(6, Color.BLACK);
        c.setTranslateX(x);
        c.setTranslateY(y);
        return c;
    }

    public void actualizar(int d1, int d2) {
        dibujarDado(dado1, d1);
        dibujarDado(dado2, d2);
    }

    private void dibujarDado(Pane cara, int numero) {
        cara.getChildren().retainAll(cara.getChildren().get(0)); 
        double[][] posiciones = obtenerPosiciones(numero);

        for (double[] p : posiciones) {
            cara.getChildren().add(punto(p[0], p[1]));
        }
    }


    private double[][] obtenerPosiciones(int numero) {
        switch (numero) {
            case 1: return new double[][] {{35, 35}};
            case 2: return new double[][] {{17, 17}, {52, 52}};
            case 3: return new double[][] {{17, 17}, {35, 35}, {52, 52}};
            case 4: return new double[][] {{17, 17}, {52, 17}, {17, 52}, {52, 52}};
            case 5: return new double[][] {{17, 17}, {52, 17}, {35, 35}, {17, 52}, {52, 52}};
            case 6: return new double[][] {{17, 17}, {52, 17}, {17, 35}, {52, 35}, {17, 52}, {52, 52}};
        }
        return new double[0][0];
    }
}

  