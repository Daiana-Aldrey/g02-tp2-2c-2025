package edu.fiuba.algo3.vistas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VistaMuelle extends ImageView {

    public VistaMuelle() {
        // Asegúrate de que la ruta sea correcta y la imagen exista
        String ruta = "file:src/main/resources/puertos/muelle.png";

        try {
            this.setImage(new Image(ruta));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: No se encontró la imagen muelle.png en la ruta especificada.");
            // Opcional: Cargar una imagen de fallback o dejarlo vacío.
        }

        // Altura fija (el grosor del muelle). Ajusta este valor si es muy grueso o fino.
        this.setFitHeight(8);

        // IMPORTANTE: Permitimos que el ancho se estire, pero no la altura.
        this.setPreserveRatio(false);
    }
}
