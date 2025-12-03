package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.controllers.HandlerTirarDados;
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
    private final VistaDados vistaDados;
    private final Label estadoLabel;
    private final Label jugadorInferiorLabel;
    private VistaRecursos vistaRecursos;

    public VistaJuego(JuegoObservable modelo) {
        this.modelo = modelo;
        this.modelo.agregarObservador(this);

        this.setStyle("-fx-background-color: #87cfe8;");

        // dados
        vistaDados = new VistaDados();
        HBox contenedorDados = new HBox(vistaDados);
        contenedorDados.setPadding(new Insets(50, 0, 0, 50));
        contenedorDados.setAlignment(Pos.TOP_LEFT);
        setCenter(contenedorDados);

        //Estado 
        estadoLabel = new Label("Bienvenido a Catán");
        estadoLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        BorderPane.setMargin(estadoLabel, new Insets(10));
        setTop(estadoLabel);

 
        
        // botones
        Button verRecursosBtn = new BotonAccion("Ver Recursos", e -> mostrarEstado("Sin implementar"));
        Button verCartasBtn = new BotonAccion("Ver Cartas", e -> mostrarEstado("Sin implementar"));
        Button intercambiarBtn = new BotonAccion("Intercambiar", e -> mostrarEstado("Sin implementar"));
        
        Button bankBtn = new Button("BANK");
        bankBtn.setStyle("-fx-background-color: #f0f0f0; -fx-font-weight: bold; -fx-background-radius: 20;");

        Button tirarDadoBtn = new BotonAccion("Tirar dados", new HandlerTirarDados(modelo));
        Button pasarTurnoBtn = new BotonAccion("⏭ Pasar turno", e -> mostrarEstado("Sin implementar"));

        //jugador
        Circle avatarJugador = new Circle(20, Color.BLACK);
        avatarJugador.setStroke(Color.WHITE);
        jugadorInferiorLabel = new Label(modelo.juego().jugadorActual().nombre()); 
        jugadorInferiorLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        VBox panelJugador = new VBox(2, avatarJugador, jugadorInferiorLabel);
        panelJugador.setAlignment(Pos.CENTER);

        //botones
        HBox izquierda = new HBox(15, verRecursosBtn, verCartasBtn, intercambiarBtn);
        izquierda.setAlignment(Pos.CENTER_LEFT);

        HBox derecha = new HBox(15, tirarDadoBtn, pasarTurnoBtn, panelJugador);
        derecha.setAlignment(Pos.CENTER_RIGHT);

        Region spacerIzq = new Region();
        Region spacerDer = new Region();
        HBox.setHgrow(spacerIzq, Priority.ALWAYS);
        HBox.setHgrow(spacerDer, Priority.ALWAYS);

        // barra
        HBox barra = new HBox(20, izquierda, spacerIzq, bankBtn, spacerDer, derecha);
        barra.setPadding(new Insets(10, 20, 10, 20));
        barra.setAlignment(Pos.CENTER);
        barra.setStyle("-fx-background-color: #3b2145;");

      //recursos
        this.vistaRecursos = new VistaRecursos(modelo.juego().jugadorActual());
        HBox contenedorRecursos = new HBox(vistaRecursos);
        contenedorRecursos.setPadding(new Insets(5, 20, 5, 20)); 
        contenedorRecursos.setAlignment(Pos.CENTER_RIGHT); 
        VBox zonaInferior = new VBox(0, contenedorRecursos, barra);
        zonaInferior.setAlignment(Pos.BOTTOM_CENTER);

        setBottom(zonaInferior);
        actualizarVistaDados();
    }

    private void mostrarEstado(String mensaje) {
        estadoLabel.setText(mensaje);
    }

    @Override
    public void actualizar(Observable observado, Object evento) {
        if (evento instanceof String && evento.equals("DADOS")) {
            actualizarVistaDados();
        }
    }

    private void actualizarVistaDados() {
        int d1 = modelo.getDado1();
        int d2 = modelo.getDado2();
        int suma = modelo.getSuma();
        vistaDados.actualizar(d1, d2);
        mostrarEstado("Tirada: " + d1 + " + " + d2 + " = " + suma);
    }
}