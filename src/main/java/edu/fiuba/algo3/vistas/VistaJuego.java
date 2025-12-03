package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.controllers.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        Button tirarDadoBtn = new BotonAccion("Tirar dados", new HandlerTirarDados(modelo));
        Button pasarTurnoBtn = new Button(); 
        pasarTurnoBtn.setOnAction(new HandlerPasarTurno(modelo));
        
        
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
        Circle avatarJugador = new Circle(20, Color.BLACK);
        avatarJugador.setStroke(Color.WHITE);
        jugadorInferiorLabel = new Label(modelo.juego().jugadorActual().nombre()); 
        jugadorInferiorLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        VBox panelJugador = new VBox(2, avatarJugador, jugadorInferiorLabel);
        panelJugador.setAlignment(Pos.CENTER);

        // organizar botones en barra
        HBox grupoFinDeTurno = new HBox(5, pasarTurnoBtn, panelJugador);
        grupoFinDeTurno.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox controlesDerecha = new HBox(15, bankBtn, intercambiarBtn, verCartasBtn, tirarDadoBtn, grupoFinDeTurno);
        controlesDerecha.setAlignment(Pos.CENTER_RIGHT);

        //barra
        HBox barra = new HBox(20, spacer, controlesDerecha);
        barra.setPadding(new Insets(10, 20, 10, 20));
        barra.setAlignment(Pos.CENTER);
        barra.setStyle("-fx-background-color: #3b2145;");

        // Recursos
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
        if (evento instanceof String) {
            String mensaje = (String) evento;
            
            if (mensaje.equals("DADOS")) {
                actualizarVistaDados();
            }
            else if (mensaje.equals("TURNO")) {
                actualizarNombreJugador(); 
            }
            else if (mensaje.equals("RECURSOS")) {
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
}