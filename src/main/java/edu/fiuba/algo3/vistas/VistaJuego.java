package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.controlador.ControladorJuego;
import edu.fiuba.algo3.observador.Observador;
import edu.fiuba.algo3.observador.Observable;
import edu.fiuba.algo3.vistas.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class VistaJuego extends BorderPane implements Observador {

    private final JuegoObservable modelo;
    private final ControladorJuego controlador;
    private VistaDados vistaDados;

    private final Label estadoLabel;
    private final Button tirarDadoBtn;

    public VistaJuego(JuegoObservable modelo) {
        this.modelo = modelo;
        this.controlador = new ControladorJuego(modelo, this);
        vistaDados = new VistaDados();
        HBox contenedorDados = new HBox(vistaDados);
        contenedorDados.setSpacing(20);
        contenedorDados.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        contenedorDados.setPadding(new javafx.geometry.Insets(50, 0, 0, 50));
        setCenter(contenedorDados);


        modelo.agregarObservador(this);

        estadoLabel = new Label("Bienvenido a Catán");
        estadoLabel.setStyle("-fx-font-size: 14px;");
        BorderPane.setMargin(estadoLabel, new Insets(10));
        setTop(estadoLabel);

        tirarDadoBtn = new Button("Tirar dados");
        HBox barra = new HBox(10, tirarDadoBtn);
        barra.setPadding(new Insets(10));
        setBottom(barra);

        tirarDadoBtn.setOnAction(e -> controlador.manejarTirarDados());
    }

    public void mostrarEstado(String mensaje) {
        estadoLabel.setText(mensaje);
    }

    @Override
    public void actualizar(Observable observado, Object evento) {
        if (evento instanceof String) {
            String msg = (String) evento;
            if (msg.startsWith("TIRADA:")) {
                String numero = msg.substring("TIRADA:".length());
                mostrarEstado("Se tiró un " + numero);
            }
        }
    }
    
    public void actualizarDados(int d1, int d2) {
        vistaDados.actualizar(d1, d2);
    }

}
