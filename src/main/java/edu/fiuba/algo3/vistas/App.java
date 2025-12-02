package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        StackPane layout = new StackPane();
        var label = new Label("Hello, JavaFX running on Java.");
        var scene = new Scene(layout, 640, 480);
        layout.getChildren().add(label);
        Button boton = new Button("Cerrar");
        layout.setTranslateY(30);
        boton.setTranslateY(-255);

        boton.setOnAction(e -> {
            Platform.exit();
        });

        layout.getChildren().add(boton);

        stage.setScene(scene);
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