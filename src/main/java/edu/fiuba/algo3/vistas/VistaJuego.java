package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.controllers.*;
import edu.fiuba.algo3.observador.Observador;
import edu.fiuba.algo3.observador.Observable;
import edu.fiuba.algo3.utilidades.ReproductorMusica;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class VistaJuego extends BorderPane implements Observador {

    private final JuegoObservable modelo;
    private final VistaDados vistaDados;
    private final Label estadoLabel;
    private final Label jugadorInferiorLabel;
    private VistaRecursos vistaRecursos;
    private VistaPropuesta vistaPropuesta;
    private VistaPieza vistaPieza;

    private VistaTablero vistaTablero;
    private List<VistaVerticeEdificio> vertices;

    public VistaJuego(JuegoObservable modelo) {
        this.modelo = modelo;
        vertices = new ArrayList<>();
        this.modelo.agregarObservador(this);
        this.vistaPropuesta = new VistaPropuesta(modelo);
        
        StackPane panelCentral = new StackPane();
        panelCentral.setAlignment(Pos.CENTER);
        panelCentral.setPickOnBounds(false);

        // dados
        vistaDados = new VistaDados();
        HBox contenedorDados = new HBox(vistaDados);
        contenedorDados.setPadding(new Insets(50, 100, 0, 50));
        contenedorDados.setAlignment(Pos.TOP_LEFT);
        setCenter(contenedorDados);

        //Estado 
        estadoLabel = new Label("Bienvenido a Catán");
        estadoLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        BorderPane.setMargin(estadoLabel, new Insets(10));

        //Reproductor
        ReproductorMusica reproductor = new ReproductorMusica(
                List.of(
                        getClass().getResource("/audio/audio1.wav").toExternalForm(),
                        getClass().getResource("/audio/audio2.wav").toExternalForm(),
                        getClass().getResource("/audio/audio3.wav").toExternalForm()
                )
        );

        //Barra superior
        VistaBarraSuperior barraSuperior = new VistaBarraSuperior(estadoLabel, reproductor);


        VBox zonaSuperior = new VBox();
        zonaSuperior.setSpacing(10);
        zonaSuperior.setPadding(new Insets(10));
        zonaSuperior.getChildren().add(barraSuperior);
        zonaSuperior.getChildren().add(estadoLabel);

        setTop(zonaSuperior);

        // botones
        Button verCartasBtn = new BotonAccion("Ver Cartas", new HandlerVerCartas(modelo));
        Button intercambiarBtn = new BotonAccion("Intercambiar", new HandlerIntercambio(modelo));
        Button pasarTurnoBtn = new Button();
        pasarTurnoBtn.setOnAction(new HandlerPasarTurno(modelo));

        //boton dado
        Button tirarDadoBtn = new BotonAccion("Tirar", new HandlerTirarDados(modelo));
        tirarDadoBtn.setContentDisplay(ContentDisplay.BOTTOM);

        Image iconoDado = new Image("cubo-de-dados.png");
        ImageView vistaDado = new ImageView(iconoDado);

        vistaDado.setFitWidth(35);
        vistaDado.setFitHeight(35);
        vistaDado.setPreserveRatio(true);
        tirarDadoBtn.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 7 15 7 15; -fx-border-color: gray;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;"
        );
        tirarDadoBtn.setGraphic(vistaDado);
        tirarDadoBtn.setCursor(Cursor.HAND);



        //pasar turno
        ImageView viewTurno = new ImageView(new Image("pasar_turno.png"));
        viewTurno.setFitHeight(50);
        viewTurno.setPreserveRatio(true);
        pasarTurnoBtn.setGraphic(viewTurno);
        pasarTurnoBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
      
        //Banco
        Button bankBtn = new Button();
        ImageView viewBanco = new ImageView();
        viewBanco.setImage(new Image("banco.png")); 
        viewBanco.setFitHeight(60); 
        viewBanco.setPreserveRatio(true);
        bankBtn.setGraphic(viewBanco);
        bankBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        bankBtn.setOnAction(new HandlerBanco(modelo));
        

        //jugador
        Image img = new Image("jugador.png");
        ImageView avatarJugador = new ImageView(img);
        avatarJugador.setFitHeight(40);
        avatarJugador.setFitWidth(40);
        jugadorInferiorLabel = new Label(modelo.juego().jugadorActual().nombre()); 
        jugadorInferiorLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        VBox panelJugador = new VBox(2, avatarJugador, jugadorInferiorLabel);
        panelJugador.setAlignment(Pos.CENTER);

        // organizar Turno y vista de Jugador
        HBox grupoFinDeTurno = new HBox(5, pasarTurnoBtn, panelJugador);
        grupoFinDeTurno.setAlignment(Pos.CENTER);

        // Recursos en barra
        this.vistaRecursos = new VistaRecursos();
        HBox contenedorRecursos = new HBox(vistaRecursos);
        contenedorRecursos.setAlignment(Pos.CENTER_LEFT);

        // Botones de Pieza
        this.vistaPieza = new VistaPieza();

        Button botonCancelar = vistaPieza.obtenerBotonCancelar();

        // organizador barra derecha
        HBox controlesDerecha = new HBox(15, bankBtn, intercambiarBtn, verCartasBtn,vistaPieza,tirarDadoBtn, grupoFinDeTurno);
        controlesDerecha.setAlignment(Pos.CENTER_RIGHT);

        //barra
        HBox barraSup = new HBox(botonCancelar);
        HBox barraInf = new HBox(contenedorRecursos, controlesDerecha);
        VBox barraPadre = new VBox(barraSup, barraInf);
        HBox.setHgrow(contenedorRecursos, Priority.ALWAYS);
        HBox.setHgrow(controlesDerecha, Priority.ALWAYS);

        barraSup.setPadding(new Insets(10, 20, 10, 20));
        barraInf.setPadding(new Insets(10, 20, 10, 20));
        barraInf.setStyle("-fx-background-color: #3b2145;");

        barraSup.setAlignment(Pos.CENTER_RIGHT);
        setBottom(barraPadre);

        StackPane.setAlignment(vistaPropuesta, Pos.TOP_RIGHT);
        StackPane.setMargin(vistaPropuesta, new Insets(60, 20, 0, 0));
        panelCentral.getChildren().addAll(contenedorDados, vistaPropuesta);
        setCenter(panelCentral);

        actualizarVistaDados();
    }
    private void mostrarEstado(String mensaje) {
        estadoLabel.setText(mensaje);
    }

    @Override
    public void actualizar(Observable observado, Object evento) {
        if (evento instanceof String) {
            String msg = (String) evento;
            if (msg.equals("DADOS")) {
            	actualizarVistaDados();
            }
            if (msg.equals("TURNO")) {
            	actualizarNombreJugador();
                vistaRecursos.actualizarRecursos(modelo.juego().recursosJugadorActual());
                vistaPropuesta.actualizarPropuesta();
                vistaPropuesta.toFront();
            }
            if (msg.equals("NUEVA_PROPUESTA") || msg.equals("PROPUESTA_CERRADA")) {
            	vistaPropuesta.actualizarPropuesta();
                vistaPropuesta.toFront();
                
            }
            if(msg.equals("RECURSOS")){
                vistaRecursos.actualizarRecursos(modelo.juego().recursosJugadorActual());
            }
        }
    }

    private void actualizarNombreJugador() {
        String nombre = modelo.getNombreJugadorActual();
        jugadorInferiorLabel.setText(nombre);
        mostrarEstado("Turno de: " + nombre);
    }
    
    private void actualizarVistaDados() {
        int d1 = modelo.getDado1();
        int d2 = modelo.getDado2();
        int suma = modelo.getSuma();
        vistaDados.actualizar(d1, d2);
        mostrarEstado("Tirada: " + d1 + " + " + d2 + " = " + suma);
    }

    public void setTablero(VistaTablero vistaTablero) {
        this.vistaTablero = vistaTablero;
        vertices = vistaTablero.getVertices();
        List<VistaArista> arista = vistaTablero.getArista();
        vistaPieza.setVerticesArista(vertices, arista);
        vistaPieza.darComportamiento();
    }
}