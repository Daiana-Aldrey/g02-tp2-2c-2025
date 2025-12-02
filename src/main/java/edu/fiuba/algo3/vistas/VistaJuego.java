package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.controlador.ControladorJuego;
import edu.fiuba.algo3.observador.Observador;
import edu.fiuba.algo3.observador.Observable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;


public class VistaJuego extends BorderPane implements Observador {

    private final JuegoObservable modelo;
    private final ControladorJuego controlador;
    private final VistaDados vistaDados;
    private VistaRecursos vistaRecursos;
    private final Label estadoLabel;
    private final Button tirarDadoBtn;
    private final Button verRecursosBtn;
    private final Button verCartasBtn;
    private final Button intercambiarBtn;
    private final Button pasarTurnoBtn;
    private final Label jugadorInferiorLabel;

    public VistaJuego(JuegoObservable modelo) {
        this.modelo = modelo;
        this.controlador = new ControladorJuego(modelo, this);
        this.setStyle("-fx-background-color: #87cfe8;");
        
        modelo.agregarObservador(this);

        // dados
        vistaDados = new VistaDados();
        HBox contenedorDados = new HBox(vistaDados);
        contenedorDados.setSpacing(20);
        contenedorDados.setAlignment(Pos.TOP_LEFT);
        contenedorDados.setPadding(new Insets(50, 0, 0, 50));
        setCenter(contenedorDados);

       //mensajde de estado
        estadoLabel = new Label("Bienvenido a Catán");
        estadoLabel.setStyle("-fx-font-size: 14px;");
        BorderPane.setMargin(estadoLabel, new Insets(10));
        setTop(estadoLabel);

        // botones en barra
        verRecursosBtn = crearBotonMorado("Ver Recursos");
        verCartasBtn   = crearBotonMorado("Ver Cartas De Desarrollo");
        intercambiarBtn= crearBotonMorado("Intercambiar");

        Button bankBtn = new Button("BANK");
        bankBtn.setStyle(
                "-fx-background-color: #f0f0f0;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10 18 10 18;" +
                "-fx-background-radius: 20;" +
                "-fx-font-weight: bold;"
        );

        tirarDadoBtn = new Button("Tirar dados");
        tirarDadoBtn.setStyle(
                "-fx-background-color: #e0e0e0;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 8 15 8 15;" +
                "-fx-background-radius: 10;"
        );

        pasarTurnoBtn = new Button("⏭ Pasar turno");
        pasarTurnoBtn.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 8 15 8 15;" +
                "-fx-background-radius: 10;" +
                "-fx-font-weight: bold;"
        );

        
        //jugador en barra
        Circle avatarJugador = new Circle(20);
        avatarJugador.setFill(Color.BLACK);
        avatarJugador.setStroke(Color.WHITE);
        avatarJugador.setStrokeWidth(2);
        jugadorInferiorLabel = new Label("Jugador 1");
        jugadorInferiorLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;");
        VBox panelJugador = new VBox(2); // 2px de espacio entre circulo y nombre
        panelJugador.getChildren().addAll(avatarJugador, jugadorInferiorLabel);
        panelJugador.setAlignment(Pos.CENTER);

        //acomodacion
        HBox izquierda = new HBox(15, verRecursosBtn, verCartasBtn, intercambiarBtn);
        izquierda.setAlignment(Pos.CENTER_LEFT);

        HBox derecha = new HBox(15, tirarDadoBtn, pasarTurnoBtn, panelJugador);
        derecha.setAlignment(Pos.CENTER_RIGHT);

        Region spacerIzq = new Region();
        Region spacerDer = new Region();
        HBox.setHgrow(spacerIzq, Priority.ALWAYS);
        HBox.setHgrow(spacerDer, Priority.ALWAYS);

        HBox barra = new HBox(20,
                izquierda,
                spacerIzq,
                bankBtn,
                spacerDer,
                derecha
        );
        barra.setPadding(new Insets(10, 20, 10, 20));
        barra.setAlignment(Pos.CENTER);
        barra.setStyle("-fx-background-color: #3b2145;"); 

        setBottom(barra);

        //Vista de Recursos del jugador
        HBox listaRecursos = new HBox(20,
                izquierda,
                spacerIzq,
                bankBtn,
                spacerDer,
                derecha
        );
        listaRecursos.setPadding(new Insets(10, 20, 10, 20));
        listaRecursos.setAlignment(Pos.CENTER);
        listaRecursos.setStyle("-fx-background-color: #3b2145;");

        this.vistaRecursos = new VistaRecursos(modelo.juego().jugadorActual());

        HBox contenedorRecursos = new HBox(vistaRecursos);
        contenedorRecursos.setPadding(new Insets(5, 20, 5, 20));
        contenedorRecursos.setAlignment(Pos.CENTER_RIGHT);

        VBox zonaInferior = new VBox(8, contenedorRecursos, listaRecursos);
        zonaInferior.setAlignment(Pos.CENTER_RIGHT);

        setBottom(zonaInferior);

        // controladores a implementar
        tirarDadoBtn.setOnAction(e -> controlador.manejarTirarDados());
        verRecursosBtn.setOnAction(e -> mostrarEstado("Ver Recursos (todavía sin implementar)"));
        verCartasBtn.setOnAction(e -> mostrarEstado("Ver Cartas de Desarrollo (todavía sin implementar)"));
        intercambiarBtn.setOnAction(e -> mostrarEstado("Intercambiar (todavía sin implementar)"));
        pasarTurnoBtn.setOnAction(e -> mostrarEstado("Pasar turno (todavía sin implementar)"));
    }

    private Button crearBotonMorado(String texto) {
        Button b = new Button(texto);
        b.setStyle(
                "-fx-background-color: #4b1f4f;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 8 18 8 18;" +
                "-fx-background-radius: 15;" +
                "-fx-font-weight: bold;"
        );
        return b;
    }

    public void mostrarEstado(String mensaje) {
        estadoLabel.setText(mensaje);
    }

    @Override
    public void actualizar(Observable observado, Object evento) {
        if (evento instanceof String) {
            String msg = (String) evento;

            if (msg.startsWith("TIRADA:")) {
                String numero = msg.substring("TIRADA:".length());
                mostrarEstado("Se tiró un " + numero);
                String nombreJugadorActual = modelo.juego().jugadorActual().nombre();
                jugadorInferiorLabel.setText(nombreJugadorActual);
            }
        }
    }

    public void actualizarDados(int d1, int d2) {
        vistaDados.actualizar(d1, d2);
    }
}

