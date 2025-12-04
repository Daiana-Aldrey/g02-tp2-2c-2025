package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorTablero;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class VistaTablero {
    private static final int CANTIDADHEXAGONOS = 19;
    private static final int TAMANIOFILA = 125;
    private static final int TAMANIOCOLUMNA = 153;
    private static final int COLUMNA = 0;
    private static final int FILA = 1;
    private static final int DESPLAZAMIENTOMITAD = 75;
    private static final int DESPLAZAMIENTOTEXTONUMEROY = 94;
    private static final int DESPLAZAMIENTOTEXTOPROBABILIDADY = 104;

    private ControladorTablero controlador;
    private int indice;
    List<Polygon> hexagonos;
    List<Rectangle> rectangulos;
    List<Group> hexagonosConFicha;
    Group tablero;
    int desplazamientoX;
    int desplazamientoY;

    public VistaTablero() {
        indice = 0;
        hexagonos = new ArrayList<>();
        rectangulos = new ArrayList<>();
        hexagonosConFicha = new ArrayList<>();
        tablero = new Group();
        desplazamientoX = 0;
        desplazamientoY = 0;

        coordenarHexagonos();
    }

    public Group getVistaTablero() {
        return tablero;
    }

    private Polygon crearHexagono(int x, int y){
        Polygon hexagono = new Polygon();
        hexagono.setStroke(ConstanteColores.coloresTablero[ConstanteColores.ARENA]);
        hexagono.setStrokeWidth(12);

        if (y % 2 == 0){
        hexagono.getPoints().addAll(new Double[]{
                TAMANIOCOLUMNA * x + 100.0, TAMANIOFILA * y + 35.0,
                TAMANIOCOLUMNA * x + 175.0, TAMANIOFILA * y + 0.0,
                TAMANIOCOLUMNA * x + 250.0, TAMANIOFILA * y + 35.0,
                TAMANIOCOLUMNA * x + 250.0, TAMANIOFILA * y + 120.0,
                TAMANIOCOLUMNA * x + 175.0, TAMANIOFILA * y + 155.0,
                TAMANIOCOLUMNA * x + 100.0, TAMANIOFILA * y + 120.0,
        });
        } else {
            hexagono.getPoints().addAll(new Double[]{
                    TAMANIOCOLUMNA * x + 100.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 35.0,
                    TAMANIOCOLUMNA * x + 175.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 0.0,
                    TAMANIOCOLUMNA * x + 250.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 35.0,
                    TAMANIOCOLUMNA * x + 250.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 120.0,
                    TAMANIOCOLUMNA * x + 175.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 155.0,
                    TAMANIOCOLUMNA * x + 100.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 120.0,
            });
        }
        return hexagono;
    }

    private Rectangle crearRectangulo(int x, int y){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(45.0f);
        rectangle.setHeight(45.0f);

        if (y % 2 == 0){
            rectangle.setX(TAMANIOCOLUMNA * x + 152);
        } else {
            rectangle.setX(TAMANIOCOLUMNA * x + 152 - DESPLAZAMIENTOMITAD);
        }

        rectangle.setY(TAMANIOFILA * y + 70);

        rectangle.setArcWidth(30.0);
        rectangle.setArcHeight(20.0);

        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);

        return rectangle;
    }

    private void coordenarHexagonos(){
        int x, y;
        int[] posicionMatriz;
        for (int i = 0; i < CANTIDADHEXAGONOS; i++) {
            posicionMatriz = obtenerColumnaFila(i, desplazamientoX, desplazamientoY);
            x = posicionMatriz[COLUMNA];
            y = posicionMatriz[FILA];

            Polygon hexagonoNuevo = crearHexagono(x, y);
            hexagonos.add(hexagonoNuevo);
        }
    }

    public int[] obtenerColumnaFila(int hexagono , int desplazarmientoX, int desplazarmientoY) {
        int x,y = 0;
        int x1 = desplazarmientoX;
        int y1 = desplazarmientoY;
        if (hexagono < 3) {
            x = x1 + 2 + hexagono;
            y = y1;
        } else if (hexagono < 7) {
            x = x1 + 1 + hexagono - 2;
            y = y1 + 1;
        } else if (hexagono < 12) {
            x = x1 + hexagono - 6;
            y = y1 + 2;
        } else if (hexagono < 16) {
            x = x1 + 1 + hexagono - 11;
            y = y1 + 3;
        } else {
            x = x1 + 2 + hexagono - 16;
            y = y1 + 4;
        }

        int[] posicionMatriz = {x,y};

        return posicionMatriz;
    }


    public void ponerPastizal(){
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.VERDECLARO]);
        Image img = new Image("oveja.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }

    public void ponerDesierto(){
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.PARAMO]);

        Image img = new Image("cactus.png");
        ImageView view = new ImageView(img);

        view.setFitHeight(50);
        view.setFitWidth(50);

        int[] posicionMatriz = obtenerColumnaFila(indice, desplazamientoX, desplazamientoY);
        int x =  posicionMatriz[COLUMNA];
        int y =  posicionMatriz[FILA];

        view.setX(x * TAMANIOCOLUMNA + 100);
        view.setY(y * TAMANIOFILA + 55);

        Group desiertoSinFicha = new Group(hexagono, view);
        hexagonosConFicha.add(desiertoSinFicha);
        indice++;
    }

    public void ponerBosque(){
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.VERDEOSCURO]);

        Image img = new Image("pino.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }


    public void ponerMotania(){
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.GRIS]);

        Image img = new Image("piedra.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }

    public void ponerCampo(){
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.AMARILLOCAMPO]);

        Image img = new Image("maiz-de-poste.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }

    public void ponerColina(){
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.TERRACOTA]);

        Image img = new Image("pared-de-ladrillo.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }

    public void setControlador(ControladorTablero controlador) {
        this.controlador = controlador;
    }

    public void ponerFicha2(int numeroHexagono){
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("2");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text textoPosibilidad = new Text(".");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if(y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 172);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 172 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void ponerFicha3(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("3");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text textoPosibilidad = new Text("..");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if(y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 169);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void ponerFicha4(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("4");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text textoPosibilidad = new Text("...");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if(y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 167);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 167 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void ponerFicha5(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("5");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text textoPosibilidad = new Text("....");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if(y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 163);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 163 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);
        textoNumero.setFill(Color.BLACK);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void ponerFicha6(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("6");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoNumero.setFill(Color.RED);

        Text textoPosibilidad = new Text(".....");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoPosibilidad.setFill(Color.RED);

        if(y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 161);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 161 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void ponerFicha8(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("8");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoNumero.setFill(Color.RED);

        Text textoPosibilidad = new Text(".....");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoPosibilidad.setFill(Color.RED);

        if(y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 161);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 161 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void ponerFicha9(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("9");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text textoPosibilidad = new Text("....");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if(y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 163);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 163 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void  ponerFicha10(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("10");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text textoPosibilidad = new Text("...");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if(y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 163);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 167);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 163 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 167 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void  ponerFicha11(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("11");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text textoPosibilidad = new Text("..");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if (y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 163);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 169);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 163 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 169 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    public void  ponerFicha12(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono,desplazamientoX,desplazamientoY);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("12");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text textoPosibilidad = new Text(".");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if (y % 2 == 0) {
            textoNumero.setX(TAMANIOCOLUMNA * x + 163);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 172);
        } else {
            textoNumero.setX(TAMANIOCOLUMNA * x + 163 - DESPLAZAMIENTOMITAD);
            textoPosibilidad.setX(TAMANIOCOLUMNA * x + 172 - DESPLAZAMIENTOMITAD);
        }

        textoNumero.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTONUMEROY);
        textoPosibilidad.setY(TAMANIOFILA * y + DESPLAZAMIENTOTEXTOPROBABILIDADY);

        hexagonosConFicha.get(hexagonosConFicha.size() - 1).getChildren().addAll(textoNumero, textoPosibilidad);
    }

    private void agruparHexagonoConFicha(Polygon hexagono, ImageView img) {
        int[] coordenadas = obtenerColumnaFila(indice, desplazamientoX, desplazamientoY);
        int x = coordenadas[COLUMNA];
        int y = coordenadas[FILA];
        Rectangle rectangulo = crearRectangulo(x, y);

        img.setFitWidth(37);
        img.setFitHeight(37);
        colocarImagen(img, x, y);

        Group terrenoConFicha = new Group(hexagono,rectangulo, img);
        hexagonosConFicha.add(terrenoConFicha);

        indice++;
    }

    private void colocarImagen(ImageView img, double x, double y) {
        if (y % 2 == 0) {
            img.setX(TAMANIOCOLUMNA * x + 157);
        } else {
            img.setX(TAMANIOCOLUMNA * x + 157 - DESPLAZAMIENTOMITAD);
        }
        img.setY(TAMANIOFILA * y + 23);
    }

    public void crearVista() {
        controlador.colocarTerrenos();
        for (Group group : hexagonosConFicha) {
            tablero.getChildren().add(group);
        }
    }

}
