package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        List<Jugador> jugadores = List.of(
                new Jugador("Jugador 1"),
                new Jugador("Jugador 2"),
                new Jugador("Jugador 3")
        );

        Juego juego = new Juego(jugadores);

        JuegoObservable modeloObservable = new JuegoObservable(juego);
        VistaJuego vista = new VistaJuego(modeloObservable);
        VistaTablero vistaTablero = new VistaTablero();

        Scene escena = new Scene(vista, 1024, 768);
        stage.setTitle("Catán - Algo3");
        stage.setScene(escena);
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