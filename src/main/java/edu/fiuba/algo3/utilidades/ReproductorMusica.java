package edu.fiuba.algo3.utilidades;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.List;

public class ReproductorMusica {

    private final List<String> listaRutas;
    private int indiceActual;
    private MediaPlayer player;

    public ReproductorMusica(List<String> listaRutas) {
        this.listaRutas = listaRutas;
        this.indiceActual = 0;
        reproducirActual();
    }

    public void reproducirActual() {
        if (player != null) {
            player.stop();
            player.dispose();
        }

        try {
            String ruta = listaRutas.get(indiceActual);
            Media media = new Media(ruta);
            player = new MediaPlayer(media);
            player.setVolume(0.1);
            player.setOnEndOfMedia(this::siguiente);
            player.play();
        } catch (Exception e) {
            System.err.println("No se pudo reproducir la canción: " + e.getMessage());
        }
    }

    public void pausar() {
        player.pause();
    }

    public void reanudar() {
        player.play();
    }

    public void siguiente() {
        indiceActual = (indiceActual + 1) % listaRutas.size();
        reproducirActual();
    }

    public void anterior() {
        indiceActual = (indiceActual - 1 + listaRutas.size()) % listaRutas.size();
        reproducirActual();
    }

    public void subirVolumen() {
        double v = player.getVolume();
        player.setVolume(Math.min(1.0, v + 0.05));
    }

    public void bajarVolumen() {
        double v = player.getVolume();
        player.setVolume(Math.max(0.0, v - 0.05));
    }
}
