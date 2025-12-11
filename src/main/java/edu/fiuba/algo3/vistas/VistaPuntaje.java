package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaPuntaje extends VBox {

    private final Map<Jugador, Label> labelsPV = new HashMap<>();
    private final Map<Jugador, Label> labelsBonusCaballeria = new HashMap<>();
    private final Map<Jugador, Label> labelsBonusRuta = new HashMap<>();

    public VistaPuntaje(List<Jugador> jugadores) {
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_RIGHT);

        for (Jugador jugador : jugadores) {
            HBox tarjeta = crearTarjetaJugador(jugador);
            getChildren().add(tarjeta);
        }
    }

    private HBox crearTarjetaJugador(Jugador jugador) {
        VBox contenido = new VBox(5);
        contenido.setAlignment(Pos.CENTER);

        Label nombre = new Label(jugador.nombre());
        nombre.setTextFill(jugador.obtenerColor());
        nombre.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label pv = new Label(jugador.puntosDeVictoria() + " PV");
        pv.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        pv.setTextFill(jugador.obtenerColor());
        labelsPV.put(jugador, pv);

        Label bonusCaballeria = new Label("");
        bonusCaballeria.setStyle("-fx-font-size: 12px;");
        bonusCaballeria.setTextFill(jugador.obtenerColor());
        labelsBonusCaballeria.put(jugador, bonusCaballeria);

        Label bonusRuta = new Label("");
        bonusRuta.setStyle("-fx-font-size: 12px;");
        bonusRuta.setTextFill(jugador.obtenerColor());
        labelsBonusRuta.put(jugador, bonusRuta);

        contenido.getChildren().addAll(nombre, pv, bonusCaballeria, bonusRuta);

        HBox tarjeta = new HBox(contenido);
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setMinWidth(120);
        tarjeta.setStyle(
                "-fx-background-color: #333333;" +
                        "-fx-padding: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;"
        );

        return tarjeta;
    }

    public void actualizarPV(List<Jugador> jugadores, Jugador caballeria, int bonusCaballeria, Jugador ruta, int bonusRuta) {
        for (Jugador jugador : jugadores) {
            int pvModelo = jugador.puntosDeVictoria();
            Label labelPV = labelsPV.get(jugador);
            Label labelCaballeria = labelsBonusCaballeria.get(jugador);
            Label labelRuta = labelsBonusRuta.get(jugador);

            labelCaballeria.setText("");
            labelRuta.setText("");

            int pvMostrado = pvModelo;

            if (jugador == caballeria) {
                pvMostrado -= bonusCaballeria;
                labelCaballeria.setText("Gran Caballería (+" + bonusCaballeria + ")");
            }

            if(jugador == ruta){
                pvMostrado -= bonusRuta;
                labelRuta.setText("Gran Ruta Comercial (+" + bonusRuta + ")");
            }

            labelPV.setText("PV: " + pvMostrado);
        }
    }
}
