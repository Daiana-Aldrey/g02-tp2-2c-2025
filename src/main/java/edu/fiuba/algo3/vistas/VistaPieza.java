package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorPieza;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.List;

public class VistaPieza extends HBox {
    private ControladorPieza controlador;
    private Button btnCancelar;
    public VistaPieza() {
        setSpacing(20);

        Image iconoCiudad = new Image("edificio.png");
        ImageView vistaCiudad = new ImageView(iconoCiudad);

        vistaCiudad.setFitWidth(40);
        vistaCiudad.setFitHeight(40);
        vistaCiudad.setPreserveRatio(true);

        Button btnCiudad = new Button();
        btnCiudad.setGraphic(vistaCiudad);

        btnCiudad.setGraphic(vistaCiudad);
        btnCiudad.setStyle(
                "-fx-background-color: #787878;" +
                        "-fx-padding: 15;" +
                        "-fx-border-color: gray;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;"
        );
        btnCiudad.setPrefSize(40, 40);

        Image iconoPoblado = new Image("casa.png");
        ImageView vistaPoblado = new ImageView(iconoPoblado);

        Button btnPoblado = new Button();

        disenioBoton(btnPoblado, vistaPoblado);

        Image iconoCamino = new Image("camino-recto.png");
        ImageView vistaCamino = new ImageView(iconoCamino);

        Button btnCamino = new Button();

        disenioBoton(btnCamino, vistaCamino);

        btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle(
                "-fx-text-fill: white;" +
                "-fx-background-color: red;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 9 16 9 16;" +
                "-fx-background-radius: 10;"
        );
        btnCancelar.setCursor(Cursor.HAND);
        btnCancelar.setVisible(false);


        controlador = new ControladorPieza(this, btnCiudad, btnCamino, btnPoblado, btnCancelar);


        getChildren().addAll(btnCiudad,btnPoblado,btnCamino);
    }

    public void setVerticesArista(List<VistaVerticeEdificio> vertices, List<VistaArista> aristas) {
        controlador.setVertices(vertices);
        controlador.setArista(aristas);
    }

    public void darComportamiento() {
        controlador.darComportamiento();
    }


    public void disenioBoton(Button boton, ImageView imagen) {
        imagen.setFitWidth(40);
        imagen.setFitHeight(40);
        imagen.setPreserveRatio(true);

        boton.setGraphic(imagen);
        boton.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 15;" +
                        "-fx-border-color: gray;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;"
        );
        boton.setPrefSize(40, 40);
        boton.setCursor(Cursor.HAND);

    }

    public Button obtenerBotonCancelar() {
        return btnCancelar;
    }
}
