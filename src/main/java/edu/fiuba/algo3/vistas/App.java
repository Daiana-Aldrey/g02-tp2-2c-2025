package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorIngresoNombres;
import edu.fiuba.algo3.controllers.ControladorTablero;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        ControladorIngresoNombres controlador = new ControladorIngresoNombres(stage);
        VistaInicio vistaInicio = new VistaInicio(stage, controlador);
        vistaInicio.mostrar();
    }

    public void iniciarJuego(Stage stage, List<Jugador> jugadores) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #87cfe8;");

        Juego juego = new Juego(jugadores);

        JuegoObservable modeloObservable = new JuegoObservable(juego);
        VistaJuego vistaJuego = new VistaJuego(modeloObservable);

        VistaTablero vistaTablero = new VistaTablero();
        Tablero tablero = Tablero.getInstance();
        ControladorTablero controladorTablero = new ControladorTablero(tablero, vistaTablero);
        vistaTablero.setControlador(controladorTablero);
        vistaTablero.crearVista();
        vistaJuego.setTablero(vistaTablero);


        Group groupTablero = vistaTablero.getVistaTablero();
        groupTablero.setTranslateY(-55);

        root.getChildren().add(vistaJuego);
        root.getChildren().add(groupTablero);

        Scene escena = new Scene(root, 1300, 800);
        stage.setTitle("Catán - Algo3");
        stage.setScene(escena);
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void stop(){
        System.out.println("Se ha terminado el programa.");
    }

    public static void main(String[] args) {
        launch(args);
    }

}