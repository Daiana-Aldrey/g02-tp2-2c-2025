package edu.fiuba.algo3;

import edu.fiuba.algo3.vistas.App;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.vistas.VistaJuego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Dados.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

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

        Scene escena = new Scene(vista, 1024, 768);
        stage.setTitle("Catán - Algo3");
        stage.setScene(escena);
        stage.show();
    }

}

