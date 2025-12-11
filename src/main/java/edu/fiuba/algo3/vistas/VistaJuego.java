package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.controllers.*;
import edu.fiuba.algo3.modelo.Jugador;
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
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VistaJuego extends BorderPane implements Observador {
    private final JuegoObservable modelo;
    private final VistaDados vistaDados;
    private final Label estadoLabel;
    private final Label nombreInferiorLabel;
    private final VBox iconoJugador;
    private final Button tirarDadoBtn;
    private final Button pasarTurnoBtn;
    private final Button botonCiudad;
    private final Button botonCamino;
    private final Button botonPoblado;
    private final Button botonCancelar;

    private VistaRecursos vistaRecursos;
    private VistaPropuesta vistaPropuesta;
    private VistaPieza vistaPieza;
    private VistaPuntaje vistaPuntaje;

    private VistaTablero vistaTablero;
    private List<VistaVerticeEdificio> vertices;
    private List<VistaLadron> ladrones;
    private boolean primerLadron;

    private final List<Stage> ventanasAbiertas = new ArrayList<>();

    public VistaJuego(JuegoObservable modelo) {
        this.modelo = modelo;
        vertices = new ArrayList<>();
        this.modelo.agregarObservador(this);
        this.vistaPropuesta = new VistaPropuesta(modelo);
        this.vistaPuntaje = new VistaPuntaje(modelo.juego().jugadores());
        this.setRight(vistaPuntaje);
        primerLadron = false;

        pasarTurnoBtn = new Button();
        
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
        Button verCartasBtn = new BotonAccion("Ver Cartas", new HandlerVerCartas(modelo,this));
        Button intercambiarBtn = new BotonAccion("Intercambiar", new HandlerIntercambio(modelo,this));
        Button pasarTurnoBtn = new Button();
        pasarTurnoBtn.setOnAction(event -> {
            cerrarVentanasAbiertas();
            new HandlerPasarTurno(modelo).handle(event);
        });

        //boton dado
        tirarDadoBtn = new BotonAccion("Tirar", new HandlerTirarDados(modelo));
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
        bankBtn.setOnAction(new HandlerBanco(modelo,this));

        //jugador
        Image img = new Image("jugador.png");
        ImageView avatarJugador = new ImageView(img);

        avatarJugador.setFitHeight(40);
        avatarJugador.setFitWidth(40);

        iconoJugador = new VBox(avatarJugador);
        iconoJugador.setAlignment(Pos.CENTER);
        iconoJugador.setMaxSize(50, 50);
        iconoJugador.setMinSize(50, 50);

        nombreInferiorLabel = new Label(modelo.juego().jugadorActual().nombre());
        nombreInferiorLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        VBox panelJugador = new VBox(2, iconoJugador, nombreInferiorLabel);
        panelJugador.setAlignment(Pos.CENTER);


        // organizar Turno y vista de Jugador
        HBox grupoFinDeTurno = new HBox(5, pasarTurnoBtn, panelJugador);
        grupoFinDeTurno.setAlignment(Pos.CENTER);

        // Recursos en barra
        this.vistaRecursos = new VistaRecursos();
        HBox contenedorRecursos = new HBox(vistaRecursos);
        contenedorRecursos.setAlignment(Pos.CENTER_LEFT);

        // Botones de Pieza
        this.vistaPieza = new VistaPieza(vistaRecursos);
        botonCancelar = vistaPieza.obtenerBotonCancelar();
        botonCamino = vistaPieza.obtenerBotonCamino();
        botonPoblado = vistaPieza.obtenerBotonPoblado();
        botonCiudad = vistaPieza.obtenerBotonCiudad();

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
        StackPane.setAlignment(vistaPropuesta, Pos.BOTTOM_LEFT);
        StackPane.setMargin(vistaPropuesta, new Insets(0, 0, 20, 20));
        panelCentral.getChildren().addAll(contenedorDados, vistaPropuesta);
        setCenter(panelCentral);

        primeraRonda();
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
                invisibilizarBotonDados();
            }
            if (msg.equals("TURNO")) {
                actualizarJugador();
                vistaRecursos.actualizarRecursos(modelo.juego().recursosJugadorActual());
                vistaPropuesta.actualizarPropuesta();
                vistaPropuesta.toFront();
                
                if (modelo.esFaseInicial()) {
                    configurarInterfazFaseInicial();
                    invisibilizarBotonDados();  
                    
                    boolean pusoPoblado = modelo.yaPusoPobladoInicial();
                    boolean pusoCamino = modelo.yaPusoCaminoInicial();
                    if (pusoPoblado && pusoCamino) {
                        habilitarBotonPasarTurno();
                    } else {
                        deshabilitarBotonPasarTurno();
                    }

                } else {
                    visibilizarBotonDadosTurnoGeneral();
                    actualizarVistaPieza(modelo.juego().jugadorActual());
                }
            }
            if (msg.equals("NUEVA_PROPUESTA") || msg.equals("PROPUESTA_CERRADA")) {
            	vistaPropuesta.actualizarPropuesta();
                vistaPropuesta.toFront();
                
            }
            if(msg.equals("RECURSOS")){
                vistaRecursos.actualizarRecursos(modelo.juego().recursosJugadorActual());
            }
            if(msg.equals("PV")){
                vistaPuntaje.actualizarPV(
                        modelo.juego().jugadores(),
                        modelo.obtenerJugadorConGranCaballeria(),
                        2,
                        modelo.obtenerJugadorConRutaMayor(),
                        2
                );
            }
            if (msg.equals("CONSTRUCCION_INICIAL")) {
                configurarInterfazFaseInicial();
                vistaRecursos.actualizarRecursos(modelo.juego().recursosJugadorActual());
            }
            
            if (msg.equals("LADRON")) {
                mostrarEstado("¡Salió un 7! Mueve al Ladrón (Hace clic en un terreno)");
                for (VistaLadron ladron: ladrones) {
                    ladron.verDisponible();
                }
                if (!primerLadron) {
                    vistaTablero.sacarLadron();
                    primerLadron = true;
                }

            }
            
            if (msg.equals("LADRON_MOVIDO")) {
                actualizarJugador();
                vistaRecursos.actualizarRecursos(modelo.juego().recursosJugadorActual());
                for (VistaLadron ladron: ladrones){
                    ladron.sacarLadron();
                    ladron.sacarDisponibles();
                }
            }
        }
    }
    
    private void habilitarBotonPasarTurno() {
        pasarTurnoBtn.setDisable(false);
    }

    private void deshabilitarBotonPasarTurno() {
        pasarTurnoBtn.setDisable(true);
    }

    private void actualizarJugador() {
        String nombre = modelo.getNombreJugadorActual();
        nombreInferiorLabel.setText(nombre);
        mostrarEstado("Turno de: " + nombre);
        iconoJugador.setBackground(new Background(new BackgroundFill(modelo.juego().jugadorActual().obtenerColor(), new CornerRadii(100), Insets.EMPTY)));
    }
    
    private void actualizarVistaDados() {
        int d1 = modelo.getDado1();
        int d2 = modelo.getDado2();
        int suma = modelo.getSuma();
        vistaDados.actualizar(d1, d2);
        mostrarEstado("Tirada: " + d1 + " + " + d2 + " = " + suma);
    }

    private void visibilizarBotonDadosTurnoGeneral() {
        tirarDadoBtn.setDisable(false);
    }

    private void invisibilizarBotonDados() {
        tirarDadoBtn.setDisable(true);
    }

    public void setTablero(VistaTablero vistaTablero) {
        this.vistaTablero = vistaTablero;
        vertices = vistaTablero.getVertices();
        List<VistaArista> aristas = vistaTablero.getArista();
        vistaPieza.setVerticesArista(vertices, aristas);
        vistaPieza.setBonificador(modelo.getBonificadorRutaMayor());
        ladrones = vistaTablero.getBotonesLadron();
        for (VistaLadron boton : ladrones) {
            boton.inicializarControlador(modelo);
        }
        for (VistaArista arista: aristas) {
            arista.setModeloObservable(modelo);
        }
    }

    private void actualizarVistaPieza(Jugador jugador) {
        vistaPieza.darComportamiento(jugador);
    }

    private void primeraRonda() {
        actualizarVistaDados();
        invisibilizarBotonDados();
        actualizarVistaPieza(modelo.juego().jugadorActual());
        iconoJugador.setBackground(new Background(new BackgroundFill(modelo.juego().jugadorActual().obtenerColor(), new CornerRadii(100), Insets.EMPTY)));
        
        if (modelo.esFaseInicial()) {
        	configurarInterfazFaseInicial();
            invisibilizarBotonDados();
            habilitarBotonPasarTurno();

            if(modelo.esFaseInicial()){
                VistaInfo.mostrar("Atención", "En las primeras dos rondas cada jugdor deberá colocar un poblado y un camino, en ese orden.");
            }
        }
    }

    public void registrarVentana(Stage stage){
        ventanasAbiertas.add(stage);
    }

    private void cerrarVentanasAbiertas(){
        for(Stage ventana : ventanasAbiertas){
            ventana.close();
        }
        ventanasAbiertas.clear();
    }
    
    private void configurarInterfazFaseInicial() {
        invisibilizarBotonDados();
        boolean pusoPoblado = modelo.yaPusoPobladoInicial();
        boolean pusoCamino = modelo.yaPusoCaminoInicial();

        if (pusoPoblado && pusoCamino) {
            habilitarBotonPasarTurno();
        } else {
            deshabilitarBotonPasarTurno();
        }

        vistaPieza.darComportamientoInicial(modelo); 

        if (pusoPoblado) {
            vistaPieza.deshabilitarPoblado();
        } else {
            vistaPieza.habilitarPoblado();
        }

        if (pusoCamino) {
            vistaPieza.deshabilitarCamino();
        } else {
            vistaPieza.habilitarCamino();
        }
    }
}