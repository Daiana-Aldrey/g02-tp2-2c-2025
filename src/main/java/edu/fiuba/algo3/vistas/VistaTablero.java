package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorTablero;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VistaTablero extends Application {
    private static final int CANTIDADHEXAGONOS = 19;
    private ControladorTablero controlador;
    //luego poner en el constructor
    private int indice = 0;
    List<Polygon> hexagonos = new ArrayList<>();
    Group root = new Group();

    private Polygon crearHexagono(int x, int y){
        int tamfil = 105;
        int tamcol = 150;
        Polygon hexagono = new Polygon();
        hexagono.setStroke(ConstanteColores.coloresTablero[ConstanteColores.ARENA]);
        hexagono.setStrokeWidth(12);

        if (y % 2 == 0){
        hexagono.getPoints().addAll(new Double[]{
                tamcol * x + 100.0, tamfil * y + 50.0,
                tamcol * x + 250.0, tamfil * y + 50.0,
                tamcol * x + 175.0, tamfil * y + 25.0,
                tamcol * x + 250.0, tamfil * y + 130.0,
                tamcol * x + 175.0, tamfil * y + 155.0,
                tamcol * x + 100.0, tamfil * y + 130.0,
        });
        } else {
            hexagono.getPoints().addAll(new Double[]{
                    tamcol * x + 100.0 - 75, tamfil * y + 50.0,
                    tamcol * x + 175.0 - 75, tamfil * y + 25.0,
                    tamcol * x + 250.0 - 75, tamfil * y + 50.0,
                    tamcol * x + 250.0 - 75, tamfil * y + 130.0,
                    tamcol * x + 175.0 - 75, tamfil * y + 155.0,
                    tamcol * x + 100.0 - 75, tamfil * y + 130.0,
            });
        }
        return hexagono;
    }

    private Rectangle crearRectangulo(int x, int y){
        Rectangle rectangle = new Rectangle();
        rectangle.setX(452.0f);
        rectangle.setY(85.0f);
        rectangle.setWidth(45.0f);
        rectangle.setHeight(45.0f);

        rectangle.setAccessibleText("hola");
        rectangle.setAccessibleText("que onda");

        rectangle.setArcWidth(30.0);
        rectangle.setArcHeight(20.0);

        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);

        Text text = new Text("4");
        text.setFill(Color.BLACK);

        root.getChildren().addAll(rectangle, text);
        return rectangle;
    }

    @Override
    public void start(Stage stage) throws Exception {
        coordenarHexagonos();
        setControlador(new ControladorTablero(Tablero.getInstance(), this));
        controlador.colocarTerrenos();
        crearRectangulo(0,0);
        arrancar(stage);

    }

    private void coordenarHexagonos(){
        int y = 0;
        int x = 0;
        for (int i = 0; i < CANTIDADHEXAGONOS; i++) {
            if (i < 3) {
                x = 2 + i;
                y = 0;
            } else if (i < 7) {
                x = 1 + i - 2;
                y = 1;
            } else if (i < 12) {
                x = i - 6;
                y = 2;
            } else if (i < 16) {
                x = 1 + i - 11;
                y = 3;
            } else {
                x = 2 + i - 16;
                y = 4;
            }
            Polygon hexagonoNuevo = crearHexagono(x, y);
            hexagonos.add(hexagonoNuevo);
            root.getChildren().add(hexagonoNuevo);
        }
    }

    public void arrancar(Stage stage) {
        Scene scene = new Scene(root ,600, 300);
        stage.setScene(scene);

        stage.show();
    }

    public void ponerPastizal(){
        hexagonos.get(indice).setFill(ConstanteColores.coloresTablero[ConstanteColores.VERDECLARO]);
        indice++;
    }

    public void ponerDesierto(){
        hexagonos.get(indice).setFill(ConstanteColores.coloresTablero[ConstanteColores.PARAMO]);
        indice++;
    }

    public void ponerBosque(){
        hexagonos.get(indice).setFill(ConstanteColores.coloresTablero[ConstanteColores.VERDEOSCURO]);
        indice++;
    }

    public void ponerMotania(){
        hexagonos.get(indice).setFill(ConstanteColores.coloresTablero[ConstanteColores.GRIS]);
        indice++;
    }

    public void ponerCampo(){
        hexagonos.get(indice).setFill(ConstanteColores.coloresTablero[ConstanteColores.AMARILLOCAMPO]);
        indice++;
    }

    public void ponerColina(){
        hexagonos.get(indice).setFill(ConstanteColores.coloresTablero[ConstanteColores.TERRACOTA]);
        indice++;
    }

    public void setControlador(ControladorTablero controlador) {
        this.controlador = controlador;
    }

    public static void main(String args[]){
        launch(args);
    }
}
