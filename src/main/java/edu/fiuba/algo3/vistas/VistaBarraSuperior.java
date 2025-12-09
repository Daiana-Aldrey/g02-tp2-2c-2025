package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.utilidades.ReproductorMusica;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class VistaBarraSuperior extends HBox {

    private final Button btnFullScreen;

    public VistaBarraSuperior(Label estadoLabel, ReproductorMusica reproductor) {

        this.btnFullScreen = crearBotonFullscreen();

        // Botones info
        Button btnAcercaDe = crearBotonTexto("Acerca de", this::mostrarAcercaDe);
        Button btnComoJugar = crearBotonTexto("Cómo jugar", this::mostrarComoJugar);

        //Botones para la musica
        Button btnAnterior = crearBoton("⏮", reproductor::anterior);
        Button btnPause = crearBoton("⏸", reproductor::pausar);
        Button btnPlay = crearBoton("▶", reproductor::reanudar);
        Button btnSiguiente = crearBoton("⏭", reproductor::siguiente);
        Button btnVolMenos = crearBoton("-", reproductor::bajarVolumen);
        Button btnVolMas = crearBoton("+", reproductor::subirVolumen);

        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        this.getChildren().addAll(
                btnAcercaDe,
                btnComoJugar,
                estadoLabel,
                espacio,
                btnAnterior, btnPause, btnPlay, btnSiguiente,
                btnVolMenos, btnVolMas,
                btnFullScreen
        );

        this.setSpacing(5);
        this.setPadding(new Insets(5, 0, 5, 0));
        this.setAlignment(Pos.CENTER_LEFT);
    }

    private Button crearBotonTexto(String texto, Runnable accion) {
        Button btn = new Button(texto);
        btn.setCursor(Cursor.HAND);
        btn.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-size: 14px;" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 6 12;"
        );
        btn.setOnAction(e -> accion.run());
        return btn;
    }

    private Button crearBoton(String texto, Runnable accion) {
        Button btn = new Button(texto);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: transparent; -fx-font-size: 16px;");
        btn.setOnAction(e -> accion.run());
        return btn;
    }

    private Button crearBotonFullscreen() {
        ImageView icono = new ImageView(
                new Image(getClass().getResourceAsStream("/fullScreen.png"))
        );
        icono.setFitWidth(35);
        icono.setFitHeight(35);

        Button btn = new Button();
        btn.setGraphic(icono);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color: transparent; -fx-padding: 5;");

        btn.setOnAction(e -> {
            Stage stage = (Stage) this.getScene().getWindow();
            if (stage == null) return;

            boolean nuevo = !stage.isFullScreen();
            stage.setFullScreen(nuevo);

            if (nuevo) {
                btn.setStyle(
                        "-fx-background-color: rgba(0, 0, 0, 0.10);" +
                                " -fx-background-radius: 6;" +
                                " -fx-padding: 5;"
                );
            } else {
                btn.setStyle(
                        "-fx-background-color: transparent;" +
                                "-fx-padding: 5;"
                );
            }
        });

        return btn;
    }

    private void mostrarAcercaDe() {
        VistaInfo.mostrar("Acerca de", "Trabajo hecho por estudiantes de Ingeniería Informática de la Facultad de Ingeniería de la UBA.\n" +
                "- Lucia Povis\n" +
                "- Daiana Aldrey\n" +
                "- Gianfranco Turco\n" +
                "- Joselin Rojas\n" +
                "- Carolina Amarilla\n" +
                "\nTrabajo práctico académico.");
    }


    private void mostrarComoJugar() {
        VistaInfo.mostrar("Cómo jugar", "El objetivo del juego es ser el primero en llegar a 10 puntos de victoria. " +
                "Los PV se obtienen colonizando una isla, construyendo poblados (1 punto) y ciudades (2 puntos), " +
                "conectándolos con carreteras y comerciando recursos. Estos se obtienen al tirar los dados y colocar " +
                "tus construcciones en terrenos con los números que salgan, gestionando bien los recursos, usando cartas " +
                "de desarrollo y bloqueando a oponentes con el ladrón cuando sale un 7.\n");
    }
}
