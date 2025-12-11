package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.Excepciones.VictimaInvalida;
import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.vistas.VistaLadron;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControladorMoverLadron {
    private JuegoObservable modelo;
    private UbicacionVertice ubicacionDelTerreno;
    private VistaLadron vista;

    public ControladorMoverLadron(JuegoObservable modelo, UbicacionVertice ubicacion, VistaLadron vista) {
        this.modelo = modelo;
        this.ubicacionDelTerreno = ubicacion;
        this.vista = vista;
    }

    public void manejarClick() {
        if (!modelo.esTurnoLadron()) {
            return;
        }
        try {
            List<String> opciones = modelo.obtenerVictimasPosibles(ubicacionDelTerreno);
            
            String nombreParaBackend = null;
            if (opciones.isEmpty()) {
                modelo.moverLadronObservable(ubicacionDelTerreno, null);
                return; 
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(opciones.get(0), opciones);
            dialog.setTitle("Mover Ladrón");
            dialog.setHeaderText("Has movido al ladrón a esta ubicación.");
            dialog.setContentText("Elige a la víctima:");

            Optional<String> resultado = dialog.showAndWait();

            if (resultado.isPresent()) {
                nombreParaBackend = resultado.get();
                modelo.moverLadronObservable(ubicacionDelTerreno, nombreParaBackend);
            }
            vista.colocarLadron();

        } catch (VictimaInvalida e) {
            mostrarAlerta("Error de Robo", "Ese jugador no tiene construcciones adyacentes.");
        } catch (ColocacionInvalida e) {
            mostrarAlerta("Movimiento Inválido", "El ladrón ya está aquí o posición inválida.");
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}