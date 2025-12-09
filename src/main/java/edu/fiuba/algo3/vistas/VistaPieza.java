package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorPieza;
import edu.fiuba.algo3.modelo.Jugador;
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
        Button btnCiudad = new Button();
        establecerImagenBoton(vistaCiudad, btnCiudad);
        disenioDesactivado(btnCiudad);

        Image iconoPoblado = new Image("casa.png");
        ImageView vistaPoblado = new ImageView(iconoPoblado);
        Button btnPoblado = new Button();
        establecerImagenBoton(vistaPoblado, btnPoblado);
        disenioBotonActivado(btnPoblado);

        Image iconoCamino = new Image("camino-recto.png");
        ImageView vistaCamino = new ImageView(iconoCamino);
        Button btnCamino = new Button();
        establecerImagenBoton(vistaCamino, btnCamino);
        disenioBotonActivado(btnCamino);

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

    public void darComportamiento(Jugador jugador) {
        controlador.darComportamiento(jugador);
    }

    public void disenioDesactivado(Button botonDesactivado) {
        botonDesactivado.setDisable(true);
    }

    public void disenioBotonActivado(Button boton) {
        boton.setCursor(Cursor.HAND);
        boton.setDisable(false);

    }

    public Button obtenerBotonCancelar() {
        return btnCancelar;
    }

    public void establecerImagenBoton(ImageView imagen, Button boton) {
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
    }
}
