package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Recurso.Recurso;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaRecursos extends HBox {

    private final Map<String, Label> labelsRecursos = new HashMap<>();

    public VistaRecursos() {
        setSpacing(20);
        setAlignment(Pos.CENTER);

        String[] recursos = {"madera",  "ladrillo", "lana", "grano", "mineral"};

        for (String recurso : recursos) {
            HBox panel = crearVistaDeRecursoDelJugador("recursos/" + recurso + ".png", recurso);
            getChildren().add(panel);
        }
    }

    private HBox crearVistaDeRecursoDelJugador(String imgNombre, String nombreRecurso) {
        Image icono = new Image("file:src/main/resources/" + imgNombre);
        ImageView vista = new ImageView(icono);
        vista.setFitWidth(50);
        vista.setFitHeight(50);

        Label cantidad = new Label("0");
        labelsRecursos.put(nombreRecurso, cantidad);

        HBox fila = new HBox(5, vista, cantidad);
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.setMinHeight(50);
        fila.setMaxHeight(50);

        fila.setStyle(
                "-fx-background-color: #e0e0e0;" +
                        "-fx-padding: 0 15 0 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-font-size: 18px;"
        );

        return fila;
    }

    public void actualizarRecursos(List<Recurso> recursos) {
        for (Recurso r : recursos) {
            Label lbl = labelsRecursos.get(r.recurso().toLowerCase());
            if (lbl != null) {
                lbl.setText(String.valueOf(r.cantidad()));
            }
        }
    }
}
