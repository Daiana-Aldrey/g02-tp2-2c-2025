package edu.fiuba.algo3.vistas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VistaPuerto extends ImageView {

    public VistaPuerto(String nombreRecurso) {
        String nombreArchivo = "puerto_" + nombreRecurso.toLowerCase() + ".png";
        String ruta = "file:src/main/resources/puertos/" + nombreArchivo;

        try {
            this.setImage(new Image(ruta));
        } catch (IllegalArgumentException e) {
            System.out.println("No se encontró imagen para: " + nombreRecurso + ". Usando genérico.");
            this.setImage(new Image("file:src/main/resources/puertos/puerto_general.png"));
        }
        
        this.setFitHeight(50);
        this.setFitWidth(50);
        this.setPreserveRatio(true);
    }
}
