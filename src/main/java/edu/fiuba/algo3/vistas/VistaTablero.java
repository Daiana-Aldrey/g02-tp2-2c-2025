package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorTablero;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
    private static final int TAMANIOARISTA = 76;

    private static final int COLUMNA = 0;
    private static final int FILA = 1;
    private static final int DESPLAZAMIENTOMITAD = 76;
    private static final int DESPLAZAMIENTOTEXTONUMEROY = 114;
    private static final int DESPLAZAMIENTOTEXTOPROBABILIDADY = 124;
    private static final int[] VERTICEPRIMEROFILA = {8,17,28,39,48,55};
    private static final int[] VERTICEULTIMOFILA = {7,16,27,38,47,54};

    private static final int PRIMERAFILA = 0;
    private static final int SEGUNDAFILA = 1;
    private static final int TERCERAFILA = 2;
    private static final int CUARTAFILA = 3;
    private static final int QUINTAFILA = 4;
    private static final int SEXTAFILA = 5;

    private ControladorTablero controlador;
    private int indice;
    List<Polygon> hexagonos;
    List<Rectangle> rectangulos;
    List<Group> hexagonosConFicha;
    List<VistaVerticeEdificio> vertices;
    List<VistaArista> aristas;
    Group tablero;

    public VistaTablero() {
        indice = 0;
        hexagonos = new ArrayList<>();
        rectangulos = new ArrayList<>();
        hexagonosConFicha = new ArrayList<>();
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();

        tablero = new Group();

        coordenarHexagonos();
    }

    public Group getVistaTablero() {
        return tablero;
    }

    private Polygon crearHexagono(int x, int y) {
        Polygon hexagono = new Polygon();
        hexagono.setStroke(ConstanteColores.coloresTablero[ConstanteColores.ARENA]);
        hexagono.setStrokeWidth(12);

        if (y % 2 == 0){
        hexagono.getPoints().addAll(new Double[]{
                TAMANIOCOLUMNA * x + 100.0, TAMANIOFILA * y + 55.0,
                TAMANIOCOLUMNA * x + 175.0, TAMANIOFILA * y + 20.0,
                TAMANIOCOLUMNA * x + 250.0, TAMANIOFILA * y + 55.0,
                TAMANIOCOLUMNA * x + 250.0, TAMANIOFILA * y + 140.0,
                TAMANIOCOLUMNA * x + 175.0, TAMANIOFILA * y + 175.0,
                TAMANIOCOLUMNA * x + 100.0, TAMANIOFILA * y + 140.0,
        });
        } else {
            hexagono.getPoints().addAll(new Double[]{
                    TAMANIOCOLUMNA * x + 100.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 55.0,
                    TAMANIOCOLUMNA * x + 175.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 20.0,
                    TAMANIOCOLUMNA * x + 250.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 55.0,
                    TAMANIOCOLUMNA * x + 250.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 140.0,
                    TAMANIOCOLUMNA * x + 175.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 175.0,
                    TAMANIOCOLUMNA * x + 100.0 - DESPLAZAMIENTOMITAD, TAMANIOFILA * y + 140.0,
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

        rectangle.setY(TAMANIOFILA * y + 90);

        rectangle.setArcWidth(30.0);
        rectangle.setArcHeight(20.0);

        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);

        return rectangle;
    }

    private void coordenarHexagonos() {
        int x, y;
        int[] posicionMatriz;
        for (int i = 0; i < CANTIDADHEXAGONOS; i++) {
            posicionMatriz = obtenerColumnaFila(i);
            x = posicionMatriz[COLUMNA];
            y = posicionMatriz[FILA];

            Polygon hexagonoNuevo = crearHexagono(x, y);
            hexagonos.add(hexagonoNuevo);
        }
    }

    public int[] obtenerColumnaFila(int hexagono) {
        int x, y = 0;

        if (hexagono < 3) {
            x = 2 + hexagono;
            y = PRIMERAFILA;
        } else if (hexagono < 7) {
            x = hexagono - 1;
            y = SEGUNDAFILA;
        } else if (hexagono < 12) {
            x =  hexagono - 6;
            y =  TERCERAFILA;
        } else if (hexagono < 16) {
            x =  hexagono - 10;
            y =  CUARTAFILA;
        } else {
            x = hexagono - 14;
            y = QUINTAFILA;
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

        int[] posicionMatriz = obtenerColumnaFila(indice);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        view.setX(x * TAMANIOCOLUMNA + 100);
        view.setY(y * TAMANIOFILA + 75);

        Group desiertoSinFicha = new Group(hexagono, view);
        hexagonosConFicha.add(desiertoSinFicha);
        indice++;
    }

    public void ponerBosque() {
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.VERDEOSCURO]);

        Image img = new Image("pino.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }


    public void ponerMotania() {
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.GRIS]);

        Image img = new Image("piedra.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }

    public void ponerCampo() {
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.AMARILLOCAMPO]);

        Image img = new Image("maiz-de-poste.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }

    public void ponerColina() {
        Polygon hexagono = hexagonos.get(indice);
        hexagono.setFill(ConstanteColores.coloresTablero[ConstanteColores.TERRACOTA]);

        Image img = new Image("pared-de-ladrillo.png");
        ImageView view = new ImageView(img);

        agruparHexagonoConFicha(hexagono, view);
    }

    public void setControlador(ControladorTablero controlador) {
        this.controlador = controlador;
    }

    public void ponerFicha2(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("2");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text textoPosibilidad = new Text(".");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if (y % 2 == 0) {
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
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("3");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text textoPosibilidad = new Text("..");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if (y % 2 == 0) {
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
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("4");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text textoPosibilidad = new Text("...");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if (y % 2 == 0) {
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
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("5");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text textoPosibilidad = new Text("....");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if (y % 2 == 0) {
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
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("6");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoNumero.setFill(Color.RED);

        Text textoPosibilidad = new Text(".....");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoPosibilidad.setFill(Color.RED);

        if (y % 2 == 0) {
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
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("8");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoNumero.setFill(Color.RED);

        Text textoPosibilidad = new Text(".....");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        textoPosibilidad.setFill(Color.RED);

        if (y % 2 == 0) {
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
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("9");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text textoPosibilidad = new Text("....");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if (y % 2 == 0) {
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

    public void ponerFicha10(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        Text textoNumero = new Text("10");
        textoNumero.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text textoPosibilidad = new Text("...");
        textoPosibilidad.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        if (y % 2 == 0) {
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

    public void ponerFicha11(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
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

    public void ponerFicha12(int numeroHexagono) {
        int[] posicionMatriz = obtenerColumnaFila(numeroHexagono);
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
        int[] coordenadas = obtenerColumnaFila(indice);
        int x = coordenadas[COLUMNA];
        int y = coordenadas[FILA];
        Rectangle rectangulo = crearRectangulo(x, y);

        img.setFitWidth(37);
        img.setFitHeight(37);
        colocarImagen(img, x, y);

        Group terrenoConFicha = new Group(hexagono, rectangulo, img);
        hexagonosConFicha.add(terrenoConFicha);

        indice++;
    }

    private void colocarImagen(ImageView img, double x, double y) {
        if (y % 2 == 0) {
            img.setX(TAMANIOCOLUMNA * x + 157);
        } else {
            img.setX(TAMANIOCOLUMNA * x + 157 - DESPLAZAMIENTOMITAD);
        }
        img.setY(TAMANIOFILA * y + 43);
    }

    public void crearVertice(VistaVerticeEdificio vistaVertice, Ubicacion ubicacion) {
        int vertice = ubicacion.getUbicacionInt();

        int[] posicionMatriz = obtenerFilaColumnaVertice(vertice);
        int x = posicionMatriz[COLUMNA];
        int y = posicionMatriz[FILA];

        if (y == PRIMERAFILA || y == TERCERAFILA || y == QUINTAFILA) {
            if (x % 2 == 0) {
                vistaVertice.setTranslateX(TAMANIOARISTA * x + 234);
                vistaVertice.setTranslateY(TAMANIOFILA * y + 27);
            } else {
                vistaVertice.setTranslateX(TAMANIOARISTA * x + 234);
                vistaVertice.setTranslateY(TAMANIOFILA * y - 2);
            }
        } if (y == SEGUNDAFILA || y == CUARTAFILA  || y == SEXTAFILA) {
            if (x % 2 == 0) {
                vistaVertice.setTranslateX(TAMANIOARISTA * x + 234);
                vistaVertice.setTranslateY(TAMANIOFILA * y - 2);
            } else {
                vistaVertice.setTranslateX(TAMANIOARISTA * x + 234);
                vistaVertice.setTranslateY(TAMANIOFILA * y + 27);
            }
        }


        vertices.add(vistaVertice);
    }

    public void crearArista(VistaArista vistaArista, Ubicacion ubicacion, Ubicacion ubicacion2) {
        int vertice1 = ubicacion.getUbicacionInt();
        int vertice2 = ubicacion2.getUbicacionInt();

        VistaVerticeEdificio vistaVertice1 = vertices.get(vertice1 - 1);
        VistaVerticeEdificio vistaVertice2 = vertices.get(vertice2 - 1);

        vistaVertice1.agregarAdyacente(vistaVertice2);
        vistaVertice2.agregarAdyacente(vistaVertice1);

        double coordenadaX1 = vistaVertice1.getTranslateX();
        double coordenadaY1 = vistaVertice1.getTranslateY();

        double coordenadaX2 = vistaVertice2.getTranslateX();
        double coordenadaY2 = vistaVertice2.getTranslateY();

        double coordenadaAristaX = (coordenadaX2 + coordenadaX1) / 2;
        double coordenadaAristaY = (coordenadaY2 + coordenadaY1) / 2;

        vistaArista.setTranslateX(coordenadaAristaX);
        vistaArista.setTranslateY(coordenadaAristaY);

        aristas.add(vistaArista);
    }

    private int[] obtenerFilaColumnaVertice(int vertice) {
        int x = 0;
        int y = 0;
        if (vertice < VERTICEPRIMEROFILA[PRIMERAFILA]) {
            x = 1 + vertice;
            y = PRIMERAFILA;
        } else if (vertice < VERTICEPRIMEROFILA[SEGUNDAFILA]) {
            x = vertice - 7;
            y = SEGUNDAFILA;
        } else if (vertice < VERTICEPRIMEROFILA[TERCERAFILA]) {
            x = vertice - 17;
            y = TERCERAFILA;
        } else if (vertice < VERTICEPRIMEROFILA[CUARTAFILA]) {
            x = vertice - 28;
            y = CUARTAFILA;
        } else if (vertice < VERTICEPRIMEROFILA[QUINTAFILA]){
            x = vertice - 38;
            y = QUINTAFILA;
        } else if (vertice < VERTICEPRIMEROFILA[SEXTAFILA]) {
            x = vertice - 46;
            y = SEXTAFILA;
        }

        int[] posicionMatriz = {x,y};

        return posicionMatriz;
    }

 

	public void dibujarPuerto(VistaPuerto vistaPuerto, int indiceVertice1, int indiceVertice2) {
	     VistaVerticeEdificio v1 = vertices.get(indiceVertice1); 
	     VistaVerticeEdificio v2 = vertices.get(indiceVertice2);

	     double xMedio = (v1.getTranslateX() + v2.getTranslateX()) / 2;
	     double yMedio = (v1.getTranslateY() + v2.getTranslateY()) / 2;
	
	     vistaPuerto.setTranslateX(xMedio);
	     vistaPuerto.setTranslateY(yMedio);

	     tablero.getChildren().add(vistaPuerto);
	 } 
	
    
    
	
    public void crearVista() {
        controlador.colocarTerrenos();
        controlador.colocarVertices();
        controlador.colocarAristas();
        for (Group group : hexagonosConFicha) {
            tablero.getChildren().add(group);
        }
        for (VistaVerticeEdificio vertice: vertices) {
            tablero.getChildren().add(vertice);
        }
        for (VistaArista arista: aristas) {
            tablero.getChildren().add(arista);
        }
        
        controlador.colocarPuertos();
    }
}
