package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorPieza;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.List;

public class VistaPieza extends HBox {
    ControladorPieza controlador;
    public VistaPieza() {
        setSpacing(20);

        Image iconoCiudad = new Image("edificio.png");
        ImageView vistaCiudad = new ImageView(iconoCiudad);

        vistaCiudad.setFitWidth(40);
        vistaCiudad.setFitHeight(40);
        vistaCiudad.setPreserveRatio(true);

        Button btnCiudad = new Button();
        btnCiudad.setGraphic(vistaCiudad);
        btnCiudad.setStyle(
                 "-fx-background-color: white;" +
                 "-fx-padding: 15; -fx-border-color: gray;" +
                 "-fx-border-width: 2;" +
                 "-fx-background-radius: 15;" +
                 "-fx-border-radius: 15;"
        );
        btnCiudad.setPrefSize(40, 40);
        btnCiudad.setCursor(Cursor.HAND);

        Image iconoPoblado = new Image("casa.png");
        ImageView vistaPoblado = new ImageView(iconoPoblado);

        vistaPoblado.setFitWidth(40);
        vistaPoblado.setFitHeight(40);
        vistaPoblado.setPreserveRatio(true);

        Button btnPoblado = new Button();
        btnPoblado.setGraphic(vistaPoblado);
        btnPoblado.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 15; -fx-border-color: gray;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;"
        );
        btnPoblado.setPrefSize(40, 40);
        btnPoblado.setCursor(Cursor.HAND);

        Image iconoCamino = new Image("camino-recto.png");
        ImageView vistaCamino = new ImageView(iconoCamino);

        vistaCamino.setFitWidth(40);
        vistaCamino.setFitHeight(40);
        vistaCamino.setPreserveRatio(true);

        Button btnCamino = new Button();
        btnCamino.setGraphic(vistaCamino);
        btnCamino.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 15; -fx-border-color: gray;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;"
        );
        btnCamino.setPrefSize(40, 40);
        btnCamino.setCursor(Cursor.HAND);

        controlador = new ControladorPieza(this, btnCiudad, btnCamino, btnPoblado);

        getChildren().addAll(btnCiudad,btnPoblado,btnCamino);
    }

    public void setVerticesArista(List<VistaVerticeEdificio> vertices, List<VistaArista> aristas) {
        controlador.setVertices(vertices);
        controlador.setArista(aristas);
    }

    public void darComportamiento() {
        controlador.darComportamiento();
    }
}
